package com.ajoufinder.api.controller.auth;

import com.ajoufinder.api.controller.auth.dto.request.EmailVerificationRequestDto;
import com.ajoufinder.api.controller.auth.dto.request.VerificationCodeRequestDto;
import com.ajoufinder.api.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/email/verify")
  public ResponseEntity<String> verifyEmail(@Valid @RequestBody EmailVerificationRequestDto requestDto) {
    authService.sendVerificationCode(requestDto.email());
    return ResponseEntity.ok("인증 이메일이 발송되었습니다.");
  }

//  @PostMapping("/email/complete-registration")
//  public ResponseEntity<String> verifyCode(@RequestParam String token, @RequestParam String email) {
//    authService.completeEmailVerification(email, token);
//    return ResponseEntity.ok("이메일 인증이 완료되었습니다.);
//  }

  @GetMapping("/nickname/check")
  public ResponseEntity<String> verifyNickname(@RequestParam String nickname) {
    authService.verifyNickname(nickname);
    return ResponseEntity.ok("사용가능한 닉네임입니다.");
  }
}
