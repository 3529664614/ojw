package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.NoticeMapper;
import com.ojw.backend.pojo.Notice;
import com.ojw.backend.service.in.user.account.manager.GetNoticeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetNoticeListServiceImpl implements GetNoticeListService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public JSONObject getNoticeList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<Notice> noticeIPage = new Page<>(page, 10);
        JSONObject resp = new JSONObject();
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Notice> notices = noticeMapper.selectPage(noticeIPage, queryWrapper).getRecords();
        resp.put("error_message", "success");
        resp.put("notices", notices);
        resp.put("total_notices", noticeMapper.selectCount(null));
        return resp;
    }
}
