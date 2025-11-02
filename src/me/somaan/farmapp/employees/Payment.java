package me.somaan.farmapp.employees;

public interface Payment
{
    /**
     * Gross Salary
     */
    double grossSalary = 10000;

    /**
     * Calculates the salary based on the class and grossSalary.
     * @return the salary from the grossSalary.
     */
    double getSalary();
}
