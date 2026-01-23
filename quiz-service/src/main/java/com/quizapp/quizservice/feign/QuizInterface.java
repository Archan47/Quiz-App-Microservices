package com.quizapp.quizservice.feign;

import com.quizapp.quizservice.entity.QuestionWrapper;
import com.quizapp.quizservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/create")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numOfQuestions);

    @PostMapping("question/getQuizQuestions")
    public  ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionsIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
