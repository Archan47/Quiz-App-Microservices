package com.quizapp.quizservice.controller;


import com.microservices.quizapp.entity.Question;
import com.microservices.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("/update")
    public String updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable String id) {
        try {
            questionService.deleteQuestion(Integer.parseInt(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
