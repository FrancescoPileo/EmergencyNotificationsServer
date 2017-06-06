/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;

import com.idstid.group1.emergencynotifications.Appsession;
import com.idstid.group1.emergencynotifications.Appuser;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kekko
 */
@Stateless
@Path("appsession")
public class AppsessionFacadeREST extends AbstractFacade<Appsession> {

    @PersistenceContext(unitName = "EmergencyNotificationsPU")
    private EntityManager em;

    public AppsessionFacadeREST() {
        super(Appsession.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Appsession entity) {
        
        System.out.println(entity.toString());
        super.create(entity);
    }

    @PUT
    @Path("/id/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Appsession entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appsession find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("/username/{username}/last")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appsession find(@PathParam("username") String username) {
                
        Appuser user = (Appuser) getEntityManager().
                createNamedQuery("Appuser.findByUsername").
                setParameter("username", username).getSingleResult();

        Appsession appsession =  (Appsession) getEntityManager()
                .createNamedQuery("Appsession.findByUsername").
                setParameter("username", user).
                getResultList().get(0);
        
                System.out.println(appsession.toString());
                
       return appsession;         
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appsession> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appsession> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
