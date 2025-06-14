package org.zerock.ex1.repository;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.entity.MemberEntity;
import org.zerock.ex1.exception.MemberException;

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
                .mpw(passwordEncoder.encode("1111"))
                .mname("USER" + i)
                .email("user" + i + "@aaa.com")
                .role(i <= 80 ? "USER" : "ADMIN")
                .build();

            memberRepository.save(memberEntity);
        }
    }

    @Test
    public void testRead() {
        String mid = "user1";

        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity = result.orElseThrow(MemberException.NOT_FOUND::get);

        System.out.println(memberEntity);
    }

    @Test
    @Commit
    @Transactional
    public void testUpdate() {
        String mid = "user1";

        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity = result.orElseThrow(MemberException.NOT_FOUND::get);

        memberEntity.changePassword(passwordEncoder.encode("2222"));
        memberEntity.changeName("USER1-1");
    }

    @Test
    @Commit
    @Transactional
    public void testDelete() {
        String mid = "user1";
        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity = result.orElseThrow(() -> MemberException.NOT_FOUND.get());

        memberRepository.delete(memberEntity);
    }
}