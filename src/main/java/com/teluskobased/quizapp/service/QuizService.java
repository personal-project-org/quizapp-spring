package com.teluskobased.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teluskobased.quizapp.dao.QuestionDao;
import com.teluskobased.quizapp.dao.QuizDao;
import com.teluskobased.quizapp.model.Question;
import com.teluskobased.quizapp.model.QuestionWrapper;
import com.teluskobased.quizapp.model.Quiz;
import com.teluskobased.quizapp.model.Response;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
       
       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestion(questions);
       quizDao.save(quiz);

       return new ResponseEntity<>("Success", HttpStatus.CREATED);
       
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = this.getQuestions(quiz.get());
        //Question Wrapper contains all fields except for the answer
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    private List<Question> getQuestions(Quiz quiz) {
        return quiz.getQuestion();
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = this.getQuestions(quiz);
        int right = 0;
        int index = 0;
        for(Response response: responses){
            String correctAnswerFromDB = questions.get(index).getRightAnswer();
            if (response.getAnswerFromUser().equals(correctAnswerFromDB)) {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
