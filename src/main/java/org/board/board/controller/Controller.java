package org.board.board.controller;

import org.board.board.entity.Board;
import org.board.board.entity.Comment;
import org.board.board.service.BoardService;
import org.board.board.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller

@RequestMapping("/board")
public class Controller {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("title", "대쉬보드");
        return "index";
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        model.addAttribute("tables", boardService.getAllBoards());
        return "tables";
    }

    @GetMapping("/board")
    public String getPosts(Model model) {
        model.addAttribute("board", boardService.getAllBoards());
        return "index";  // `posts.html` 템플릿을 반환
    }


    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerBoard(@RequestParam String title,
                                @RequestParam String writer,
                                @RequestParam String content) {

        // Board 객체 생성
        Board board = new Board();
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);

        // 서비스 레벨에서 등록 처리
        boardService.registerBoard(board);

        return "redirect:/"; // 등록 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/detail/{boardNo}")
    public String getBoardDetail(@PathVariable Long boardNo, Model model) {
        Board board = boardService.getBoard(boardNo);  // 게시글 조회
        List<Comment> comments = commentServiceImpl.getCommentsByBoard(board);  // 댓글 조회
        model.addAttribute("board", board);
        model.addAttribute("comments", comments);  // 댓글 목록 추가
        return "tableDetail";  // 게시글 상세 페이지로 리턴
    }

    @GetMapping("detailupdate/{boardNo}")
    public String detailUpdate(@PathVariable Long boardNo, Model model) {
        Board board = boardService.getBoard(boardNo);
        model.addAttribute("board", board);
        return "tableDetailupdate";
    }

    @PostMapping("/update")
    public String updateBoard(@RequestParam("boardNo") Long boardNo,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content) {
        boardService.updateBoard(boardNo, title, content);
        return "redirect:/tables";
    }
}
