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
        <v-text-field label="제목" placeholder="제목을 입력해주세요." type="string"></v-text-field>
        <v-row>
            <div class="col-4" v-for="(item, idx) in items" :key="idx">
                <img :src="item" alt="" style="width: 100%; height: 100%" />
            </div>
            <!-- <img :src="imageUrl" alt="" /> -->
        </v-row>

        <input @change="onChangeImages" multiple type="file" />
    </v-container>
</template>
<script>
export default {
    data() {
        return {
            items: [],
            imageUrl: '',
        };
    },
    methods: {
        onChangeImages(e) {
            console.log('받을거 : ' + e.target.files);
            for (var i = 0; i < e.target.files.length; i++) {
                this.items.push(URL.createObjectURL(e.target.files[i]));
            }
        },
    },
};
</script>
