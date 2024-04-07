package com.example.restapi.mapper;

import com.example.restapi.dto.QuestionResponseDto;
import com.example.restapi.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public QuestionResponseDto toResponseDto(Question question) {
        return new QuestionResponseDto(
                question.getId(),
                question.getQuestion(),
                question.getAnswer()
        );
    }
}
