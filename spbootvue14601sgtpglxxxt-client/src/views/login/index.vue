<template>
    <div class="login-index">
        <div class="admin_login_wrap" v-loading="loading">
            <h1>{{ setting.title }}</h1>
            <div class="adming_login_border">
                <div class="admin_input">
                    <form action="javascript:;" method="post">
                        <ul class="admin_items">
                            <li>
                                <label for="user">用户名：</label>
                                <input type="text" name="username" v-model="form.username" value="" id="user" class="admin_input_style" />
                            </li>
                            <li>
                                <label for="pwd">密码：</label>
                                <input type="password" name="pwd" v-model="form.pwd" value="" id="pwd" class="admin_input_style" />
                            </li>
                            <li>
                                <el-button-group>
                                    <el-button disabled size="mini"> 权限 </el-button>
                                    <el-button size="mini" @click="form.cx = r" :type="form.cx==r ? 'primary' : 'default'" v-for="r in rules">{{ r }}</el-button>
                                </el-button-group>
                            </li>
                            <li>
                                <input type="button" @click="login" value="提交" class="btn btn-primary" />
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <p class="admin_copyright">&copy; Powered by {{setting.title}}</p>
        </div>
    </div>
</template>
<style type="text/scss" scoped lang="scss">
    .login-index{
        width: 100%;
        height: 100%;
        font: 14px/1.5 '微软雅黑';
        background: url("./images/login-bg.jpg") no-repeat #f9f9f9;
        background-size: cover;
        padding-top: 140px;
        a {
            color: #00007F;
            text-decoration: none;
        }

        a:hover {
            color: #bd0a01;
            text-decoration: underline;
        }

        .admin_login_wrap {
            margin: 0px auto;

            width: 400px;
        }

        .admin_login_wrap h1 {
            font-weight: normal;
            font-size: 20px;
            text-align: center;
            margin-bottom: 15px;
        }

        .adming_login_border {
            box-shadow: 0px 0px 8px 0 #ccc,0px 0px 3px 0 #ccc;
            border: 1px solid #eee;
            background: #fff;
            padding: 0 5px 15px;
            border-radius: 8px;
            position: relative;
        }


        .admin_input {
            margin: 15px auto 0;
            padding: 30px;
        }
        .admin_items{

        }
        .admin_items li {
            line-height: 40px;
            margin-bottom: 20px;
            list-style: none;
            clear: both;
            position: relative;

        }

        .admin_input label {
            position: absolute;
            display: block;
            margin-bottom: 5px;
            width: 60px;
            text-align: right;
            top: 50%;
            transform: translateY(-50%);
            left: 10px;
        }
        .pagerandom{
            position: relative;
        }
        .pagerandom>img{
            position: absolute;
            right: 5px;
            top: 50%;
            transform: translateY(-50%);
            width: 63px;
            height: 25px;
        }

        .admin_input_style {
            border: 1px solid #e6e6e6;
            background: #fff;
            padding: 0px;
            padding-left: 80px;
            display: block;
            width: 100%;
            box-sizing: border-box;
            height: 40px;
            line-height: 40px;
            outline: none;
            color: #666;
            border-radius: 5px;
            transition-duration: 0.3s;
        }

        .admin_input_style:focus {
            border-color: rgba(82, 168, 236, 0.8);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
            background: #f8f8f8;
        }

        .btn {
            display: inline-block;
            *display: inline;
            padding: 12px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #bbbbbb;
            *border: 0;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #a2a2a2;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            *zoom: 1;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
            color: #333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active, .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active, .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn-primary {
            width: 100%;
            color: #fff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #006dcc;
            *background-color: #0044cc;
            background-image: -moz-linear-gradient(top, #0088cc, #0044cc);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0044cc));
            background-image: -webkit-linear-gradient(top, #0088cc, #0044cc);
            background-image: -o-linear-gradient(top, #0088cc, #0044cc);
            background-image: linear-gradient(to bottom, #0088cc, #0044cc);
            background-repeat: repeat-x;
            border-color: #0044cc #0044cc #002a80;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
        }

        .btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
            color: #ffffff;
            background-color: #0044cc;
            *background-color: #003bb3;
        }

        .btn-primary:active, .btn-primary.active {
            background-color: #003399 \9;
        }

        .admin_copyright {
            color: #585858;
            margin-top: 10px;
            font-family: Arial;
            text-align: center;
        }

        .admin_copyright a:link, .admin_copyright a:visited {
            color: #f90;
        }

        .admin_copyright a:hover {
            color: #f90;
        }
    }
</style>
<script>
    import setting from "@/setting";
    import api from "@/api";
    export default {
        name: "login-index",
        data() {
            return {
                setting,
                captchUrl: null,
                loading: false,
                captchCode: "",
                form: {
                    username: "",
                    pwd: "",
                    cx: "管理员",
                },
                rules: ["管理员", "用户"],
            };
        },
        watch: {},
        computed: {},
        methods: {
            loadCaptch() {
                api.captch().then((res) => {
                    this.captchUrl = res;
                });
            },
            login() {
                /*if(this.captchCode != this.form.pagerandom)
                {
                    this.$message.error('验证码错误');
                    return;
                }*/
                this.loading = true;
                this.$store
                    .dispatch("user/login", this.form)
                    .then((res) => {
                        this.loading = false;
                        if (res.code == api.code.OK) {
                            this.$message.success("登录成功");
                            var redirect = this.$route.query.redirect;
                            if (redirect) {
                                this.$router.replace(redirect);
                            } else {
                                this.$router.replace("/admin/sy");
                            }
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
            this.form.cx = this.rules[0];
            this.loadCaptch();
        },
        mounted() {},
        destroyed() {},
    };
</script>
