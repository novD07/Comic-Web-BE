package com.example.comicwebbe.dto;

import com.example.comicwebbe.entity.Category;
import lombok.Data;
import org.attoparser.dom.Text;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateStoryRequest {
    private String ten;
    private MultipartFile avtFile;
    private String gioithieu;
    private String tacgia;
    private LocalDate thoi_gian_dang;
    private Integer view;
    private List<Long> idTheLoais;
}
