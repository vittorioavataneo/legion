package org.generation.italy.legion.controllers;

import jakarta.validation.Valid;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {
    private AbstractDidacticService service;
    @Autowired
    public CourseController(AbstractDidacticService service) {
        this.service = service;
        System.out.println(this.service.getClass().getName());
    }
    @GetMapping("/home")
    public String showHome(){
        return "home";
    }

    @GetMapping("/")
    public String goHome(){
        return "home";
    }

    @GetMapping("/showCourseInsertForm")
    public String showCourseForm(Course course){
        return "add_new_course";
    }

    @PostMapping("/saveCourse")
    public String insertCourse(Model m, @Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "add_new_course";
        }
        try {
            service.saveCourse(course);
            return "redirect:/indexCourse";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/indexCourse")
    public String showCourses(Model m){
        try {
            List<Course> courseList = service.findAllCourses();
            m.addAttribute("find_course", courseList);
            return "find_course";
        }catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/findCourseById")
    public String findById(Model m, long courseId){
        try {
            Optional<Course> c = service.findCourseById(courseId);
            /*Course x = null;
            if (c.isPresent()){
                x=c.get();
            }else {
                x = new Course();
            }*/
            Course found = c.orElse(new Course());
            m.addAttribute("course", found);
            return "course_detail";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/findCoursesByTitleContains")
    public String findByName(Model m, String part){
        try {
            List<Course> courseList = service.findCoursesByTitleContains(part);
            m.addAttribute("courses", courseList);
            return "list_courses";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }


}
