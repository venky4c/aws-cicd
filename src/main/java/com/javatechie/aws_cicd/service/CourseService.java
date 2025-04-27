package com.javatechie.aws_cicd.service;

import com.javatechie.aws_cicd.dto.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final List<Course> allCourses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void addCourse(Course course){
            allCourses.add(course);
    }

    public Optional<Course> getCourseById(int id) {
        return allCourses.stream().filter(course -> course.getId() == id).findFirst();
    }

    public boolean updateCourse(int id, Course newCourse){
        return getCourseById(id).map(existingCourse -> {
            allCourses.remove(existingCourse);
            allCourses.add(newCourse);
            return true;
        }).orElse(false);
    }

    public boolean deleteCourseById(int id) {
        return allCourses.removeIf(course -> course.getId() == id);
    }
}
