package com.ajoufinder.domain.user.entity;

import com.ajoufinder.domain.TimeStamp;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.user.entity.constant.Role;
import com.ajoufinder.domain.user.entity.constant.UserCategory;
import com.ajoufinder.domain.user.entity.constant.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeStamp {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="user_id")
  private Long userId;

  @Column(nullable = false,length=50)
  private String name;

  @Column(nullable = false,unique = true, length=30)
  private String nickname;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(name = "email_verified", nullable = false)
  private boolean emailVerified;

  @Column(nullable=false,unique=true,length=100)
  private String password;

  @Column(length=50)
  private String major;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private UserCategory category;

  @Column(length=500)
  private String description;

  @Column(name="profile_image_url",length=200)
  private String profileImageUrl;

  @Column(name="phone_number",length=15)
  private String phoneNumber;

  private Integer age;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private UserStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private Role role;

  @Column(name="refresh_token",length=500)
  private String refreshToken;

  @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Board>boards=new ArrayList<>();

  @Builder
  public User(String name,String email, String password, String nickname, String major, UserCategory category,UserStatus status, Role role, String refreshToken){
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.major =major;
    this.category=category;
    this.status=status;
    this.role=role;
    this.refreshToken=refreshToken;
  }

  public void passwordEncode(PasswordEncoder passwordEncoder){
    this.password=passwordEncoder.encode(this.password);
  }
}
