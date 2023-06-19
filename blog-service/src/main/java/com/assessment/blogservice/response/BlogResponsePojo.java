package com.assessment.blogservice.response;

import com.assessment.blogservice.request.BlogRequestPojo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponsePojo extends BlogRequestPojo {
    private LocalDate publishedDate;
}
