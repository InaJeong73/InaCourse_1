package com.ajoufinder.api.controller.user;

import com.ajoufinder.api.controller.user.dto.request.UserCreateRequestDto;
import com.ajoufinder.api.controller.user.dto.request.UserLoginRequestDto;
import com.ajoufinder.api.controller.user.dto.response.UserCreateResponseDto;
import com.ajoufinder.api.service.user.UserCommandService;
import com.ajoufinder.api.service.user.UserQueryService;
import com.ajoufinder.domain.user.entity.User;
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
  public ResponseEntity<UserCreateResponseDto> register(@RequestBody @Valid UserCreateRequestDto requestDto) {
    User user=userCommandService.createUser(requestDto);
    UserCreateResponseDto responseDto = UserCreateResponseDto.from(user);
    return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public void login(@RequestBody @Valid UserLoginRequestDto dto){
//  return ResponseEntity.status(HttpStatus.OK).build();
  }


}
