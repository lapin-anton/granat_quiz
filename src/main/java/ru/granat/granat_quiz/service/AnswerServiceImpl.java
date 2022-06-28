package ru.granat.granat_quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.granat.granat_quiz.dao.AnswerRepository;
import ru.granat.granat_quiz.dao.QuestionRepository;
import ru.granat.granat_quiz.exception.CustomException;
import ru.granat.granat_quiz.mapper.CustomMapStruct;
import ru.granat.granat_quiz.model.dto.AddAnswerDto;
import ru.granat.granat_quiz.model.dto.AnswerDto;
import ru.granat.granat_quiz.model.entity.Answer;
import ru.granat.granat_quiz.model.entity.Question;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public AnswerDto addAnswer(long questionId, AddAnswerDto addAnswerDto) {
        Answer answer = null;
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isPresent()) {
            answer = Answer.builder()
                    .content(addAnswerDto.getContent())
                    .isRight(addAnswerDto.isRight())
                    .question(questionOpt.get())
                    .build();
            answer = answerRepository.save(answer);
        }
        return answer == null ? null : CustomMapStruct.INSTANCE.mapAnswer(questionId, answer);
    }

    @Override
    public AnswerDto updateAnswer(long questionId, long answerId, AddAnswerDto addAnswerDto) throws CustomException {
        if (answerRepository.updateAnswerById(answerId, addAnswerDto.getContent(), addAnswerDto.isRight()) == 0) {
            throw new CustomException(String.format("Answer with id=%s is not exists", answerId));
        }
        return CustomMapStruct.INSTANCE.mapAnswer(questionId, answerRepository.findById(answerId).get());
    }

    @Override
    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }
}
