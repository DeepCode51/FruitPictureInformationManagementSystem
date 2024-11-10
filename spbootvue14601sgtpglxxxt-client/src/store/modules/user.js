import http from "@/utils/ajax/http";
import api from "@/api";
let token = localStorage.getItem("token");
const state = {
    token: token ? token : "",
    session: {},
};

const actions = {
    login(context, data) {
        return new Promise((resolve, reject) => {
            http.post(api.user.login, data)
                .then((res) => {
                    if (res.code == api.code.OK) {
                        context.commit("setSession", res.data.session);
                        context.commit("setToken", res.data.token);
                    }
                    resolve(res);
                })
                .catch((err) => {
                    reject(err);
                });
        });
    },
    tokenLogin(context, data) {
        return new Promise((resolve, reject) => {
            // 使用token 登录
            var token = context.state.token;
            http.post(api.user.tokenLogin, { token })
                .then((res) => {
                    if (res.code == api.code.OK) {
                        // 登录成功
                        context.commit("setSession", res.data.session);
                        context.commit("setToken", res.data.token);
                        resolve(true);
                    } else {
                        resolve(false);
                    }
                })
                .catch(reject);
        });
    },
    logout(context) {
        return new Promise((resolve, reject) => {
            http.post(api.user.logout)
                .then((res) => {
                    if (res.code == api.code.OK) {
                        context.commit("setSession", {});
                        context.commit("setToken", "");
                    }
                    resolve(res);
                })
                .catch((err) => {
                    reject(err);
                });
        });
    },
};

const mutations = {
    setSession(state, data) {
        state.session = data;
    },
    setToken(state, token) {
        state.token = token;
        window.localStorage.setItem("token", token);
    },
};

export default {
    namespaced: true,
    state,
    actions,
    mutations,
};
