package org.iclass.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.entity.GalleryEntity;
import org.iclass.board.repository.GalleryUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GalleryUploadService {

    private final GalleryUploadRepository uploadRepository;

    public void uploadGallery(GalleryDTO dto) throws IOException {
        MultipartFile file = dto.getFile();
        //        server directory  => c:\\upload
        String path = "c:\\upload";
        if(file.getSize() != 0) {
//            diretory에 저장하려면 java.io.File 객체를 생성합니다.
            File pathFile = new File(path + "\\" + file.getOriginalFilename() );
//            파일 전송: MultipartFile 객체를 파일시스템으로 저장(전송)
            file.transferTo(pathFile);
//            db에 저장할 파일명 저장
            dto.setFileNames(file.getOriginalFilename());
//            db 테이블에 저장될 값 확인
            log.info(":::dto: {}:::", dto);
            uploadRepository.save(dto.toEntity());
        }
    }

//    람다식 안에서 리턴 받을 변수는 전역 변수만 가능
//    람다식은 함수형 인터페이스(푸사아메서드가 1개인 인터페이스)를 익명 클래스로 구현한 것
    GalleryDTO dto = null;
/*    public GalleryDTO one(int i) {
//        GalleryEntity entity = uploadRepository.findById(i);
        Optional<GalleryEntity> optional = uploadRepository.findById(i);
        optional.ifPresent(e -> {
            GalleryEntity entity = optional.get();
            dto = GalleryDTO.of(entity);
            log.info(":::조회한 entity: {}:::", entity);
        });
        return dto;
    }*/

    public List<GalleryDTO> findAll() {
//        GalleryEntity entity = uploadRepository.findById(i);
        List<GalleryEntity> list = uploadRepository.findAll();
        return list.stream().map(GalleryDTO::of).collect(Collectors.toList());
    }
}
