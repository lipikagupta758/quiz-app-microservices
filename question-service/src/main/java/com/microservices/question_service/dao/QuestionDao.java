package com.microservices.question_service.dao;

import com.microservices.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    //Optional- @Query("SELECT q FROM Question q WHERE q.category = ?1"), because JPA will automatically implement this method based on its name
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    //native query means it is sql query, we used it because jpql does not supports orderby and limit keyword
    List<Integer> findRandomQuestionsByCategory(String category, Integer numQ);
}
