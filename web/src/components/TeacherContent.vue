<template>
  <ContentField>
    <template v-slot:first>
      <div id="accordion">
        <div class="card">
          <div class="card-header">
            <a class="btn" data-bs-toggle="collapse" href="#collapseOne">
              首页
            </a>
          </div>
          <div id="collapseOne" class="collapse show" data-bs-parent="#accordion">
            <div class="card-body">
              #1 内容：菜鸟教程 -- 学的不仅是技术，更是梦想！！！
            </div>
          </div>
        </div>
      </div>


    </template>
    <template v-slot:second>
      bbb
    </template>
  </ContentField>
</template>

<script>
import ContentField from "@/components/ContentField.vue";
export default {
  name: "TeacherContent",
  components: {
    ContentField
  }
}
</script>

<style scoped>

</style>