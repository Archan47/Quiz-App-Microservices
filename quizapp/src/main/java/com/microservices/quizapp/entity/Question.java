package com.microservices.quizapp;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "quiz")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "category")
    private String category;
}
