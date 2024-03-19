package com.ojw.backend.service.impl.user.account;

import com.ojw.backend.pojo.User;
import com.ojw.backend.service.impl.utils.UserDetailsImpl;
import com.ojw.backend.service.in.user.account.InfoService;
import com.ojw.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Map<String, String> getinfo(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());
        Map<String, String> data = new HashMap<>();
        data.put("error_message", "success");
        data.put("token", jwt);
        return data;
    }
}
