package com.example.SpringLearn.services.person;

import com.example.SpringLearn.models.person.Person;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {

    final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Person person, Person per) {
        personRepository.updateById(person.getFirstname(), person.getLastname(), person.getAge(), per.getId());
    }


    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }


    public void fileUpload(MultipartFile multipartFile, Person person) {
        Logger log = Logger.getLogger(PersonService.class.getSimpleName());
        try {
            byte[] bytes = multipartFile.getBytes();
            String s = UUID.randomUUID().toString().substring(0, 10);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\Nikolay\\Desktop\\upload\\" + s + ".png"));

            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();

            person.setPath(s +".png");
            personRepository.save(person);

            log.info("ИДИ В ПЕНЬ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
