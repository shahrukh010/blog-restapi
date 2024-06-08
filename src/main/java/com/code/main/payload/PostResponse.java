package com.code.main.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private String pageno;
    private String pagesize;
    private String totalElements;
    private String totalPages;
    private Boolean last;
}
