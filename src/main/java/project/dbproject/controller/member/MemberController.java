package project.dbproject.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dbproject.dto.LoginRequestDto;
import project.dbproject.dto.LoginResponse;
import project.dbproject.dto.SignUpRequestDto;
import project.dbproject.service.MemberService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "https://dbproject.azurewebsites.net")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    private Map<String, String> tokenStore = new HashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto member) throws NoSuchAlgorithmException {
        if (!memberService.validateMember(member.getUserId(), member.getPassword())) {
            memberService.save(member);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginDto) throws NoSuchAlgorithmException {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();
        boolean isValid = memberService.validateMember(userId, password);
        if (isValid) {
            String token = generateToken();
            tokenStore.put(userId, token);
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 부분 제거
        }

        if (tokenStore.containsValue(token)) {
            tokenStore.remove(token);
            return ResponseEntity.ok("logout success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token) {
        if (tokenStore.containsKey(token)) {
            return ResponseEntity.ok("valid token");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        random.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }
}
