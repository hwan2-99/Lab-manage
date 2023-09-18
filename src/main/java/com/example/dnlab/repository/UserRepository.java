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


    User findByUid(String uid);

    List<User> getUserByName(String name);
    @Query("select u from User u where u.name like %:name% order by u.id desc")
    Slice<User> findUserByName(@Param("name") String name, Pageable pageable);

    User findById(int id);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM Attendance a WHERE a.user = u AND DATE(a.startTime) = CURRENT_DATE)")
    List<User> getUsersWithoutAttendance();

    @Modifying
    @Query("UPDATE User u SET u.generation = :generation, u.leaderYN = :leaderYN WHERE u.id = :user_id")
    void updateUserGeneration(@Param("generation") int generation, @Param("user_id") int user_id, @Param("leaderYN") boolean leaderYN);

}
