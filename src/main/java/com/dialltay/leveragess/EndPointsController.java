package com.dialltay.leveragess;

import com.dialltay.leveragess.employee.Employee;
import com.dialltay.leveragess.employee.EmployeeService;
import com.dialltay.leveragess.name.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPointsController {

    private final NameService nameService;
    private final EmployeeService employeeService;

    public EndPointsController(NameService nameService, EmployeeService employeeService) {
        this.nameService = nameService;
        this.employeeService = employeeService;
    }

    // curl -u alphamar:mar123 http://localhost:8080/hello
    @GetMapping("/hello")
    String sayHello() {
        return "Hello, " + nameService.getName() + "!";
    }

    // curl -u alphamar:mar123 http://localhost:8080/secret/names/alphamar
    @GetMapping("/secret/names/{name}")
    List<String> names(@PathVariable String name) {
        return this.nameService.getSecretName(name);
    }

    // curl -u alphamar:mar123 http://localhost:8080/employees/details/alphamar (this will work)
    // curl -u betamar:mar321 http://localhost:8080/employees/details/betamar (this will fail)
    // curl -u betamar:mar321 http://localhost:8080/employees/details/alphamar (this will work)
    @GetMapping("/employees/details/{name}")
    Employee details(@PathVariable String name) {
        return this.employeeService.employeeDetails(name);
    }
}
