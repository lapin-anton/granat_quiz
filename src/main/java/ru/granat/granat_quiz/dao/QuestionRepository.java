package ru.granat.granat_quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.granat.granat_quiz.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Query("update Question q set q.content = :content where q.id = :id")
    int updateQuestionContentById(@Param("id") long id, @Param("content") String content);

}
