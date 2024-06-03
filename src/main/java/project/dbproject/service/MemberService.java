package project.dbproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dbproject.domain.Member;
import project.dbproject.dto.SignUpRequestDto;
import project.dbproject.repository.MemberRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(final SignUpRequestDto dto) throws NoSuchAlgorithmException {
        Member member = dto.toEntity();
        member.setPassword(hashPassword(member.getPassword()));
        memberRepository.save(member);
    }

    public Long findMemberIdByMemberName(final String memberId) {
        Optional<Member> member = memberRepository.findByUserId(memberId);
        return member.map(Member::getId).orElse(null);
    }

    public String findByMemberName(final String userId) {
        return memberRepository.findByUserId(userId).get().getUserId();
    }

    public boolean validateMember(String userId, String password) {
        Optional<Member> member = memberRepository.findByUserId(userId);
        if (member.isPresent()) {
            String hashedPassword;
            try {
                hashedPassword = hashPassword(password);
            } catch (NoSuchAlgorithmException e) {
                return false;
            }
            return member.get().getPassword().equals(hashedPassword);
        } else {
            return false;
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
