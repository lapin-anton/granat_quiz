package ru.granat.granat_quiz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.granat.granat_quiz.exception.CustomException;
import ru.granat.granat_quiz.mapper.CustomMapStruct;
import ru.granat.granat_quiz.model.dto.AddAnswerDto;
import ru.granat.granat_quiz.model.dto.AddQuestionDto;
import ru.granat.granat_quiz.model.dto.AnswerDto;
import ru.granat.granat_quiz.model.dto.QuestionDto;
import ru.granat.granat_quiz.service.AnswerService;
import ru.granat.granat_quiz.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    // get all questions of test
    @GetMapping("/")
    public ResponseEntity<List<QuestionDto>> getTest() {
        log.info("Income request to get all questions");
        List<QuestionDto> result = questionService.getAllQuestions()
                .stream().map(CustomMapStruct.INSTANCE::mapQuestion)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    // add new question
    @Transactional
    @PostMapping("/question/add")
    public ResponseEntity<QuestionDto> addNewQuestion(@RequestBody AddQuestionDto addQuestionDto) {
        log.info("Income request to add new question");
        QuestionDto result = questionService.addNewQuestion(addQuestionDto);
        return ResponseEntity.ok().body(result);
    }

    // update question content by id
    @Transactional
    @PostMapping("/question/update")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestParam long id, @RequestParam String content) throws CustomException {
        log.info("Income request to update question");
        QuestionDto result = questionService.updateQuestion(id, content);
        return ResponseEntity.ok().body(result);
    }

    // delete question by id
    @Transactional
    @PostMapping("/question/delete")
    public ResponseEntity<String> deleteQuestion(@RequestParam long id) {
        log.info("Income request delete question");
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().body(String.format("Question with id=%s deleted successfully", id));
    }

    // add new answer by question id
    @Transactional
    @PostMapping("/answer/add")
    public ResponseEntity<AnswerDto> addAnswer(@RequestParam long questionId, @RequestBody AddAnswerDto addAnswerDto) {
        log.info("Income request to add new answer by question id");
        AnswerDto result = answerService.addAnswer(questionId, addAnswerDto);
        return ResponseEntity.ok().body(result);
    }

    // update answer by id
    @Transactional
    @PostMapping("/answer/update")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestParam long questionId, @RequestParam long answerId, @RequestBody AddAnswerDto addAnswerDto) throws CustomException {
        log.info("Income request to update answer by id");
        AnswerDto result = answerService.updateAnswer(questionId, answerId, addAnswerDto);
        return ResponseEntity.ok().body(result);
    }

    // delete answer by id
    @Transactional
    @PostMapping("/answer/delete")
    public ResponseEntity<String> deleteAnswer(@RequestParam long id) {
        log.info("Income request to delete answer by id");
        answerService.deleteAnswer(id);
        return ResponseEntity.ok().body(String.format("Answer with id=%s deleted successfully", id));
    }

}
