package com.quizapp.quizservice.services;

import com.quizapp.quizservice.dao.QuizDao;
import com.quizapp.quizservice.entity.QuestionWrapper;
import com.quizapp.quizservice.entity.Quiz;
import com.quizapp.quizservice.entity.Response;
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
    //QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

//        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//
//        quizDao.save(quiz);
//
//        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

//        Optional<Quiz> quiz = quizDao.findById(id);
//        List<Question> questionFromDB= quiz.get().getQuestions();
          List<QuestionWrapper> questionsForUser = new ArrayList<>();
//
//        for(Question q: questionFromDB){
//            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            questionsForUser.add(questionWrapper);
//        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

//        Quiz quiz = quizDao.findById(id)
//                .orElseThrow(() -> new RuntimeException("Quiz not found"));
//
//        List<Question> questions = quiz.getQuestions();
//
//        // Convert questions to Map<questionId, Question>
//        Map<Integer, Question> questionMap = new HashMap<>();
//        for (Question q : questions) {
//            questionMap.put(q.getId(), q);
//        }
//
          int rightAns = 0;
//
//        for (Response response : responses) {
//            Question question = questionMap.get(response.getId());
//
//            if (question != null &&
//                    response.getResponse().equalsIgnoreCase(question.getRightAnswer())) {
//                rightAns++;
//            }
//        }

        return new ResponseEntity<>(rightAns, HttpStatus.OK);
    }

}
