package com.example.restapi.repository;

import com.example.restapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByUserUsername(String username);
    boolean existsByIdAndAnswerContainsIgnoreCase(Long id, String answer);
}
