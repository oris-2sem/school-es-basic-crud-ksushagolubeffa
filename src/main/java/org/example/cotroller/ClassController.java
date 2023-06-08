package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.response.ClassResponse;
import org.example.service.ClassService;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService service;

    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAll() {
        return ResponseEntity.ok(service.getClasses());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ClassResponse classResponse, @RequestParam("id") Long id) {
        service.addClass(classResponse, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        service.deleteClass(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(ClassResponse classResponse, @PathVariable("id")Long id) {
        service.updateClass(classResponse, id);
        return ResponseEntity.accepted().build();
    }
}
