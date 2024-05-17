package com.ezen.www.handler;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.stream.events.Comment;
import java.util.List;

@Getter
@Setter
@ToString
public class PagingHandler {

    private int startPage;
    private int endPage;
    private boolean prev,next;
    private int totalCount;
    private int realEndPage;
    private PagingVO pgvo;

    private List<CommentVO> cmtList;

    public PagingHandler(PagingVO pgvo, int totalCount){
        this.pgvo = pgvo;
        this.totalCount = totalCount;


        //pageNo => 1 / 10=> 0.1(올림) => 1*10 => 10
        //pageNo => 2 / 10=> 0.2(올림) => 1*10 => 10
        //pageNo => 9 / 10=> 0.9(올림) => 1*10 => 10
        //pageNo => 11 / 10=> 1.1(올림) => 2*10 => 20
        //
        this.endPage =(int) Math.ceil(pgvo.getPageNo() / (double)10)*10;
        this.startPage = endPage -9;

        // 진짜 끝 페이지
        //103 -> 103 / 10 => 10.3 => 11페이지

        this.realEndPage =(int) Math.ceil(totalCount/ (double)pgvo.getQty());

        if(realEndPage < endPage) {
            this.endPage = realEndPage;

        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEndPage;
    }

//    comment 용 생성자 추가
    public PagingHandler(PagingVO pgvo, int totalCount , List<CommentVO> cmtList){
        this(pgvo,totalCount);
        this.cmtList = cmtList;
    }


}
