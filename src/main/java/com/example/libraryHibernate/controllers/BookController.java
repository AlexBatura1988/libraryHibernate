package com.example.libraryHibernate.controllers;

import com.example.libraryHibernate.services.BookService;
import com.example.libraryHibernate.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "book_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if(page == null || booksPerPage == null){
            model.addAttribute("books",bookService.findAll(sortByYear));
        }else {
            model.addAttribute("books",bookService.findWithPagination(page,booksPerPage,sortByYear));
        }
        return "books/index";
    }
}
