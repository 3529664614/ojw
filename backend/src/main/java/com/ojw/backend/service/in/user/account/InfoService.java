package com.ojw.backend.service.in.user.account;

import java.util.Map;

public interface InfoService {
    Map<String, String> getinfo(String username, String password);
}
