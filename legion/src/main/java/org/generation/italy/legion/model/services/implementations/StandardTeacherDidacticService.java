package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandardTeacherDidacticService implements AbstractTeacherDidacticService {
    private TeacherRepository repo;

    @Autowired
    public StandardTeacherDidacticService(TeacherRepository repo){
        this.repo = repo;
        System.out.println(this.repo.getClass().getName());

    }
    public void setRepo(TeacherRepository repo) { // setter injection
        this.repo = repo;
    }

    @Override
    public Iterable<Teacher> findTeachersWithCompetenceByLevel(Level teacherLevel) throws DataException {
        return repo.findWithCompetenceByLevel(teacherLevel);
    }

    @Override
    public Iterable<Teacher> findTeachersWithSkillAndLevel(long idSkill, Level competenceLevel) {
        return repo.findWithSkillAndLevel(idSkill, competenceLevel);
    }

    @Override
    public List<Teacher> findAllEntity() throws DataException {
        return repo.findAll();
    }

    @Override
    public Optional<Teacher> findEntityById(long id) throws DataException {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public Teacher createEntity(Teacher entity) throws DataException {
        return repo.create(entity);
    }

    @Override
    public void updateEntity(Teacher entity) throws EntityNotFoundException, DataException {
        repo.update(entity);
    }

    @Override
    public void deleteEntityById(long id) throws EntityNotFoundException, DataException {
        repo.deleteById(id);
    }
}
