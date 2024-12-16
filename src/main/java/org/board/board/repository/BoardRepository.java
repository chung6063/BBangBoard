package org.board.board.repository;

import jakarta.transaction.Transactional;
import org.board.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.title = :title, b.content = :content WHERE b.boardNo = :board_no")
    void updateBoard(@Param("title") String title, @Param("content") String content, @Param("board_no") Long boardNo);

}
