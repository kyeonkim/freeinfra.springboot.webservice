package com.kyeonkim.freeinfra.springboot.webservice.service.posts;

import com.kyeonkim.freeinfra.springboot.webservice.domain.posts.Posts;
import com.kyeonkim.freeinfra.springboot.webservice.domain.posts.PostsRepository;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsResponseDto;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsSaveRequestDto;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsUpdateRequestDto;
import com.kyeonkim.freeinfra.springboot.webservice.web.dto.PostsListResponseDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Object findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
