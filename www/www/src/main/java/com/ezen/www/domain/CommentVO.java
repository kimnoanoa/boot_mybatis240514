package com.ezen.www.domain;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private long bno;
    private long cno;
    private String writer;
    private String content;
    private String regAt;
    private String modAt;

}
