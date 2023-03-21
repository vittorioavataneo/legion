package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;

public interface AbstractTeacherDidacticService extends AbstractCrudDidacticService<Teacher> {
    Iterable<Teacher> findTeachersWithCompetenceByLevel(Level teacherLevel) throws DataException;
    Iterable<Teacher> findTeachersWithSkillAndLevel(long idSkill, Level competenceLevel) ;

}
