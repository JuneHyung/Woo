<template>
    <v-container>
        <div class="outBox">
            <p>{{ ref_name }}</p>
            <v-row class="box">
                <div>
                    <drop
                        class="drop list"
                        style="
                            width: 70px;
                            height: 200px;
                            border: 1px solid black;
                            overflow: scroll;
                        "
                    >
                        <drag
                            v-for="(ing, index) in addList"
                            :key="index"
                            :class="{ [ing]: true }"
                            :transfer-data="{ item: ing, list: addList, example: 'list' }"
                            >{{ ing.ingredientsdetail.name }}</drag
                        >
                    </drop>
                </div>
                <v-row>
                    <div v-for="(list, i) in lists" :key="i" class="drop">
                        <div
                            v-for="(inlist, ind) in list"
                            :key="ind"
                            style="border: 1px solid black; width: 115px; height: 50px"
                        >
                            <drop
                                style="width: 120px; height: 50px; overflow: scroll"
                                class="dropBox"
                                @drop="handleDrop(inlist, ...arguments, ind, i, inlist)"
                            >
                                <drag
                                    v-for="(ininlist, inde) in inlist"
                                    :key="inde"
                                    :transfer-data="{
                                        item: ininlist,
                                        list: inlist,
                                        example: 'list',
                                    }"
                                >
                                    <p>{{ ininlist.ingredientsdetail.name }}</p>
                                </drag>
                            </drop>
                        </div>
                    </div>
                </v-row>
            </v-row>
            <v-row>
                <v-spacer></v-spacer>
                <v-dialog v-model="addDialog">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="addBtn" v-bind="attrs" v-on="on">+ 재료 추가</div>
                    </template>
                    <v-card>
                        <v-card-title>재료를 추가해주세요!</v-card-title>
                        <v-card-text>
                            <v-select
                                :items="category"
                                v-model="addIngredients.ingredientsdetail.category"
                                label="Solo field"
                                solo
                                @change="getIngredientsName()"
                            ></v-select>
                            <v-select
                                :items="ingredientsName"
                                v-model="addIngredients.ingredientsdetail.name"
                                label="Solo field"
                                solo
                                @change="selectId(addIngredients.ingredientsdetail.name)"
                            ></v-select>
                            <input type="date" v-model="addIngredients.expired" />
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="addIngred()"> 추가완료 </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-dialog v-model="minusDialog">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="minusBtn" v-bind="attrs" v-on="on">- 재료 빼기</div>
                    </template>
                    <v-card>
                        <v-card-title>재료를 빼주세요!</v-card-title>
                        <v-card-text>
                            <v-row v-for="(ingredient, index) in ingredients" :key="index">
                                <p>{{ ingredient.ingredientsdetail.name }}</p>
                                <v-spacer></v-spacer>
                                <div @click="removeIngred(ingredient.id)">X</div>
                            </v-row>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="removeIngred()"> 빼기완료 </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </div>
        <div stlye="margin-top:50px;">
            <p>유통기한 1주일 미만</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item
                    v-for="(ingredient, index) in ingredients"
                    :key="index"
                    @click="goRecipeList(ingredient.ingredientsdetail.id)"
                >
                    <v-card class="ma-1" height="120" width="80">
                        <img
                            :src="
                                require(`@/assets/images/ingredients/${ingredient.ingredientsdetail.image}`)
                            "
                            alt="재료이미지"
                            style="width: 100%; height: 85%; padding: 5px"
                        />

                        <p style="text-align: center; height: 15%">
                            {{ ingredient.ingredientsdetail.name }}
                        </p>
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
    </v-container>
</template>
<script>
import http from '../../api/axios.js';
import { Drag, Drop } from 'vue-drag-drop';
export default {
    components: { Drag, Drop },
    name: 'RefManage',
    data() {
        return {
            addDialog: false,
            minusDialog: false,
            ref_id: 0,
            ref_name: '',
            garbages: [],
            category: ['All'],
            ingredients: [],
            addIngredients: {
                expired: 'string',
                locx: 0,
                locy: 0,
                fridge: {
                    id: 0,
                },
                ingredientsdetail: {
                    id: 0,
                    category: '',
                    name: '',
                },
            },
            addItem: {
                expired: 'string',
                locx: 10,
                locy: 10,
                fridgeId: 0,
                ingredientsDetailId: 0,
            },

            ingredientsName: [],
            ingredientsId: [],
            lists: [
                [[], [], [], []],
                [[], [], [], []],
                [[], [], [], []],
                [[], [], [], []],
            ],
            addList: [
                {
                    id: 0,
                    expired: 'string',
                    locx: 0,
                    locy: 0,
                    fridgeId: 0,
                    fridge: {
                        id: '',
                    },
                    ingredientsdetail: {
                        id: '',
                        name: '',
                    },
                    ingredientsDetailId: 0,
                },
            ],
            temp: [],
            moveItem: {
                id: 0,
                expired: 'string',
                locx: 0,
                locy: 0,
                fridgeId: 0,
                ingredientsDetailId: 0,
            },
        };
    },

    created() {
        this.ref_id = this.$route.params.rid;
        this.addIngredients.fridge.id = this.ref_id;
        this.getIngredients();
        this.getCategory();
    },
    methods: {
        getCategory() {
            http.get(`fridge/categoryList`)
                .then((response) => {
                    this.category = response.data.category;
                    this.category.unshift('All');
                })
                .catch(() => {
                    console.log('실패');
                });
        },
        getIngredientsName() {
            this.ingredientsName.splice(0);
            this.ingredientsId.splice(0);
            let category = this.addIngredients.ingredientsdetail.category;
            if (category == 'All') {
                http.get(`fridge/ingredientsDetailList`).then((response) => {
                    let name = response.data.ingredients;
                    for (var i = 0; i < name.length; i++) {
                        this.ingredientsName.push(name[i].name);
                        this.ingredientsId.push(name[i].id);
                    }
                });
            } else {
                http.get(`fridge/categoryByingredients/${category}`)
                    .then((response) => {
                        let name = response.data.ingredients;
                        for (var i = 0; i < name.length; i++) {
                            this.ingredientsName.push(name[i].name);
                            this.ingredientsId.push(name[i].id);
                        }
                    })
                    .catch(() => {
                        alert('실패');
                    });
            }
        },
        getIngredients() {
            http.get(`fridge/ingredients/${this.ref_id}`)
                .then((response) => {
                    this.ingredients = response.data.ingredients;
                    this.ref_name = this.ingredients[0].fridge.name;

                    this.ingredients.forEach((el) => {
                        let x = el.locx;
                        let y = el.locy;
                        if (x == 10 || y == 10) {
                            console.log(el);
                            console.log('옴?');
                            let temp = {
                                id: el.id,
                                expired: el.expired,
                                fridgeId: el.fridge.id,
                                fridge: {
                                    id: el.fridge.id,
                                },
                                ingredientsDetailId: el.ingredientsdetail.id,
                                locx: el.locx,
                                locy: el.locy,
                                ingredientsdetail: {
                                    id: el.ingredientsdetail.id,
                                    name: el.ingredientsdetail.name,
                                },
                            };
                            // this.moveItem.id = el.id;
                            // this.moveItem.expired = el.expired;
                            // this.moveItem.fridgeId = el.fridge.id;
                            // this.moveItem.ingredientsDetailId = el.fridge.ingredientsdetail.id;
                            // this.moveItem.locx = el.locx;
                            // this.moveItem.locy = el.locy;
                            // this.moveItem.ingredientsDetailName = el.ingredientsdetail.name;
                            // console.log(this.moveItem);
                            this.addList.push(temp);
                            console.log('끝?');
                        } else {
                            this.lists[y][x].push(el);
                        }
                    });
                    console.log('정보받기 성공');
                })
                .catch(() => {
                    alert('정보받기 실패!');
                });
        },

        checkShelfLife() {
            var current = new Date();

            let year = current.getFullYear();
            let month = current.getMonth();
            let date = current.getDate();
            let today = `${year}-${month}-${date}`;
            let todayO = new Date(today);
            console.log(todayO.getTime());
            console.log(this.ingredients.length);

            console.log('왔나? : ' + this.ingredients[0].expired);
            for (var i = 0; i < this.ingredients.length; i++) {
                console.log(i);
                var ingre = new Date(this.ingredients[0].expired);
                if (ingre.getTime() > todayO.getTime()) {
                    this.garbages = this.ingredients[i];
                }
            }
        },
        selectId(name) {
            console.log('왔니?');
            let idx = this.ingredientsName.indexOf(name);
            console.log('idx : ' + idx);
            this.addIngredients.ingredientsdetail.id = this.ingredientsId[idx];
        },
        addIngred() {
            this.addItem.expired = this.addIngredients.expired;
            this.addItem.fridgeId = this.ref_id;
            this.addItem.ingredientsDetailId = this.addIngredients.ingredientsdetail.id;

            http.post(`fridge/addIngredients`, this.addItem)
                .then(() => {
                    this.addDialog = false;
                    let detailId = this.addItem.ingredientsDetailId;

                    this.ingredients.forEach((el) => {
                        if (el.ingredientsdetail.id == detailId) {
                            let temp = {
                                id: el.id,
                                expired: el.expired,
                                fridgeId: el.firdge.id,
                                fridge: {
                                    id: el.fridge.id,
                                },
                                ingredientsDetailId: el.ingredientsdetail.id,
                                locx: el.locx,
                                locy: el.locy,
                                ingredientsdetail: {
                                    id: el.ingredientsdetail.id,
                                    name: el.ingredientsdetail.name,
                                },
                            };

                            this.addList.push(temp);
                        }
                    });
                    window.location.href = `/refmanage/${this.ref_id}`;
                })
                .catch((error) => {
                    alert(error);
                });
        },
        removeIngred(id) {
            http.delete(`fridge/delIngredients/${id}`)
                .then(() => {
                    alert('삭제 성공');
                    this.minusDialog = false;
                    window.location.href = `/refmanage/${this.ref_id}`;
                })
                .catch((error) => {
                    alert(error);
                });
        },
        goRecipeList(id) {
            this.$router.push({
                name: 'RecipeList',
                params: { ingredient_id: id },
            });
        },
        handleDrop(toList, data, event, x, y) {
            // console.log(`event : ${event}`);
            // console.log(`x: ${x}`);
            // console.log(`y: ${y}`);
            this.temp = data.item;

            data.item.locx = x;
            data.item.locy = y;

            toList.push(data.item);
            toList.sort((a, b) => a > b);
            if (data.list) {
                data.list.splice(data.list.indexOf(data.item), 1);
            }

            this.moveItem.id = this.temp.id;
            this.moveItem.expired = this.temp.expired;
            this.moveItem.locx = x;
            this.moveItem.locy = y;
            this.moveItem.fridgeId = this.temp.fridge.id;
            this.moveItem.ingredientsDetailId = this.temp.ingredientsdetail.id;

            // if(data.addList){
            //     data.addList.splice(data.)
            // }

            http.put(`fridge/moveIngredients`, this.moveItem)
                .then(() => {
                    console.log('clear');
                    window.location.href = `/refmanage/${this.ref_id}`;
                })
                .catch((error) => alert(error));
        },
    },
};
</script>
<style scoped>
.list {
    width: 100px;
    margin-right: 10px;
}

.inBox {
    width: 400px;
    height: 100px;
}

.ingredient {
    box-sizing: border-box;
    border: 1px solid #bbb;
    cursor: pointer;
    margin: 0 10px;
}

.drop.over {
    border-color: #aaa;
    background: #ccc;
}
.drag {
    display: inline-block;
}

.drop {
    display: inline-block;
    vertical-align: top;
    padding: 8px;
    margin-bottom: 20px;
    width: auto;
    height: auto;
}
.box {
    margin: 10px;
    width: 610px;
    height: 250px;

    text-align: center;
    font-size: 24px;
}
.outBox {
    width: 330px;
    overflow-x: scroll;
}
.dropBox::-webkit-scrollbar {
    display: none;
}
</style>
