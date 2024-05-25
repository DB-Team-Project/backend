package project.dbproject.dto;

import lombok.Getter;
import project.dbproject.domain.Member;

@Getter
public class SignUpRequestDto {
    private final String userId;
    private final String password;

    public SignUpRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Member toEntity() {
        Member member = new Member();
        member.setUserId(this.userId);
        member.setPassword(this.password);
        return member;
    }
}
