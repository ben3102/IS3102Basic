package com.is3102.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="employee")
@NamedQueries({
        @NamedQuery(name = Employee.ALL, query = "SELECT u FROM Employee u "),
        @NamedQuery(name = Employee.TOTAL, query = "SELECT COUNT(u) FROM Employee u")})
public class        Employee extends BaseEntity implements Serializable{

    public final static String ALL = "Employee.populateEmployees";
    public final static String TOTAL = "Employee.countEmployeesTotal";

    private String username;

    private String password;

    private String email;

    private String identification;

    private String sendEmail;

    private String registerDate;

    private String activation;

    private String lastResetDate;

    private String resetCount;

    private String access;

    @ManyToMany
    @JoinTable(name = "employee_group_map", joinColumns = {
            @JoinColumn(name = "employeeid")}, inverseJoinColumns = {
            @JoinColumn(name = "groupid")})
    private List<EmployeeGroup> employeeGroups;

    public Employee(){
        employeeGroups = new ArrayList<EmployeeGroup>();
        System.out.println("in Employee");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getLastResetDate() {
        return lastResetDate;
    }

    public void setLastResetDate(String lastResetDate) {
        this.lastResetDate = lastResetDate;
    }

    public String getResetCount() {
        return resetCount;
    }

    public void setResetCount(String resetCount) {
        this.resetCount = resetCount;
    }

    public List<EmployeeGroup> getEmployeeGroups() {
        return employeeGroups;
    }

    public void setEmployeeGroups(List<EmployeeGroup> employeeGroups) {
        this.employeeGroups = employeeGroups;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
