package com.sbs.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity // answer 테이블
public class Answer {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

//  나중에 게시판 번호 같은거 만들었을때 게시판번호를 지정해주지 않으면 null값이 들어갈 수 있기 때문에
//  private Integer questionId;

    @ManyToOne // 1 대 다수 관계에서는 필수 작성 One: question, Many: answer
    private Question question;

//  mappedBy Answer 클래스의 question 변수 이름을 적어야 함
//  CascadeType.REMOVE 를 하면 question을 삭제할 때 답변도 같이 삭제한다는 뜻
//  OneToMany는 테이블의 컬럼으로 생성되지 않음, 선택 작성
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
