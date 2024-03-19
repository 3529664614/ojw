package com.ojw.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.pojo.User;
import com.ojw.backend.service.impl.utils.UserDetailsImpl;
import com.ojw.backend.service.in.user.account.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddUserServiceImpl implements AddUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, String> adduser(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        Map<String, String> new_data = new HashMap<>();
        String user_username = data.get("user_username");
        String user_phone = data.get("user_phone");
        String user_name = data.get("user_name");
        String user_sex = data.get("user_sex");
        String user_password = data.get("user_password");
        String user_confirmedPassword = data.get("user_confirmedPassword");
        String user_type = data.get("user_type");
        String user_age = data.get("user_age");
        if (user_username == null) {
            new_data.put("error_message", "学号或者工号不能为空");
            return new_data;
        }
        if (user_password == null || user_confirmedPassword == null) {
            new_data.put("error_message", "密码不能为空");
            return new_data;
        }
        user_username.trim();
        if (user_username.length() ==  0) {
            new_data.put("error_message", "学号或工号不能为空");
            return new_data;
        }
        if (user_password.length() == 0 || user_confirmedPassword.length() == 0) {
            new_data.put("error_message", "密码不能为空");
            return new_data;
        }
        if (user_username.length() > 20) {
            new_data.put("error_message", "学号或者工号长度不能超过20");
            return new_data;
        }
        if (user_password.length() > 100 || user_confirmedPassword.length() > 100) {
            new_data.put("error_message", "密码长度不能超过100");
            return new_data;
        }
        if (!user_password.equals(user_confirmedPassword)) {
            new_data.put("error_message", "两次输入的密码不一致");
            return new_data;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user_username);
        User user_one = userMapper.selectOne(queryWrapper);
        if (user_one != null) {
            new_data.put("error_message", "学号或者工号已经存在");
            return new_data;
        }
        user_password = passwordEncoder.encode(user_password);
        User user = new User(
                null,
                user_username,
                user_password,
                user_name,
                user_phone,
                user_sex,
                user_age,
                user_type
        );
        userMapper.insert(user);
        if (user_type.equals("学生")) {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            calendar.add(Calendar.YEAR, 4);
            Date end = calendar.getTime();
            Student student = new Student(
                    null,
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    user_sex,
                    user_age,
                    now,
                    end,
                    "未填",
                    "11",
                    "无",
                    "未填",
                    "未填"
            );
            studentMapper.insert(student);

        }
        new_data.put("error_message", "success");
        return new_data;
    }
}
