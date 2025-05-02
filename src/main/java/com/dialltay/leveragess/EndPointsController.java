package com.dialltay.leveragess;

import com.dialltay.leveragess.document.Document;
import com.dialltay.leveragess.document.DocumentService;
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
    private final DocumentService documentService;

    public EndPointsController(NameService nameService,
                               EmployeeService employeeService,
                               DocumentService documentService) {
        this.nameService = nameService;
        this.employeeService = employeeService;
        this.documentService = documentService;
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

    // curl -u alphamar:mar123 http://localhost:8080/employees/details/alphamar
    // curl -u betamar:mar321 http://localhost:8080/employees/details/betamar (this will fail)
    // curl -u betamar:mar321 http://localhost:8080/employees/details/alphamar
    @GetMapping("/employees/details/{name}")
    Employee details(@PathVariable String name) {
        return this.employeeService.employeeDetails(name);
    }

    // curl -u alphamar:mar123 http://localhost:8080/documents/post/doc12
    // curl -u betamar:mar321 http://localhost:8080/documents/post/doc12
    @GetMapping("/documents/post/{code}")
    Document documentWithPostAuthorize(@PathVariable String code) {
        return this.documentService.getDocument(code);
    }

    // curl -u alphamar:mar123 http://localhost:8080/documents/pre/doc12
    // curl -u betamar:mar321 http://localhost:8080/documents/pre/doc12
    @GetMapping("/documents/pre/{code}")
    Document documentWithPreAuthorize(@PathVariable String code) {
        return this.documentService.getDocumentByCode(code);
    }

    // curl -u alphamar:mar123 http://localhost:8080/documents/owner/alphamar
    // curl -u betamar:mar321 http://localhost:8080/documents/owner/alphamar
    @GetMapping("/documents/owner/{ower}")
    Document documentByOwner(@PathVariable String ower) {
        return this.documentService.getDocumentByOwner(ower);
    }
}
