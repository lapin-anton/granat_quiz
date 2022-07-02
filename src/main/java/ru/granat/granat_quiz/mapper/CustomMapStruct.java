package ru.granat.granat_quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.granat.granat_quiz.model.dto.AnswerDto;
import ru.granat.granat_quiz.model.dto.PersonDto;
import ru.granat.granat_quiz.model.dto.QuestionDto;
import ru.granat.granat_quiz.model.entity.Answer;
import ru.granat.granat_quiz.model.entity.Person;
import ru.granat.granat_quiz.model.entity.Question;

import java.util.stream.Collectors;

@Mapper
public interface CustomMapStruct {

    CustomMapStruct INSTANCE = Mappers.getMapper(CustomMapStruct.class);

    default QuestionDto mapQuestion(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .answers(question.getAnswers() != null ? question.getAnswers().stream()
                        .map(ans -> mapAnswer(question.getId(), ans))
                        .collect(Collectors.toList()) : null)
                .build();
    }

    default AnswerDto mapAnswer(long questionId, Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(questionId)
                .isRight(answer.isRight())
                .build();
    }

    PersonDto mapPerson(Person person);

}
