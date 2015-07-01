/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.wilfly.swarm;

import cn.orz.pascal.example.wilfly.swarm.api.EmployeeResource;
import cn.orz.pascal.example.wilfly.swarm.config.JaxRsApplication;
import cn.orz.pascal.example.wilfly.swarm.config.JpaResources;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.Datasource;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.datasources.Driver;
import org.wildfly.swarm.jaxrs.JAXRSDeployment;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 *
 * @author koduki
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Container container = new Container();
        registDataSource(container);

        JAXRSDeployment deployment = new JAXRSDeployment(container);

        // set config
        deployment.getArchive().addClass(JpaResources.class);
        deployment.addResource(JaxRsApplication.class);

        // load resouces
        deployment.getArchive().addAsWebInfResource(new ClassLoaderAsset("META-INF/beans.xml", Main.class.getClassLoader()), "beans.xml");
        deployment.getArchive().addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.getArchive().addAsWebInfResource(new ClassLoaderAsset("META-INF/load.sql", Main.class.getClassLoader()), "classes/META-INF/load.sql");

        // load classes
        deployment.addResource(EmployeeResource.class);
        deployment.getArchive().addPackage("cn.orz.pascal.example.wilfly.swarm.domain");

        container.start().deploy(deployment);

    }

    private static void registDataSource(Container container) {
        container.subsystem(new DatasourcesFraction()
                .driver(new Driver("h2")
                        .datasourceClassName("org.h2.Driver")
                        .xaDatasourceClassName("org.h2.jdbcx.JdbcDataSource")
                        .module("com.h2database.h2"))
                .datasource(new Datasource("InMemoryPersistenceUnit")
                        .driver("h2")
                        .connectionURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                        .authentication("sa", "sa"))
        );

        // Prevent JPA Fraction from installing it's default datasource fraction
        container.fraction(new JPAFraction()
                .inhibitDefaultDatasource()
                .defaultDatasourceName("InMemoryPersistenceUnit")
        );
    }
}
