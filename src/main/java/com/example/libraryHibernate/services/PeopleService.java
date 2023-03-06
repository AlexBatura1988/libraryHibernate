package com.example.libraryHibernate.services;

import com.example.libraryHibernate.models.Book;
import com.example.libraryHibernate.models.Person;
import com.example.libraryHibernate.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findByFullName(String name) {
        return this.peopleRepository.findByFullName(name);
    }

    @Transactional
    public void save(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        List<Book> books = getBookByPersonId(id);
        if(!books.isEmpty()){
            for(Book book : books){
                book.setTakenAt(null);
            }
        }
        peopleRepository.deleteById(id);


    }

    public List<Book> getBookByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);
        Hibernate.initialize(person.get().getBooks());
        return person.get().getBooks();
    }
}
