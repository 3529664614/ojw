package com.ojw.backend.consumer;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.consumer.utils.JwtAuthentication;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.OffMessage;
import com.ojw.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {

    final public static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
//    final private static ConcurrentHashMap<Integer, List<OffMessage>> offAllMessageList = new ConcurrentHashMap();
    private Session session = null;
    private User user;
    public static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.session = session;
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);

        if (this.user != null) {
            users.put(userId, this);
        } else {
            this.session.close();
        }
//        if (offAllMessageList.containsKey(userId)) {
//            List<OffMessage> offMessages = offAllMessageList.get(userId);
//            Iterator it = offMessages.iterator();
//            while (it.hasNext()) {
//                OffMessage message = (OffMessage) it.next();
//                sendOffAllMessage(message);
//            }
//            offMessages.remove(userId);
//        }
    }

//    private void sendOffAllMessage(OffMessage message) {
//        JSONObject resp = new JSONObject();
//        resp.put("id", message.getId());
//        resp.put("toId", message.getToId());
//        resp.put("chatContent", message.getChatContent());
//        sendMessage(String.valueOf(resp));
//    }

    @OnClose
    public void onClose() {
        if (this.user != null) {
            users.remove(this.user.getId());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        sendUserMessage(message);
    }

    private void sendUserMessage(String message) {
        OffMessage data = JSON.parseObject(message, OffMessage.class);
        Integer id = data.getId();
        Integer toId = data.getToId();
        String chatContent = data.getChatContent();
        JSONObject resp = new JSONObject();
        if (users.containsKey(toId)) {
            resp.put("id", id);
            resp.put("toId", toId);
            resp.put("chatContent", chatContent);
            users.get(toId).sendMessage(String.valueOf(resp));
        }
//        else {
//            if (offAllMessageList.containsKey(toId)) {
//                List<OffMessage> list = offAllMessageList.get(toId);
//                list.add(data);
//                offAllMessageList.put(toId, list);
//            } else {
//                offAllMessageList.put(toId, ListUtil.toList(data));
//            }
//        }
//        System.out.println(offAllMessageList);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
