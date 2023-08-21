package com.teluskobased.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teluskobased.quizapp.dao.QuizDao;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    
}
