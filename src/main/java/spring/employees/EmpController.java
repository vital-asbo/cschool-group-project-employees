package spring.employees;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.EmployeeDao;
import spring.hibernate.Employees;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {
    private List<Employees> list;

    public EmpController() {
//        list = new ArrayList<>();
//        list.add(new Employees ("Janek", "Kowalski","Zamiejska 12", "Warszawa",12000,32,new Date("12-05-2014"),4,"m.k@wp.pl"));
////        list.add(new Employees(2, "Zosia", 9000, "Makowiec"));
////        list.add(new Employees(3, "Marek", 10000, "Warszawa"));
////        list.add(new Employees(4, "Krysytna", 13000, "Ryzowice"));
    }

    EmployeeDao employeeDao = new EmployeeDao();

    @RequestMapping("/")
    public String indexGet() {
        return "emp/index";
    }


    @RequestMapping(value = "/empform", method = RequestMethod.GET)
    public ModelAndView showform(Model model) {
//        model.addAttribute("employees", new Employees ());
        return new ModelAndView("emp/empform", "employees", new Employees());

    }

    @RequestMapping(value = "/save_emp")
    public ModelAndView save(@ModelAttribute(value = "employees") Employees employees) {
        if (employees.getId() == 0) {
            System.out.println("Adding a new emp");
            employeeDao.saveEmployee(employees);
//            employees.setId(list.size() + 1);
//            list.add(employees);
        } else {
            employeeDao.updateEmployees(employees);

        }
        return new ModelAndView("redirect:/viewemp");
    }



//    @RequestMapping(value = "/delete_emp", method = RequestMethod.POST)
    @RequestMapping(value = "/delete_emp")
    public ModelAndView delete(@RequestParam(value = "emp_id") String emp_id) {
//        list.remove(getEmployeesById(Integer.parseInt(emp_id)));
        int i= Integer.parseInt(emp_id);
        Employees employees = new Employees();
        EmpController empController= new EmpController();
        employees = empController.getEmployeesById(i);
        empController.employeeDao.deleteEmployee(employees);

        return new ModelAndView("redirect:/viewemp");
    }

//    @RequestMapping(value = "/edit_emp", method = RequestMethod.POST)
    @RequestMapping(value = "/edit_emp")
    public ModelAndView edit(@RequestParam(value = "emp_id") String id) {
        int i= Integer.parseInt(id);
        Employees employees = new Employees();
        EmpController empController= new EmpController();
        employees = empController.getEmployeesById(i);

        return new ModelAndView("emp/empform", "employees", employees);
    }



    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView test() {
        System.out.println("Test");
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping("/viewemp")
    public ModelAndView viewemp(Model model) {
       List <Employees>  list1 = new ArrayList<>();
        list1 = employeeDao.getEmployees();
        return new ModelAndView("emp/viewemp", "list", list1);
    }

    private Employees getEmployeesById(@RequestParam int id) {
        List<Employees> listEmp = new ArrayList<>();
        listEmp = employeeDao.getEmployees();
        return listEmp.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}