/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author kekko
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.idstid.group1.emergencynotifications.service.AppsessionFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.AppuserFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.BeaconFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.EnviromentalvaluesFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.MapFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.NodeFacadeREST.class);
        resources.add(com.idstid.group1.emergencynotifications.service.UserpositionFacadeREST.class);
    }
    
}
