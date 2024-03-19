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
      <input v-model="user_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入姓名" aria-label=".form-control-sm example">
      <input v-model="user_phone" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入电话号码" aria-label=".form-control-sm example">
      <button @click="user_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="user_reset_list" type="button" class="btn btn-danger manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207l-5-5-5 5V13.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7.207Z"/>
          </svg>
          重置
        </div>
      </button>
      <button type="button" class="btn btn-primary manager-first-second" data-bs-toggle="modal" data-bs-target="#add_user_modal">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5a.5.5 0 0 0-1 0v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1Z"/>
            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
          </svg>
          新增
        </div>
      </button>

      <div class="modal fade" id="add_user_modal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5">新增用户</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <label for="add_user_name" class="form-label">姓名</label>
                <input v-model="add_user.user_name" type="text" class="form-control" id="add_user_name" placeholder="请输入名称">
              </div>
              <div class="mb-3">
                <label for="add_user_age" class="form-label">年龄</label>
                <input v-model="add_user.user_age" type="text" class="form-control" id="add_user_age" placeholder="请输入年龄">
              </div>
              <div class="mb-3">
                <label for="update_user_name" class="form-label">学号或工号</label>
                <input v-model="add_user.user_username" type="text" class="form-control" id="update_user_name" placeholder="请输入学号或工号">
              </div>
              <div class="mb-3">
                <label for="add_user_password" class="form-label">密码</label>
                <input v-model="add_user.user_password" type="password" class="form-control" id="add_user_password" placeholder="请输入密码">
              </div>
              <div class="mb-3">
                <label for="add_user_confirmedPassword" class="form-label">确认密码</label>
                <input v-model="add_user.user_confirmedPassword" type="password" class="form-control" id="add_user_confirmedPassword" placeholder="请输入确认密码">
              </div>
              <div class="mb-3">
                <label for="add_user_phone" class="form-label">电话</label>
                <input v-model="add_user.user_phone" type="tel" class="form-control" id="add_user_phone" placeholder="请输入电话">
              </div>
              <label class="form-label">性别</label>
              <select v-model="add_user.user_sex" class="form-select" aria-label="Default select example">
                <option value="男" selected>男</option>
                <option value="女">女</option>
              </select>
              <label class="form-label">类型</label>
              <select v-model="add_user.user_type" class="form-select" aria-label="Default select example">
                <option value="管理员" selected>管理员</option>
                <option value="老师">老师</option>
                <option value="学生">学生</option>
              </select>
            </div>
            <div class="modal-footer">
              <div class="error_message">{{add_user.error_message}}</div>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button @click="Add_user" type="button" class="btn btn-primary">确认</button>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="manager-second">
      <table class="table table-striped table-hover" style="text-align: center">
        <thead>
          <tr>
            <th>姓名</th>
            <th>年龄</th>
            <th>电话号码</th>
            <th>性别</th>
            <th>类型</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>
              <span> {{user.name}} </span>
            </td>
            <td>
              <span>
                {{user.age}}
              </span>
            </td>
            <td>
              <span> {{user.phone}} </span>
            </td>
            <td>
              <span> {{user.sex}} </span>
            </td>
            <td>
              <span>
                {{user.userType}}
              </span>
            </td>
            <td>
              <button type="button" class="btn btn-primary manager-second-first" data-bs-toggle="modal" :data-bs-target="'#update_user_modal_' + user.id">
                编辑
              </button>

              <div class="modal fade" :id="'update_user_modal_' + user.id" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5">编辑页面</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <div class="mb-3">
                        <label for="update_user_name" class="form-label">名称</label>
                        <input v-model="user.name" type="text" class="form-control" id="update_user_name" placeholder="请输入名称">
                      </div>
                      <div class="mb-3">
                        <label for="update_user_phone" class="form-label">电话</label>
                        <input v-model="user.phone" type="text" class="form-control" id="update_user_phone" placeholder="请输入电话">
                      </div>
                      <div class="mb-3">
                        <label for="update_user_age" class="form-label">年龄</label>
                        <input v-model="user.age" type="text" class="form-control" id="update_user_age" placeholder="请输入年龄">
                      </div>
                      <label class="form-label">性别</label>
                      <select v-model="user.sex" class="form-select" aria-label="Default select example">
                        <option value="男">男</option>
                        <option value="女">女</option>
                      </select>
                      <label class="form-label">类型</label>
                      <select v-model="user.userType" class="form-select" aria-label="Default select example">
                        <option value="管理员" selected>管理员</option>
                        <option value="老师">老师</option>
                        <option value="学生">学生</option>
                      </select>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                      <button @click="update_user(user)" type="button" class="btn btn-primary">保存修改</button>
                    </div>
                  </div>
                </div>
              </div>
              <button @click="remove_user(user.id)" type="button" class="btn btn-danger manager-second-first">删除</button>
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
import $ from 'jquery';
import {ref, reactive} from "vue";
import {useStore} from "vuex";
import { Modal} from "bootstrap/dist/js/bootstrap";

export default {
  name: "UserControlView",
  components: {
    ContentField
  },
  setup() {
    let pages = ref([]);
    let current_page = 1;
    let total_users = 0;
    let users = ref([]);
    const store = useStore();
    let user_name = ref('');
    let user_phone = ref('');
    let one = 1;
    const add_user = reactive({
      user_username: "",
      user_password: "",
      user_confirmedPassword: "",
      user_phone: "",
      user_name: "",
      user_sex: "男",
      user_age: "",
      user_type: "管理员",
      error_message: "",
    })
    const user_search = () => {
      pull_page(one, user_name, user_phone)
    }

    const user_reset_list = () => {
      user_name.value = "";
      user_phone.value = "";
      pull_page(one, user_name, user_phone);
    }
    const user_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_users / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, user_name, user_phone);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_users / 10));
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
    const pull_page = (page, user_name, user_phone) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/getlist/",
        type: "get",
        data: {
          page: page,
          user_name: user_name.value,
          user_phone: user_phone.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          users.value = resp.users;
          total_users = resp.users_count;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, user_name, user_phone);

    const update_user = user => {
      $.ajax({
        url: "http://localhost:3000/api/user/update/",
        type: "post",
        data: {
          user_id: user.id,
          user_name: user.name,
          user_phone: user.phone,
          user_sex: user.sex,
          user_age: user.age,
          user_type: user.userType,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#update_user_modal_" + user.id).hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
            console.log("success");
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    const remove_user = user_id => {
      $.ajax({
        url: "http://localhost:3000/api/user/remove/",
        type: "post",
        data: {
          user_id: user_id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            pull_page(current_page, user_name, user_phone);
            console.log("success");
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    const Add_user = () => {
      add_user.error_message = "";
      $.ajax({
        url: "http://localhost:3000/api/user/add/",
        type: "post",
        data: {
          user_username: add_user.user_username,
          user_phone: add_user.user_phone,
          user_password: add_user.user_password,
          user_confirmedPassword: add_user.user_confirmedPassword,
          user_name: add_user.user_name,
          user_sex: add_user.user_sex,
          user_type: add_user.user_type,
          user_age: add_user.user_age,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            add_user.user_name = "";
            add_user.user_sex = "";
            add_user.user_password = "";
            add_user.user_confirmedPassword = "";
            add_user.user_sex = "男";
            add_user.user_phone = "";
            add_user.user_username = "";
            add_user.user_type = "manager";
            add_user.user_age = "";
            Modal.getInstance("#add_user_modal").hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
            pull_page(current_page, user_name, user_phone);
          } else {
            add_user.error_message = resp.error_message;
          }
        }
      })
    }


    return {
      pull_page,
      total_users,
      current_page,
      pages,
      update_pages,
      users,
      user_name,
      user_phone,
      user_search,
      user_click_page,
      user_reset_list,
      update_user,
      remove_user,
      add_user,
      Add_user,
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
