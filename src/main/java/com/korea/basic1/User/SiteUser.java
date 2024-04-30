package com.korea.basic1.User;

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

    private String usernickname;

    @Lob
    private byte[] profileImage;

    // 다른 메서드들...

    public String getBase64EncodedProfileImage() {
        if (profileImage != null) {
            return Base64.getEncoder().encodeToString(profileImage);
        } else {
            // 프로필 이미지가 없는 경우 기본 이미지를 반환하거나 빈 문자열을 반환할 수 있습니다.
            return ""; // 빈 문자열 반환
        }
    }
}
