package com.ajoufinder.api.controller.user;

import com.ajoufinder.api.controller.user.dto.request.UserCreateRequestDto;
import com.ajoufinder.api.controller.user.dto.request.UserLoginRequestDto;
import com.ajoufinder.api.service.user.UserCommandService;
import com.ajoufinder.api.service.user.UserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1/users")
public class UserController {
  private final UserCommandService userCommandService;
  private final UserQueryService userQueryService;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody @Valid UserCreateRequestDto dto) {
    userCommandService.createUser(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public void login(@RequestBody @Valid UserLoginRequestDto dto){
//  return ResponseEntity.status(HttpStatus.OK).build();
  }


}
