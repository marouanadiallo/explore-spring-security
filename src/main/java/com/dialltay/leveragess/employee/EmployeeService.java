package com.dialltay.leveragess.employee;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee employeeDetails(String name);
}

@Service
class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = Map.of(
            "alphamar", new Employee("alphamar", List.of("book1", "book2"), List.of("reader", "accountant")),
            "betamar", new Employee("betamar", List.of("book3", "book4"), List.of("researcher"))
    );

    /**
     * The post-authorization protects the return value of the method. Remember that.
     * Here, make sure that the method caller gets the employee details only if the employee has the role of "reader".
     * <b>returnObject</b> refers to the return value of the method.
     * @return the employee details
     */
    @PostAuthorize("returnObject.roles.contains('reader')")
    @Override
    public Employee employeeDetails(String name) {
        return this.employees.get(name);
    }
}
