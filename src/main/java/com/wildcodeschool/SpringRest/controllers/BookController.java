package com.wildcodeschool.SpringRest.controllers;

import com.wildcodeschool.SpringRest.entities.Book;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wildcodeschool.SpringRest.repositories.BookRepository;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/blogs")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/blogs/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blogs")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/blogs/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setContent(book.getContent());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("blogs/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}