package org.generation.italy.legion.model.data.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.implementations.InMemoryCourseRepository;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.implementations.StandardDidacticService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.generation.italy.legion.model.data.implementations.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class StandardDidacticServiceTest {
    private Course c1;
    private Course c2;
    private Course c3;
    private CourseRepository repo;
    private StandardDidacticService service;

    @BeforeEach
    void setUp(){
        repo = new InMemoryCourseRepository();
        service = new StandardDidacticService(repo);
        c1 = new Course(0, TITLE, DESCRIPTION, PROGRAM, DURATION,true, LocalDate.of(2021,12,3));
        c2 = new Course(0, TITLE2, DESCRIPTION2, PROGRAM2, DURATION2,false ,LocalDate.of(2020,1,14));
        c3 = new Course(0, TITLE3, DESCRIPTION3, PROGRAM3, DURATION3, true, LocalDate.of(2018,6,20));
        try {
            repo.create(c1);
            repo.create(c2);
            repo.create(c3);
        } catch (DataException e) {
            fail("Errore nell'inserimento corsi nel setup: "+ e.getMessage());
        }
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void adjustActiveCourses_should_return_false_when_actives_already_less_than_desired() {

        try {
            boolean result = service.adjustActiveCourses(2);
            assertFalse(result);
            assertEquals(true, c1.isActive());
            assertEquals(false, c2.isActive());
            assertEquals(true, c3.isActive());
        } catch (DataException e) {
            fail("Erroreeee");
        }
    }

    @Test
    void adjustActiveCourses_should_deactivate_course_when_too_old(){
        try {
            assertTrue(service.adjustActiveCourses(1));
            assertTrue(c1.isActive());
            assertFalse(c2.isActive());
            assertFalse(c3.isActive());
        } catch (DataException e) {
            fail("Erroreeee");
        }
    }

}