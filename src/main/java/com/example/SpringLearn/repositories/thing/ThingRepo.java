package com.example.SpringLearn.repositories.thing;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ThingRepo extends JpaRepository<Thing, Long> {

    @Transactional
    @Modifying
    @Query("update Thing th set th.parameters = th.parameters + 50, th.smith = th.smith + 1 where th.id = :id")
    void upThing(@Param("id") Long id);


}
