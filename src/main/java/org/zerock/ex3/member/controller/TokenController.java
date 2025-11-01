package org.zerock.ex3.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex3.member.dto.MemberDTO;
import org.zerock.ex3.member.security.util.JWTUtil;
import org.zerock.ex3.member.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/token")
@Log4j2
@RequiredArgsConstructor
public class TokenController {

    private final MemberService memberService;

    private final JWTUtil jwtUtil;

    @PostMapping("/make")
    public ResponseEntity<Map<String, String>> makeToken(@RequestBody MemberDTO memberDTO) {
        log.info("make token............");

        //해당 사용자가 있는지 확인
        MemberDTO memberDTOResult = memberService.read(memberDTO.getMid(), memberDTO.getMpw());

        log.info(memberDTOResult);

        String mid = memberDTOResult.getMid();

        Map<String, Object> dataMap = memberDTOResult.getDateMap();

        String assessToken = jwtUtil.createToken(dataMap, 10);
        String refreshToken = jwtUtil.createToken(Map.of("mid", mid), 60 * 24 * 7);

        log.info("assessToken: " + assessToken);
        log.info("refreshToken: " + refreshToken);

        return ResponseEntity.ok(Map.of("assessToken", assessToken, "refreshToken", refreshToken));
    }
}
