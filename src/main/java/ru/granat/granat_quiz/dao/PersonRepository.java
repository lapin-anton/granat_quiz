package ru.granat.granat_quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.granat.granat_quiz.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
