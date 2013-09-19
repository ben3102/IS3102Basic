package com.is3102.controller;

import com.is3102.entity.Employee;
import com.is3102.entity.EmployeeGroup;
import com.is3102.service.EmployeeService;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@Named
@SessionScoped
public class EmployeeController implements Serializable  {

    private @Inject EmployeeService das;
    // Selected employees that will be removed
    private Employee[] selectedEmployees;
    // Lazy loading employee list
    private LazyDataModel<Employee> lazyModel;
    // Creating new employee
    private Employee newEmployee = new Employee();
    // Selected employee that will be updated
    private Employee selectedEmployee = new Employee();

    // Available groups list
    private List<EmployeeGroup> groupList;

    /**
     * Default constructor
     */
    public EmployeeController() {
        System.out.println("employee controller");

    }

    /**
     * Initializing Data Access Service for LazyEmployeeDataModel class
     * role list for EmployeeContoller class
     */
    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        lazyModel = new LazyEmployeeDataModel(das);
        groupList = das.findWithNamedQuery(EmployeeGroup.ALL);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateEmployee() {
        System.out.println("doCreateEmployee");
        das.create(newEmployee);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateEmployee(ActionEvent actionEvent){
        System.out.println("doUpdateEmployee");

            das.update(selectedEmployee);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteEmployees(ActionEvent actionEvent){
            das.deleteItems(selectedEmployees);
    }     
        
    /**
     * Getters, Setters
     * @return 
     */

    public Employee getSelectedEmployee() {  
        return selectedEmployee;  
    }  

    /**
     *
     * @param selectedEmployee
     */
    public void setSelectedEmployee(Employee selectedEmployee) {  
            this.selectedEmployee = selectedEmployee;  
    } 
        
    /**
     *
     * @return
     */
    public Employee[] getSelectedEmployees() {  
            return selectedEmployees;  
    }  
        
    /**
     *
     * @param selectedEmployees
     */
    public void setSelectedEmployees(Employee[] selectedEmployees) {  
            this.selectedEmployees = selectedEmployees;  
    }

    /**
     *
     * @return
     */
    public Employee getNewEmployee() {
            return newEmployee;
    }

    /**
     *
     * @param newEmployee
     */
    public void setNewEmployee(Employee newEmployee) {
            this.newEmployee = newEmployee; 
    }
       
    /**
     *
     * @return LazyDataModel
     */
    public LazyDataModel<Employee> getLazyModel() {
        return lazyModel;
    }

    public EmployeeService getDas() {
        return das;
    }

    public void setDas(EmployeeService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<Employee> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<EmployeeGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<EmployeeGroup> groupList) {
        this.groupList = groupList;
    }
}
                    