package com.ma.boot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
	@Autowired
	PersonResponsitory personResponsitory;
	
	@RequestMapping("/save")
	public Person save(String name, String address, Integer age) {
		Person p = personResponsitory.save(new Person(null, name, age, address));
		return p;
	}
	
	@RequestMapping("/q1")
	public List<Person> q1(String address) {
		List<Person> persons = personResponsitory.findByAddress(address);
		return persons;
	}
	
	@RequestMapping("/q2")
	public Person q2(String name, String address) {
		Person person = personResponsitory.findByNameAndAddress(name, address);
		return person;
	}

	@RequestMapping("/q3")
	public Person q3(String name, String address) {
		Person person = personResponsitory.withNameAndAddressQuery(name, address);
		return person;
	}
	
	@RequestMapping("/q4")
	public Person q4(String name, String address) {
		Person person = personResponsitory.withNameAndAddressNamedQuery(name, address);
		return person;
	}
	
	@RequestMapping("/sort")
	public List<Person> sort() {
		List<Person> persons =  personResponsitory.findAll(new Sort(Direction.ASC,"age"));
		return persons;
	}
	
	@RequestMapping("/page")
	public Page<Person> page(int page, int size ) {
		Page<Person> persons =  personResponsitory.findAll(new PageRequest(page,size));
		return persons;
	}
	
}
