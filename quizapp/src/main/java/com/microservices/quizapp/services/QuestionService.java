package com.microservices.quizapp.services;

import com.microservices.quizapp.entity.Question;
import com.microservices.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity< List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>( questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    // Topic categorization
    public ResponseEntity <List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    // Level categorization
    public ResponseEntity <List<Question>> getLevelWiseQuestions(String category,String difficultyLevel) {
        try{
        return new ResponseEntity<>(questionDao.findByCategoryAndDifficultyLevel(category,difficultyLevel),HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    // add question (Admin)
    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }

    // update question (Admin)
    public String updateQuestion(Question question) {
        questionDao.save(question);
        return "updated question";
    }

    // delete question (Admin)
    public void deleteQuestion(Integer id) {
        if (!questionDao.existsById(id)) {
            throw new RuntimeException("Question not found with id: " + id);
        }
        questionDao.deleteById(id);
    }
}