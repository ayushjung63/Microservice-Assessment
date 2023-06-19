package com.assessment.blogservice.controller;

import com.assessment.blogservice.generic.BaseController;
import com.assessment.blogservice.request.BlogRequestPojo;
import com.assessment.blogservice.response.ApiResponse;
import com.assessment.blogservice.response.BlogResponsePojo;
import com.assessment.blogservice.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController extends BaseController {
    private final BlogService blogService;

    /**
     *
     * @param id
     * @return BlogRequestPojo with blog fetched message
     */
    @Operation(
            summary = "API to fetch blog by id",
            responses = @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                content = {@Content(schema = @Schema(implementation = BlogResponsePojo.class ))}
            )
    )
    @GetMapping("/id/{id}")
    private ResponseEntity<ApiResponse> fetchById(@PathVariable("id") Integer id){
        BlogRequestPojo data = blogService.getById(id);
        return new ResponseEntity(
                apiResponse("Blog Fetched Successfully",data),
                HttpStatus.OK
        );
    }

    /**
     * @return  List<BlogResponsePojo> with List fetched message
     */
    @Operation(
            summary = "API to fetch blog list",
            responses = @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = BlogResponsePojo.class ))}
            )
    )
    @GetMapping("/list")
    private ResponseEntity<ApiResponse> fetchBlogList(){
        List<BlogResponsePojo> data = blogService.getBlogList();
        return new ResponseEntity(
                apiResponse("Blog List Fetched Successfully",data),
                HttpStatus.OK
        );
    }

    /**
     * @param blogRequestPojo
     * @return ID[Integer] of Saved Data with created message
     * @Valid is used to Validate the blogRequestPojo for Validation written in Attributes
     */
    @Operation(
            summary = "API to save or update blog",
            responses = @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = Integer.class ))}
            )
    )
    @PostMapping("/save")
    @PreAuthorize("hasPermission(#this.this.permissionName,'CREATE')")
    private ResponseEntity<ApiResponse> createOrUpdateBlog(@Valid @RequestBody BlogRequestPojo blogRequestPojo){
        Integer data = blogService.createOrUpdate(blogRequestPojo);
        return new ResponseEntity(
                apiResponse("Blog Saved Successfully",data),
                HttpStatus.CREATED
        );
    }

    /**
     *
     * @param id
     * @return ID[Integer] of deleted blog with delete message
     */
    @Operation(
            summary = "API to delete blog by id",
            responses = @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = Integer.class ))}
            )
    )
    @DeleteMapping("/delete/id/{id}")
    private ResponseEntity<ApiResponse> createOrUpdateBlog(@PathVariable("id") Integer id){
        Integer data = blogService.deleteById(id);
        return new ResponseEntity(
                apiResponse("Blog Deleted Successfully",data),
                HttpStatus.OK
        );
    }
}
