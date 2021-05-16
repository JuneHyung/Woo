<template>
    <v-container>
        <div class="outBox listBox">
            <p class="titleText">
                {{ ref_name }}
            </p>
            <v-row class="box">
                <div>
                    <drop class="drop list listBox ingredList">
                        <drag
                            v-for="(ing, index) in addList"
                            :key="index"
                            :class="{ [ing]: true }"
                            :transfer-data="{ item: ing, list: addList, example: 'list' }"
                            class="font-18"
                            >{{ ing.ingredientsdetail.name }}</drag
                        >
                    </drop>
                    <v-row class="refBtnBox">
                        <v-dialog v-model="addDialog">
                            <template v-slot:activator="{ on, attrs }">
                                <div v-bind="attrs" v-on="on" class="ingrePlusBtn">
                                    <img :src="plusBtn" alt="추가버튼" />
                                </div>
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
                                    <v-btn color="primary" text @click="addIngred()">
                                        추가완료
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                        <v-spacer></v-spacer>
                        <v-dialog v-model="minusDialog">
                            <template v-slot:activator="{ on, attrs }">
                                <div v-bind="attrs" v-on="on" class="ingreMinusBtn">
                                    <img :src="minusBtn" alt="뺴기버튼" />
                                </div>
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
                                    <v-btn color="primary" text @click="removeIngred()">
                                        빼기완료
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-row>
                </div>
                <v-row>
                    <div v-for="(list, i) in lists" :key="i" class="drop">
                        <p class="fridgeTitleBox">FRIDGE</p>
                        <div v-for="(inlist, ind) in list" :key="ind" class="ingredientsBox">
                            <drop
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
                                    <v-row class="ingredientsList">
                                        <v-spacer></v-spacer>
                                        <div class="ingredImg">
                                            <img
                                                :src="
                                                    require(`@/assets/images/ingredients/${ininlist.ingredientsdetail.image}`)
                                                "
                                                alt="재료이미지"
                                            />
                                        </div>
                                        <p class="ingredName">
                                            {{ ininlist.ingredientsdetail.name }}
                                        </p>
                                        <v-spacer></v-spacer>
                                    </v-row>
                                </drag>
                            </drop>
                        </div>
                    </div>
                </v-row>
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <v-row class="explainIngredBox my-7 py-3">
            <v-spacer></v-spacer>
            <p>
                빨간색으로 표시된 재료들은 <br />
                <span>"유통기한이 지난"</span>
                식품입니다.
            </p>
            <v-spacer></v-spacer>
            <v-icon class="expainIngredIcon">mdi-arrow-down-bold-box-outline</v-icon>
            <v-spacer></v-spacer>
        </v-row>
        <div>
            <p class="titleText">유통기한 1주일 미만</p>
            <v-slide-group center-active show-arrows class="garbageBox">
                <v-slide-item v-for="(ingredient, index) in garbages" :key="index" class="itemBox">
                    <v-card
                        class="ma-1"
                        height="100"
                        width="70"
                        @click="goRecipeList(ingredient.ingredientsdetail.id)"
                    >
                        <img
                            :src="
                                require(`@/assets/images/ingredients/${ingredient.ingredientsdetail.image}`)
                            "
                            alt="재료이미지"
                            class="itemImg"
                        />

                        <p class="itemText">
                            {{ ingredient.ingredientsdetail.name }}
                        </p>
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <div class="mt-7">
            <p class="titleText">내 재료 목록</p>
            <v-slide-group center-active show-arrows class="garbageBox">
                <v-slide-item v-for="(ingredient, index) in ingredients" :key="index">
                    <v-card
                        class="ma-1"
                        height="100"
                        width="70"
                        @click="goRecipeList(ingredient.ingredientsdetail.id)"
                    >
                        <img
                            :src="
                                require(`@/assets/images/ingredients/${ingredient.ingredientsdetail.image}`)
                            "
                            alt="재료이미지"
                            class="itemImg"
                        />

                        <p class="itemText">
                            {{ ingredient.ingredientsdetail.name }}
                        </p>
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
    </v-container>
</template>
<script>
// import http from '../../api/axios.js';
import {
    getCategory,
    getAllIngredients,
    getSomeIngredients,
    getIngredients,
    addIngredients,
    deleteIngredients,
    moveIngredients,
    getFridgeDetail,
} from '../../api/refrigerator.js';
import { moveRecipeList } from '@/api/move.js';
import { Drag, Drop } from 'vue-drag-drop';
import swal from 'sweetalert';
export default {
    components: { Drag, Drop },
    name: 'RefManage',

    data() {
        return {
            addDialog: false,
            minusDialog: false,
            ref_id: 0,
            ref_name: '',
            ref_type: '',
            garbages: [],
            category: ['All'],
            ingredients: Array,
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
            lists: [],
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
            fridgeInfo: [],
            plusBtn: require('@/assets/images/plusBtn.png'),
            minusBtn: require('@/assets/images/minusBtn.png'),
            tempList: [],
        };
    },

    created() {
        this.ref_id = this.$route.params.rid;
        this.addIngredients.fridge.id = this.ref_id;
        this.getFridgeDetail();
        this.getAllCategories();
    },
    methods: {
        getAllCategories() {
            getCategory()
                .then((response) => {
                    this.category = response.data.category;
                    this.category.unshift('All');
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getIngredientsName() {
            this.ingredientsName.splice(0);
            this.ingredientsId.splice(0);
            let category = this.addIngredients.ingredientsdetail.category;
            if (category == 'All') {
                getAllIngredients().then((response) => {
                    let name = response.data.ingredients;
                    for (var i = 0; i < name.length; i++) {
                        this.ingredientsName.push(name[i].name);
                        this.ingredientsId.push(name[i].id);
                    }
                });
            } else {
                getSomeIngredients(category)
                    .then((response) => {
                        let name = response.data.ingredients;
                        for (var i = 0; i < name.length; i++) {
                            this.ingredientsName.push(name[i].name);
                            this.ingredientsId.push(name[i].id);
                        }
                    })
                    .catch(() => {
                        swal('실패!', {
                            icon: 'error',
                        });
                    });
            }
        },
        getFridgeDetail() {
            getFridgeDetail(this.ref_id)
                .then((response) => {
                    this.fridgeInfo = response.data.fridge;
                    this.ref_name = response.data.fridge.name;
                    this.ref_type = response.data.fridge.type;
                    this.setRefType();
                })
                .catch((error) => console.log(error));
        },
        getIngredients() {
            getIngredients(this.ref_id)
                .then((response) => {
                    this.ingredients = response.data.ingredients;

                    this.ingredients.forEach((el) => {
                        let x = el.locx;
                        let y = el.locy;
                        if (x == 10 || y == 10) {
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
                            this.addList.push(temp);
                        } else {
                            this.lists[y][x].push(el);
                        }
                    });
                    setTimeout(this.checkShelfLife, 100);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        setRefType() {
            switch (this.ref_type) {
                case 44:
                    this.lists = [
                        [[], [], [], []],
                        [[], [], [], []],
                    ];
                    break;
                case 55:
                    this.lists = [
                        [[], [], [], [], []],
                        [[], [], [], [], []],
                    ];
                    break;
                case 66:
                    this.lists = [
                        [[], [], [], [], [], []],
                        [[], [], [], [], [], []],
                    ];
                    break;
                case 4444:
                    this.lists = [
                        [[], [], [], []],
                        [[], [], [], []],
                        [[], [], [], []],
                        [[], [], [], []],
                    ];
                    break;
                case 5555:
                    this.lists = [
                        [[], [], [], [], []],
                        [[], [], [], [], []],
                        [[], [], [], [], []],
                        [[], [], [], [], []],
                    ];
                    break;
            }
            if (this.ref_type) {
                if (this.ref_type == 4444 || this.ref_type == 5555) {
                    let box = document.querySelector('.box');
                    box.classList.add('fourBox');
                }
                this.getIngredients();
            }
        },
        checkShelfLife() {
            let current = new Date();

            let year = current.getFullYear();
            let month = current.getMonth() + 1;
            let date7 = current.getDate() + 7;
            let today7 = `${year}-${month}-${date7}`;
            let today7O = new Date(today7);

            for (var i = 0; i < this.ingredients.length; i++) {
                var ingre = new Date(this.ingredients[i].expired);

                if (ingre.getTime() <= today7O.getTime()) {
                    this.garbages.push(this.ingredients[i]);
                }
            }
            setTimeout(this.checkDeadItem, 100);
        },
        checkDeadItem() {
            let current = new Date();

            let year = current.getFullYear();
            let month = current.getMonth() + 1;
            let date = current.getDate() + 1;
            let today = `${year}-${month}-${date}`;
            let todayO = new Date(today);

            let itemBox = document.querySelectorAll('.itemText');
            itemBox.forEach((el) => {
                this.tempList.push(el.innerText);
            });

            for (var i = 0; i < this.ingredients.length; i++) {
                let life = new Date(this.ingredients[i].expired);
                if (life.getTime() <= todayO.getTime()) {
                    itemBox.forEach((el) => {
                        let text = el.innerText;

                        if (text == this.ingredients[i].ingredientsdetail.name) {
                            el.classList.add('deadItem');
                        }
                    });
                }
            }
        },
        selectId(name) {
            let idx = this.ingredientsName.indexOf(name);
            this.addIngredients.ingredientsdetail.id = this.ingredientsId[idx];
        },
        addIngred() {
            this.addItem.expired = this.addIngredients.expired;
            this.addItem.fridgeId = this.ref_id;
            this.addItem.ingredientsDetailId = this.addIngredients.ingredientsdetail.id;

            addIngredients(this.addItem)
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
                    swal(error, {
                        icon: 'error',
                    });
                });
        },
        removeIngred(id) {
            deleteIngredients(id)
                .then(() => {
                    swal('삭제 성공!', {
                        icon: 'success',
                    });
                    this.minusDialog = false;
                    window.location.href = `/refmanage/${this.ref_id}`;
                })
                .catch((error) => {
                    swal(error, {
                        icon: 'error',
                    });
                });
        },
        goRecipeList(id) {
            moveRecipeList(id);
        },
        handleDrop(toList, data, event, x, y) {
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
            moveIngredients(this.moveItem)
                .then(() => {
                    window.location.href = `/refmanage/${this.ref_id}`;
                })
                .catch((error) => {
                    swal(error, {
                        icon: 'error',
                    });
                });
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/refManage.css';
</style>
