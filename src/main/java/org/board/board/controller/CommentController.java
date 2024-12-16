package org.board.board.controller;

import org.board.board.entity.Board;
import org.board.board.entity.Comment;
import org.board.board.repository.BoardRepository;
import org.board.board.repository.CommentRepository;
import org.board.board.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentServiceImpl commentServiceImpl;

    @Autowired
    public CommentController(CommentRepository commentRepository, BoardRepository boardRepository, CommentServiceImpl commentServiceImpl) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping("/comments/{boardNo}")
    public List<Comment> getCommentsByBoardNo(@PathVariable Long boardNo) {
        return commentRepository.findByBoardBoardNo(boardNo);
    }

    // 댓글 추가
    @PostMapping("/comments/add")
    public String addComment(@RequestParam Long boardNo, @RequestParam String writer, @RequestParam String content) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setWriter(writer);
        comment.setContent(content);

        commentRepository.save(comment);

        return "redirect:/board/detail/" + boardNo;  // 댓글 추가 후 게시글 상세 페이지로 리다이렉트
    }

    // 댓글 삭제
    @PostMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam Long boardNo) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);

        // 댓글 삭제 후 해당 게시글 상세 페이지로 리다이렉트
        return "redirect:/board/detail/" + boardNo;
    }

    //댓글 수정
    @PostMapping("/comments/update/{id}")
    @ResponseBody
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestParam String content) {
        try {
            commentServiceImpl.updateComment(id, content); // 댓글 업데이트 로직 수행
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("success", false));
        }
    }
}
