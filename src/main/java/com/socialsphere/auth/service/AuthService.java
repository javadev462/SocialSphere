package com.socialsphere.auth.service;

import com.socialsphere.auth.dto.Request;
import com.socialsphere.auth.dto.Response;

public interface AuthService {

    Response register(Request request);
}
