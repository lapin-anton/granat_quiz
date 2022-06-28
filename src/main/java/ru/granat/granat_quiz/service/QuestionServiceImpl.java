package ru.granat.granat_quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.granat.granat_quiz.dao.AnswerRepository;
import ru.granat.granat_quiz.dao.QuestionRepository;
import ru.granat.granat_quiz.exception.CustomException;
import ru.granat.granat_quiz.mapper.CustomMapStruct;
import ru.granat.granat_quiz.model.dto.AddQuestionDto;
import ru.granat.granat_quiz.model.dto.QuestionDto;
import ru.granat.granat_quiz.model.entity.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public QuestionDto addNewQuestion(AddQuestionDto addQuestionDto) {
        Question q = Question.builder()
                .content(addQuestionDto.getContent())
                .build();
        q = questionRepository.save(q);
        return CustomMapStruct.INSTANCE.mapQuestion(questionRepository.findById(q.getId()).get());
    }

    @Override
    public QuestionDto updateQuestion(long id, String content) throws CustomException {
        if (questionRepository.updateQuestionContentById(id, content) == 0) {
            throw new CustomException(String.format("Question with id=%s is not exists.", id));
        }
        return CustomMapStruct.INSTANCE.mapQuestion(questionRepository.findById(id).get());
    }

    @Override
    public void deleteQuestion(long id) {
        answerRepository.deleteAllByQuestionId(id);
        questionRepository.deleteById(id);
    }
}
