package com.ezen.www.controller;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")

public class BoardController {
    private final BoardService bsv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(BoardVO bvo, @RequestParam(value = "files",required = false)MultipartFile[] files) {
        log.info(">>>> bvo >>>{}", bvo);
        List<FileVO> flist = null;
        if(files[0].getSize() > 0 || files != null){
            flist = fh.uploadFiles(files);
        }
        int isOk = bsv.register(new BoardDTO(bvo,flist));
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model m, PagingVO pgvo) {
        log.info(">>> pgvo >>{}",pgvo);
        //totalCount 가져오기
        int totalCount = bsv.getTotalCount(pgvo);

        // pagingHandler
        PagingHandler ph = new PagingHandler(pgvo,totalCount);
        m.addAttribute("ph",ph);

        m.addAttribute("list", bsv.getList(pgvo));
    }

    @GetMapping("/detail")
    public void detail(Model m, @RequestParam("bno") long bno){
        m.addAttribute("bdto",bsv.getDetail(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO bvo){
        bsv.update(bvo);
        return "redirect:/board/detail?bno="+bvo.getBno();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")long bno){
        bsv.delete(bno);
        return "redirect:/board/list";
    }
}
