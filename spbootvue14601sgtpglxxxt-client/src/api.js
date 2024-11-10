import http from "@/utils/ajax/http";
import { extend } from "@/utils/extend";

const api = {
    code: {
        OK: 0,
    },
    user: {
        login: "authLogin.do?ac=login",
        tokenLogin: "tokenLogin.do",
        logout: "logout.do",
    },
    checkUpdate: "sh.do",
    editorPassword: "editPassword",
    checkField: "checkno.do",
    dbQuery: "/db/query",
    dbSelect: "/db/select",

    captch() {
        var url = "captcha.do?rd=" + Math.floor(Math.random() * 100000);
        return new Promise((resolve, reject) => {
            http.get(url).then((res) => {
                var url = res.data;
                resolve(url);
            }, reject);
        });
    },
    search: {
        select: "selectUpdateSearch.do",
        table: "tableAjax.do?a=table",
        selectView: "selectView.do",
        all: "selectAll.do",
    },
    attachment: {
        uploadUrl: "upload_re.do",
        upload(file) {
            return new Promise((resolve, reject) => {
                var formdata = new FormData();
                formdata.append("fujian", file, file.name || "tmp.png");
                http.post(api.attachment.uploadUrl, formdata)
                    .then((res) => {
                        if (res.code == api.code.OK) {
                            resolve(res.data);
                        } else {
                            reject(res.msg);
                        }
                    })
                    .catch((err) => {
                        reject(err.message);
                    });
            });
        },
    },
    admins: {
        list: "admins_list.do",
        insert: "adminsinsert.do",
        update: "adminsupdate.do",
        delete: "admins_delete.do",
        detail: "admins_detail.do",
        create: "admins_add.do",
        edit: "admins_updt.do",
    },
    yonghu: {
        list: "yonghu_list.do",
        insert: "yonghuinsert.do",
        update: "yonghuupdate.do",
        delete: "yonghu_delete.do",
        detail: "yonghu_detail.do",
        create: "yonghu_add.do",
        edit: "yonghu_updt.do",
    },
    liuyanban: {
        list: "liuyanban_list.do",
        insert: "liuyanbaninsert.do",
        update: "liuyanbanupdate.do",
        delete: "liuyanban_delete.do",
        detail: "liuyanban_detail.do",
        create: "liuyanban_add.do",
        edit: "liuyanban_updt.do",
        listliuyanren: "liuyanban_list_liuyanren.do",
    },
    lunbotu: {
        list: "lunbotu_list.do",
        insert: "lunbotuinsert.do",
        update: "lunbotuupdate.do",
        delete: "lunbotu_delete.do",
        detail: "lunbotu_detail.do",
        create: "lunbotu_add.do",
        edit: "lunbotu_updt.do",
    },
    pinglun: {
        list: "pinglun_list.do",
        insert: "pingluninsert.do",
        update: "pinglunupdate.do",
        delete: "pinglun_delete.do",
        detail: "pinglun_detail.do",
        create: "pinglun_add.do",
        edit: "pinglun_updt.do",
        listpinglunren: "pinglun_list_pinglunren.do",
    },
    shuiguotupian: {
        list: "shuiguotupian_list.do",
        insert: "shuiguotupianinsert.do",
        update: "shuiguotupianupdate.do",
        delete: "shuiguotupian_delete.do",
        detail: "shuiguotupian_detail.do",
        create: "shuiguotupian_add.do",
        edit: "shuiguotupian_updt.do",
        listfaburen: "shuiguotupian_list_faburen.do",
        weblist: "shuiguotupianlist.do",
        webdetail: "shuiguotupiandetail.do",
        batch: "shuiguotupian_batch.do",
    },
};

export default api;
