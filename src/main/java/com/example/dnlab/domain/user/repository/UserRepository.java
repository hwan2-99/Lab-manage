package com.example.dnlab.domain.user.repository;

import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User getUserById(String id);

    User findById(String id);

    List<User> getUserByName(String name);

    User getUserByNum(int num);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM Attendance a WHERE a.user = u)")
    List<User> getUsersWithoutAttendance();

    @Modifying
    @Query("UPDATE User u SET u.generation = :generation, u.leaderYN = :leaderYN WHERE u.num = :user_num")
    void updateUserGeneration(@Param("generation") int generation, @Param("user_num") int user_num, @Param("leaderYN") boolean leaderYN);

}
