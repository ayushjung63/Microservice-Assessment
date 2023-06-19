package com.assessment.blogservice.service;

import com.assessment.blogservice.request.BlogRequestPojo;
import com.assessment.blogservice.response.BlogResponsePojo;

import java.util.List;

public interface BlogService {
    Integer createOrUpdate(BlogRequestPojo blogRequestPojo);
    BlogResponsePojo getById(Integer id);
    List<BlogResponsePojo> getBlogList();
    Integer deleteById(Integer id);
}
