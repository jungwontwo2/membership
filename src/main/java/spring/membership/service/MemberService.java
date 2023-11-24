package spring.membership.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.membership.dto.MemberDTO;
import spring.membership.entity.MemberEntity;
import spring.membership.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public MemberDTO login(MemberDTO memberDTO) {
        //1. 회원이 입력한 이메일로 DB에서 조회함
        //2. DB에서 조회한 비밀번호와 사용자가 입력한 번호가 맞는지 확인
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //조회 결과가 있음(해당 이메일 가진 회원정보 있음)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호가 일치하는 경우
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else{
                    return null;
            }
        }else {
            //해당 이메일을 가진 회원이 없다.
                return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id){
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if(memberEntity.isPresent()){
            MemberEntity memberEntity1 = memberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity1);
            return memberDTO;
        }
        else {
            return null;
        }
    }


    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        }
        else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO){
        //save는 id값이 없으면 저장
        //id값이 있으면 update가 실행됨
        //toUpdate에서는 id값이 있는 상황이기 때문에 update가 실행됨
        memberRepository.save(MemberEntity.toUpdateMemeberEntity(memberDTO));
    }
}
