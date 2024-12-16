package org.board.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no") // 데이터베이스 컬럼 이름과 일치시킴
    private Long boardNo;

    private String title;
    private String writer;
    private String content;
    private Long view = 0L;
    private Long suggest = 0L;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
