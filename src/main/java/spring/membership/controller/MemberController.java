package spring.membership.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.membership.dto.MemberDTO;
import spring.membership.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;
    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    //로그인
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult!=null){
            //로그인 성공
            //세션에 loginEmail이라는 변수로 memberDTO.getMemberEmail()을 통하여 이메일을 받게함
            session.setAttribute("loginEmail",memberDTO.getMemberEmail());
            //main.html로 옮김
            return "main";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList =memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id,Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }
    //로그인을 하게되면 main.html이 보임
    //해당 html에서 정보 수정을 누름
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        //로그인 할 때 setAttribute로 loginEmail 안에 memberDTO.getMemberEmail()을 통해 이메일을 넣어놨음
        //해당 이메일을 가져옴
        String myEmail = (String) session.getAttribute("loginEmail");
        //이메일로 memberDTO를 찾음
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        //memberDTO를 model에 추가함
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/"+memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}
