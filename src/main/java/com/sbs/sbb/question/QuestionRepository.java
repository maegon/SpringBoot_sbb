package com.sbs.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    //findBySubject
    Question findBySubject(String s);

    Question findBySubjectAndContent(String s, String s1);
}