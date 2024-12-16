package org.board.board.service;

import org.board.board.entity.Board;
import org.board.board.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBoard(Board board);

    void addComment(Long boardNo, String content, String writer);

    void updateComment(Long id , String Content);

}
