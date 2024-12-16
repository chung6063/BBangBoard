package org.board.board.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity  // 엔티티로 선언
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")  // board_no와 매핑
    private Board board;

    private LocalDateTime createdAt = LocalDateTime.now();  // 기본값 설정
}
