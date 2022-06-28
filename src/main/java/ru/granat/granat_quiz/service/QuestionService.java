package ru.granat.granat_quiz.service;

import ru.granat.granat_quiz.exception.CustomException;
import ru.granat.granat_quiz.model.dto.AddQuestionDto;
import ru.granat.granat_quiz.model.dto.QuestionDto;
import ru.granat.granat_quiz.model.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    QuestionDto addNewQuestion(AddQuestionDto addQuestionDto);

    QuestionDto updateQuestion(long id, String content) throws CustomException;

    void deleteQuestion(long id);

}
