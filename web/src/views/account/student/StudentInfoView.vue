<template>
  <ContentField>

    <button type="button" class="btn btn-primary manager-first-second" data-bs-toggle="modal" data-bs-target="#edit_modal" style="display: none;">
    </button>

    <div class="modal fade" id="edit_modal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5"></h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" style="display: flex; justify-content: center; align-items: center;">
            <span style="font-size: 30px; color: greenyellow; font-weight: bold; font-style: italic">操作成功</span>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 100%">关闭</button>
          </div>
        </div>
      </div>
    </div>

    <div class="manager-first">
      <div style="display: inline-flex">
        <div style="font-size: 26px; color:lightblue;">我的个人信息</div>
        <div style="position: absolute; right: 0;">
          <button type="button" class="btn btn-primary manager-first-second" data-bs-toggle="modal" data-bs-target="#update_user_modal" >
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5a.5.5 0 0 0-1 0v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1Z"/>
                <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
              </svg>
              编辑
            </div>
          </button>
        </div>
      </div>
      <div class="modal fade" id="update_user_modal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5">编辑信息</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <label for="add_user_name" class="form-label">姓名</label>
                <input v-model="student_info.name" type="text" class="form-control" id="add_user_name" placeholder="请输入名称" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_name" class="form-label">学号</label>
                <input v-model="student_info.username" type="text" class="form-control" id="add_user_name" placeholder="请输入学号" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_name1" class="form-label">性别</label>
                <input v-model="student_info.sex" type="text" class="form-control" id="add_user_name1" placeholder="请输入性别" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_age" class="form-label">年龄</label>
                <input v-model="student_info.age" type="text" class="form-control" id="add_user_age" placeholder="请输入年龄" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="update_user_name" class="form-label">学院</label>
                <input v-model="student_info.college" type="text" class="form-control" id="update_user_name" placeholder="请输入学院" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_password" class="form-label">专业</label>
                <input v-model="student_info.special" type="text" class="form-control" id="add_user_password" placeholder="请输入专业" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_confirmedPassword" class="form-label">班级</label>
                <input v-model="student_info.classroom" type="text" class="form-control" id="add_user_confirmedPassword" placeholder="请输入班级" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_phone1" class="form-label">入学时间</label>
                <input v-model="student_info.createtime" type="date" class="form-control" id="add_user_phone1" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_phone2" class="form-label">毕业时间</label>
                <input v-model="student_info.endtime" type="date" class="form-control" id="add_user_phone2" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_phone3" class="form-label">地址</label>
                <input v-model="student_info.address" type="text" class="form-control" id="add_user_phone3" placeholder="请输入地址">
              </div>
              <div class="mb-3">
                <label for="add_user_phone4" class="form-label">奖项</label>
                <input v-model="student_info.awarded" type="text" class="form-control" id="add_user_phone4" disabled="disabled">
              </div>
              <div class="mb-3">
                <label for="add_user_phone5" class="form-label">违纪</label>
                <input v-model="student_info.violateDiscipline" type="text" class="form-control" id="add_user_phone5" disabled="disabled">
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button @click="update_user_info" type="button" class="btn btn-primary">确认</button>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="manager-second">
        <div class="mb-3 row">
          <label for="1" class="col-sm-2 col-form-label">姓名</label>
          <div class="col-sm-10">

            <input v-model="student_info.name" type="text" class="form-control" id="1" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="2" class="col-sm-2 col-form-label">学号</label>
          <div class="col-sm-10">
            <input v-model="student_info.username" type="text" class="form-control" id="2" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="3" class="col-sm-2 col-form-label">性别</label>
          <div class="col-sm-10">
            <input v-model="student_info.sex" type="text" class="form-control" id="3" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="4" class="col-sm-2 col-form-label">年龄</label>
          <div class="col-sm-10">
            <input v-model="student_info.age" type="text" class="form-control" id="4" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="9" class="col-sm-2 col-form-label">学院</label>
          <div class="col-sm-10">
            <input v-model="student_info.college" type="text" class="form-control" id="9" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="10" class="col-sm-2 col-form-label">专业</label>
          <div class="col-sm-10">
            <input v-model="student_info.special" type="text" class="form-control" id="10" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="11" class="col-sm-2 col-form-label">班级</label>
          <div class="col-sm-10">
            <input v-model="student_info.classroom" type="text" class="form-control" id="11" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="5" class="col-sm-2 col-form-label">入学时间</label>
          <div class="col-sm-10">
            <input v-model="student_info.createtime" type="date" class="form-control" id="5" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="6" class="col-sm-2 col-form-label">毕业时间</label>
          <div class="col-sm-10">
            <input v-model="student_info.endtime" type="date" class="form-control" id="6" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="7" class="col-sm-2 col-form-label">地址</label>
          <div class="col-sm-10">
            <input v-model="student_info.address" type="text" class="form-control" id="7" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="8" class="col-sm-2 col-form-label">奖项</label>
          <div class="col-sm-10">
            <input v-model="student_info.awarded" type="text" class="form-control" id="8" disabled="disabled">
          </div>
        </div>
        <div class="mb-3 row">
          <label for="9" class="col-sm-2 col-form-label">违纪</label>
          <div class="col-sm-10">
            <input v-model="student_info.violateDiscipline" type="text" class="form-control" id="9" disabled="disabled">
          </div>
        </div>
      </div>
  </ContentField>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import {ref} from "vue";
import {useStore} from "vuex";
import {Modal} from "bootstrap/dist/js/bootstrap";

export default {
  name: "StudentInfoView",
  components: {
    ContentField
  },
  setup() {
    let student_info = ref('');
    const store = useStore();

    const update_user_info = () => {
      $.ajax({
        url: "http://localhost:3000/api/user/student/info/update/",
        type: "post",
        data: {
          id: store.state.user.id,
          address: student_info.value.address,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success() {
          Modal.getInstance("#update_user_modal").hide();
          const myModal = new Modal('#edit_modal');
          myModal.show();
          pull_page();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    const pull_page = () => {
      $.ajax({
        url: "http://localhost:3000/api/user/student/get/",
        type: "get",
        data: {
          id: store.state.user.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          student_info.value = resp.student_info;
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page();



    return {
      pull_page,
      student_info,
      update_user_info,
    }
  }
}
</script>

<style scoped>
.manager-first {
  display: flex;
}

.manager-first-first {
  height: 20px;
  width: 250px;
  margin-right: 30px;
}
.manager-first-second {
  height: 31px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 30px;
  border: 1px solid black;
}

.manager-second {
  margin-top: 20px;
}

.manager-second-first {
  margin-right: 10px;
}

.error_message {
  color: red;
}

</style>
