import { createRouter, createWebHistory } from 'vue-router'
import UserAccountLoginView from "@/views/account/UserAccountLoginView.vue";
import UserAccountRegisterView from "@/views/account/UserAccountRegister.vue";
import NotFound from "@/views/404/NotFound.vue";
import store from "@/store";
import ServiceView from "@/views/account/service/ServiceView.vue";
import UserInfoView from "@/views/account/UserInfoView.vue";
import ManagerServiceView from "@/views/account/manager/ManagerServiceView.vue";
import UserControlView from "@/views/account/UserControlView.vue";
import ManagerStudentControlView from "@/views/account/manager/ManagerStudentControlView.vue";
import ManagerCollegeControlView from "@/views/account/manager/ManagerCollegeControlView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/user/service/",
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/service/",
    name: "user_service",
    component: ServiceView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/info/",
    name: "user_info",
    component: UserInfoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/service/manager/service/",
    name: "manager_service",
    component: ManagerServiceView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/service/manager/student/control/",
    name: "manager_student_control",
    component: ManagerStudentControlView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/service/user/control/",
    name: "user_control",
    component: UserControlView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/service/manager/college/control/",
    name: "manager_college_control",
    component: ManagerCollegeControlView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "user_account_login"});
  } else {
    next();
  }
})

export default router
