package com.example.dnlab.service;

import com.example.dnlab.domain.Board;
import com.example.dnlab.dto.post.PostListResDto;
import com.example.dnlab.dto.post.PostReqDto;
import com.example.dnlab.dto.post.PostResDto;
import com.example.dnlab.dto.post.PostUpdateReqDto;
import com.example.dnlab.domain.Post;
import com.example.dnlab.repository.BoardRepository;
import com.example.dnlab.repository.PostRepository;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    LocalDate today = LocalDate.now();
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시글 작성
    public PostResDto createPost(PostReqDto req, int boardId, int userId){

        // 게시판 선택 시 게시판 pk 불러오기
        Board board = boardRepository.findById(boardId);
        User user = userRepository.findById(userId);


        Post post = req.toEntity(user,board);
        post.setCreatedAt(today);

        postRepository.save(post);
        return PostResDto.builder()
                .userName(user.getName())
                .title(req.getTitle())
                .build();
    }

    // 게시글 삭제
    public PostResDto deletePost(int id){
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
        } else {
            throw new NoSuchElementException("존재하지 않는 게시글입니다.");
        }
        return PostResDto.builder()
                .id(postOptional.get().getId())
                .build();
    }

    //게시글 내용 수정
    public PostResDto updateContent(int id, PostUpdateReqDto req){
        log.info("content: {}", req.getContent());
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            postRepository.updateContentById(id,req.getContent());
        } else {
            throw new NoSuchElementException("게시글을 찾을 수 없습니다");
        }

        return PostResDto.builder()
                .id(postOptional.get().getId())
                .build();
    }
    // 모든 게시글 조회
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public List<PostListResDto> getPostByBoardId(int boardId, Pageable pageable){
        List<Post> posts = postRepository.findAllByBoardIdOrderByCreatedAtDesc(boardId,pageable);
        return posts.stream()
                .map(PostListResDto::new)
                .collect(Collectors.toList());
    }
}
