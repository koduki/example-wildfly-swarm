/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.wilfly.swarm.domain;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EmployeeService {

    public List<Employee> findAll() {
        return new ArrayList<Employee>() {{
            add(new Employee(1L, "emp01"));
            add(new Employee(2L, "emp02"));
        }};
    }

}