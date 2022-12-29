package com.example.libraryHibernate.repositories;

import com.example.libraryHibernate.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
