import http from "@/utils/ajax/http";
import { empty } from "@/utils/extend";
import date from "@/utils/date";
import api from "@/api";

function getMessage(msg, def) {
    return msg ? msg : def;
}

export default {
    checkRemote(rule, value, callback) {
        var postdata = {
            table: rule.module,
            checktype: rule.checktype,
            col: rule.col,
        };
        if (rule.checktype == "update") {
            postdata.id = rule.id;
        }
        postdata[rule.col] = value;
        http.post(api.checkField, postdata).then((res) => {
            if (res) {
                callback();
            } else {
                callback(new Error("内容重复了"));
            }
        });
    },
    checkCard(rule, value, callback, source, options) {
        if (empty(value) || /^[1-9]([0-9]{14}|[0-9]{17})$/.test(value)) {
            callback();
            return;
        }
        callback(new Error(options.message ? options.message : "请输入一个正确的身份证号"));
    },
    checkPhone(rule, value, callback, source, options) {
        if (empty(value) || /^[0-9]{11}$/.test(value)) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "请输入一个正确的手机号码")));
    },
    checkTel(rule, value, callback, source, options) {
        if (empty(value) || /^[+]{0,1}(\d){1,4}[ ]{0,1}([-]{0,1}((\d)|[ ]){1,12})+$/.test(value)) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "请输入正确的固定电话号码")));
    },
    checkMin(rule, value, callback, source, options) {
        if ((empty(value) && value !== 0) || value >= rule.value) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "请输入不小于" + rule.value + "值")));
    },
    checkMax(rule, value, callback, source, options) {
        if ((empty(value) && value !== 0) || value <= rule.value) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "请输入不大于" + rule.value + "值")));
    },
    checkMinlength(rule, value, callback, source, options) {
        if (value == "" || value.length >= rule.value) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "字符不小于" + rule.value + "个字符")));
    },
    checkMaxlength(rule, value, callback, source, options) {
        if (value == "" || value.length <= rule.value) {
            callback();
            return;
        }
        callback(new Error(getMessage(options.message, "字符不大于" + rule.value + "个字符")));
    },
    checkNumber(rule, value, callback, source, options) {
        if (empty(value) || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value)) {
            callback();
            return;
        }
        callback(new Error(options.message ? options.message : "请输入一个有效数字"));
    },
    getID() {
        return date("mdHi") + Math.floor(Math.random() * 10000);
    },
    date: date,
};
