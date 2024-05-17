package com.ezen.www.handler;

import com.ezen.www.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileHandler {
    private final String UP_DIR = "/Users/kimnoa/Desktop/noa/_myProject/_java/fileupload/";

    public List<FileVO> uploadFiles(MultipartFile[] files) {
        // 리턴 객체 생성
        List<FileVO> flist = new ArrayList<>();
        // 오늘날짜 경로생성
        LocalDate date = LocalDate.now();
        String today = date.toString();


        // 오늘날짜를 폴더형식으로 구성
        today = today.replace("-", File.separator);
        File folders = new File(UP_DIR,today);
        // 폴더생성 => mkdir(폴더 한개생성), mkdirs(하위 폴더까지 구조로 생성)
        //exists : 있는지 없는지 확인
        if(!folders.exists()) {
            folders.mkdirs(); //폴더생성 명령
        }
        for(MultipartFile file : files) {
            FileVO fvo = new FileVO();
            fvo.setSave_dir(today);
            fvo.setFile_size(file.getSize()); //return long

            // getOriginalFilename() : 경로+파일명 / 파일경로를 포함하는 케이스도 있음
            String originalFileName = file.getOriginalFilename();
            String onlyFileName = originalFileName.substring(
                    originalFileName.lastIndexOf(File.separator)+1);
            fvo.setFile_name(onlyFileName);

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            fvo.setUuid(uuidStr);

            //디스크에 저장
            // 디스크에 저장할 파일객체 생성 -> 저장
            //uuid_fileName / uuid_th_fileName

            String fullFileName = uuidStr +"_"+onlyFileName;
            File storeFile = new File(folders,fullFileName);

            // 저장 => 경로 또는 파일이 엇다면 IO 발생
            try {
                file.transferTo(storeFile);
                if(isImageFile(storeFile)) {
                    fvo.setFile_type(1);

                    // 썸네일생성
                    File thumbNail = new File(folders,uuidStr+"_th_"+onlyFileName);
                    Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);


                }
                //파일타입 결정 -> 이미지만 썸네일 저장
            } catch (Exception e) {

                e.printStackTrace();
            }

            flist.add(fvo);
        }


        return flist;

    }

    private boolean isImageFile(File file) throws IOException {
        String mimeType = new Tika().detect(file);
        return mimeType.startsWith("image")? true:false;

    }
}