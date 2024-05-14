package com.ezen.www.service;


import com.ezen.www.domain.BoardVO;

import java.util.List;

public interface BoardService {
    int register(BoardVO bvo);


    Object getList();

    Object getDetail(long bno);

    void update(BoardVO bvo);

    void delete(long bno);
}
