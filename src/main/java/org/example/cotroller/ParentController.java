package org.example.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.response.ParentResponse;
import org.example.service.ParentService;

import java.util.List;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<ParentResponse>> getAll() {
        return ResponseEntity.ok(service.getParents());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ParentResponse> getParent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getParent(id));
    }

    @GetMapping("/getByStudent/{id}")
    public ResponseEntity<ParentResponse> getByStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getParentByStudent(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ParentResponse parentResponse) {
        service.addParent(parentResponse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long parentId) {
        service.deleteParent(parentId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(ParentResponse parentResponse, @PathVariable("id")Long parentId) {
        service.updateParent(parentResponse, parentId);
        return ResponseEntity.accepted().build();
    }
}
