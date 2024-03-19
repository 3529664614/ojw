package com.ojw.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.pojo.User;
import com.ojw.backend.service.impl.utils.UserDetailsImpl;
import com.ojw.backend.service.in.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, String> updateManager(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        Map<String, String> new_data = new HashMap<>();
        String user_id = data.get("user_id");
        User user = userMapper.selectById(user_id);
        String user_name = data.get("user_name");
        String user_phone = data.get("user_phone");
        String user_sex = data.get("user_sex");
        String user_type = data.get("user_type");
        String user_age = data.get("user_age");
        if (user_name == null) {
            new_data.put("error_message", "姓名不能为空");
            return new_data;
        }
        User user1 = new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user_name,
                user_phone,
                user_sex,
                user_age,
                user_type
        );

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        Student student = studentMapper.selectOne(queryWrapper);
        student.setAge(user_age);
        student.setSex(user_sex);
        student.setName(user_name);
        studentMapper.updateById(student);
        userMapper.updateById(user1);
        new_data.put("error_message", "success");
        return new_data;
    }
}
