import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

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
