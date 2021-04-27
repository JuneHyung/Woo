<template>
    <v-container>
        <div>
            <div>
                <p class="mainText">내 냉장고 목록</p>
            </div>
            <div style="width: 330px; height: 230px"></div>
            <div>
                <hr />
                <p>클릭시 냉장고 관리가 가능합니다</p>
                <div style="margin-top: 20px">
                    <hr />
                </div>
            </div>
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
};
</script>

<style scoped>
.mainText {
    font-size: 24px;
}
</style>
