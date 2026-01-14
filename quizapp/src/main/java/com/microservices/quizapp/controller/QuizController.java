package com.microservices.quizapp.controller;


import com.microservices.quizapp.Question;
import com.microservices.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuizController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return  questionService.getAllQuestions();
    }

    @GetMapping("/allQuestions/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/allQuestions/{category}/{difficultyLevel}")
    public List<Question> getQuestionsByLevel(@PathVariable String category ,@PathVariable String difficultyLevel){
        return questionService.getLevelWiseQuestions(category,difficultyLevel);
    }


}
