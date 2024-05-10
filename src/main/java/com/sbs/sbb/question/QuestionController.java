package com.sbs.sbb.question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
// @Validated 컨트롤러에서는 생략가능, 다른데선 Validation 라이브러리 사용시 써줘야함
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
    // validation 라이브러리 사용시 해당 함수에 @Valid 꼭 붙여줘야 유효성 검사를 함
    // QuestionForm 값을 바인딩 할 때 유효성 체크를 해라!!
    public String questionCreate(@Valid QuestionForm questionForm) {

        Question q = this.questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list";
    }
}
