<template>
  <ContentField class="user_select">
    <template v-slot:first>
      <div style="width: 100%; color:white; font-weight: bold; font-size: 32px">学籍管理系统</div>
      <div id="accordion">
        <div class="card">
          <div class="card-header">
            <a class="btn" data-bs-toggle="collapse" href="#collapseOne">
              首页
            </a>
          </div>
        </div>
      </div>
      <div id="accordion">
        <div class="card">
          <div class="card-header">
            <a class="btn" data-bs-toggle="collapse" href="#collapseTwo">
              系统管理
            </a>
          </div>
          <div id="collapseTwo" class="collapse show" data-bs-parent="#accordion">
            <div class="card-body">
              <div>
                <router-link class="nav-link" :to="{name: 'manager_user_control'}" role="button">
                  <div class="zi">用户管理</div>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <template v-slot:second>
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">首页</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <nav aria-label="breadcrumb" style="margin-top:10px">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="#">Home</a></li>
                  <li class="breadcrumb-item"><a href="#">Library</a></li>
                </ol>
              </nav>
            </ul>
          </div>
        </div>
      </nav>
    </template>
  </ContentField>
  <slot></slot>
</template>

<script>
import ContentField from "@/components/ContentField.vue";
export default {
  name: "ManagerContent",
  components: {
    ContentField
  }
}
</script>

<style scoped>
.user_select {
  user-select: none;
}

</style>
