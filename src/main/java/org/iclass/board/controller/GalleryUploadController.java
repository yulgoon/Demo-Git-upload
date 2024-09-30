package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.entity.GalleryEntity;
import org.iclass.board.repository.GalleryUploadRepository;
import org.iclass.board.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GalleryUploadController {

    private final GalleryUploadService uploadService;

    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<GalleryDTO> list = uploadService.findAll();
        model.addAttribute("list", list);
        log.info(":::list: {}:::", list.toString());
        return "gallery";
    }

    @PostMapping("/gallery")
    public String upload(GalleryDTO dto) throws IOException {
        MultipartFile file = dto.getFile(); // gallery.html form 태그 안의 <input type="file" accept="image/*" name="file">
        log.info(":::title: {}:::", dto.getTitle());
        log.info(":::file_name: {}:::", file.getOriginalFilename());
        log.info(":::file_size: {}:::", file.getSize());

        uploadService.uploadGallery(dto);

        return "redirect:/gallery";
    }
}
