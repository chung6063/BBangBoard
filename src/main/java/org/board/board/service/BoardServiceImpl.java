package org.board.board.service;

import org.board.board.entity.Board;
import org.board.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoard(Long boardNo) {
        Optional<Board> optionalBoard = boardRepository.findById(boardNo);
        if (optionalBoard.isPresent()) {
            return optionalBoard.get();
        } else {
            throw new IllegalArgumentException("번호를 찾을수 없습니다." + boardNo);
        }
    }

    @Override
    public void registerBoard(Board board) {
        // 현재 시간을 설정
        LocalDate now = LocalDate.now();

        // Board 객체에 생성일시 및 수정일시 설정
        board.setCreatedAt(now);
        board.setUpdatedAt(now);

        // Board를 데이터베이스에 저장
        boardRepository.save(board);
    }

    public void updateBoard(Long boardNo, String title, String content) {
        boardRepository.updateBoard(title, content, boardNo); // boardNo 전달
    }

    @Override
    public void deleteBoard(Board board) {

    }


}
