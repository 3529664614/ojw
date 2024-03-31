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
      <input v-model="teacher_lesson_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入课程名称" aria-label=".form-control-sm example">
      <button @click="teacher_lesson_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="teacher_lesson_reset_list" type="button" class="btn btn-danger manager-first-second">
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
          <th>课程名</th>
          <th>开课年份</th>
          <th>季度</th>
          <th>班级</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="teacher_lesson in teacher_lessons" :key="teacher_lesson.id">
          <td>
            <span> {{teacher_lesson.lesson}} </span>
          </td>
          <td>
              <span>
                {{teacher_lesson.createtime}}
              </span>
          </td>
          <td>
            <span> {{teacher_lesson.season}} </span>
          </td>
          <td>
            <span> {{teacher_lesson.classroom}} </span>
          </td>
          <td>
            <button @click="import_student_info(teacher_lesson.lesson)" type="button" class="btn btn-primary " data-bs-toggle="modal" data-bs-target="#import_student_grades" >
              导入学生成绩
            </button>

            <div class="modal fade modal-lg" id="import_student_grades" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <table class="table table-striped table-hover" style="text-align: center">
                      <thead>
                        <tr>
                          <th>学生姓名</th>
                          <th>分数</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="student in students" :key="student.id">
                          <td>
                            <span>{{student.name}}</span>
                          </td>
                          <td>
                            <span>
                              <input v-model="student.grade" class="form-control form-control-sm" type="text" placeholder="请输入成绩" aria-label=".form-control-sm example">
                            </span>
                          </td>
                          <td>
                            <button @click="click_student_grade_update(student)" type="button" class="btn btn-primary" style="width: 100%">确认导入</button>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 100%">关闭</button>
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
          <li class="page-item" @click="teacher_lesson_click_page(-2)">
            <a class="page-link" href="#">前一页</a>
          </li>
          <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="teacher_lesson_click_page(page.number)">
            <a class="page-link" href="#">{{page.number}}</a>
          </li>

          <li class="page-item" @click="teacher_lesson_click_page(-1)">
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
import {ref} from "vue";
import {useStore} from "vuex";
import { Modal} from "bootstrap/dist/js/bootstrap";

export default {
  name: "TeacherLessonView",
  components: {
    ContentField
  },
  setup() {
    let pages = ref([]);
    let current_page = 1;
    let total_teacher_lessons = 0;
    let teacher_lessons = ref([]);
    const store = useStore();
    let teacher_lesson_name = ref('');
    let one = 1;
    let students = ref([]);
    let total_students = 0;

    const test = () => {
      success_prompt("操作成功！");
    }
    let prompt = function (message, style, time)
    {
      style = (style === undefined) ? 'alert-success' : style;
      time = (time === undefined) ? 1200 : time;
      $('<div id="promptModal">')
          .appendTo('body')
          .addClass('alert '+ style)
          .css({"display":"block",
            "z-index":99999,
            "left":($(document.body).outerWidth(true) - 120) / 2,
            // "top":($(window).height() - 45) / 2
            "top": "90px",
            "position": "absolute",
            "padding": "10px",
            "border-radius": "5px"})
          .html(message)
          .show()
          .delay(time)
          .fadeOut(10,function(){
            $('#promptModal').remove();
          });
    };

    let success_prompt = function(message, time)
    {
      prompt(message, 'alert-success', time);
    };

    const click_student_grade_update = (student) => {
      $.ajax({
        url: "http://localhost:3000/api/user/teacher/student/grade/update/",
        type: "post",
        data: {
          id: student.studentId,
          grade: student.grade
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          test();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    const import_student_info = (lesson) => {
      $.ajax({
        url: "http://localhost:3000/api/student_lesson/getlist/",
        type: "get",
        data: {
          page: 1,
          message: "all",
          lesson_name: lesson.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          students.value = resp.student_lessons;
          total_students = resp.total_student_lessons;
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    const teacher_lesson_search = () => {
      pull_page(one, teacher_lesson_name)
    }

    const teacher_lesson_reset_list = () => {
      teacher_lesson_name.value = "";
      pull_page(one, teacher_lesson_name);
    }
    const teacher_lesson_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_teacher_lessons / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, teacher_lesson_name);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_teacher_lessons / 10));
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
    const pull_page = (page, teacher_lesson_name) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/teacher_lesson/getlist/",
        type: "get",
        data: {
          page: page,
          teacher_name: store.state.user.name,
          lesson_name: teacher_lesson_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teacher_lessons.value = resp.teacher_lessons;
          total_teacher_lessons = resp.total_teacher_lessons;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, teacher_lesson_name);

    const update_teacher_lesson = teacher_lesson => {
      $.ajax({
        url: "http://localhost:3000/api/teacher_lesson/update/",
        type: "post",
        data: {
          teacher_lesson_id: teacher_lesson.id,
          teacher_lesson_name: teacher_lesson.name,
          teacher_lesson_phone: teacher_lesson.phone,
          teacher_lesson_sex: teacher_lesson.sex,
          teacher_lesson_age: teacher_lesson.age,
          teacher_lesson_type: teacher_lesson.teacher_lessonType,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#update_teacher_lesson_modal_" + teacher_lesson.id).hide();
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

    const remove_teacher_lesson = teacher_lesson_id => {
      $.ajax({
        url: "http://localhost:3000/api/teacher_lesson/remove/",
        type: "post",
        data: {
          teacher_lesson_id: teacher_lesson_id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            pull_page(current_page, teacher_lesson_name);
            console.log("success");
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }

    return {
      pull_page,
      total_teacher_lessons,
      current_page,
      pages,
      update_pages,
      teacher_lessons,
      teacher_lesson_name,
      teacher_lesson_search,
      teacher_lesson_click_page,
      teacher_lesson_reset_list,
      update_teacher_lesson,
      remove_teacher_lesson,
      students,
      import_student_info,
      total_students,
      click_student_grade_update,
      test,
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
