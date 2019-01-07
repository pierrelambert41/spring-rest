package com.wildcodeschool.SpringRest.controllers;

import com.wildcodeschool.SpringRest.entities.Blog;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wildcodeschool.SpringRest.repositories.BlogRepository;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/blogs")
    public List<Blog> index(){
        return blogRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Blog show(@PathVariable int id){
        return blogRepository.findById(id).get();
    }

    @PostMapping("/blogs/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blogs")
    public Blog create(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }

    @PutMapping("/blogs/{id}")
    public Blog update(@PathVariable int id, @RequestBody Blog blog){
        // getting blog
        Blog blogToUpdate = blogRepository.findById(id).get();
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setContent(blog.getContent());
        return blogRepository.save(blogToUpdate);
    }

    @DeleteMapping("blogs/{id}")
    public boolean delete(@PathVariable int id){
        blogRepository.deleteById(id);
        return true;
    }
}