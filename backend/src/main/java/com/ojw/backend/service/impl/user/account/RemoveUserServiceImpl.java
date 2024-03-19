package com.ojw.backend.service.impl.user.account;

import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.User;
import com.ojw.backend.service.impl.utils.UserDetailsImpl;
import com.ojw.backend.service.in.user.account.RemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveUserServiceImpl implements RemoveUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        String user_id = data.get("user_id");
        userMapper.deleteById(user_id);
        Map<String, String> new_data = new HashMap<>();
        new_data.put("error_message", "success");
        return new_data;
    }
}
