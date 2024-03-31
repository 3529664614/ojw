package com.ojw.backend.service.impl.user.account;

import com.ojw.backend.pojo.User;
import com.ojw.backend.service.impl.utils.UserDetailsImpl;
import com.ojw.backend.service.in.user.account.LoginService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service

public class LoginServiceimpl implements LoginService {
    @Override
    public Map<String, String> login() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Map<String, String> data = new HashMap<>();
        data.put("error_message", "success");
        data.put("id", user.getId().toString());
        data.put("username", user.getUsername());
        data.put("name", user.getName());
        data.put("sex", user.getSex());
        data.put("userType", user.getUserType());
        data.put("age", user.getAge());
        return data;
    }
}
