<template>
<!--  <ManagerServiceView></ManagerServiceView>-->
  <ManagerServiceView v-if="$store.state.user.userType === '管理员'"></ManagerServiceView>
  <TeacherServiceView v-else-if="$store.state.user.userType === '老师'"></TeacherServiceView>
  <StudentServiceView v-else-if="$store.state.user.userType === '学生'"></StudentServiceView>

</template>

<script>
import ManagerServiceView from "@/views/account/manager/ManagerServiceView.vue";
import TeacherServiceView from "@/views/account/teacher/TeacherServiceView.vue";
import StudentServiceView from "@/views/account/student/StudentServiceView.vue";
import {useStore} from "vuex";

export default {
  name: "ServiceView",
  components: {
    ManagerServiceView,
    TeacherServiceView,
    StudentServiceView,
  },
  setup() {
    const store = useStore();
    return {
      store,
    }
  }
}
</script>

<style scoped>

</style>
