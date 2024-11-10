const path = require("path");
const setting = require("./src/setting");
function resolve(dir) {
    return path.join(__dirname, dir);
}

module.exports = {
    publicPath: "/",
    lintOnSave: false,
    productionSourceMap: false,
    configureWebpack: {
        devtool: "source-map",
        // provide the app's title in webpack's name field, so that
        // it can be accessed in index.html to inject the correct title.
        name: "Home",
        resolve: {
            alias: {
                "@": resolve("src"),
            },
        },
    },
    devServer: {
        proxy: setting.proxyUrl,
    },
};
