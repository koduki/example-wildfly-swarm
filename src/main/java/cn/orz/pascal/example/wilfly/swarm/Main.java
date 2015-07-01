/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.wilfly.swarm;

import cn.orz.pascal.example.wilfly.swarm.api.EmployeeResource;
import cn.orz.pascal.example.wilfly.swarm.api.JaxRsApplication;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSDeployment;

/**
 *
 * @author koduki
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Container container = new Container();

        JAXRSDeployment deployment = new JAXRSDeployment(container);
        deployment.addResource(JaxRsApplication.class);
        deployment.addResource(EmployeeResource.class);
        deployment.getArchive().addPackage("cn.orz.pascal.example.wilfly.swarm.domain");

        container.start().deploy(deployment);

    }
}
