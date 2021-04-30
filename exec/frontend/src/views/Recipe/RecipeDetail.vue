<template>
    <v-container>
        <h1 style="text-align: center">{{ item.title }}</h1>
        <div style="width: 100%; height: 300px; margin: 20px auto">
            <img :src="url" style="width: 100%; height: 100%" />
        </div>

        <v-sheet class="mx-auto">
            <v-slide-group center-active>
                <v-slide-item v-for="(thumbnail, idx) in item.img" :key="idx">
                    <v-card
                        class="ma-2 thumbnail"
                        height="60"
                        width="60"
                        @click="changeImage(thumbnail)"
                    >
                        <img :src="thumbnail" style="width: 100%; height: 100%" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </v-sheet>

        <v-row style="margin-top: 20px !important">
            <p>작성자 : {{ item.writer }}</p>
            <v-spacer></v-spacer>
            <div style="width: 20px; height: 20px; margin: 0 5px">
                <img
                    src="../../assets/images/thumbs_up.jpg"
                    alt="좋아요"
                    style="width: 100%; height: 100%"
                />
            </div>
            <div style="width: 20px; height: 20px; margin: 0 5px">
                <img
                    src="../../assets/images/thumbs_down.jpg"
                    alt="싫어요"
                    style="width: 100%; height: 100%"
                />
            </div>
        </v-row>
        <v-row>
            <p>구독자 : 1K</p>
            <div class="addBtn">구독하기</div>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data() {
        return {
            item: {
                title: '대마초보다 중독성 강한 마약 계란',
                img: [
                    require('@/assets/images/ingredients/pear.png'),
                    require('@/assets/images/ingredients/onion.png'),
                    require('@/assets/images/ingredients/milk.png'),
                    require('@/assets/images/ingredients/lemon.png'),
                    require('@/assets/images/ingredients/plum.png'),
                ],
                writer: 'paka999',
            },
            url: '',
        };
    },
    created() {
        this.url = this.item.img[0];
    },
    methods: {
        changeImage(thumbnail) {
            this.url = thumbnail;
        },
    },
};
</script>
