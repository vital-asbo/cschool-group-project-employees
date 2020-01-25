package spring.employees;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.hibernate.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {
    private List<Employees> listEmployees;
    private List<Cars> listCars;
    private HibernateDao hibernateDao;

    public EmpController() {
//        list = new ArrayList<>();
//        list.add(new Employees ("Janek", "Kowalski","Zamiejska 12", "Warszawa",12000,32,new Date("12-05-2014"),4,"m.k@wp.pl"));
////        list.add(new Employees(2, "Zosia", 9000, "Makowiec"));
////        list.add(new Employees(3, "Marek", 10000, "Warszawa"));
////        list.add(new Employees(4, "Krysytna", 13000, "Ryzowice"));
        try {
            hibernateDao = new HibernateDao();
            DateSource.supplyDB();
            listEmployees= hibernateDao.get(Employees.class);
            listCars= hibernateDao.get(Cars.class);
        } catch(NullPointerException ex){
            System.out.println("Brak połączenia z bazą danych");
            ex.getMessage();
            listEmployees = new ArrayList<>();
        }
    }

//    HibernateDao hibernateDao = new HibernateDao();

    @RequestMapping("/")
    public String indexGet() {
        return "emp/index";
    }


    @RequestMapping(value = "/empform", method = RequestMethod.GET)
    public ModelAndView showform(Model model) {
//        model.addAttribute("employees", new Employees ());
        return new ModelAndView("emp/empform", "employees", new Employees());

    }

    @RequestMapping(value = "/carform", method = RequestMethod.GET)
    public ModelAndView showform1(Model model) {
//        model.addAttribute("employees", new Employees ());
        ModelAndView modelAndView = new ModelAndView();
        List<Employees> list2 = hibernateDao.getEmployees();
        modelAndView.addObject("employeesList", list2);
        modelAndView.addObject( "cars", new Cars());
        modelAndView.setViewName("emp/empform");
        return modelAndView;

    }

    @RequestMapping(value = "/save_emp")
    public ModelAndView save(@ModelAttribute(value = "employees") Employees employees) {
        if (employees.getId() == 0) {
            System.out.println("Adding a new emp");
            hibernateDao.saveDB(employees);
            employees.setId(listEmployees.size() + 1);
            listEmployees.add(employees);
        } else {
            hibernateDao.updateEmployees(employees);
            listEmployees.set(employees.getId(),employees);

        }
        return new ModelAndView("redirect:/viewemp");
    }


    @RequestMapping(value = "/save_car")
    public ModelAndView save(@ModelAttribute(value = "cars") Cars cars) {
        if (cars.getId() == 0) {
            System.out.println("Adding a new emp");
            hibernateDao.saveDB(cars);
            cars.setId(listCars.size() + 1);
            listCars.add(cars);
        } else {
            hibernateDao.updateEmployees(cars);

        }
        return new ModelAndView("redirect:/viewcars");
    }


//    @RequestMapping(value = "/delete_emp", method = RequestMethod.POST)
    @RequestMapping(value = "/delete_emp")
    public ModelAndView delete(@RequestParam(value = "emp_id") String emp_id) {
//        list.remove(getEmployeesById(Integer.parseInt(emp_id)));
        int i= Integer.parseInt(emp_id);
      //  Employees employees = getEmployeesById(i);
//        try {
//            hibernateDao.deleteEmployee(getEmployeesById(i));
//        }catch ()
        hibernateDao.deleteEmployee(getEmployeesById(i));

        return new ModelAndView("redirect:/viewemp");
    }

//    @RequestMapping(value = "/edit_emp", method = RequestMethod.POST)
    @RequestMapping(value = "/edit_emp")
    public ModelAndView edit(@RequestParam(value = "emp_id") String id) {
        int i= Integer.parseInt(id);
        Employees employees = getEmployeesById(i);

        return new ModelAndView("emp/empform", "employees", employees);
    }

    @RequestMapping(value = "edit_cars")
    public ModelAndView edit1(@RequestParam(value = "cars_id") String id) {

        ModelAndView modelAndView = new ModelAndView();
        int i= Integer.parseInt(id);
        Cars cars = getCarsById(i);
//        List<Employees> list2 = hibernateDao.getEmployees();
        modelAndView.addObject("employeesList", listEmployees);
        modelAndView.addObject("cars", listCars);
        modelAndView.setViewName("emp/carform");


        return modelAndView;
    }



/*    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView test() {
        System.out.println("Test");
        return new ModelAndView("redirect:/viewemp");
    }*/

    @RequestMapping("/viewemp")
    public ModelAndView viewemp(Model model) {
//       List <Employees>  list1 = new ArrayList<>();
//        list1 = hibernateDao.getEmployees();
        return new ModelAndView("emp/viewemp", "list", listEmployees);
    }

    @RequestMapping("/viewcar")
    public ModelAndView viewcar(Model model) {
//        hibernateDao = new HibernateDao();
//        List<Cars> list2 = hibernateDao.getCars();
        return new ModelAndView("emp/viewcar", "list", listCars);
    }

    private Employees getEmployeesById(@RequestParam int id) {
//        List<Employees> listEmp = new ArrayList<>();
//        listEmp = hibernateDao.getEmployees();
        return listEmployees.stream().filter(f -> f.getId() == id).findFirst().get();
    }

    private Cars getCarsById(@RequestParam int id) {
//        List<Cars> listCar = new ArrayList<>();
//        listCar = hibernateDao.getCars();
        return listCars.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}