package org.board.board.service;

import org.board.board.entity.Board;

import java.util.List;

public interface BoardService {

    void registerBoard(Board board);
    void updateBoard(Long boardNo, String title, String content);
    void deleteBoard(Board board);

    List<Board> getAllBoards();
    Board getBoard(Long boardNo);
}
