<template>
    <v-container>
        <p class="mainText">요리추천(유통기한 순)</p>
        <div class="box" style="padding-top: 10px">
            <v-row>
                <div class="margin: 0 auto;">
                    <img
                        src="../assets/images/된장찌개.jpg"
                        alt="된장찌개"
                        style="width: 160px; height: 160px"
                    />
                    <!-- <p style="text-aligin: center; width: 160px; height: 160px">된장찌개</p> -->
                </div>
                <v-spacer></v-spacer>
                <div>
                    <div>
                        <p style="font-size: 14px">필요재료</p>
                    </div>

                    <div class="margin: 0 auto;">
                        <span>애호박, </span>
                        <span>두부,</span>
                        <span>양파, </span> <span>대파, </span> <span>된장</span>
                    </div>
                    <!-- <button>-> 조리법 보러가기</button> -->
                </div>
                <v-spacer></v-spacer>
            </v-row>
            <div style="width: 330px; height: 10px padding-bottom: 20px;">
                <v-row>
                    <v-spacer></v-spacer>
                    <div style="text-align: center">
                        <p>된장찌개</p>
                    </div>

                    <v-spacer></v-spacer>
                    <button color="gray">-> 조리법 보러가기</button>
                    <v-spacer></v-spacer>
                </v-row>
            </div>
        </div>

        <br />
        <div class="box" style="padding-top: 30px">
            <v-row>
                <div class="margin: 0 auto;">
                    <img
                        src="../assets/images/계란말이.jpg"
                        alt="계란말이"
                        style="width: 160px; height: 160px"
                    />
                    <!-- <p style="text-aligin: center; width: 160px; height: 160px">된장찌개</p> -->
                </div>
                <v-spacer></v-spacer>
                <div>
                    <div>
                        <p style="font-size: 14px">필요재료</p>
                    </div>

                    <div class="margin: 0 auto;">
                        <span>계란, </span>
                        <span>대파,</span>
                        <span>소금, </span>
                    </div>
                    <!-- <button>-> 조리법 보러가기</button> -->
                </div>
                <v-spacer></v-spacer>
            </v-row>
            <div style="width: 330px; height: 10px padding-bottom: 20px;">
                <v-row>
                    <v-spacer></v-spacer>
                    <div style="text-align: center">
                        <p>계란말이</p>
                    </div>

                    <v-spacer></v-spacer>
                    <button color="gray">-> 조리법 보러가기</button>
                    <v-spacer></v-spacer>
                </v-row>
            </div>
        </div>
    </v-container>
</template>
<script>
export default {
    name: 'ShareRecipe',
};
</script>

<style scoped>
.mainText {
    font-size: 20px;
}
.box {
    /* border-top: 4px dotted black; */
    border-bottom: 4px dotted black;
}
</style>
