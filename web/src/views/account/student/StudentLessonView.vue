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
      <input v-model="student_lesson_name" class="form-control form-control-sm manager-first-first" type="text" placeholder="请输入课程名称" aria-label=".form-control-sm example">
      <button @click="student_lesson_search" type="button" class="btn btn-light manager-first-second">
        <div>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
          搜索
        </div>
      </button>
      <button @click="student_lesson_reset_list" type="button" class="btn btn-danger manager-first-second">
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
          <th>老师</th>
          <th>开课时间</th>
          <th>季度</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="student_lesson in student_lessons" :key="student_lesson.id">
          <td>
            <span> {{student_lesson.lesson}} </span>
          </td>
          <td>
              <span>
                {{student_lesson.teacherName}}
              </span>
          </td>
          <td>
            <span> {{student_lesson.createtime}} </span>
          </td>
          <td>
            <span> {{student_lesson.season}} </span>
          </td>
          <td>
            <button @click="remove_student_lesson(student_lesson.id)" type="button" class="btn btn-danger manager-second-first">删除</button>
          </td>
        </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination" style="float: right">
          <li class="page-item" @click="student_lesson_click_page(-2)">
            <a class="page-link" href="#">前一页</a>
          </li>
          <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="student_lesson_click_page(page.number)">
            <a class="page-link" href="#">{{page.number}}</a>
          </li>

          <li class="page-item" @click="student_lesson_click_page(-1)">
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
  name: "StudentLessonView",
  components: {
    ContentField
  },
  setup() {
    let pages = ref([]);
    let current_page = 1;
    let total_student_lessons = 0;
    let student_lessons = ref([]);
    const store = useStore();
    let student_lesson_name = ref('');
    let one = 1;
    const student_lesson_search = () => {
      pull_page(one, student_lesson_name)
    }

    const student_lesson_reset_list = () => {
      student_lesson_name.value = "";
      pull_page(one, student_lesson_name);
    }
    const student_lesson_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_student_lessons / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page, student_lesson_name);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_student_lessons / 10));
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
    const pull_page = (page, student_lesson_name) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/student_lesson/getlist/",
        type: "get",
        data: {
          page: page,
          lesson_name: student_lesson_name.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          student_lessons.value = resp.student_lessons;
          total_student_lessons = resp.total_student_lessons;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page, student_lesson_name);

    const update_student_lesson = student_lesson => {
      $.ajax({
        url: "http://localhost:3000/api/student_lesson/update/",
        type: "post",
        data: {
          student_lesson_id: student_lesson.id,
          student_lesson_name: student_lesson.name,
          student_lesson_phone: student_lesson.phone,
          student_lesson_sex: student_lesson.sex,
          student_lesson_age: student_lesson.age,
          student_lesson_type: student_lesson.student_lessonType,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            Modal.getInstance("#update_student_lesson_modal_" + student_lesson.id).hide();
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

    const remove_student_lesson = student_lesson_id => {
      $.ajax({
        url: "http://localhost:3000/api/student_lesson/remove/",
        type: "post",
        data: {
          student_lesson_id: student_lesson_id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            pull_page(current_page, student_lesson_name);
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
      total_student_lessons,
      current_page,
      pages,
      update_pages,
      student_lessons,
      student_lesson_name,
      student_lesson_search,
      student_lesson_click_page,
      student_lesson_reset_list,
      update_student_lesson,
      remove_student_lesson,
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
