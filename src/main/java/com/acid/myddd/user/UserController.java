package com.acid.myddd.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acid.myddd.user.command.CreateUserCommand;
import com.acid.myddd.user.command.UpdateEmailCommand;
import com.acid.myddd.user.command.UserCommandHandler;
import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.query.UserQueryService;
import com.acid.myddd.user.query.dto.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserCommandHandler commandHandler;
    private final UserQueryService queryService;

    public UserController(UserCommandHandler commandHandler, UserQueryService queryService) {
        this.commandHandler = commandHandler;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserCommand command) {
        User user = commandHandler.handle(command);
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
            @RequestBody UpdateEmailCommand command) {
        User updatedUser = commandHandler.handle(id, command);
        return queryService.findById(updatedUser.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        commandHandler.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
} 