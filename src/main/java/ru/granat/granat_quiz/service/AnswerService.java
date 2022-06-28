package ru.granat.granat_quiz.service;

import ru.granat.granat_quiz.exception.CustomException;
import ru.granat.granat_quiz.model.dto.AddAnswerDto;
import ru.granat.granat_quiz.model.dto.AnswerDto;

public interface AnswerService {

    AnswerDto addAnswer(long questionId, AddAnswerDto addAnswerDto);

    AnswerDto updateAnswer(long questionId, long answerId, AddAnswerDto addAnswerDto) throws CustomException;

    void deleteAnswer(long answerId);

}
