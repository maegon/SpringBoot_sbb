package com.sbs.sbb.answer;

import com.sbs.sbb.question.Question;
import com.sbs.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private LocalDateTime modifyDate;

//  나중에 게시판 번호 같은거 만들었을때 게시판번호를 지정해주지 않으면 null값이 들어갈 수 있기 때문에
//  private Integer questionId;

    @ManyToOne // 1 대 다수 관계에서는 필수 작성 One: question, Many: answer
    private Question question;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voters = new LinkedHashSet<>();

    public void addVoter(SiteUser voter) {
        voters.add(voter);
    }
}
