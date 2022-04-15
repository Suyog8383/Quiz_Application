package com.example.demo.QuestionService;

import java.util.List;

import com.example.demo.model.*;


public interface QuestionService {
    List < Question > getAllQuestions();
    void saveQuestion(Question question);
    com.example.demo.model.Question getQuestionById(int quesId);
	void deleteQuestionById(int quesId);
}