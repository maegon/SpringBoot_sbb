package com.sbs.sbb.answer;

import com.sbs.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
// @RequiredArgsConstructor 을 달아줘야 private final이 가능하고 이것은 자동으로 객체를 만들어줌을 의미
public class AnswerService {
    private final AnswerRepository answerRepository;
    public Answer create(Question q, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(q);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
        // 무언가를 입력받아 저장할 꺼면 리포지터리 만들어서 save 시킬것

        return answer;
    }
}
