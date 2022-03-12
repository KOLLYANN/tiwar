package com.example.SpringLearn.repositories.person;

import com.example.SpringLearn.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Transactional
    @Query("update Person set firstname = ?1, lastname = ?2, age = ?3 where id = ?4")
    void updateById(String firstname,
                    String lastname,
                    int age,
                    Long id);
}
