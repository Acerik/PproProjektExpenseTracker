package cz.uhk.pproprojektexpensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction_tag")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TransactionTag extends AbstractAuditingEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color = "#00AA00";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
