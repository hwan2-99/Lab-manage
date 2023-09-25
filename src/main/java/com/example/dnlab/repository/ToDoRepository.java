package com.example.dnlab.repository;

import com.example.dnlab.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUserIdAndWeekStartDate(int userId, LocalDate weekStartDate, Pageable pageable);

    void deleteById(int id);

    @Modifying
    @Query("UPDATE Todo t SET t.content = :content WHERE t.id = :id")
    void updateContentById(int id, String content);

    List<Todo> findAllByWeekStartDate(LocalDate weekStartDate, Pageable pageable);
}
