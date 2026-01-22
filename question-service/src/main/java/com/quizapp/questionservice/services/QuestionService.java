package com.quizapp.questionservice.services;


import com.quizapp.questionservice.dao.QuestionDao;
import com.quizapp.questionservice.entity.Question;
import com.quizapp.questionservice.entity.QuestionWrapper;
import com.quizapp.questionservice.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    // All questions
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

    // Get id of quiz questions
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numOfQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numOfQuestions);

        return new ResponseEntity<>(questions,HttpStatus.OK);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionFromIds(List<Integer> questionsIds) {
        List<QuestionWrapper> Wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionsIds) {
            questions.add(questionDao.findById(id).get());
        }

        for (Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setTitle(question.getCategory());
            questionWrapper.setQuestion(question.getQuestion());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());

            Wrappers.add(questionWrapper);
        }

        return new ResponseEntity<>(Wrappers,HttpStatus.OK);


    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int rightAns = 0;

        for (Response response : responses) {
            Optional<Question> question = questionDao.findById(response.getId());

           if (response.getResponse().equals(question.get().getRightAnswer()))
               rightAns++;
        }
        return new ResponseEntity<>(rightAns, HttpStatus.OK);
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