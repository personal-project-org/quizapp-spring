package com.teluskobased.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teluskobased.quizapp.dao.QuestionDao;
import com.teluskobased.quizapp.model.Question; 

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<List<Question>>(questionDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<String>("New Question Successfully Added",HttpStatus.CREATED); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("There was an error. - addQuestion", HttpStatus.BAD_REQUEST); 

    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> existingQuestion = questionDao.findById(id);

        if (existingQuestion.isPresent()) {
            Question questionToUpdate = existingQuestion.get();

            if (updatedQuestion.getQuestionTitle() != null){
                questionToUpdate.setQuestionTitle(updatedQuestion.getQuestionTitle());
            }
            if (updatedQuestion.getCategory() != null){
                questionToUpdate.setCategory(updatedQuestion.getCategory());
            }
            if (updatedQuestion.getDifficultyLevel() != null){
                questionToUpdate.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            }
            if (updatedQuestion.getOption1() != null){
                questionToUpdate.setOption1(updatedQuestion.getOption1());
            }
            if (updatedQuestion.getOption2() != null){
                questionToUpdate.setOption2(updatedQuestion.getOption2());
            }
            if (updatedQuestion.getOption3() != null){
                questionToUpdate.setOption3(updatedQuestion.getOption3());
            }
            if (updatedQuestion.getOption4() != null){
                questionToUpdate.setOption4(updatedQuestion.getOption4());
            }

            try {
                questionDao.save(questionToUpdate);
                Question res = questionDao.findById(id).get();
                return new ResponseEntity<Question>(res, HttpStatus.OK);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
            return new ResponseEntity<Question>(new Question(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            questionDao.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<String>("There was an error. - deleteQuestionById", HttpStatus.BAD_REQUEST);

    }
}
