package ibf.ssf.day14.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf.ssf.day14.model.Employee;
import ibf.ssf.day14.service.EmployeeService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees") // String of methods can be specified, see API doc
public class EmployeeController {
    
    // Auto instantiates class on start up
    @Autowired
    EmployeeService empService;

    // localhost:<port no>/employees/add
    @GetMapping("/add")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employeeNew", employee);
        
        // must have employeeadd.html in resources/templates folder
        return "employeeadd"; 
    }

    // @ModelAttribute("employeeNew") - instantiate data from employeeNew into model
    @PostMapping("/add")
    public String saveEmployee( @Valid @ModelAttribute("employeeNew") Employee employeeForm, BindingResult bindings, Model model) throws FileNotFoundException {

        if (bindings.hasErrors()) {
            return "employeeadd";
        } else {
            // Save new employee in redis
            empService.addEmployee(employeeForm);
            model.addAttribute("savedEmployee", employeeForm);
            return "success";
        }
    }

    // Display employees records
    @GetMapping("/list")
    public String employeeList(Model model) {
        List<Employee> employees = empService.getEmployeeList();
        model.addAttribute("employees", employees);

        return "employeelist";
    }


    @GetMapping("/delete/{email}")
    public String deleteEmployee(@PathVariable("email") String email) {
        Optional<Employee> opt = empService.findEmployeeByEmail(email);
        
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE: opt" + opt.get().toString());
        
        if (opt.isPresent()) {
            Employee emp = opt.get();
            // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>DELETE: " + emp);
            empService.deleteEmployee(emp);
        }
        return "redirect:/employees/list";
    }
}
