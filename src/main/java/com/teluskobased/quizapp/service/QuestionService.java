package com.teluskobased.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teluskobased.quizapp.Question;
import com.teluskobased.quizapp.dao.QuestionDao;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "New Question Successfully Added";
    }

    public Question updateQuestion(Integer id, Question updatedQuestion) {
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

            questionDao.save(questionToUpdate);

            Question res = questionDao.findById(id).get();
            
            return res;
        }
        return null;
    }

    public void deleteQuestionById(Integer id) {
        questionDao.deleteById(id);
    }
}
