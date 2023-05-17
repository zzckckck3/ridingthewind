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
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import(/* webpackChunkName: "notice" */ "@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () =>
          import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeList.vue"),
      },
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
