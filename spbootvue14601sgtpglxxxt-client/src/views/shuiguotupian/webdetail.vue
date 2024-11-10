<template>
    <div class="shuiguotupian-detail" v-loading="loading">
        <div>
            <e-container>
                <div class="">
                    <div class="goods-info clearfix">
                        <div class="gallery-list">
                            <e-shangpinban :images="map.tupian"></e-shangpinban>
                        </div>
                        <div class="goods-right-content">
                            <h3 class="title" v-text="map.mingcheng"></h3>
                            <div class="descount">
                                <div>
                                    <span class="name"> 编号： </span>
                                    <span class="val"> {{ map.bianhao }} </span>
                                </div>
                                <div>
                                    <span class="name"> 标签： </span>
                                    <span class="val"> {{ map.biaoqian }} </span>
                                </div>
                                <div>
                                    <span class="name"> 发布人： </span>
                                    <span class="val"> {{ map.faburen }} </span>
                                </div>
                                <div>
                                    <span class="name"> 发布时间： </span>
                                    <span class="val"> {{ map.addtime }} </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="goods-content" v-html="map.jianjie"></div>
                </div>
            </e-container>
        </div>
        <div>
            <e-container>
                <e-module-model-box title="评论区">
                    <e-comments module="shuiguotupian" :lists="comments" :biaoti="map.mingcheng" user="yonghu" headimg="touxiang" :is-reply="false" name="xingming"> </e-comments>
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
                loading: false, // 加载

                map: {
                    bianhao: "",
                    mingcheng: "",
                    tupian: "",
                    biaoqian: "",
                    jianjie: "",
                    faburen: "",
                    addtime: "",
                },
                comments: [],
            };
        },
        props: {
            id: {
                type: [String, Number],
                required: true,
            },
        },
        watch: {
            id: {
                handler() {
                    this.loadDetail();
                },
                immediate: true,
            },
        },
        computed: {},
        methods: {
            loadDetail() {
                if (this.loading) return;
                this.loading = true;
                this.$get(api.shuiguotupian.webdetail, {
                    id: this.id,
                })
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
        created() {},
        mounted() {},
        destroyed() {},
    };
</script>
