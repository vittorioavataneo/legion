package org.generation.italy.legion.controllers;

import jakarta.validation.Valid;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Address;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractAddressDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    private AbstractAddressDidacticService service;

    @Autowired
    public AddressController(AbstractAddressDidacticService service) {
        this.service = service;
    }

    @GetMapping("/showAddressInsertForm")
    public String showCourseForm(Course course){
        return "add_address";
    }

    @GetMapping("/saveAddress")
    public String insertAddress(Model m, @Valid Address address, BindingResult result) {
        if (result.hasErrors()) {
            return "add_address";
        }
        try {
            service.createEntity(address);
            return "add_teacher";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }
}
