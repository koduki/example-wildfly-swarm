/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.wilfly.swarm.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author koduki
 */
@Named
@ApplicationScoped
public class JpaResources {

    @Produces
    @PersistenceContext(unitName = "InMemoryPersistenceUnit")
    private EntityManager em;

}
