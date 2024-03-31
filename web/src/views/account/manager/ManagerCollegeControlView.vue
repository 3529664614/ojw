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
      <input v-model="college_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入学院" aria-label=".form-control-sm example">
      <button @click="college_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="college_reset_list" type="button" class="btn btn-danger manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207l-5-5-5 5V13.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7.207Z"/>
          </svg>
          重置
        </div>
      </button>
      <button type="button" class="btn btn-primary manager-first-second" data-bs-toggle="modal" data-bs-target="#add_college_modal">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5a.5.5 0 0 0-1 0v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1Z"/>
            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
          </svg>
          新增学院
        </div>
      </button>

      <div class="modal fade" id="add_college_modal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5">新增学院</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <label for="add_college_name" class="form-label">学院</label>
                <input v-model="add_college_name" type="text" class="form-control" id="add_college_name" placeholder="请输入学院">
              </div>
            </div>
            <div class="modal-footer">
              <div class="error-message">{{ add_college_error_message }}</div>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button @click="add_college_type" type="button" class="btn btn-primary">确认</button>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="manager-second">
      <table class="table table-striped table-hover" style="text-align: center">
        <thead>
        <tr>
          <th>学院</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="college in colleges" :key="college.id">
          <td>
            <span> {{college.type}} </span>
          </td>
          <td>
            <button @click="click_special_info(college.type)" type="button" class="btn btn-secondary manager-second-first" data-bs-toggle="modal" :data-bs-target="'#college_info_modal' + college.id" style="margin-right: 20px">
              查看所有专业
            </button>
            <div class="modal fade" :id="'college_info_modal' + college.id" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">所有专业</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <table class="table table-striped table-hover" style="text-align: center">
                      <thead>
                        <tr>
                          <th>专业</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="special in specials" :key="special.id">
                          <td>
                            <span>{{special.special}}</span>
                          </td>
                        </tr>
                      </tbody>
                      <nav aria-label="Page navigation example">
                        <ul class="pagination" style="float: right">
                          <li class="page-item" @click="special_click_page(-2, college.type)">
                            <a class="page-link" href="#">前一页</a>
                          </li>
                          <li :class="'page-item ' + page.is_active" v-for="page in special_pages" :key="page.number" @click="special_click_page(page.number, college.type)">
                            <a class="page-link" href="#">{{page.number}}</a>
                          </li>
                          <li class="page-item" @click="special_click_page(-1, college.type)">
                            <a class="page-link" href="#">后一页</a>
                          </li>
                        </ul>
                      </nav>
                    </table>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 100%;">关闭</button>
                  </div>
                </div>
              </div>
            </div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#add_college_special_modal_' + college.id" style="margin-right: 20px">
              新增专业
            </button>

            <div class="modal fade" :id="'add_college_special_modal_' + college.id" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">新增专业</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="add_college_special_name" class="form-label">专业</label>
                      <input v-model="Add_college_special_name" type="text" class="form-control" id="add_college_special_name" placeholder="请输入专业">
                    </div>
                  </div>
                  <div class="modal-footer">
                    <div class="error-message">{{ add_college_special_message }}</div>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button @click="add_college_special(college.type, college.id)" type="button" class="btn btn-primary">确认</button>
                  </div>
                </div>
              </div>
            </div>
            <button @click="remove_college_type(college.type)" type="button" class="btn btn-danger">删除</button>
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
import {ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";
import {Modal} from "bootstrap/dist/js/bootstrap";

export default {
  name: "ManagerCollegeControlView",
  components: {
    ContentField,
  },
  setup() {
    let pages = ref([]);
    let special_pages = ref([]);
    let special_current_page = 1;
    let total_specials = 0;
    let current_page = 1;
    let total_colleges = 0;
    let colleges = ref([]);
    const store = useStore();
    let college_name = ref('');
    let specials = ref([]);
    let one = 1;
    let add_college_name = ref('');
    let add_college_error_message = ref('');
    let Add_college_special_name = ref('');
    let add_college_special_message = ref('');

    const remove_college_type = (college_type) => {
      $.ajax({
        url: "http://localhost:3000/api/user/college/remove/",
        type: "post",
        data: {
          college_type: college_type,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            console.log(resp);
            pull_page(one, "");
          }
        },
      })
    }
    const add_college_special = (college_type, id) => {
      add_college_special_message.value = "";
      $.ajax({
        url: "http://localhost:3000/api/user/college/special/add/",
        type: "post",
        data: {
          college_type: college_type,
          special: Add_college_special_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            Modal.getInstance('#add_college_special_modal_' + id).hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
          } else {
            add_college_error_message.value = resp.error_message;
          }
        },
        error() {
          add_college_error_message.value = "专业名不能为空";
        }
      })
    }

    const college_search = () => {
      pull_page(one, college_name);
    }

    const college_reset_list = () => {
      college_name.value = "";
      pull_page(one, college_name);
    }

    const add_college_type = () => {
      add_college_error_message.value = "";
      $.ajax({
        url: "http://localhost:3000/api/user/college/add/",
        type: "post",
        data: {
          college_name: add_college_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            add_college_name.value = "";
            Modal.getInstance("#add_college_modal").hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
            pull_page(one, "");
          } else {
            add_college_error_message.value = "学院名不能为空";
          }
        },
        error() {
          add_college_error_message.value = "学院名不能为空";
        }
      })
    }

    const user_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_colleges / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, college_name);
      }
    }

    const click_special_info = type => {
      special_pull_page(special_current_page, type);
    }
    const special_click_page = (page, college_name) => {
      console.log(page, college_name);
      if (page === -2) page = special_current_page - 1;
      else if (page === -1) page = special_current_page + 1;
      let max_page = parseInt(Math.ceil(total_specials / 10));
      if (page >= 1 && page <= max_page) {
        special_pull_page(page, college_name);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_colleges / 10));
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
    const special_update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_specials / 10));
      let new_pages = [];
      for (let i = special_current_page - 2; i <= special_current_page + 2; ++ i) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === special_current_page ? "active" : "",
          });
        }
      }
      special_pages.value = new_pages;
    }
    const pull_page = (page, college_name) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/college/getlist/",
        type: "get",
        data: {
          page: page,
          college_name: college_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          colleges.value = resp.colleges;
          total_colleges = resp.total_colleges;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, college_name);
    const special_pull_page = (page, college_name) => {
      special_current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/special/getlist/",
        type: "get",
        data: {
          page: page,
          college_name: college_name,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            console.log(resp);
            specials.value = resp.specials;
            total_specials = resp.total_specials;
            special_update_pages();
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    return {
      pages,
      current_page,
      total_colleges,
      college_name,
      one,
      colleges,
      store,
      pull_page,
      user_click_page,
      update_pages,
      specials,
      special_pull_page,
      special_current_page,
      special_click_page,
      special_update_pages,
      special_pages,
      total_specials,
      click_special_info,
      add_college_name,
      add_college_type,
      add_college_error_message,
      college_search,
      college_reset_list,
      add_college_special_message,
      add_college_special,
      Add_college_special_name,
      remove_college_type,
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

.error-message {
  color: red;
}

</style>
