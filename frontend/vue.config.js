const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/tour": {
        target: "http://localhost", // 실제 API 서버 주소
        changeOrigin: true,
        pathRewrite: {
          "^/tour": "/tour",
        },
      },
      "/article": {
        target: "http://localhost", // 실제 API 서버 주소
        changeOrigin: true,
        pathRewrite: {
          "^/article": "/article",
        },
      },
    },
    headers: {
      "Access-Control-Allow-Origin": "*", // 모든 도메인에서의 요청 허용
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE", // 허용할 HTTP 메서드 설정
      "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept", // 허용할 요청 헤더 설정
    },
  },
});
