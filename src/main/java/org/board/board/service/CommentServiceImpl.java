package org.board.board.service;

import org.board.board.entity.Board;
import org.board.board.entity.Comment;
import org.board.board.repository.CommentRepository;
import org.board.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Comment> getCommentsByBoard(Board board) {
        return commentRepository.findByBoardBoardNo(board.getBoardNo());  // board_no로 댓글 조회
    }

    @Override
    public void addComment(Long board_no, String content, String writer) {
        // board_no로 Board 객체를 조회
        Board board = boardRepository.findById(board_no)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.setBoard(board);  // Board 객체 설정
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setCreatedAt(LocalDateTime.now());  // 댓글 작성일시 설정

        commentRepository.save(comment);  // 댓글 저장
    }

    @Override
    public void updateComment(Long id, String content) {
            Comment comment = commentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
            comment.setContent(content);
            commentRepository.save(comment);
        }
    }

