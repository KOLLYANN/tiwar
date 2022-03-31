package com.example.SpringLearn.services.thing;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.thing.ThingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingService {

    final ThingRepo thingRepo;

    @Autowired
    public ThingService(ThingRepo thingRepo) {
        this.thingRepo = thingRepo;
    }


    public void upThing(Long id) {
        thingRepo.upThing(id);
    }

    public List<Thing> findAll() {
        return thingRepo.findAll();
    }

    public Thing findById(Long id) {
        return thingRepo.findById(id).get();
    }

    public void saveThing(Thing thing) {
        thingRepo.save(thing);
    }

    public void saveAllThing(List<Thing> things) {
        thingRepo.saveAll(things);
    }

    public void deleteThingById(Thing thing) {
        thingRepo.deleteById(thing.getId());
    }

}
