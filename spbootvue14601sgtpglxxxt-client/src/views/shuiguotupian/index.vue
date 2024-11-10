<template>
    <div v-loading="loading">
        <div>
            <e-container>
                <div class="floor_goods_wrap clearfix">
                    <div style="margin-bottom: 20px">
                        <form action="javascript:;" @submit="searchSubmit" class="form-search">
                            <table class="jd-search">
                                <tbody>
                                    <tr>
                                        <td class="label">名称</td>
                                        <td>
                                            <el-input type="text" style="width: 150px" v-model="search.mingcheng" placeholder="请输入关键词"> </el-input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">标签</td>
                                        <td>
                                            <p class="search-radio">
                                                <a href="javascript:;" @click="selectRadio('biaoqian','')" :class="{active:!search.biaoqian}">全部</a>

                                                <a href="javascript:;" :class="{active:search.biaoqian == '正常'}" @click="selectRadio('biaoqian','正常')">正常</a>
                                                <a href="javascript:;" :class="{active:search.biaoqian == '腐烂'}" @click="selectRadio('biaoqian','腐烂')">腐烂</a>
                                                <a href="javascript:;" :class="{active:search.biaoqian == '未成熟'}" @click="selectRadio('biaoqian','未成熟')">未成熟</a>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <el-button type="success" @click="searchSubmit">搜索</el-button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <el-row :gutter="20">
                        <el-col :md="6" v-for="r in list">
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
            </e-container>
        </div>
    </div>
</template>
<style type="text/scss" scoped lang="scss"></style>
<script>
    import api from "@/api";
    import { extend } from "@/utils/extend";
    import objectDiff from "objectdiff";

    /**
     * 后台列表页面
     */
    export default {
        data() {
            return {
                loading: false,
                list: [],
                search: {
                    bianhao: "",

                    mingcheng: "",

                    tupian: "",

                    biaoqian: "",

                    jianjie: "",

                    faburen: "",

                    addtime: "",
                },
                page: 1, // 当前页
                pagesize: 12, // 页大小
                totalCount: 0, // 总行数
                total: {},
            };
        },
        watch: {},
        computed: {},
        methods: {
            searchSubmit() {
                this.loadList(1);
            },
            loadList(page) {
                // 防止重新点加载列表
                if (this.loading) return;
                this.loading = true;
                this.page = page;
                // 筛选条件
                var filter = extend(true, {}, this.search, { page: page + "", pagesize: this.pagesize + "" });
                var diff = objectDiff.diff(filter, this.$route.query);
                if (diff.changed != "equal") {
                    // 筛选的条件不一致则更新链接
                    this.$router.replace({
                        // 更新query
                        path: this.$route.path,
                        query: filter,
                    });
                }
                this.$post(api.shuiguotupian.weblist, filter)
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
            selectRadio(target, name) {
                this.search[target] = name;
                this.searchSubmit(1);
            },
        },
        beforeRouteUpdate(to, form, next) {
            var search = extend(this.search, to.query);
            if (search.page) {
                this.page = Math.floor(to.query.page);
                delete search.page;
            }
            if (search.pagesize) {
                this.pagesize = Math.floor(to.query.pagesize);
                delete search.pagesize;
            }
            this.loadList(1);
            next();
        },
        created() {
            var search = extend(this.search, this.$route.query);
            if (search.page) {
                this.page = Math.floor(this.$route.query.page);
                delete search.page;
            }
            if (search.pagesize) {
                this.pagesize = Math.floor(this.$route.query.pagesize);
                delete search.pagesize;
            }
            this.loadList(this.page);
        },
        mounted() {},
        destroyed() {},
    };
</script>
