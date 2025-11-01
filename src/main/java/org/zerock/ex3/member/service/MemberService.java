package org.zerock.ex3.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex3.member.dto.MemberDTO;
import org.zerock.ex3.member.entity.MemberEntity;
import org.zerock.ex3.member.exception.MemberExceptions;
import org.zerock.ex3.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO: 사용자 정보가져온 후 암호화 가능한값인지 체크 후 반환
    public MemberDTO read(String mid, String mpw) {
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        if(!passwordEncoder.matches(mpw, memberEntity.getMpw())) {
            throw MemberExceptions.BAD_CREDENTIALS.get();
        }

        return new MemberDTO(memberEntity);
    }
}
