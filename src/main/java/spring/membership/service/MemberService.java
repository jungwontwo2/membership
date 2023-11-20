package spring.membership.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.membership.dto.MemberDTO;
import spring.membership.entity.MemberEntity;
import spring.membership.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        //repository의 save를 호출해야함
        //repository의 save는 DTO가 아닌 Entity객체를 넘겨서 실행함
        // 그래서 DTO를 Entity로 바꿔줘야 함
        MemberEntity memberEntity = MemberEntity.toMemeberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
