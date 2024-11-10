<template>
    <div class="reply J_ReplyBlock" v-if="replyList.length>0">
        <div class="reply-block" v-for="hf in replyList">
            <div class="reply-content">
                <span class="reply-user">
                    <b class="reply-user-nick J_User">{{ hf.huifuren }}</b>  :
                </span>
                {{ hf.huifuneirong }}
            </div>
            <div class="reply-operate reply-operate--hot">
                <i class="reply-dot">·</i>
                <span>{{ hf.addtime }}</span>
            </div>
        </div>

    </div>
</template>

<script>
    import api from '@/api'
    export default {
        name: "replyList",
        data(){
            return {
                replyList:[]
            }
        },
        props:{
            commitid:[String,Number]
        },
        watch:{
            commitid(){
                this.loadReply()
            }
        },
        methods:{
            loadReply(){
                if(this.commitid){
                    this.$post(api.search.all , {
                        table:'pinglunhuifu',
                        where:JSON.stringify({pinglunid:this.commitid,}),
                        limit:100,
                    }).then(res=>{
                        this.replyList = res.data;
                    })
                }

            }
        },
        created(){
            this.loadReply();
        }
    }
</script>

<style scoped lang="scss">
    .reply {
        background-color: #f7f7f7;
        padding: 8px 16px 24px;
        margin-top: 10px;
    }
    .reply-block {
        position: relative;
    }
    .reply-content {
        font-size: 14px;
        color: #4b4b4b;
        line-height: 24px;
        margin-top: 10px;
    }
    .reply-user {
        color: #4b4b4b;
    }
    .center-click .my-avatar, .user-click .comment-short .common-avatar, .user-click .comment-username, .user-click .reply-user-nick {
        cursor: pointer;
    }

    .reply-user-nick {
        font-weight: 600;
    }
    .reply-operate--hot {
        padding-left: 20px;
        background: url(./asset/news-icon.png) 0 -104px no-repeat;
    }
    .reply-operate {
        height: 14px;
        line-height: 14px;
        font-size: 12px;
        margin-top: 6px;
        color: #adadad;
    }
    .reply-operate span {
        color: #adadad;
    }

    .reply-operate-item {
        cursor: pointer;
    }
    .reply-dot {
        display: inline-block;
        margin: 0 2px;
    }
    .reply-operate span {
        color: #adadad;
    }
    .reply-operate .reply-operate-report, .reply-operate .reply-operate-report-dot {
        display: none;
    }

</style>