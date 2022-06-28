package ru.granat.granat_quiz.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {

    private Long id;

    private String content;

    private long questionId;

    private boolean isRight;

}
