package com.pegasus.pegasus.repositories;

import java.util.List;

// Importações necessárias
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pegasus.pegasus.entities.Project;
import com.pegasus.pegasus.DTOs.ProjectInfo;

// A anotação @Repository indica que esta interface é um repositório gerenciado pelo Spring.
@Repository("projectsRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Método para encontrar informações resumidas de projetos por ID de usuário
    @Query("SELECT NEW com.pegasus.pegasus.DTOs.ProjectInfo(p.id, p.name, p.description) FROM Project p WHERE p.idUser = :userId")
    List<ProjectInfo> findAllProjectsByUserId(@Param("userId") Long userId);
}
