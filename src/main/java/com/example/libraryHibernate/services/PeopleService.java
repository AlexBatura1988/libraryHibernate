package com.example.libraryHibernate.services;

import com.example.libraryHibernate.models.Book;
import com.example.libraryHibernate.models.Person;
import com.example.libraryHibernate.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Book> getBookByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);
        Hibernate.initialize(person.get().getBooks());
        return person.get().getBooks();
    }
}
