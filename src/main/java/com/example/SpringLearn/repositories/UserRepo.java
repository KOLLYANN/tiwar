package com.example.SpringLearn.repositories;

import com.example.SpringLearn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("update User u set u.username = :name where u.id = :id")
    void updateName(@Param("name") String username, @Param("id") long id);
}
