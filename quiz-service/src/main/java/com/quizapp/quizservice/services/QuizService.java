package com.quizapp.quizservice.services;

import com.quizapp.quizservice.dao.QuizDao;
import com.quizapp.quizservice.entity.QuestionWrapper;
import com.quizapp.quizservice.entity.Quiz;
import com.quizapp.quizservice.entity.Response;
import com.quizapp.quizservice.feign.QuizInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {


    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try{
            List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            quizDao.save(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

          Optional<Quiz> quiz = quizDao.findById(id);
          List<Integer> questionIds= quiz.get().getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questionsForUser = quizInterface.getQuestionFromId(questionIds);
          return questionsForUser;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);

        return score;
    }

}
