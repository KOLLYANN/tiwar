package com.example.SpringLearn.repositories.thing;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepo extends JpaRepository<Thing, Long> {

//    @Query("from Thing th where th.place = 'Голова' and th.user = :id")
//    Thing findThingByUserId(@Param("user") Long id);


}
