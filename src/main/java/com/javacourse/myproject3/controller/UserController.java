package com.javacourse.myproject3.controller;

import com.javacourse.myproject3.dto.*;
import com.javacourse.myproject3.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserSummaryResponse> create(@RequestBody CreateUserRequest req) throws IOException {
        UserSummaryResponse resp = userService.create(req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        boolean d = detail != null && detail;
        if (d) {
            UserDetailResponse resp = userService.getById(id, true);
            return ResponseEntity.ok(resp);
        }
        UserDetailResponse resp = userService.getById(id, false);
        // adapt to return only id,name,surname
        return ResponseEntity.ok(new UserSummaryResponse(resp.getId(), resp.getName(), resp.getSurname()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(@RequestParam(required = false) Boolean detail) {
        boolean d = detail != null && detail;
        List<UserResponse> list = userService.getAll(d);
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<UserSummaryResponse> update(@RequestBody UpdateUserRequest req) {
        UserSummaryResponse resp = userService.update(req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
