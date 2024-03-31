package com.ojw.backend.service.impl.user.account.student;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.service.in.user.account.student.GetStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GetStudentInfoServiceImpl implements GetStudentInfoService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JSONObject getStudentInfo(Map<String, String> data) {
        Integer id = Integer.valueOf(data.get("id"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        Student student = studentMapper.selectOne(queryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        resp.put("student_info", student);
        return resp;
    }
}
