package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.CollegeMapper;
import com.ojw.backend.pojo.College;
import com.ojw.backend.service.in.user.account.manager.GetCollegeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetCollegeListServiceImpl implements GetCollegeListService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public JSONObject getCollegeList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<College> collegeIPage = new Page<>(page, 10);
        String college_name = data.get("college_name");
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("type", college_name);
        List<College> colleges = collegeMapper.selectPage(collegeIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("colleges", colleges);
        resp.put("colleges_count", collegeMapper.selectCount(queryWrapper));
        return resp;
    }
}
