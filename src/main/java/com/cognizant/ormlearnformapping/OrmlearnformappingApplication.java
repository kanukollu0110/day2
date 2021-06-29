package com.cognizant.ormlearnformapping;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.cognizant.ormlearnformapping.model.Department;
import com.cognizant.ormlearnformapping.model.Employee;
import com.cognizant.ormlearnformapping.model.Skill;
import com.cognizant.ormlearnformapping.model.Stock;
import com.cognizant.ormlearnformapping.services.DepartmentService;
import com.cognizant.ormlearnformapping.services.EmployeeService;
import com.cognizant.ormlearnformapping.services.SkillService;
import com.cognizant.ormlearnformapping.services.StockService;

@SpringBootApplication
public class OrmlearnformappingApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmlearnformappingApplication.class);
	@Autowired
	StockService stockService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	SkillService skillService;
	

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmlearnformappingApplication.class, args);
		
	}
@Bean
	CommandLineRunner testGetAllStocks() {
		return res -> {
			LOGGER.info("START  testGetAllStocks");
			List<Stock> stocks = stockService.getAllStocks();
			LOGGER.debug("stocks = {}", stocks);
			LOGGER.info("END testGetAllStocks");
		};
	}

	
	@Bean
	CommandLineRunner testGetStocksOfFBInSep2019() {
		return res -> {
			LOGGER.info("START testGetStocksOfFBInSep2019");
			List<Stock> stocks = stockService.getStocksOfFBInSep2019();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END testGetStocksOfFBInSep2019");
		};
	}

	
	@Bean
	CommandLineRunner testGetStocksOfGoogleGT1250() {
		return res -> {
			LOGGER.info("START testGetStocksOfGoogleGT1250");
			List<Stock> stocks = stockService.getStocksOfGoogleGT1250();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END testGetStocksOfGoogleGT1250");
		};
	}

	
	@Bean
	CommandLineRunner testGetTop3StocksByVolume() {
		return res -> {
			LOGGER.info("START testGetTop3StocksByVolume");
			List<Stock> stocks = stockService.getTop3StocksByVolume();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END testGetTop3StocksByVolume");
		};
	}

	
	@Bean
	CommandLineRunner test3LowestNetflixStocks() {
		return res -> {
			LOGGER.info("START test3LowestNetflixStocks");
			List<Stock> stocks = stockService.get3LowestNetflixStocks();
			for (Stock stock : stocks) {
				LOGGER.debug("stocks = {}", stock);
			}
			LOGGER.info("END test3LowestNetflixStocks");
		};
	}

	
	@Bean
	CommandLineRunner tstGetEmp() {
		return res -> {

			LOGGER.info("Start tstGetEmp");

			Employee employee = employeeService.getById(1);
			LOGGER.debug("Employee:{}", employee);
			LOGGER.debug("Department:{}", employee.getDepartment());
			LOGGER.debug("Skills:{}", employee.getSkillList());

			LOGGER.info("End tstGetEmp");
		};

	}

	
	@Bean
	CommandLineRunner tstAddEmp() {
		return res -> {

			LOGGER.info("Start tstAddEmp");

			Department department = departmentService.getById(1);
			Employee employee = Employee.builder().name("kalyan").salary(BigDecimal.valueOf(10800)).permanent(true)
				.dateOfBirth(new Date(1999, 01, 10)).department(department).build();
			employeeService.save(employee);

			LOGGER.info("End tstAddEmp");
		};
	}

	
	@Bean
	CommandLineRunner tstUpdateEmp() {

		return res -> {
			LOGGER.info("Start tstUpdateEmp");

			Employee employee = employeeService.getById(1);
			Department department = departmentService.getById(2);
			employee.setDepartment(department);
			employeeService.save(employee);

			LOGGER.info("End tstUpdateEmp");
		};
	}

	
	@Bean
	CommandLineRunner tstGetDep() {
		return res -> {

			LOGGER.info("Start tstGetDep");

			Department department = departmentService.getById(3);
			department.getEmployeeList().forEach(emp -> System.out.println(emp));

			LOGGER.info("End tstGetDep");
		};
	}

	
	@Bean
	CommandLineRunner tstAddSkillToEmp() {
		return res -> {

			LOGGER.info("Start tstAddSkillToEmp");

			Employee employee = employeeService.getById(1);
			Skill skill = skillService.getById(2);
			employee.getSkillList().add(skill);
			employeeService.save(employee);

			LOGGER.info("End tstAddSkillToEmp");
		};
	}

	
	@Bean
	CommandLineRunner getAllPerm() {
		return res -> {

			LOGGER.info("Starting getAllPerm");

			List<Employee> all = employeeService.getAll();
			LOGGER.debug("{}", all);

			LOGGER.info("Ending getAllPerm");
		};
	}

	
	@Bean
	CommandLineRunner getAvgSal() {
		return res -> {
			LOGGER.info("Starting getAvgSal");

			double averageSalary = employeeService.getAverageSalary();
			LOGGER.debug("{}", averageSalary);

			LOGGER.info("Ending getAvgSal");

		};
	}

	
	@Bean
	CommandLineRunner getAvgSalaryByDept() {
		return res -> {
			LOGGER.info("Starting getAvgSalaryByDept");

			double averageSalary = employeeService.getAverageSalaryByDept(3);
			LOGGER.debug("{}", averageSalary);

			LOGGER.info("Ending of getAvgSalaryByDept");

		};
	}

	
	@Bean
	CommandLineRunner usingNative() {
		return res -> {
			LOGGER.info("Starting of usingNative");

			List<Employee> allEmployeesNative = employeeService.getAllEmployeesNative();
			LOGGER.debug("{}", allEmployeesNative);

			LOGGER.info("Ending of usingNative");

		};
	}

}











	