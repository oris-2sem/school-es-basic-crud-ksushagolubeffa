package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.response.LessonResponse;
import org.example.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<LessonResponse>> getAll() {
        return ResponseEntity.ok(service.getLessons());
    }

    @GetMapping("/getByTeacher/{id}")
    public ResponseEntity<List<LessonResponse>> getByTeacher(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getLessonsByTeacher(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody LessonResponse lessonResponse, @RequestParam("lesson-id")Long timetableId) {
        service.addLesson(lessonResponse, timetableId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deleteLesson(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(LessonResponse lessonResponse, @PathVariable("id") Long id) {
        service.updateLesson(lessonResponse, id);
        return ResponseEntity.accepted().build();
    }
}
