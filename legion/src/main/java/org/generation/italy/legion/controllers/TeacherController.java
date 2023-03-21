package org.generation.italy.legion.controllers;

import jakarta.validation.Valid;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Address;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {
    private AbstractTeacherDidacticService service;

    @Autowired
    public TeacherController(AbstractTeacherDidacticService service) {
        this.service = service;
    }

    @GetMapping("/showTeacherInsertForm")
    public String showTeacherForm(Teacher teacher){
        return "add_teacher";
    }

    @GetMapping("/saveTeacher")
    public String insertTeacher(Model m, @Valid Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "add_teacher";
        }
        try {
            service.createEntity(teacher);
            return "home";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/indexTeacher")
    public String indexTeacher(Model m){
        try {
            List<Teacher> teacherList = service.findAllEntity();
            m.addAttribute("find_teacher_by_skill_level", teacherList);
            return "find_teacher_by_skill_level";
        }catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/findTeachersWithSkillAndLevel")
    public String findTeachersWithSkillAndLevel(Model m, long idSkill, Level skillLevel){
        Iterable<Teacher> teachersIterable = service.findTeachersWithSkillAndLevel(idSkill, skillLevel);
        m.addAttribute("teachers", teachersIterable);
        return "list_teacher";
    }

    @GetMapping("/findTeacherById")
    public String findTeacherById(Model m,long id){
        try {
            Optional<Teacher> t = service.findEntityById(id);
            Teacher found = t.orElse(new Teacher());
            m.addAttribute("teacher", found);
            return "teacher_detail";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

}
