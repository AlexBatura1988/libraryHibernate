package com.example.libraryHibernate;

import com.example.libraryHibernate.models.Person;
import com.example.libraryHibernate.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class LibraryHibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryHibernateApplication.class, args);
	}
}
