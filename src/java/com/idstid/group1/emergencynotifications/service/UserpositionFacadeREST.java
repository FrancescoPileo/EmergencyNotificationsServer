/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;

import com.idstid.group1.emergencynotifications.Appsession;
import com.idstid.group1.emergencynotifications.Appuser;
import com.idstid.group1.emergencynotifications.Userposition;
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
@Path("userposition")
public class UserpositionFacadeREST extends AbstractFacade<Userposition> {

    @PersistenceContext(unitName = "EmergencyNotificationsPU")
    private EntityManager em;

    public UserpositionFacadeREST() {
        super(Userposition.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Userposition entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Userposition entity) {
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
    public Userposition find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userposition> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("/username/{username}/last")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Userposition find(@PathParam("username") String username) {
                
        Appuser user = (Appuser) getEntityManager().
                createNamedQuery("Appuser.findByUsername").
                setParameter("username", username).getSingleResult();

        Userposition position =  (Userposition) getEntityManager()
                .createNamedQuery("Userposition.findByIduser").
                setParameter("iduser", user).
                getResultList().get(0);
                        
       return position;         
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userposition> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
