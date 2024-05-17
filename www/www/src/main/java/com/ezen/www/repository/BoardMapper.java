package com.ezen.www.repository;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insert(BoardVO bvo);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(long bno);

    void delete(long bno);

    void update(BoardVO bvo);

    int getTotalCount(PagingVO pgvo);

    long getBno();
}
