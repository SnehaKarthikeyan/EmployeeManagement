package com.inzeph.EmployeeManagement;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.LeaveType;
import com.inzeph.EmployeeManagement.Model.Role;
import com.inzeph.EmployeeManagement.Repository.LeaveTypeRepo;
import com.inzeph.EmployeeManagement.Repository.RoleRepo;
import com.inzeph.EmployeeManagement.Service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private LeaveTypeRepo leaveTypeRepo;

	@Override
	public void run(String... args) throws Exception {
		if(roleRepo.findAll().size() == 0){
			Role admin = new Role(1,"ADMIN");
			Role staff = new Role(2,"STAFF");
			Role manager = new Role(3,"MANAGER");
			Role engineer = new Role(4,"ENGINEER");
			roleRepo.save(admin);
			roleRepo.save(staff);
			roleRepo.save(manager);
			roleRepo.save(engineer);

			LeaveType sickLeave = new LeaveType(1,"Sick Leave");
			LeaveType casualLeave = new LeaveType(2,"Casual Leave");

			leaveTypeRepo.save(sickLeave);
			leaveTypeRepo.save(casualLeave);

			Employee employee = new Employee();

			employee.setName("admin");
			employee.setPassword("$2a$12$RVpoKInvfqONBjzmymqMd.ifjWUnO8XECX9w9mT8CxnXEW4Z6QVGi");
			employee.setDesignation("Managing Director");
			employee.setActive(true);
			employee.setContact(1234567890L);
			employee.setAadhar(1234567890L);
			employee.setRole(1);

			employeeService.addEmployee(employee);
		}
	}
}
