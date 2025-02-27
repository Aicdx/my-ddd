package com.acid.myddd.user.interfaces.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acid.myddd.user.application.command.UserCommandService;
import com.acid.myddd.user.application.query.UserQueryService;
import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.interfaces.dto.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserCommandService commandService;
    private final UserQueryService queryService;

    public UserController(UserCommandService commandService, UserQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        User user = commandService.createUser(
            request.getUsername(),
            request.getEmail(),
            request.getPassword()
        );
        return queryService.findById(user.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
        return queryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<UserDTO> updateUserEmail(
            @PathVariable UUID id,
            @RequestBody UpdateEmailRequest request) {
        User updatedUser = commandService.updateUserEmail(id, request.getEmail());
        return queryService.findById(updatedUser.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        commandService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
} 