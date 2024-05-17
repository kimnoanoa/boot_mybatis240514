package com.ezen.www.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BoardDTO {
    private BoardVO bvo;
    private List<FileVO> flist;
}
