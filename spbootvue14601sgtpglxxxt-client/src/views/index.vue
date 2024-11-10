<template>
    <div v-loading="loading">
        <div>
            <el-carousel indicator-position="outside" height="480px">
                <el-carousel-item v-for="item in bhtList" :key="item.id">
                    <div style="background-size: cover" @click="$goto(item.url)" :style="{'background-image': 'url('+item.image+')',width:'100%', height: '480px'}"></div>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div>
            <e-container>
                <e-module-model-box title="水果图片">
                    <div class="floor_goods_wrap clearfix">
                        <el-row :gutter="20">
                            <el-col :md="6" v-for="r in shuiguotupianlist1">
                                <div class="floor_goods_wrap_li">
                                    <div class="floor_goods_wrap_b">
                                        <router-link :to="{path:'/shuiguotupiandetail',query:{id:r.id}}" class="floor_goods_img">
                                            <e-img-box :src="r.tupian" pb="100"></e-img-box>
                                        </router-link>
                                        <router-link :to="{path:'/shuiguotupiandetail',query:{id:r.id}}" class="floor_goods_tit" v-html="$substr(r.mingcheng,20)"></router-link>
                                        <router-link :to="{path:'/shuiguotupiandetail',query:{id:r.id}}" class="floor_goods_txt" v-html="$substr(r.jianjie,20)"></router-link>
                                    </div>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </e-module-model-box>
            </e-container>
        </div>
    </div>
</template>
<style type="text/scss" scoped lang="scss"></style>
<script>
    import api from "@/api";
    import { extend } from "@/utils/extend";
    import jdk from "@/utils/extend";
    export default {
        data() {
            return {
                loading: false,
                bhtList: [],
                shuiguotupianlist1: [],
            };
        },
        watch: {},
        computed: {},
        methods: {
            loadPageData() {
                if (this.loading) return;
                this.loading = true;
                this.$get("/index")
                    .then((res) => {
                        this.loading = false;
                        if (res.code == api.code.OK) {
                            extend(this, res.data);
                        } else {
                            this.$message.error(res.msg);
                        }
                    })
                    .catch((err) => {
                        this.loading = false;
                        this.$message.error(err.message);
                    });
            },
        },
        created() {
            this.loadPageData();
        },
        mounted() {},
        destroyed() {},
    };
</script>
