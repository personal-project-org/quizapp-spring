package com.teluskobased.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teluskobased.quizapp.Question;

@Repository
// JpaRepository contructor requires the table/class name and the primary key type (id)
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

}
