package com.teluskobased.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teluskobased.quizapp.Question;
import com.teluskobased.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController  {

    // Injects dependency instead of importing directly
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
       return questionService.getAllQuestions();
    }

}