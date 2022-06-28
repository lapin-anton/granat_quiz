package ru.granat.granat_quiz.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDto {

    private Long id;

    private String content;

    private List<AnswerDto> answers;

}
