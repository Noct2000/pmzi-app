package com.example.restapi.service;

import com.example.restapi.model.Question;
import java.util.List;

public interface QuestionService extends CrudService<Question> {
    boolean existsByIdAndAnswerIgnoreCase(Long id, String answer);

    List<Question> findAllByUsername(String username);
}
