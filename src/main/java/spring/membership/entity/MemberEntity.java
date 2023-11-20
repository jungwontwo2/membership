package spring.membership.entity;

import lombok.Getter;
import lombok.Setter;
import spring.membership.dto.MemberDTO;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column String memberPassword;

    @Column String memberName;

    public static MemberEntity toMemeberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        return memberEntity;
    }

}
