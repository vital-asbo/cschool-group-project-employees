package spring.employees;

import javafx.scene.input.DataFormat;

import java.util.Date;
import java.util.Objects;

public class Emp {


    private int ID;
    private String LastName;
    private String FirstName;
    private String Address;
    private String City;
    private int Salary;
    private int Age;
    private DataFormat StartJobDate;
    private int Benefit;
    private String Email;

    public Emp() {}



    @Override
    public String toString() {
        return "Emp{" +
                "ID=" + ID +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", Salary=" + Salary +
                ", Age=" + Age +
                ", StartJobDate=" + StartJobDate +
                ", Benefit=" + Benefit +
                ", Email='" + Email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return ID == emp.ID &&
                Salary == emp.Salary &&
                Age == emp.Age &&
                Benefit == emp.Benefit &&
                Objects.equals(LastName, emp.LastName) &&
                Objects.equals(FirstName, emp.FirstName) &&
                Objects.equals(Address, emp.Address) &&
                Objects.equals(City, emp.City) &&
                Objects.equals(StartJobDate, emp.StartJobDate) &&
                Objects.equals(Email, emp.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit, Email);
    }

    public int getID() {
        return ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public DataFormat getStartJobDate() {
        return StartJobDate;
    }

    public void setStartJobDate(DataFormat startJobDate) {
        StartJobDate = startJobDate;
    }

    public int getBenefit() {
        return Benefit;
    }

    public void setBenefit(int benefit) {
        Benefit = benefit;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
