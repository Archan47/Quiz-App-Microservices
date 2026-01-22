package com.quizapp.quizservice.dao;


import com.quizapp.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
