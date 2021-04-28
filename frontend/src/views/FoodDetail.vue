<template>
    <v-container>
        <p class="mainText" style="padding-bottom: 10px">요리 상세 페이지</p>
        <div style="width: 310px; height: 200px">
            <p class="foodText">된장찌개</p>
            <v-row>
                <v-spacer></v-spacer>
                <div>
                    <img src="../assets/images/된장찌개.jpg" alt="" class="foodImg" />
                </div>
                <v-spacer></v-spacer>
                <div style="width: 140px">
                    <p>- 주재료</p>
                    <span>ooo, </span> <span>ooo, </span> <span>ooo, </span> <span>ooo, </span>
                    <p>- 부재료</p>
                    <span>ooo, </span> <span>ooo, </span> <span>ooo, </span> <span>ooo, </span>
                    <p>난이도</p>
                    <div>
                        <v-rating
                            background-color="purple lighten-3"
                            color="#bc6ff1"
                            dense
                            size="32"
                            length="5"
                            readonly
                            value="4"
                        ></v-rating>
                    </div>
                </div>
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <div>
            <p>영상보기</p>
            <v-img
                src="../assets/images/다운로드.png"
                style="width: 250px; height: 150px; margin: 15px auto"
            ></v-img>
        </div>
    </v-container>
</template>

<script>
export default {
    name: 'FoodDetail',
};
</script>

<style scoped>
.mainText {
    font-size: 24px;
}
.foodImg {
    width: 160px;
    height: 160px;
}
.foodText {
    padding-top: 10px;
    padding-bottom: 10px;
}
</style>
