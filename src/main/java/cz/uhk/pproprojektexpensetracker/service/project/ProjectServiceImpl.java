package cz.uhk.pproprojektexpensetracker.service.project;

import cz.uhk.pproprojektexpensetracker.dto.ProjectListItemDto;
import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.repository.ProjectRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl extends AbstractServiceImpl<Project> implements ProjectService {


    @Override
    public List<ProjectListItemDto> getProjectsListByUserId(Long userId) {
        return ((ProjectRepository) repository).findAllByUserIdIs(userId).stream()
                .map(project -> ProjectListItemDto.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .spentAmount(project.getTransactions().stream().mapToDouble(Transaction::getAmount).sum())
                        .lastTransactionDate(project.getTransactions().getLast().getDate())
                        .sumOfTransactions((long) project.getTransactions().size())
                        .build())
                .toList();
    }

    @Override
    public List<Project> getAllByUserId(Long userId) {
        return ((ProjectRepository) repository).findAllByUserIdIs(userId);
    }

    @Override
    public Project getProjectByIdAndUserId(Long id, Long userId) {
        return ((ProjectRepository) repository).findByIdAndUserIdIs(id, userId).orElse(null);
    }

}
