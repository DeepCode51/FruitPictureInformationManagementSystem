<template>
    <div class="v-mod" v-loading="loading">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span> 修改密码</span>
            </div>
            <div class="">
                <el-form ref="modForm" :model="form" inline-message label-width="120px">
                    <el-form-item label="原密码" prop="oldPassword" required :rules="[{required:true,message:'请填写原密码'}]">
                        <el-input style="width: 200px" type="password" v-model="form.oldPassword"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newPwd" required :rules="[{required:true,message:'请填写新密码'}]">
                        <el-input style="width: 200px" type="password" v-model="form.newPwd"></el-input>
                    </el-form-item>
                    <el-form-item
                        label="确认密码"
                        prop="newPwd2"
                        required
                        :rules="[{required:true,message:'请填写确认密码'},
                                  {validator:checkPassword,message:'两次密码不一致', trigger: 'blur' }]"
                    >
                        <el-input style="width: 200px" type="password" v-model="form.newPwd2"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="savePassword">保存</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>
<style type="text/scss" lang="scss"></style>
<script>
    import api from "@/api";
    export default {
        name: "v-mod",
        data() {
            return {
                loading: false,
                form: {
                    oldPassword: "",
                    newPwd: "",
                    newPwd2: "",
                },
            };
        },
        watch: {},
        computed: {},
        methods: {
            savePassword() {
                this.$refs.modForm
                    .validate()
                    .then(() => {
                        if (this.loading) {
                            return;
                        }
                        this.loading = true;
                        var form = this.form;
                        this.$post(api.editorPassword, form)
                            .then((res) => {
                                this.loading = false;
                                if (res.code == api.code.OK) {
                                    this.$message.success("密码修改成功");
                                    this.$refs.modForm.resetFields();
                                } else {
                                    this.$message.error(res.msg);
                                }
                            })
                            .catch((err) => {
                                this.loading = false;
                                this.$message.error(err.message);
                            });
                    })
                    .catch(() => {});
            },
            checkPassword(rule, value, callback) {
                if (value == this.form.newPwd) {
                    callback();
                    return;
                }
                callback(new Error("两次密码不一致"));
            },
        },
        created() {},
        mounted() {},
        destroyed() {},
    };
</script>
