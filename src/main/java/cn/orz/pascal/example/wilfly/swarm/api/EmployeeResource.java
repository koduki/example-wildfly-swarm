/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.wilfly.swarm.api;

import cn.orz.pascal.example.wilfly.swarm.domain.Employee;
import cn.orz.pascal.example.wilfly.swarm.domain.EmployeeService;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Yoshimasa Tanabe
 */
@ApplicationScoped
@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> findAll() {
        List<Employee> results = employeeService.findAll();
        System.err.println(results);
        return results;
    }

}