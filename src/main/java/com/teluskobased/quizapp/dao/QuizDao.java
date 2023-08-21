package com.teluskobased.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teluskobased.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}

