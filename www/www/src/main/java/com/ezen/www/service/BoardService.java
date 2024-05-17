package com.ezen.www.service;


import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

import java.util.List;

public interface BoardService {
    int register(BoardDTO bdto);


    Object getList(PagingVO pgvo);

    BoardDTO getDetail(long bno);

    void update(BoardVO bvo);

    void delete(long bno);

    int getTotalCount(PagingVO pgvo);
}
