package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.response.StudentResponse;
import org.example.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponse>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/getByClass/{id}")
    public ResponseEntity<List<StudentResponse>> getByClass(@PathVariable("id")Long id) {
        return ResponseEntity.ok(service.getStudentsByClass(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("id")Long id) {
        return ResponseEntity.ok(service.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody StudentResponse studentResponse, @RequestParam("idParent")Long idParent,
                                 @RequestParam("idClass")Long idClass) {
        service.addStudent(studentResponse, idParent, idClass);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        service.deleteStudent(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(StudentResponse studentResponse, @PathVariable("id")Long id) {
        service.updateStudent(studentResponse, id);
        return ResponseEntity.accepted().build();
    }
}
