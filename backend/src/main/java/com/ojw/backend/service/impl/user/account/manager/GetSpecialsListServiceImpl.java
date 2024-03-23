package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.SpecialMapper;
import com.ojw.backend.pojo.College;
import com.ojw.backend.pojo.Special;
import com.ojw.backend.service.in.user.account.manager.GetSpecialListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetSpecialsListServiceImpl implements GetSpecialListService {
    @Autowired
    private SpecialMapper specialMapper;
    @Override
    public JSONObject getSpecialList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<Special> specialIPage = new Page<>(page, 10);
        String college_name = data.get("college_name");
        QueryWrapper<Special> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("type", college_name);
        List<Special> specials = specialMapper.selectPage(specialIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        resp.put("specials", specials);
        resp.put("total_specials", specialMapper.selectCount(queryWrapper));
        return resp;
    }
}
