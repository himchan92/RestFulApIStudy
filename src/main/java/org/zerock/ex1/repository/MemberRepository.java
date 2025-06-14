package org.zerock.ex1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
