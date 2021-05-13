<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 24px">
                <img
                    src="../../assets/images/header/sharing.png"
                    alt="레시피"
                    style="width: 100%; height: 100%"
                />
            </div>
            <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                레시피 등록
            </p>
        </v-row>
        <div>
            <v-text-field
                label="제목"
                placeholder="제목을 입력해주세요."
                type="string"
                v-model="title"
            ></v-text-field>
            <v-row>
                <div class="col-4" v-for="(item, idx) in items" :key="idx">
                    <img :src="item" alt="" style="width: 100%; height: 100%" />
                </div>
            </v-row>

            <input @change="onChangeImages" multiple type="file" ref="images" />
        </div>
        <div style="margin-top: 20px">
            <v-btn
                @click="resetRecipeCreate"
                style="
                    font-size: 18px;
                    border: 1px solid #d9418d;
                    border-radius: 10px;
                    padding: 5px;
                    color: #d9418d;
                    margin-right: 10px;
                "
                >초기화</v-btn
            >
            <v-btn
                @click="recipeCreate"
                style="
                    font-size: 18px;
                    border: 1px solid #d9418d;
                    border-radius: 10px;
                    padding: 5px;
                    color: #d9418d;
                    background-color: #fff;
                "
                >레시피 등록하기</v-btn
            >
        </div>
    </v-container>
</template>

<script>
import swal from 'sweetalert';
import { createRecipe } from '@/api/recipe.js';
import { moveSubscribe } from '@/api/move.js';
// import http from '@/api/axios.js';

export default {
    data() {
        return {
            items: [],
            imageUrl: '',
            recipe: {
                id: '',
                name: '',
                category: '',
                subcategory: '',
                url: '',
                views: '',
            },
            images: [],
            title: '',
        };
    },
    methods: {
        onChangeImages(e) {
            for (var i = 0; i < e.target.files.length; i++) {
                this.items.push(URL.createObjectURL(e.target.files[i]));
                this.images.push(e.target.files[i]);
            }
        },
        resetRecipeCreate() {
            this.title = '';
            this.recipe.name = '';
            this.items = '';
        },
        recipeCreate() {
            if (this.title == '') {
                swal('요리 이름을 입력해주세요!', {
                    icon: 'error',
                });
            } else if (this.items == '') {
                swal('이미지를 입력해주세요!', {
                    icon: 'error',
                });
            } else {
                let formData = new FormData();

                formData.append('title', this.title);
                for (var i = 0; i < this.images.length; i++)
                    formData.append('images', this.images[i]);

                createRecipe(formData)
                    .then((response) => {
                        console.log(response);
                        swal('레시피 등록이 완료되었습니다', {
                            icon: 'success',
                        });
                        moveSubscribe();
                    })
                    .catch(() => {
                        swal('등록실패!', {
                            icon: 'error',
                        });
                    });
            }
        },
    },
};
</script>
