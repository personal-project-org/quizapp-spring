package com.teluskobased.quizapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Auto creates getters, setters
@Data
// Handles the mapping of fields here to the ones within the database table
@Entity
public class Question {
    
    // Specifies id as the dedicated ID field, incrementing sequentially when a new entity is added
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private String difficultyLevel; 
    private String category;

}
