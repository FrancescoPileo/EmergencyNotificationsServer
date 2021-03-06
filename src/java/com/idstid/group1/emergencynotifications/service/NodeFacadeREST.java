/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;

import com.idstid.group1.emergencynotifications.Node;
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
@Path("node")
public class NodeFacadeREST extends AbstractFacade<Node> {

    @PersistenceContext(unitName = "EmergencyNotificationsPU")
    private EntityManager em;

    public NodeFacadeREST() {
        super(Node.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Node entity) {
        super.create(entity);
    }

    @PUT
    @Path("id/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Node entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @DELETE
    @Path("/all")
    public void remove() {
        getEntityManager().createNamedQuery("Node.deleteAll").executeUpdate();
    }

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Node find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("/name/{nodename}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Node find(@PathParam("nodename") String nodename) {
        Node node = null;
        List<Node> nodes = this.getEntityManager().createNamedQuery("Node.findByNodename", Node.class).
                setParameter("nodename", nodename).getResultList();
        if (!nodes.isEmpty()){
            node = nodes.get(0);
        }
        return node;
        
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Node> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Node> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
