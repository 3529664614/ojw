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
      <input v-model="curriculum_lesson" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入课程名" aria-label=".form-control-sm example">
      <input v-model="curriculum_teacher_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入老师姓名" aria-label=".form-control-sm example">
      <button @click="curriculum_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="curriculum_reset_list" type="button" class="btn btn-danger manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207l-5-5-5 5V13.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7.207Z"/>
          </svg>
          重置
        </div>
      </button>
      <button @click="edit_curriculum_edit" type="button" class="btn btn-primary manager-first-second" data-bs-toggle="modal" data-bs-target="#add_curriculum_modal">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5a.5.5 0 0 0-1 0v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1Z"/>
            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
          </svg>
          新增
        </div>
      </button>
      <div class="modal fade" id="add_curriculum_modal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5">新增课程安排</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="modal-body">
                <div class="mb-3">
                  <label for="update_user_phone" class="form-label">课程名</label>
                  <input v-model="add_curriculum.lesson" type="text" class="form-control" id="update_user_phone" placeholder="请输入课程名">
                </div>
                <div class="mb-3">
                  <label class="form-label">老师</label>
                  <select v-model="add_curriculum.teacher" class="form-select" aria-label="Default select example">
                    <option v-for="teacher in teachers" :key="teacher.id" :value="teacher.name + '*' + teacher.id">{{teacher.name}}&nbsp;&nbsp;{{teacher.college}}</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="update_user_createtime" class="form-label">开课时间</label>
                  <input v-model="add_curriculum.createtime" type="date" class="form-control" id="update_user_createtime" placeholder="请输入开课时间">
                </div>
                <div class="mb-3">
                  <label for="update_curriculum_age" class="form-label">授课班级</label>
                  <input v-model="add_curriculum.classroom" type="text" class="form-control" id="update_curriculum_age" placeholder="请输入授课班级">
                </div>
                <div class="mb-3">
                  <label class="form-label">季度</label>
                  <select v-model="add_curriculum.season" class="form-select" aria-label="Default select example">
                    <option value="春季">春季</option>
                    <option value="秋季">秋季</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="update_curriculum_awarded" class="form-label">课程描述</label>
                  <input v-model="add_curriculum.content" type="text" class="form-control" id="update_curriculum_awarded" placeholder="请输入课程描述">
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <div class="error_message">{{add_curriculum.error_message}}</div>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button @click="Add_curriculum" type="button" class="btn btn-primary">确认</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="manager-second">
      <table class="table table-striped table-hover" style="text-align: center">
        <thead>
        <tr>
          <th>课程名</th>
          <th>老师</th>
          <th>开课日期</th>
          <th>授课班级</th>
          <th>季度</th>
          <th>课程描述</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="curriculum in curriculums" :key="curriculum.id">
          <td>
            <span> {{curriculum.lesson}} </span>
          </td>
          <td>
            <span> {{curriculum.teacher}} </span>
          </td>
          <td>
            <span> {{curriculum.createtime}} </span>
          </td>
          <td>
            <span> {{curriculum.classroom}} </span>
          </td>
          <td>
            <span> {{curriculum.season}} </span>
          </td>
          <td>
            <span> {{curriculum.content}} </span>
          </td>
          <td>
            <button @click="edit_curriculum_edit" type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#update_curriculum_modal_' + curriculum.id">
              编辑
            </button>

            <div class="modal fade" :id="'update_curriculum_modal_' + curriculum.id" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">编辑页面</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="update_user_phone" class="form-label">课程名</label>
                      <input v-model="curriculum.lesson" type="text" class="form-control" id="update_user_phone" placeholder="请输入课程名">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">老师</label>
                      <select v-model="curriculum.teacher" class="form-select" aria-label="Default select example">
                        <option v-for="teacher in teachers" :key="teacher.id" :value="teacher.name">{{teacher.name}}&nbsp;&nbsp;{{teacher.college}}</option>
                      </select>
                    </div>
                    <div class="mb-3">
                      <label class="update_user_createtime">开课日期</label>
                      <input v-model="curriculum.createtime" type="date" class="form-control" id="update_user_createtime" placeholder="请输入开课日期">
                    </div>

                    <div class="mb-3">
                      <label for="update_curriculum_age" class="form-label">授课班级</label>
                      <input v-model="curriculum.classroom" type="text" class="form-control" id="update_curriculum_age" placeholder="请输入授课班级">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">季度</label>
                      <select v-model="curriculum.season" class="form-select" aria-label="Default select example">
                        <option value="春季">春季</option>
                        <option value="秋季">秋季</option>
                      </select>
                    </div>

                    <div class="mb-3">
                      <label for="update_curriculum_awarded" class="form-label">课程描述</label>
                      <input v-model="curriculum.content" type="text" class="form-control" id="update_curriculum_awarded" placeholder="请输入课程描述">
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button @click="update_curriculum(curriculum)" type="button" class="btn btn-primary">保存修改</button>
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
import {reactive, ref} from "vue";
import {useStore} from "vuex";
import {Modal} from 'bootstrap/dist/js/bootstrap';
export default {
  name: "ManagerCurriculumControlView",
  components: {
    ContentField,
  },
  setup() {
    let pages = ref([]);
    let current_page = 1;
    let total_curriculums = 0;
    let curriculums = ref([]);
    const store = useStore();
    let curriculum_lesson = ref('');
    let curriculum_teacher_name = ref('');
    let one = 1;
    let teachers = ref([]);
    let add_curriculum = reactive({
      lesson: "",
      teacher: "",
      classroom: "",
      season: "",
      content: "",
      createtime: "",
      error_message: "",
    })

    const Add_curriculum = () => {
      const teacher = add_curriculum.teacher.split('*');
      $.ajax({
        url: "http://localhost:3000/api/user/curriculum/add/",
        type: "post",
        data: {
          lesson: add_curriculum.lesson,
          teacher_id: teacher[1],
          teacher: teacher[0],
          classroom: add_curriculum.classroom,
          season: add_curriculum.season,
          content: add_curriculum.content,
          createtime: add_curriculum.createtime,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#add_curriculum_modal").hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
            pull_page(one, "", "");
          } else {
            add_curriculum.error_message.value = resp.error_message;
          }
        },
        error() {
          add_curriculum.error_message.value = "失败";
        }
      })
    }

    const edit_curriculum_edit = () => {
      $.ajax({
        url: "http://localhost:3000/api/user/teacher/getlist/",
        type: "get",
        data: {
          page: 1,
          message: "all",
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teachers.value = resp.teachers;
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    const curriculum_search = () => {
      pull_page(one, curriculum_lesson, curriculum_teacher_name);
    }

    const curriculum_reset_list = () => {
      curriculum_lesson.value = "";
      curriculum_teacher_name.value = "";
      pull_page(one, curriculum_lesson, curriculum_teacher_name);
    }
    const user_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_curriculums / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, curriculum_lesson, curriculum_teacher_name);
      }
    }
    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_curriculums / 10));
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
    const pull_page = (page, curriculum_lesson, curriculum_teacher_name) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/curriculum/getlist/",
        type: "get",
        data: {
          page: page,
          curriculum_lesson: curriculum_lesson.value,
          curriculum_teacher_name: curriculum_teacher_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          curriculums.value = resp.curriculums;
          console.log(resp);
          total_curriculums = resp.curriculums_count;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, curriculum_lesson, curriculum_teacher_name);

    const update_curriculum = curriculum => {
      $.ajax({
        url: "http://localhost:3000/api/user/curriculum/update/",
        type: "post",
        data: {
          curriculum_id: curriculum.id,
          curriculum_teacher_id: curriculum.teacherId,
          curriculum_lesson: curriculum.lesson,
          curriculum_teacher: curriculum.teacher,
          curriculum_season: curriculum.season,
          curriculum_classroom: curriculum.classroom,
          curriculum_content: curriculum.content,
          curriculum_createtime: curriculum.createtime,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            Modal.getInstance("#update_curriculum_modal_" + curriculum.id).hide();
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
      curriculum_lesson,
      curriculum_teacher_name,
      one,
      total_curriculums,
      current_page,
      update_pages,
      user_click_page,
      curriculums,
      curriculum_search,
      update_curriculum,
      curriculum_reset_list,
      teachers,
      edit_curriculum_edit,
      add_curriculum,
      Add_curriculum,
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
