package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Component
@Scope("prototype")
@Table(name = "cost_history")
public class CostHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Positive
    private int year;

    @Positive
    private int cost;

    @ManyToOne
    @JoinColumn(name = "business_type_id")
    private LegalPersonBusinessType businessType;


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LegalPersonBusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(LegalPersonBusinessType businessType) {
        this.businessType = businessType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
