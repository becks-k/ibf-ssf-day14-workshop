package ibf.ssf.day14.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf.ssf.day14.util.Util;

@Repository
public class EmployeeRepo {

    @Autowired
    @Qualifier(Util.T_ONE)
    RedisTemplate<String, String> template;


    // Return list of employees in String
    // Deserialize in service class
    public List<String> findAllEmployees() {
        return template.opsForList().range(Util.KEY_EMP, 0, -1);
    }

    // Add new employees
    public void addEmployee(String employee) {
        template.opsForList().rightPush(Util.KEY_EMP, employee);
    }

    // Get index of employee
    public Long getIndexEmployee(String employee) {
        Long index = -1L;
        Optional<Long> opt = Optional.ofNullable(template.opsForList().indexOf(Util.KEY_EMP, employee));
        if (opt.isPresent()) {
            index = opt.get();
        }
        // System.out.println(">>>>>>>>>>>>>>>>> INDEX:" + index);
        return index;
    }

    // Update employee details
    public void updateEmployee(String employee) {
        if (getIndexEmployee(employee) != -1)
        template.opsForList().set(Util.KEY_EMP, getIndexEmployee(employee), employee);
    }

    // Delete employee data
    public void deleteEmployee(String employee) {
        Long index = getIndexEmployee(employee);
        if (index != -1)
        template.opsForList().remove(Util.KEY_EMP, 1, employee);
    }

    // No. of employees
    public Long getNoEmployees() {
        return template.opsForList().size(Util.KEY_EMP);
    }
}
