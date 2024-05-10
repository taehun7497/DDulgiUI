package com.korea.dulgiUI.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String nickname;

    private String userRole;

    private String gender; // 라디오 버튼 값 받기

    private String mobile;

    private String location;

    private String languages;

    private String birthday;

    @Lob
    private byte[] profileImage;

    public String getBase64EncodedProfileImage() {
        if (profileImage != null) {
            return Base64.getEncoder().encodeToString(profileImage);
        } else {
            // 프로필 이미지가 없는 경우 기본 이미지를 반환하거나 빈 문자열을 반환할 수 있습니다.
            return ""; // 빈 문자열 반환
        }
    }
}
