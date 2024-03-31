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
      <input v-model="teacher_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入姓名" aria-label=".form-control-sm example">
      <input v-model="teacher_college" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入学院" aria-label=".form-control-sm example">
      <button @click="teacher_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="teacher_reset_list" type="button" class="btn btn-danger manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207l-5-5-5 5V13.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7.207Z"/>
          </svg>
          重置
        </div>
      </button>
    </div>
    <div class="manager-second">
      <table class="table table-striped table-hover" style="text-align: center">
        <thead>
        <tr>
          <th>姓名</th>
          <th>学院</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="teacher in teachers" :key="teacher.id">
          <td>
            <span> {{teacher.name}} </span>
          </td>
          <td>
            <span> {{teacher.college}} </span>
          </td>
          <td>
            <button type="button" class="btn btn-secondary manager-second-first" data-bs-toggle="modal" :data-bs-target="'#teacher_info_modal' + teacher.id" style="margin-right: 20px">
              查看完整信息
            </button>
            <div class="modal fade" :id="'teacher_info_modal' + teacher.id" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">个人信息</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div>
                      <div class="container text-center">
                        <div class="row align-items-start">
                          <div class="row">
                            名称: {{teacher.name}}
                          </div>
                          <div class="row">
                            工号: {{teacher.username}}
                          </div>
                          <div class="row">
                            性别: {{teacher.sex}}
                          </div>
                          <div class="row">
                            年龄: {{teacher.age}}
                          </div>
                          <div class="row">
                            学院: {{teacher.college}}
                          </div>
                          <div class="row">
                            入职时间: {{teacher.createtime}}
                          </div>
                          <div class="row">
                            职龄: {{teacher.workAge}}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 100%;">关闭</button>
                  </div>
                </div>
              </div>
            </div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#update_teacher_modal_' + teacher.id">
              编辑
            </button>

            <div class="modal fade" :id="'update_teacher_modal_' + teacher.id" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">编辑页面</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="update_user_phone" class="form-label">名称</label>
                      <input v-model="teacher.name" type="text" class="form-control" id="update_user_phone" placeholder="请输入名称">
                    </div>
                    <div class="mb-3">
                      <label for="update_teacher_username" class="form-label">学号或工号</label>
                      <input v-model="teacher.username" type="text" class="form-control" id="update_teacher_username" placeholder="请输入学号或工号">
                    </div>
                    <div class="mb-3">
                      <label for="update_teacher_age" class="form-label">年龄</label>
                      <input v-model="teacher.age" type="text" class="form-control" id="update_teacher_age" placeholder="请输入年龄">
                    </div>
                    <label class="form-label">性别</label>
                    <select v-model="teacher.sex" class="form-select" aria-label="Default select example">
                      <option value="男">男</option>
                      <option value="女">女</option>
                    </select>
                    <div class="mb-3">
                      <label for="update_teacher_college" class="form-label">学院</label>
                      <input v-model="teacher.college" type="text" class="form-control" id="update_teacher_college" placeholder="请输入学院">
                    </div>

                    <div class="mb-3">
                      <label for="update_teacher_createtime" class="form-label">入职时间</label>
                      <input v-model="teacher.createtime" type="date" class="form-control" id="update_teacher_createtime" placeholder="请输入入学时间">
                    </div>
                    <div class="mb-3">
                      <label for="update_teacher_workAge" class="form-label">职龄</label>
                      <input v-model="teacher.workAge" type="text" class="form-control" id="update_teacher_workAge" placeholder="请输入职龄">
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button @click="update_teacher(teacher)" type="button" class="btn btn-primary">保存修改</button>
                  </div>
                </div>
              </div>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination" style="float: right">
          <li class="page-item" @click="user_click_page(-2)">
            <a class="page-link" href="#">前一页</a>
          </li>
          <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="user_click_page(page.number)">
            <a class="page-link" href="#">{{page.number}}</a>
          </li>

          <li class="page-item" @click="user_click_page(-1)">
            <a class="page-link" href="#">后一页</a>
          </li>
        </ul>
      </nav>
    </div>
  </ContentField>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from "jquery";
import {ref} from "vue";
import {useStore} from "vuex";
import {Modal} from 'bootstrap/dist/js/bootstrap';
export default {
  name: "ManagerTeacherControlView",
  components: {
    ContentField,
  },
  setup() {
    let pages = ref([]);
    let current_page = 1;
    let total_teachers = 0;
    let teachers = ref([]);
    const store = useStore();
    let teacher_name = ref('');
    let teacher_college = ref('');
    let one = 1;

    const teacher_search = () => {
      pull_page(one, teacher_name, teacher_college);
    }

    const teacher_reset_list = () => {
      teacher_name.value = "";
      teacher_college.value = "";
      pull_page(one, teacher_name, teacher_college);
    }
    const user_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_teachers / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, teacher_name, teacher_college);
      }
    }
    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_teachers / 10));
      let new_pages = [];
      for (let i = current_page - 2; i <= current_page + 2; ++ i) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page ? "active" : "",
          });
        }
      }
      pages.value = new_pages;
    }
    const pull_page = (page, teacher_name, teacher_college) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/teacher/getlist/",
        type: "get",
        data: {
          page: page,
          teacher_name: teacher_name.value,
          teacher_college: teacher_college.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teachers.value = resp.teachers;
          console.log(resp);
          total_teachers = resp.total_teachers;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, teacher_name, teacher_college);

    const update_teacher = teacher => {
      $.ajax({
        url: "http://localhost:3000/api/user/teacher/update/",
        type: "post",
        data: {
          teacher_id: teacher.id,
          teacher_username: teacher.username,
          teacher_name: teacher.name,
          teacher_age: teacher.age,
          teacher_sex: teacher.sex,
          teacher_college: teacher.college,
          teacher_createtime: teacher.createtime,
          teacher_work_age: teacher.workAge,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            Modal.getInstance("#update_teacher_modal_" + teacher.id).hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    return {
      pull_page,
      pages,
      teacher_name,
      teacher_college,
      one,
      total_teachers,
      current_page,
      update_pages,
      user_click_page,
      teachers,
      teacher_search,
      update_teacher,
      teacher_reset_list,
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
