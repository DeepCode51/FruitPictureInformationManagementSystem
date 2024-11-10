import config from "@/config";
import api from "@/api";
import { isFunction, isObject, isArray } from "@/utils/extend";
import Vue from "vue";

export function formatImageSrc(value) {
    if (!value) return "";
    if (value.indexOf("/") === 0) {
        return config.service_url + value;
    }
    var url = value;
    if (!url.match(/^https?:\/\//gi)) {
        return config.service_url + "/" + value;
    }
    return url;
}

export function remove(arr, key) {
    if (isObject(key) || isArray(key)) {
        var index = findIndex(arr, (r) => r === key);
        if (index !== false) {
            remove(arr, index);
        }
    } else {
        Vue.delete(arr, key); // 交给vue 系统删除
    }
}

export function checkIssh(row, module) {
    this.$post(api.checkUpdate, {
        tablename: module,
        id: row.id,
        yuan: row.issh,
    }).then(
        (res) => {
            if (res.code == api.code.OK) {
                row.issh = row.issh == "是" ? "否" : "是";
            } else {
                this.$message.error(res.msg);
            }
        },
        (err) => {
            this.$message.error(err.message);
        }
    );
}

export function findIndex(arr, callback) {
    if (!isFunction(callback)) {
        throw new Error("findObject第二个参数是回调函数");
    }
    for (var i in arr) {
        var ci = arr[i];
        if (callback(ci, i)) {
            return i;
        }
    }
    return false;
}

export function findObject(arr, callback) {
    if (!isFunction(callback)) {
        throw new Error("findObject第二个参数是回调函数");
    }
    for (var i in arr) {
        var ci = arr[i];
        if (callback(ci, i)) {
            return ci;
        }
    }
    return false;
}
