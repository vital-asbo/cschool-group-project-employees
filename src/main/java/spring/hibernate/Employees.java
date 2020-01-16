package spring.hibernate;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Employees")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    private int id;

    @Column(name = "LastName")
    @Getter @Setter
    @NonNull
    private String lastName;

    @Column(name = "FirstName")
    @Getter @Setter
    @NonNull
    private String firstName;

    @Column(name = "Address")
    @Getter @Setter
    @NonNull
    private String address;

    @Column(name = "City")
    @Getter @Setter
    @NonNull
    private String city;

    @Column(name = "Salary")
    @Getter @Setter
    @NonNull
    private int salary;

    @Column(name = "Age")
    @Getter @Setter
    @NonNull
    private int age;

    @Column(name = "StartJobDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Getter @Setter
    @NonNull
    private Date startJobDate;

    @Column(name = "Benefit")
    @Getter @Setter
    @NonNull
    private int benefit;

    @Column(name = "Email")
    @Getter @Setter
    @NonNull
    private String email;

    public Employees() {
    }


}
