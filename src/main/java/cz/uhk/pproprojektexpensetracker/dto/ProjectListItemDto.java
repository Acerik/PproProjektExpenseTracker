package cz.uhk.pproprojektexpensetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProjectListItemDto {

    private Long id;

    private String name;

    private Double spentAmount;

    private Long sumOfTransactions;

    private LocalDate lastTransactionDate;
}
