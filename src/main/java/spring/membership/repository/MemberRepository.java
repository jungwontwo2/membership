package spring.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.membership.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}
