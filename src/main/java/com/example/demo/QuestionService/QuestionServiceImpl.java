package com.example.demo.QuestionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepository;

    @Override
    public List < Question > getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) {
        this.questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(int quesId) {
        Optional < Question > optional = questionRepository.findById(quesId);
        Question question = null;
        if (optional.isPresent()) {
        	question = optional.get();
        } else {
            throw new RuntimeException(" Question not found for id :: " + quesId);
        }
        return question;
    }


    @Override
    public void deleteQuestionById(int quesId) {
        this.questionRepository.deleteById(quesId);
   }
}