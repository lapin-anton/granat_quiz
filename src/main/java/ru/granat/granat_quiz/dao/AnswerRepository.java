package ru.granat.granat_quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.granat.granat_quiz.model.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query("delete from Answer a where a.question.id = :id")
    void deleteAllByQuestionId(@Param("id") long id);

    @Modifying
    @Query("update Answer a set a.content = :content, a.isRight = :right where a.id = :id")
    int updateAnswerById(@Param("id") long answerId,
                         @Param("content") String content,
                         @Param("right") boolean right);
}
