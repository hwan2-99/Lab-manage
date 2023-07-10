package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.post.dto.PostDto;
import com.example.dnlab.domain.board.entity.Board;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.board.repository.BoardRepository;
import com.example.dnlab.domain.post.repository.PostRepository;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    // 게시글 작성
    public void createPost(PostDto.postReq req, int board_num){
        User user = (User)session.getAttribute("user");
        int userNum = user.getNum();

        // 게시판 선택 시 게시판 pk 불러오기
        Board board = boardRepository.getBoardByNum(board_num);


        Post post = Post.builder()
                .board(board)
                .title(req.getTitle())
                .content(req.getContent())
                .user(user)
                .createdAt(LocalDate.now().atStartOfDay())
                .build();

        postRepository.save(post);
    }


    // 게시글 삭제
    public void deletePost(int num){
        Optional<Post> postOptional = postRepository.findById(num);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
        } else {
            throw new NoSuchElementException("존재하지 않는 게시글입니다.");
        }
    }

    //게시글 내용 수정
    public void updateContent(int num, PostDto.updateReq req){
        log.info("content: {}", req.getContent());
        Optional<Post> postOptional = postRepository.findById(num);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Post updatedPost = Post.builder()
                    .num(post.getNum())
                    .title(post.getTitle())
                    .content(req.getContent())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .user(post.getUser())
                    .board(post.getBoard())
                    .build();

            postRepository.save(updatedPost);

        } else {
            throw new NoSuchElementException("게시글을 찾을 수 없습니다");
        }

    }
    // 모든 게시글 조회
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public List<Post> getPostByBoardNum(int boardNum){
        return postRepository.findAllByBoardNum(boardNum);
    }
}
