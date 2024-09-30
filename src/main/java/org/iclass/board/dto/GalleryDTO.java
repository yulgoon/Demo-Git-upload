package org.iclass.board.dto;

import lombok.*;
import org.iclass.board.entity.GalleryEntity;
import org.iclass.board.repository.GalleryUploadRepository;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class GalleryDTO {

    private int idx;
    private String title;
    private LocalDateTime createdAt;
    private String fileNames;   // 테이블에는 업로드 파일의 파일명을 저장
    private String writer;

//    업로드 파일을 저장하기 위한 객체
    private MultipartFile file;
    private final GalleryUploadRepository uploadRepository;
    
//    GalleryDTO.of(): entity를 받아서 인자로 DTO 생성하기
//    보통 조회(find...etc. 메서드)할 때 결과 리턴타입이 entity
    public static GalleryDTO of(GalleryEntity entity) {
        return GalleryDTO.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .fileNames(entity.getFileNames())
                .writer(entity.getWriter())
                .build();
    }

//    인스턴스객체.toEntity(): 현재 객체 인스턴스 값으로 entity 객체 생성하기
//    보통 save, saveAll, delete, etc.할 때 메서드 인자 entity
    public GalleryEntity toEntity() {
//        return new GalleryEntity(,,,,);
        return GalleryEntity.builder()
                .idx(this.idx)
                .title(this.title)
                .fileNames(this.fileNames)
                .writer(this.writer)
                .build();   // createdAt은 auditing 필드
    }

}
