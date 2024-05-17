package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    private String uuid;
    private String save_dir;
    private String file_name;
    private int file_type;
    private long bno;
    private long file_size;
    private String reg_date;


}
