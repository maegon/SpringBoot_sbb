package com.sbs.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }


    // id값을 받아 @PathVariable을 통해 id를 detail함수 안에서도 쓸수 있게 설정
    @GetMapping("/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("id", id);
        return "question_detail";
    }
}
