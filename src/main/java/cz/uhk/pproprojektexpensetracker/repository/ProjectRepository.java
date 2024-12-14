package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends AbstractRepository<Project> {

    List<Project> findAllByUserIdIs(Long userId);

    Optional<Project> findByIdAndUserIdIs(Long id, Long userId);
}
