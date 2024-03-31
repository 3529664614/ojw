package com.ojw.backend.service.impl.user.account.manager;

import com.ojw.backend.mapper.NoticeMapper;
import com.ojw.backend.pojo.Notice;
import com.ojw.backend.service.in.user.account.manager.AddNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddNoticeServiceImpl implements AddNoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public Map<String, String> addNotice(Map<String, String> data) {
        String content = data.get("content");
        Date now = new Date();
        Map<String, String> resp = new HashMap<>();
        Notice notice = new Notice(
                null,
                content,
                now
        );
        noticeMapper.insert(notice);
        resp.put("error_message", "success");
        return resp;
    }
}
