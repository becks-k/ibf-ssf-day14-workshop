package ibf.ssf.day14.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ibf.ssf.day14.model.Employee;

@Repository
public class EmployeeRepo {

    // save location
    final String dirPath = "C:\\Users\\Rebekah\\Desktop\\NUS ISS Software Development\\ssf\\data";
    // file name
    final String fileName = "employees.txt";

    private List<Employee> employees;

    // EmployeeRepo constructor
    public EmployeeRepo() throws ParseException {

        if (employees == null) {
            // Instantiate employee list if variable is null
            employees = new LinkedList<>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = df.parse("1977-02-31");
        Employee emp = new Employee("James", "Tong", dob, "j.tong@gmail.com", "91234567", 1500, 556432);
        employees.add(emp);

        dob = df.parse("1900-01-01");
        emp = new Employee("Kenny", "Lim", dob, "klim@gmail.com", "98888888", 10000, 555555);
        employees.add(emp);
    }

    // Return list of employees
    public List<Employee> findAllEmployees() {
        return employees;
    }

    // Save data to file
    public Boolean save(Employee employee) throws FileNotFoundException {

        Boolean isSaved = false;

        // Add new employee record to employees list
        employees.add(employee);

        File file = new File(dirPath + "/" + fileName);
        OutputStream os = new FileOutputStream(file, true); // adds on to file vs overwrite file
        PrintWriter bw = new PrintWriter(os);
        bw.write(employee.toString());
        bw.write("\n");
        bw.flush();
        bw.close();

        
        isSaved = true;
        return isSaved;
    }

    // Find employee by email
    public Employee findByEmail(String email) {
        //get casts stream to employee object
        Employee retrievedEmployee = employees.stream()
            .filter(emp -> emp.getEmail().equals(email))
            .findFirst()
            .get(); //.get() is an Optional that returns a non null object


        return retrievedEmployee;
    }

    // Delete employee data
    public Boolean deleteEmployee(Employee employee) {
        Boolean isDeleted = false;
        
        int empIndex = employees.indexOf(employee);
        // -1 means not found in list 
        if (empIndex > 0) {
            employees.remove(empIndex);
            isDeleted = true; 
        }
        

        return isDeleted;
    }
}
