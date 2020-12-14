package com.ziumks.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziumks.employee.model.Employee;
import com.ziumks.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee/*")
public class EmployeeController {

	
	private final String profile;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository repository;
	
	
	EmployeeController(@Value("${spring.profiles.active}") String profile) {
        this.profile = profile;
    }
	
	@PostMapping("/")
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.add(employee);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll();
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return repository.findByDepartment(departmentId);
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}
	
    @SuppressWarnings("unused")
	@GetMapping("/exception")
    public ResponseJson exception() throws Exception {

        if("service03".equals(profile)) {
            int i = 1/0;
        }

        if("service02".equals(profile)) {
            Thread.sleep(10000);
        }

        return new ResponseJson("Success from " + profile);
    }

    private class ResponseJson {

        private final String msg;


        private ResponseJson(String msg) {
            this.msg = msg;
        }

        @SuppressWarnings("unused")
		public String getMsg() {
            return msg;
        }
    }
}

