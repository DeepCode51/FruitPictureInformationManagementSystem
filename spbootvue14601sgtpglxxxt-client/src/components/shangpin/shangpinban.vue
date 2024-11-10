<template>
    <div class="e-shangpinban">
        <el-carousel indicator-position="outside">
            <el-carousel-item v-for="item in items" v-if="item" :key="item">
                <e-img :src="item"></e-img>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    export default {
        name: "e-shangpinban",
        data() {
            return {}
        },
        props:{
            images:String
        },
        watch: {},
        computed: {
            items(){
                if(this.images) {
                    return this.images.split(",")
                }
                return [];
            }
        },

    }
</script>
