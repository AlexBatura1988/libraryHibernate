package com.example.libraryHibernate.services;

import com.example.libraryHibernate.models.Book;
import com.example.libraryHibernate.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear){
        if(sortByYear){
            return booksRepository.findAll(Sort.by("year"));
        }else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page,Integer bookPerPage,boolean sortByYear){
        if(sortByYear){
            return booksRepository.findAll(PageRequest.of(page,bookPerPage,Sort.by("year"))).getContent();
        }else {
            return booksRepository.findAll(PageRequest.of(page,bookPerPage)).getContent();
        }
    }
}
