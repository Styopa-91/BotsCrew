package com.example.demo;

import com.example.demo.model.Department;
import com.example.demo.model.Lector;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import static com.example.demo.model.Degree.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
 @Autowired
 private LectorRepository lectorRepository;

 @Autowired
 private DepartmentRepository departmentRepository;

 public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Department d1 = new Department();
		d1.setDepName("FKN");
		departmentRepository.save(d1);

		Department d2 = new Department();
		d2.setDepName("Economic");
		departmentRepository.save(d2);

		Department d3 = new Department();
		d3.setDepName("Historical");
		departmentRepository.save(d3);

		Set<Department> d123 = new HashSet<>();
		d123.add(d1);
		d123.add(d2);
		d123.add(d3);

		Set<Department> d12 = new HashSet<>();
		d12.add(d1);
		d12.add(d2);

		Set<Department> d13 = new HashSet<>();
		d13.add(d1);
		d13.add(d3);

		Set<Department> d23 = new HashSet<>();
		d23.add(d2);
		d23.add(d3);

		Set<Department> d01 = new HashSet<>();
		d01.add(d1);

		Set<Department> d02 = new HashSet<>();
		d02.add(d2);

		Set<Department> d03 = new HashSet<>();
		d03.add(d3);

		Lector l1 = new Lector();
		l1.setFirstName("Ivan");
		l1.setLastName("Petrov");
		l1.setDegree(ASSISTANT);
		l1.setSalary(8000);
		l1.setDepartments(d12);
		lectorRepository.save(l1);
		d1.setHead(l1);
		departmentRepository.save(d1);

		Lector l2 = new Lector();
		l2.setFirstName("Svitlana");
		l2.setLastName("Karpuk");
		l2.setDegree(PROFESSOR);
		l2.setSalary(12000);
		l2.setDepartments(d23);
		lectorRepository.save(l2);
		d2.setHead(l2);
		departmentRepository.save(d2);

		Lector l3 = new Lector();
		l3.setFirstName("Pavlo");
		l3.setLastName("Kravchuk");
		l3.setDegree(ASSOCIATE_PROFESSOR);
		l3.setSalary(9500);
		l3.setDepartments(d23);
		lectorRepository.save(l3);
		d3.setHead(l3);
		departmentRepository.save(d3);

		Lector l4 = new Lector();
		l4.setFirstName("Taras");
		l4.setLastName("Gordashchuk");
		l4.setDegree(ASSOCIATE_PROFESSOR);
		l4.setSalary(10000);
		l4.setDepartments(d01);
		lectorRepository.save(l4);

		Lector l5 = new Lector();
		l5.setFirstName("Kyrylo");
		l5.setLastName("Kozhumiaka");
		l5.setDegree(ASSOCIATE_PROFESSOR);
		l5.setSalary(9700);
		l5.setDepartments(d23);
		lectorRepository.save(l3);

		Lector l6 = new Lector();
		l6.setFirstName("Iryna");
		l6.setLastName("Gabuza");
		l6.setDegree(PROFESSOR);
		l6.setSalary(13000);
		l6.setDepartments(d123);
		lectorRepository.save(l6);

	 	Scanner scanner = new Scanner(System.in);

		 while (true) {

			String data = scanner.nextLine().toLowerCase();

			try {if (data.length() < 17) {
				System.out.println("BAD REQUEST: TOO SHORT");

			} else if ((data.substring(0,5).equals("show ") & data.substring(data.length()-11, data.length())
					.equals(" statistics"))) {
				String s1 = data.substring(5, data.length()-11);
				Set<Lector> set = departmentRepository.getDepartmentByDepNameIgnoreCase(s1).getLectors();
				int assist=0;
				int assoshProf=0;
				int prof=0;
				for (Lector m : set) {
					if (m.getDegree()==ASSISTANT) assist++;
					else if (m.getDegree()==ASSOCIATE_PROFESSOR) assoshProf++;
					else if (m.getDegree()==PROFESSOR) prof++;
				}
				System.out.println("assistants - " + assist);
				System.out.println("associate professors - " + assoshProf);
				System.out.println("professors - " + prof);

			} else if (data.substring(0, 17).equals("global search by ")) {
				String s5 = data.substring(17);
				List<Lector>  searchFN = lectorRepository.getLectorsByFirstNameContainingIgnoreCase(s5);
				for (Lector m : searchFN) {
					if (m == (searchFN.get(searchFN.size()-1))) System.out.print(m.getFirstName() + " " + m.getLastName());
					else System.out.print(m.getFirstName() + " " + m.getLastName() + ", ");
				}
				System.out.println();

				List<Lector>  searchLN = lectorRepository.getLectorsByLastNameContainingIgnoreCase(s5);
				for (Lector m : searchLN) {
					if (m == (searchLN.get(searchLN.size()-1))) System.out.print(m.getFirstName() + " " + m.getLastName());
					else System.out.print(m.getFirstName() + " " + m.getLastName() + ", ");
				}
				System.out.println();

			} else if (data.substring(0, 26).equals("who is head of department ")) {
				String s2 = data.substring(26);
				System.out.println("Head of " + s2 + " department is " + departmentRepository.getDepartmentByDepNameIgnoreCase(s2).getHead().getFirstName()+ " " +departmentRepository.getDepartmentByDepNameIgnoreCase(s2).getHead().getLastName());

			} else if (data.substring(0, 27).equals("show count of employee for ")) {
				String s4 = data.substring(27);
				System.out.println(departmentRepository.getDepartmentByDepNameIgnoreCase(s4).getLectors().size());

			} else if (data.substring(0, 43).equals("show the average salary for the department ")) {
				String s3 = data.substring(43);
				Set<Lector> l = departmentRepository.getDepartmentByDepNameIgnoreCase(s3).getLectors();
				float sumAv= 0;
				for (Lector i : l) {
					sumAv+=i.getSalary()/l.size();
				}
				System.out.println("the average salary of " + s3 + " is " + sumAv + " UAH");

			} else System.out.println("TRY AGAIN");
			}
			catch (NullPointerException n){
				System.out.println("BAD_REQUEST");
			}
			catch (StringIndexOutOfBoundsException se){
				System.out.println("BAD_REQUEST");
			}

		}

	}
}
