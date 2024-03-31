<template>
  <ContentField>
    <div class="manager-featured-articles">
      <h2>我的服务</h2>
      <div class="manager-tile-container">
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'notice_control'}" style="color: white; font-size: 24px">通知公告</router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'user_control'}" style="color: white; font-size: 24px">
                用户管理
              </router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'manager_college_control'}" style="color: white; font-size: 24px">学院和专业管理</router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'manager_student_control'}" style="color: white; font-size: 24px">学生管理</router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'manager_teacher_control'}" style="color: white; font-size: 24px">教师管理</router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'manager_curriculum_control'}" style="color: white; font-size: 24px">课程管理</router-link>
            </button>
          </a>
        </div>
        <div class="manager-article-tile">
          <a href="#" class="manager-tile-tag">
            <button type="button" class="btn btn-primary" style="width: 100%; height: 100%">
              <router-link :to="{name: 'chat_control'}" style="color: white; font-size: 24px">客服聊天</router-link>
            </button>
          </a>
        </div>
      </div>
    </div>
  </ContentField>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
export default {
  name: "ManagerServiceView",
  components: {
    ContentField
  }
}
</script>

<style scoped>
.manager-featured-articles {
}
.manager-tile-container {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  grid-auto-flow: row;
  margin-top: 40px;
  /*max-width: 52rem;*/
}
.manager-tile-container .manager-article-tile {
  border: 1px solid #bf2424;
  border-radius: 0.5rem;
  /*display: flex;*/
  /*flex-direction: column;*/
  /*padding: 1rem;*/
  width: 150px;
  height: 150px;
}
*, :after, :before {
  box-sizing: border-box;
}
a {
  text-decoration: none;
}
</style>
