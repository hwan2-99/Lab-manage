package com.example.dnlab.domain.toDo.repository;

import com.example.dnlab.domain.toDo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findByUserNumAndWeekStartDate(int userNum, LocalDate weekStartDate);

    void deleteByNum(int num);

    @Modifying
    @Query("UPDATE ToDo t SET t.content = :content WHERE t.num = :num")
    void updateContentByNum(int num, String content);

    List<ToDo> findAllByWeekStartDate(LocalDate weekStartDate);
}
