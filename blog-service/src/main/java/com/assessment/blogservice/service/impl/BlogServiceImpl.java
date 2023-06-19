package com.assessment.blogservice.service.impl;

import com.assessment.blogservice.entity.Blog;
import com.assessment.blogservice.exception.notfound.NotFoundException;
import com.assessment.blogservice.repo.BlogRepo;
import com.assessment.blogservice.request.BlogRequestPojo;
import com.assessment.blogservice.response.BlogResponsePojo;
import com.assessment.blogservice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepo blogRepo;

    /*
    * @Transactional annotation is used to roll back database transaction if any error occurred.
    * */
    @Transactional
    @Override
    public Integer createOrUpdate(BlogRequestPojo blogRequestPojo) {
        Integer id = blogRequestPojo.getId();
        Blog blog=new Blog();
        /*
        * If id is not null and not equals to 0, fetching existing blog from database :: to separate new create and update case
        * If id is present, blog is updated, else new blog is created
        * */
        if (Optional.ofNullable(id).orElse(0) != 0){
            blog = this.getBlog(id);
        }
        /*
        * matching properties of blogRequestPojo is set to blog :: DTO to Entity conversion
        * */
        BeanUtils.copyProperties(blogRequestPojo,blog);
        blog.setPublishedDate(LocalDate.now());
        Blog data = blogRepo.save(blog);
        return data.getId();
    }

    @Override
    public BlogResponsePojo getById(Integer id) {
        Blog blog = this.getBlog(id);
        BlogResponsePojo blogResponsePojo=new BlogResponsePojo();
        /*
         * matching properties of blog is set to blogResponsePojo :: Entity to DTO conversion
         * */
        BeanUtils.copyProperties(blog,blogResponsePojo);
        return blogResponsePojo;
    }

    @Override
    public List<BlogResponsePojo> getBlogList() {
        List<Blog> blogList = blogRepo.findAll();
        List<BlogResponsePojo> blogResponsePojoList = blogList.stream().map(blog -> {
            BlogResponsePojo blogResponsePojo = new BlogResponsePojo();
            BeanUtils.copyProperties(blog, blogResponsePojo);
            return blogResponsePojo;
        }).collect(Collectors.toList());
        return blogResponsePojoList;
    }

    @Override
    public Integer deleteById(Integer id) {
        /*
        * verify if blog to delete is present in database or not
        * */
        this.getBlog(id);
        blogRepo.deleteById(id);
        return id;
    }

    public Blog getBlog(Integer id){
        return blogRepo.findById(id).orElseThrow(
                ()->new NotFoundException("Blog with id: "+ id+" does not exist.")
        );
    }
}
