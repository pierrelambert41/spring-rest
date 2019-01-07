package com.wildcodeschool.SpringRest.repositories;

import com.wildcodeschool.SpringRest.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository <Blog, Integer>
{
    List<Blog> findByTitleContainingOrDescriptionContaining(String text, String textAgain);

}
