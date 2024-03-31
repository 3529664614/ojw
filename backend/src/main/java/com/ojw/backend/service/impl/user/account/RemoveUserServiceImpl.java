package com.ojw.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.mapper.TeacherMapper;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.pojo.Teacher;
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
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        String user_id = data.get("user_id");
        User user = userMapper.selectById(user_id);
        String username = user.getUsername();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("username", username);
        studentMapper.delete(queryWrapper1);
        teacherMapper.delete(queryWrapper);
        userMapper.deleteById(user_id);

        Map<String, String> new_data = new HashMap<>();
        new_data.put("error_message", "success");
        return new_data;
    }
}
