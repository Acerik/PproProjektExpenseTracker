package cz.uhk.pproprojektexpensetracker.service.project;

import cz.uhk.pproprojektexpensetracker.dto.ProjectListItemDto;
import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.repository.ProjectRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl extends AbstractServiceImpl<Project> implements ProjectService {


    @Override
    public List<ProjectListItemDto> getProjectsListByUserId(Long userId) {
        return ((ProjectRepository) repository).findAllByUserIdIs(userId).stream()
                .map(this::mapProjectListItemDto)
                .toList();
    }

    @Override
    public List<Project> getAllByUserId(Long userId) {
        return ((ProjectRepository) repository).findAllByUserIdIs(userId);
    }

    @Override
    public Optional<Project> findOneByIdAndUserId(Long id, Long userId) {
        Optional<Project> project = findOne(id);
        return project.filter(value -> Objects.equals(value.getUser().getId(), userId));
    }

    @Override
    public ProjectListItemDto getProjectByIdAndUserId(Long id, Long userId) {
        Project project = ((ProjectRepository) repository).findByIdAndUserIdIs(id, userId)
                .orElse(null);
        return project != null ? mapProjectListItemDto(project) : null;
    }

    private ProjectListItemDto mapProjectListItemDto(Project project) {
        return ProjectListItemDto.builder()
                .id(project.getId())
                .name(project.getName())
                .spentAmount(project.getTransactions().stream().mapToDouble(Transaction::getAmount).sum())
                .lastTransactionDate(getLastTransactionDate(project.getTransactions()))
                .sumOfTransactions((long) project.getTransactions().size())
                .build();
    }

    private LocalDate getLastTransactionDate(List<Transaction> transactions) {
        return CollectionUtils.isEmpty(transactions)
                ? null
                : transactions.stream().max(Comparator.comparing(Transaction::getDate)).get().getDate();
    }

}
