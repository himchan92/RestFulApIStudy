package org.zerock.ex3.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.ex3.member.entity.MemberEntity;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsert() {
        for(int i = 1; i <= 100; i++) {
            MemberEntity memberEntity = MemberEntity.builder()
                    .mid("user" + i)
                    .mpw(passwordEncoder.encode("1111")) //각각 다르게 암호화 저장
                    .mname("USER" + i)
                    .email("user" + i + "@aaa.com")
                    .role(i <= 80 ? "USER" : "ADMIN")
                    .build();

            memberRepository.save(memberEntity);
        }
    }
}