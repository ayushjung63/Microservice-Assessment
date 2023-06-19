package com.assessment.blogservice.repo;

import com.assessment.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
}
