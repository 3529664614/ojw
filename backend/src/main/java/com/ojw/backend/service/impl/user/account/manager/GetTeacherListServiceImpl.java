package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.TeacherMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.pojo.Teacher;
import com.ojw.backend.service.in.user.account.manager.GetTeacherListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetTeacherListServiceImpl implements GetTeacherListService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public JSONObject getTeacherList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        String message = data.get("message");
        IPage<Teacher> teacherIPage = new Page<>(page, 10);
        String teacher_name = data.get("teacher_name");
        String teacher_college = data.get("teacher_college");
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("name", teacher_name);
        if (teacher_college.length() <= 2) {
            queryWrapper.like("college", teacher_college).or().isNull("college");
        } else {
            queryWrapper.like("college", teacher_college);
        }
        List<Teacher> teachers;
        if ("all".equals(message)) {
            teachers = teacherMapper.selectList(null);
        } else {
            teachers = teacherMapper.selectPage(teacherIPage, queryWrapper).getRecords();
        }
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        resp.put("teachers", teachers);
        resp.put("total_teachers", teacherMapper.selectCount(queryWrapper));
        return resp;
    }
}
