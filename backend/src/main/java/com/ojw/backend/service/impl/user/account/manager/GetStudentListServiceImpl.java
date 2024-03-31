package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.service.in.user.account.manager.GetStudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetStudentListServiceImpl implements GetStudentListService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JSONObject getStudentList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<Student> studentIPage = new Page<>(page, 10);
        String student_name = data.get("student_name");
        String student_college = data.get("student_college");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("name", student_name);
        queryWrapper.like("college", student_college);
        List<Student> students = studentMapper.selectPage(studentIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("students", students);
        resp.put("students_count", studentMapper.selectCount(queryWrapper));
        return resp;
    }
}
