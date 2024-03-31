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
    <button v-if="$store.state.user.userType === '管理员'" type="button" class="btn btn-primary" style="margin-left: 11px; margin-bottom: 10px;" data-bs-toggle="modal" data-bs-target="#add_notice_modal">
      <div>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5a.5.5 0 0 0-1 0v1h-1a.5.5 0 0 0 0 1h1v1a.5.5 0 0 0 1 0v-1h1a.5.5 0 0 0 0-1h-1v-1Z"/>
          <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
        </svg>
        新增公告
      </div>
    </button>

    <div class="modal fade" id="add_notice_modal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5">新增公告</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label for="update_user_name" class="form-label">内容</label>
              <input v-model="add_notice_content" type="text" class="form-control" id="update_user_name" placeholder="请输入学号或工号">
            </div>
            <div class="mb-3">
              <button @click="add_notice" type="button" class="btn btn-primary" style="width: 100%">确认</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div style="padding: 10px; margin-bottom: 50px">
        <div class="card" style="width: 100%; color: #333">
          <div v-for="notice in notices" :key="notice.id">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-10">
                    <div>
                      {{notice.content}}
                    </div>
                  </div>
                  <div class="col-2">
                    {{notice.time}}
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
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
  </ContentField>
</template>
<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import {ref} from "vue";
import {useStore} from "vuex";
import {Modal} from "bootstrap/dist/js/bootstrap";

export default {
  name: "NoticeControlView",
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let notices = ref([]);
    let current_page = 1;
    let total_notices = 0;
    let pages = ref([]);
    let add_notice_content = ref('');

    const add_notice = () => {
      $.ajax({
        url: "http://localhost:3000/api/user/notice/add/",
        type: "post",
        data: {
          content: add_notice_content.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            Modal.getInstance("#add_notice_modal").hide();
            const myModal = new Modal('#edit_modal');
            myModal.show();
            pull_page(1);
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    const user_click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_page = parseInt(Math.ceil(total_notices / 10));
      if (page >= 1 && page <= max_page) {
        pull_page(page);
      }
    }
    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_notices / 10));
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
    const pull_page = page => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/user/notice/getlist/",
        type: "get",
        data: {
          page: page,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === 'success') {
            notices.value = resp.notices;
            total_notices = resp.total_notices;
            update_pages();
            console.log(resp);
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    pull_page(current_page);

    return {
      notices,
      pull_page,
      update_pages,
      pages,
      total_notices,
      user_click_page,
      add_notice_content,
      add_notice,
    }
  }
}
</script>
<style>
.tip {
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
  padding: 10px;
  width:auto;
  display:inline-block !important;
  display:inline;
}
.right {
  background-color: deepskyblue;
}
.left {
  background-color: forestgreen;
}
</style>
