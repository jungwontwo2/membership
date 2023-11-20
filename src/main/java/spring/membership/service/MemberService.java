package spring.membership.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.membership.dto.MemberDTO;
import spring.membership.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {

    }
}
