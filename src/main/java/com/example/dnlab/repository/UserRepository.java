package com.example.dnlab.repository;

import com.example.dnlab.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findById(String id);

    List<User> getUserByName(String name);
    @Query("select u from User u where u.name like %:name% order by u.num desc")
    Slice<User> findUserByName(@Param("name") String name, Pageable pageable);

    User getUserByNum(int num);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM Attendance a WHERE a.user = u AND DATE(a.startTime) = CURRENT_DATE)")
    List<User> getUsersWithoutAttendance();

    @Modifying
    @Query("UPDATE User u SET u.generation = :generation, u.leaderYN = :leaderYN WHERE u.num = :user_num")
    void updateUserGeneration(@Param("generation") int generation, @Param("user_num") int user_num, @Param("leaderYN") boolean leaderYN);

}
