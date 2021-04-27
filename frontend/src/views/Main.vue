<template>
    <v-container>
        <div>
            <div>
                <p class="mainText">내 냉장고 목록</p>
            </div>
            <div class="myRef">
                <div
                    class="col-5"
                    style="height: 250px; background-color: #eee"
                    @click="goRefManage()"
                ></div>
            </div>
            <v-row style="border-top: 1px solid black; border-bottom: 1px solid black">
                <p>클릭시 냉장고 관리가 가능합니다</p>
                <v-spacer></v-spacer>
                <span @click="goRefAdd()" class="addBtn">냉장고 추가</span>
            </v-row>
        </div>
        <div style="margin-top: 10px">
            <div>
                <p class="mainText">구독</p>
            </div>
            <div style="width: 330px; height: 200px"></div>
            <div>
                <hr />
                <p>클릭시 구독한 작성자의 상세 내용에 대해 확인할 수 있습니다</p>
                <div>
                    <hr />
                </div>
            </div>
        </div>
    </v-container>
</template>

<script>
export default {
    name: 'Main',
    dat() {
        return {};
    },
    methods: {
        goRefManage() {
            this.$router.push({ name: 'RefManage' });
        },
        goRefAdd() {
            this.$router.push({ name: 'RefAdd' });
        },
    },
};
</script>

<style scoped>
.myRef {
    margin-bottom: 20px;
}
.mainText {
    font-size: 24px;
}
</style>
