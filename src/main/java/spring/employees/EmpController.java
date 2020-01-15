package spring.employees;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.EmployeeDao;
import spring.employees.Emp;
import spring.hibernate.Employees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmpController {
    private List<Employees> list;

    public EmpController() {
        list = new ArrayList<>();
        list.add(new Employees(1, "Janek","Kowalski","Zamiejska 12", "Warszawa", 12000,32,new Date("12-03-2015"),6, "j.kowalski@o2.pl"));
//        list.add(new Employees(2, "Zosia", 9000, "Makowiec"));
//        list.add(new Employees(3, "Marek", 10000, "Warszawa"));
//        list.add(new Employees(4, "Krysytna", 13000, "Ryzowice"));
    }

    EmployeeDao employeeDao = new EmployeeDao();

    @RequestMapping("/")
    public String indexGet() {
        return "emp/index";
    }

    @RequestMapping(value = "/empform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("employees", new Employees());
        return "emp/empform";
    }

    @RequestMapping(value = "/save_emp")
    public ModelAndView save(@ModelAttribute(value = "employees") Employees employees) {
        if (employees.getId() == 0) {
            System.out.println("Adding a new emp");
            employeeDao.saveEmployee(employees);
//            employees.setId(list.size() + 1);
//            list.add(employees);
        } else {
            Employees employees1 = getEmployeesById(employees.getId());
            employees1.setFirstName(employees.getFirstName());
            employees1.setLastName(employees.getLastName());
            employees1.setAddress(employees.getAddress());
            employees1.setCity(employees.getCity());
            employees1.setAge(employees.getAge());
            employees1.setSalary(employees.getSalary());
            employees1.setStartJobDate(employees.getStartJobDate());
            employees1.setEmail(employees.getEmail());
            employees1.setBenefit(employees.getBenefit());
        }
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping(value = "/delete_emp", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "emp_id") String emp_id) {
        list.remove(getEmployeesById(Integer.parseInt(emp_id)));
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping(value = "/edit_emp", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam(value = "emp_id") String emp_id) {
        Employees employees = getEmployeesById(Integer.parseInt(emp_id));
        return new ModelAndView("emp/empform", "employees", employees);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView test() {
        System.out.println("Test");
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping("/viewemp")
    public ModelAndView viewemp(Model model) {
        return new ModelAndView("emp/viewemp", "list", list);
    }

    private Employees getEmployeesById(@RequestParam int id) {
        return list.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}