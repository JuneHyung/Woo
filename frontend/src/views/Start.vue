<template>
    <v-container>
        <div class="startImage">
            <img src="" alt="" />
        </div>
        <div class="startText" style="margin-bottom: 20px">
            <p>냉장고 관리의 현명한 선택</p>
        </div>
        <div class="mainText" style="margin-bottom: 10px">
            <p style="display: inline-block; font-size: 24px">우</p>
            <p style="display: inline-block">리집</p>
            &nbsp;&nbsp;&nbsp;
            <p style="display: inline-block; font-size: 24px">냉</p>
            <p style="display: inline-block">장고를</p>
            &nbsp;&nbsp;&nbsp;
            <p style="display: inline-block; font-size: 24px">부</p>
            <p style="display: inline-block">탁해</p>
        </div>
        <div class="mainText" style="margin-bottom: 10px">
            <p>지금 바로 경험해보세요!</p>
        </div>
        <hr />
        <div style="margin-top: 10px; margin-bottom: 10px">
            <v-row>
                <v-spacer></v-spacer>
                <img src="" alt="" class="startIcon" />
                <v-spacer></v-spacer>
                <img src="" alt="" class="startIcon" />
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <hr />
    </v-container>
</template>

<script>
export default {
    name: 'Main',
};
</script>

<style scoped>
.startText {
    text-align: center;
}
.startImage {
    width: 330px;
    height: 330px;
}
.startIcon {
    width: 60px;
    height: 60px;
}
</style>
