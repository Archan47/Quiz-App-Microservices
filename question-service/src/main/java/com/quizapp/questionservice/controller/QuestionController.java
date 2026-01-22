package com.quizapp.questionservice.controller;

import com.quizapp.questionservice.entity.Question;
import com.quizapp.questionservice.entity.QuestionWrapper;
import com.quizapp.questionservice.entity.Response;
import com.quizapp.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity <List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/allQuestions/{category}")
    public ResponseEntity <List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/allQuestions/{category}/{difficultyLevel}")
    public ResponseEntity <List<Question>> getQuestionsByLevel(@PathVariable String category ,@PathVariable String difficultyLevel){
        return questionService.getLevelWiseQuestions(category,difficultyLevel);
    }

    @GetMapping("/create")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numOfQuestions){
        return questionService.getQuestionsForQuiz(categoryName,numOfQuestions);
    }

    @PostMapping("/getQuizQuestions")
    public  ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionsIds){
        return questionService.getQuestionFromIds(questionsIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}
