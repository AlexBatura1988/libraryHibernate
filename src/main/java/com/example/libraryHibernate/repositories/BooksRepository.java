package com.example.libraryHibernate.repositories;

import com.example.libraryHibernate.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
}
