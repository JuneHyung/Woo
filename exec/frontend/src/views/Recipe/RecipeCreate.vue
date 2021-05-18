<template>
    <v-container>
        <v-row>
            <div class="recipeIconBox">
                <img :src="recipeIcon" alt="레시피" />
            </div>
            <p class="titleText recipeCreateTitle">레시피 등록</p>
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
                    <img :src="item" alt="아이템이미지" />
                </div>
            </v-row>

            <input
                @change="onChangeImages"
                multiple
                required
                type="file"
                ref="images"
                class="fileName"
            />
        </div>
        <div class="mt-5">
            <v-btn class="resetBtn" @click="resetRecipeCreate">초기화</v-btn>
            <v-btn @click="recipeCreate" class="createtBtn">레시피 등록하기</v-btn>
        </div>
    </v-container>
</template>

<script>
import swal from 'sweetalert';
import { createRecipe } from '@/api/recipe.js';
import { moveSubscribe } from '@/api/move.js';

export default {
    data() {
        return {
            recipeIcon: require('@/assets/images/header/sharing.png'),
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
            this.items.splice(0);
            this.images.splice(0);
            for (var i = 0; i < e.target.files.length; i++) {
                this.items.push(URL.createObjectURL(e.target.files[i]));
                this.images.push(e.target.files[i]);
            }
        },
        resetRecipeCreate() {
            this.title = '';
            this.recipe.name = '';
            let filename = document.querySelector('.fileName');
            filename.value = '';
            this.items.splice(0);
            this.images.splice(0);
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
                    .then(() => {
                        swal('레시피 등록이 완료되었습니다', {
                            icon: 'success',
                        });
                        moveSubscribe(0);
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
<style scoped>
@import './../../assets/css/recipeCreate.css';
</style>
