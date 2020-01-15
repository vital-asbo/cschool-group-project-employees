package spring.employees;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EmpLombok {
    private int ID;
    private String LastName;
    private String FirstName;
    private String Address;
    private String City;
    private int Salary;
    private int Age;
    private Date StartJobDate;
    private int Benefit,
    private String Email;

    private EmpLombok(){}
}
