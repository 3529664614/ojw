<template>
  <ContentField>

    <div style="padding: 10px; margin-bottom: 50px">
      <div class="row">
        <div class="col-4">
          <div class="card" style="width: 100%; min-height: 400px; color: #333">
            <div style="padding-bottom: 10px; border-bottom: 1px solid #ccc; font-size: 24px; font-weight: bold">用户列表<span style="font-size: 24px">（点击聊天气泡开始聊天）</span></div>
            <div v-for="user in users" :key="user.id">
              <div class="card">
                <div class="card-body">
                  <div style="padding: 10px 0">
                  <span style="margin-left: 10px; font-size: 20px;">{{ user.name }}</span>
                  <i class="el-icon-chat-dot-round" style="margin-left: 10px; font-size: 20px; cursor: pointer"
                     @click="updateChatUser(user)"></i>
                  <span style="font-size: 20px;color: limegreen; margin-left: 5px" v-if="user.id == chatUser.id">chatting...</span>
                </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="isOpen === '1'" class="col-8">
          <div style="width: 800px; margin: 0 auto; background-color: white;
                      border-radius: 5px; box-shadow: 0 0 10px #ccc">
            <div style="line-height: 50px; margin-left: 20px; font-size: 30px; font-weight: bold">
              {{ chatUser.name }}
            </div>
            <div id="showAllMessage" style="height: 350px; overflow:auto; border-top: 1px solid #ccc" v-html="allContent"></div>
            <div style="height: 200px">
              <textarea v-model="chatContent" style="height: 160px; width: 100%; padding: 20px; border: none; border-top: 1px solid #ccc;
               border-bottom: 1px solid #ccc; outline: none"></textarea>
              <div style="text-align: right; padding-right: 10px">
                <button @click="sendSomeMessage" type="button" class="btn btn-primary" >发送</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ContentField>
</template>
<script>
import ContentField from '@/components/ContentField.vue';
import {useStore} from "vuex";
import {onMounted, onUnmounted, reactive, ref} from "vue";
import 'element-ui/lib/theme-chalk/index.css';
import $ from 'jquery';

export default {
  name: "ChatControlView",
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    const socketUrl = `ws://localhost:3000/websocket/${store.state.user.token}/`;
    let socket = null;
    let users = ref([]);
    let chatContent = ref('');
    let chatUser = reactive({
      id: "",
      name: "",
    })
    let allContent = ref('');
    let message = ref('');
    let isOpen = ref('0');
    const updateChatUser = user => {
      chatUser.id = user.id;
      chatUser.name = user.name;
      isOpen.value = '1';
      allContent.value = "";
    }

    const sendSomeMessage = () => {
      socket.send(JSON.stringify({
        id: store.state.user.id,
        toId: chatUser.id,
        chatContent: chatContent.value,
      }));
      createContent(null, chatUser.name, chatContent.value);
      chatContent.value = "";
    }

    const createContent = (remoteUser, nowUser, text) => {  // 这个方法是用来将 json的聊天消息数据转换成 html的。
      let html;
      // 当前用户消息
      if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
            "    <div class=\"tip left\">" + text + "</div>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-2\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "</div>";
      } else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
            "    <div class=\"tip right\">" + text + "</div>\n" +
            "  </div>\n" +
            "</div>";
      }
      allContent.value += html;
      let div = document.getElementById('showAllMessage');
      div.scrollTop = div.scrollHeight;
      setTimeout(() => {
        div.scrollTop = div.scrollHeight;
      }, 20);

    }
    $.ajax({
      url: "http://localhost:3000/api/user/chat/getlist/",
      type: "get",
      data: {
        user_type: "管理员",
      },
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        users.value = resp.users;
        users.value = users.value.filter(user => user.id != store.state.user.id);
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    })
    onMounted(() => {
      console.log("aza");
      socket = new WebSocket(socketUrl);
      socket.onopen = () => {
        console.log("connected!");
      }
      socket.onmessage = msg => {
        let data = JSON.parse(msg.data);
        if (data.id == chatUser.id) {
          createContent(chatUser.id, null, data.chatContent);
        }
      }
      socket.onclose = () => {
        console.log("disconnected!");
      }
    })
    onUnmounted(() => {
      socket.close();
    })

    return {
      socket,
      socketUrl,
      users,
      chatUser,
      updateChatUser,
      isOpen,
      sendSomeMessage,
      chatContent,
      message,
      createContent,
      allContent,
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
