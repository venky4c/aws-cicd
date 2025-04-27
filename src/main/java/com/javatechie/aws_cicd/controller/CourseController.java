package com.javatechie.aws_cicd.controller;

import com.javatechie.aws_cicd.dto.Course;
import com.javatechie.aws_cicd.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        service.addCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping(value ="/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable int id){
        return ResponseEntity.of(service.getCourseById(id));
//        Optional<Course> course = courseService.getCourseById(id);
//        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> allCourses = service.getAllCourses();
        return new ResponseEntity<>(allCourses,OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course newCourse){
        boolean updated = service.updateCourse(id, newCourse);
        if (updated) {
            return new ResponseEntity<>(newCourse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id){
        boolean deleted = service.deleteCourseById(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/health")
    public String healthCheck(){
        return "UP and Running!";
    }

    @GetMapping(value="/welcome")
    public String greetings(){
        return "Hey there, I am working fine!";
    }
}
