package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String data = scanner.nextLine().toLowerCase();
			if (data.length() < 17) System.out.println("BAD REQUEST: TOO SHORT");
			else if (data.substring(0,5).equals("show ")&&data.substring(data.length()-12,data.length()).equals(" statistics")) {
				System.out.println(data.substring(5, data.length()-12));
			} else if (data.substring(0, 26).equals("who is head of department ")) {
				System.out.println(data.substring(26));
			} else if (data.substring(0, 43).equals("show the average salary for the department ")) {
				System.out.println(data.substring(43));
			} else if (data.substring(0, 27).equals("show count of employee for ")) {
				System.out.println(data.substring(27);
			} else if (data.substring(0, 17).equals("show count of employee for ")) {
				System.out.println(data.substring(17));
			}
			else System.out.println("NO");
		}

	}
}