package com.sbs.sbb.answer;

import com.sbs.sbb.question.Question;
import com.sbs.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    // private final을 작성했는지 꼭 확인(작성 안해주면 오류남)
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    // @RequestParam String content 으로 적었는데 페이지 오류가 난다면
    // @RequestParam(value="content") String content 으로 수정해볼 것
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        // 답변의 부모 질문 객체를 받아오는 역할 수행
        Question q = this.questionService.getQuestion(id);

        return "redirect:/question/detail/%d".formatted(id);
    }
}
