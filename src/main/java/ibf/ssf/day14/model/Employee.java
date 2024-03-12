package ibf.ssf.day14.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employee {

    private String id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 5, max = 15, message = "First name must be between 5 and 15 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 15, message = "Last name must be between 5 and 15 characters")
    private String lastName;

    @Past(message = "DOB has to be before present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty or blank")
    @Size(max = 30, message = "Email cannot exceed 30 characters")
    private String email;

    // First digit starts with 8/9, followed by 7 digits of 0 - 9
    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number")
    private String phoneNo;

    @Digits(fraction = 0, integer = 6, message = "Postal code must be 6 digits long")
    @Min(value = 11111, message = "Postal code starts from 11111")
    @Max(value = 88888, message = "Postal code cannot exceed 88888")
    private Integer postalCode;

    @Min(value = 1500, message = "Min salary is 1500")
    @Max(value = 50000, message = "Max salary is 50000")
    private Integer salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date dob, String email, String phoneNo, Integer salary, Integer postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.phoneNo = phoneNo;
        this.salary = salary;
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    // Changed to comma seperated value
    @Override
    public String toString() {
        return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
                + ", email=" + email + ", phoneNo=" + phoneNo + ", postalCode=" + postalCode + ", salary=" + salary;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phoneNo == null) {
            if (other.phoneNo != null)
                return false;
        } else if (!phoneNo.equals(other.phoneNo))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }



}
