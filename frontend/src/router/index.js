import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store";
Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);


  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signin" });
    //router.push({ name: "signin" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    redirect: "/user/signin",
    component: () => import(/* webpackChunkName: "user" */ "@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signin",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/MemberLogin.vue"),
      },
      {
        path: "signup",
        name: "signup",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MemberSignup.vue"),
      },
    ],
  },
  {
    path: "/article",
    name: "article",
    component: () => import(/* webpackChunkName: "article" */ "@/views/ArticleView.vue"),
    redirect: "/article/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "articleList",
        component: () =>
          import(/* webpackChunkName: "article" */ "@/components/article/ArticleList.vue"),
      },
      {
        path: "detail",
        name: "articleDetail",
        component: () =>

            import(/* webpackChunkName: "article" */ "@/components/article/ArticleDetail.vue"),
      },
      {
        path: "modify",
        name: "articleModify",
        component: () =>
            import(/* webpackChunkName: "article" */ "@/components/article/ArticleModify.vue"),
      },
      {
        path: "write",
        name: "articleWrite",
        component: () =>
            import(/* webpackChunkName: "article" */ "@/components/article/ArticleWrite.vue"),
      }
    ],
  },
  {
    path: "/tour",
    name: "tour",
    component: () => import(/* webpackChunkName: "trip" */ "@/views/TourInfoView.vue"),
    redirect: "/tour/sido",
    children: [
      {
        path: "sido",
        name: "tourinfo",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/tour/TourSearchInfo.vue"),
      },
    ],
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import(/* webpackChunkName: "trip" */ "@/views/PersonalTripView.vue"),
    children: [],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});


export default router;
