package com.example.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(generator = "question_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "question_id_seq",
            sequenceName = "question_id_seq",
            allocationSize = 1
    )
    private Long id;
    private String question;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
