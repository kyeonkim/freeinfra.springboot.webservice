package com.kyeonkim.freeinfra.springboot.webservice.web;

import com.kyeonkim.freeinfra.springboot.webservice.service.posts.PostsService;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsResponseDto;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsSaveRequestDto;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable(name = "id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable(name = "id") Long id) {
        return postsService.findById(id);
    }
}
