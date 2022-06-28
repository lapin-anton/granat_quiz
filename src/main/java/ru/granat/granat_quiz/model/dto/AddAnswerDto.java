package ru.granat.granat_quiz.model.dto;

import lombok.Data;

@Data
public class AddAnswerDto {

    private String content;

    private boolean isRight;

}
