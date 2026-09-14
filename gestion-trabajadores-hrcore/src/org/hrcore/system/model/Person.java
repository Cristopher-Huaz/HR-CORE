/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.model;

/**
 *
 * @author informatica
 */
public class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected double monthlySalary;
    protected String hireDate;
    protected String password;
    protected String department;
    protected String role;
    
     public Person() {
    }
     
    public Person(int id, String firstName, String lastName, double monthlySalary, 
                  String hireDate, String password, String department, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlySalary = monthlySalary;
        this.hireDate = hireDate;
        this.password = password;
        this.department = department;
        this.role = role;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    public void displayInformation() {
        System.out.println("=== PERSON ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Department: " + department);
        System.out.println("Role: " + role);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Monthly Salary: $" + monthlySalary);
        System.out.println("==================");
    }
    
}
