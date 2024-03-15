package ibf.ssf.day14.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf.ssf.day14.model.Employee;
import ibf.ssf.day14.repository.EmployeeRepo;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepo empRepo;

    // Serialize employees
    public Employee createEmpObj(String employee) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //sdf.setTimeZone(TimeZone.getTimeZone("SGT"));
        
        String[] record = employee.split(",");
        Employee emp = new Employee();
        emp.setId(record[0]);
        emp.setFirstName(record[1]);
        emp.setLastName(record[2]);
        emp.setDob(sdf.parse(record[3]));
        emp.setEmail(record[4]);
        emp.setPhoneNo(record[5]);
        emp.setPostalCode(Integer.parseInt(record[6]));
        emp.setSalary(Integer.parseInt(record[7]));

        // System.out.println(emp);
        return emp;
    }

    // Serialize list of string employees into employee object
    public List<Employee> getEmployeeList() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("SGT"));
        List<Employee> empList = empRepo.findAllEmployees()
            .stream()
            .map(e -> {
                Employee emp = new Employee();
                try {
                    emp = createEmpObj(e);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                return emp;
            })
            .collect(Collectors.toList());

            //System.out.println(empList);
            return empList;
    }

    // Find employee by email
    public Optional<Employee> findEmployeeByEmail(String email) {
        Optional<Employee> emp = getEmployeeList()
            .stream()
            .filter(e -> email.equals(e.getEmail()))
            .findFirst();
        
        // System.out.println(emp.get().toString());

        
        return emp;
        
    }

    // Add employee 
    public void addEmployee(Employee employee) {
        empRepo.addEmployee(employee.toString());
    }

    // Delete employee
    public void deleteEmployee(Employee employee) {
        empRepo.deleteEmployee(employee.toString());
    }

    // No. employees
    public Long getNoEmployees() {
        return empRepo.getNoEmployees();
    }
}
