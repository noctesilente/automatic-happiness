package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    /* 설명. 서블릿 때와 마찬가지로 포워딩을 통해 값을 전달할 수 있다. */
    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함");
        return "result";
    }

    /* 설명. 서블릿 때와 마찬가지로 파라미터를 활용하지 않고서는 리다이렉트를 통해 값을 전달할 수 없다.(스프링은 해법이 있다.) */
    @GetMapping("string-redirect")
    public String stringRedirect(Model model) {
        model.addAttribute("message1", "문자열로 뷰 이름 반환하며 리다이렉트");

        return "redirect:/?message1=ttt";
        // redirect: 하고 가고 싶은 경로 적어주기
        // url에 어떻게 써줄 건지 적어줄 수 있음 - ttt를 파라미터로 전달
        // main.html에서 param.으로 해서 표현
    }

    /* 설명. 스프링의 RedirectAttributes라는 객체에 attr을 담으면 리다이렉트 시에도 값(전달할 상태)이 유지된다. */
    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect");
        return "redirect:/";
    }

    /* 설명. 기존에 핸들러 메소드가 'void 또는 String'으로만 반환했는데 ModelAndView를 반환하는 것도 해 보자. */
    /* 설명. 단순 forward 시에는 String 반환과 Model을 활용하는 코드와 ModelAndView를 활용한 코드가 차이가 없다. */
    // 어디로 갈지 = 뷰의 내용, 어떤 값 가지고 갈지 = model
    // 하나로 묶어서 view resolver한테 주면 거기서 찾아서 쓰는 것

    // 일단 ModelAndView한테 요청을 받는 것
    @GetMapping("modelandview")
    public ModelAndView modelAndViewTest(ModelAndView mv) {
        mv.addObject("message2", "ModelAndView를 이용한 forward");
        mv.setViewName("result");

        return mv;
        // 이런 식으로 기존에 알고 있는 개념을 한 번에 반환함
        // 객체들을 여러 개 담을 때 사용 - addAttribute 도 아니라 addObject임
        // 개념상은 모델 쓰고 스트링 반환하는 거랑 다를 바는 없음
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {

        /* 설명. ModelAndView를 통한 리다이렉트 시에는 addObject 한 것이 parameter로 넘어간다.(?가 있는 쿼리스트링 형태로 넘어간다.) */
        mv.addObject("message2", "ModelAndView를 이용한 redirect");
        mv.setViewName("redirect:/");
        // redirect/?message2=ModelAndView를이용한redirect = 를 자동으로 해줌

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttribute(ModelAndView mv,
                                                           RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect attr");

        mv.setViewName("redirect:/");

        return mv;
    }
}
