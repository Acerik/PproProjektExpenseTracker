package cz.uhk.pproprojektexpensetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractAuditingEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project")
    private List<Transaction> transactions;
}