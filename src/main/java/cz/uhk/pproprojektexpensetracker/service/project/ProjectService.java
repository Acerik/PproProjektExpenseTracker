package cz.uhk.pproprojektexpensetracker.service.project;

import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;

public interface ProjectService extends AbstractService<Project> {

    List<Project> getAllByUserId(Long userId);

    Project getProjectByIdAndUserId(Long id, Long userId);
}
