package com.example.comicwebbe.controller;

import com.example.comicwebbe.dto.AddChapterRequest;
import com.example.comicwebbe.dto.UpdateChapterRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{storyId}")
    public ResponseEntity<List<Chapter>> getChaptersByStoryId(@PathVariable Long storyId) {
        try {
            // Gọi phương thức findListByStoryId trong ChapterService để lấy danh sách Chapter dựa trên ID của bộ truyện
            List<Chapter> chapters = chapterService.findListByStoryId(storyId);
            return ResponseEntity.ok(chapters); // Trả về danh sách Chapter nếu tồn tại

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Xử lý lỗi nếu có
        }
    }
    @GetMapping("/get/{chapterId}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable Long chapterId) {
        try {
            Optional<Chapter> chapterOptional = chapterService.findById(chapterId);
            if (chapterOptional.isPresent()) {
                return ResponseEntity.ok(chapterOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{storyId}/add")
    public ResponseEntity<String> addChapterToStory(@PathVariable Long storyId, @ModelAttribute AddChapterRequest addChapterRequest) {
        try {
            chapterService.addChapter(storyId, addChapterRequest);
            return ResponseEntity.ok("Thêm chương thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }
    @DeleteMapping("/remove/{chapterId}")
    public ResponseEntity<String> deleteChapter(@PathVariable Long chapterId) {
        try {
            chapterService.deleteChapter(chapterId);
            return ResponseEntity.ok("Xóa thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/update/{chapterId}")
    public ResponseEntity<String> updateChapter(@PathVariable Long chapterId, @ModelAttribute UpdateChapterRequest updateChapterRequest){
        try{
            chapterService.updateChapter(chapterId, updateChapterRequest);
            return ResponseEntity.ok("Sửa chapter thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
