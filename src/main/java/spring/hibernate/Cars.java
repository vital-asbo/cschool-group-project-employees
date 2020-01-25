package spring.hibernate;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cars")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Cars implements HibernateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NonNull
    public Employees employees;

    @Column(name = "Name")
    @NonNull
    private String name;

    @Column(name = "Model")
    @NonNull
    private String model;

    @Column(name = "YearOfRegistration")
    @ToString.Exclude
    @NonNull
    private Date YearOfRegistration;

    public Cars() {
    }
}
