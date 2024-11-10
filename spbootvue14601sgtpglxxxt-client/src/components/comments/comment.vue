<template>

    <div class="">
        <div class="">
            <el-form action="javascript:;" ref="form" :model="comment" method="post"  status-icon validate-on-rule-change label-width="100px">

                <el-form-item label="评论内容" prop="pinglunneirong" required
                              :rules="[{required:true, message:'请输入评论内容'}]">
                    <el-input type="textarea" v-model="comment.pinglunneirong" placeholder="请输入评论内容"></el-input>
                </el-form-item>
                <el-form-item>
                    <template v-if="$session.cx == '用户'">
                        <el-button type="primary" @click="submitComment">发布评论</el-button>
                    </template>

                </el-form-item>
            </el-form>
        </div>
        <div style="margin-top: 10px">
            <div class="comment" v-for="v in lists">
                <div class="common-avatar J_User">
                    <e-img v-if="v[headimg]" :src="v[headimg]" width="100%" height="100%"></e-img>
                    <img :src="defaultHeadimg" v-else width="100%" height="100%"/>

                </div>
                <div class="comment-block">
                    <p class="comment-user">
                        <span class="comment-username J_User"> {{ v[name] }} </span>
                        <
                        <span class="comment-time">{{ v.addtime.substr(0,10) }}</span>
                        <router-link v-if="isReply" :to="'/pinglunhuifuadd?id='+v.id">回复</router-link>
                    </p>
                    <div class="comment-content J_CommentContent">
                        {{v.pinglunneirong}}
                    </div>
                    <reply-list v-if="isReply" :commitid="v.id"></reply-list>
                </div>
            </div>
        </div>
    </div>

</template>
<style type="text/scss" scoped lang="scss">
    .comment {
        position: relative;
        margin-top: 32px;
        padding: 0px 0px 32px 68px;
        border-bottom: 1px solid rgb(240, 240, 240);
        .common-avatar {
            display: block;
            position: absolute;
            left: 0px;
            top: 8px;
            z-index: 1;
            width: 50px;
            height: 50px;
            font-size: 0px;
            background: url("./asset/default.gif") no-repeat;
            border-radius: 50%;
            background-size: cover;
            overflow: hidden;
        }
        .common-avatar img {
            width: 100%;
            height: 100%;
        }
        .comment-user {
            height: 32px;
            line-height: 32px;
            font-size: 12px;
            >span{
                display: inline-block;
            }
            .comment-username {
                font-weight: 600;
                color: rgb(55, 155, 233);
            }
            .comment-time {
                color: rgb(153, 153, 153);
                display: inline-block;
                margin-left: 12px;
            }
        }
    }
    .comment-content {
        font-size: 14px;
        color: rgb(75, 75, 75);
        line-height: 24px;
        margin-top: 10px;
        overflow-wrap: break-word;
    }
    .comment-short .comment-content {
        margin-top: 2px;
    }
</style>
<script>
    import api from '@/api'
    import {extend} from "@/utils/extend";
    import ReplyList from "@/components/comments/replyList";

    export default {
        name: "e-comments",
        components: {ReplyList},
        data() {
            return {
                comment:{
                    pingfen:5,
                    pinglunneirong:'',
                    biao:'',
                    biaoid:0,
                    biaoti:'',
                },
                isLoading:false,
                defaultHeadimg:require('./asset/default.gif')
            }
        },
        props:{
            lists:Array,
            module:String,
            biaoti:String,
            headimg:String,
            name:String,
            isReply:Boolean
        },
        watch: {},
        computed: {},
        methods: {
            submitComment(){
                this.$refs.form.validate().then(()=>{
                    var comment = this.comment;
                    comment.biao = this.module;
                    comment.biaoti = this.biaoti;
                    comment.pinglunren = this.$session.username;
                    comment.biaoid = this.$route.query.id;
                    this.isLoading = true;
                    this.$post(api.pinglun.insert , comment).then(res=>{
                        this.isLoading = false;
                        if(res.code == api.code.OK){
                            comment[this.name]   = this.$session[this.name];
                            comment[this.headimg] = this.$session[this.headimg];
                            comment.addtime = '刚刚';

                            this.lists.unshift(extend(true,{id:res.data.id},comment));
                            this.$message.success('评论成功');

                        }else{
                            this.$message.error(res.msg);
                        }
                    }).catch(err=>{
                        this.isLoading = false;
                        this.$message.error(err.message);
                    });
                })

            }
        },
        created() {

        },
        mounted() {
        },
        destroyed() {
        }
    }
</script>
