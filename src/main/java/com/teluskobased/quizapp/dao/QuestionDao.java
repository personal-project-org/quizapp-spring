package com.teluskobased.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teluskobased.quizapp.Question;

@Repository
// JpaRepository contructor requires the table/class name and the primary key type (id)
public interface QuestionDao extends JpaRepository<Question,Integer> {

}
