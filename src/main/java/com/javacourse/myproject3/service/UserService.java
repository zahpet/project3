package com.javacourse.myproject3.service;

import com.javacourse.myproject3.dto.*;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserSummaryResponse create(CreateUserRequest req) throws IOException;
    UserDetailResponse getById(Long id, boolean detail);
    List<UserResponse> getAll(boolean detail);
    UserSummaryResponse update(UpdateUserRequest req);
    void delete(Long id);
}
