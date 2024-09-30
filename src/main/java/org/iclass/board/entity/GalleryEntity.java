package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Data   // 불변객체 관련된 메서드 재저의
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    @Column(nullable = false)
    private String title;
    @CreatedDate
    private LocalDateTime createdAt;
    private String fileNames;
    private String writer;

    /*
    create table GALLERY (
        idx number(5) primary key,
        title varchar2(40) not null,
        createdAt date,
        filenames varchar2(200),
        writer varchar2(50)
    )
*/
}
