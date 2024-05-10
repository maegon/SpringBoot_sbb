package com.sbs.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }


    // id값을 받아 @PathVariable을 통해 id를 detail함수 안에서도 쓸수 있게 설정
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question q = this.questionService.getQuestion(id);

        model.addAttribute("question", q);

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
        if ( subject == null || subject.trim().length() == 0 ) {
            throw new RuntimeException("subject(을)를 입력해주세요.");
        }

        if ( subject.trim().length() > 200 ) {
            throw new RuntimeException("subject(을)를 200자 이하로 입력해주세요.");
        }

        if ( content == null || content.trim().length() == 0 ) {
            throw new RuntimeException("content(을)를 입력해주세요.");
        }

        if ( content.trim().length() > 20_000 ) {
            throw new RuntimeException("content(을)를 20,000자 이하로 입력해주세요.");
        }

        return "redirect:/question/list";
    }
}
