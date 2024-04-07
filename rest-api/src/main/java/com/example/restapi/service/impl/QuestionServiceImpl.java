package com.example.restapi.service.impl;

import com.example.restapi.model.Question;
import com.example.restapi.repository.QuestionRepository;
import com.example.restapi.service.QuestionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl extends CrudServiceImpl<Question> implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository, Question.class.getSimpleName());
        this.questionRepository = questionRepository;
    }

    @Override
    public boolean existsByIdAndAnswerIgnoreCase(Long id, String answer) {
        return questionRepository.existsByIdAndAnswerContainsIgnoreCase(id, answer);
    }

    @Override
    public List<Question> findAllByUsername(String username) {
        return questionRepository.findAllByUserUsername(username);
    }
}
