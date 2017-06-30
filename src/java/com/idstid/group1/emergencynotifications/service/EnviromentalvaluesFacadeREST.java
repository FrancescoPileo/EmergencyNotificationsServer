/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;


import com.google.gson.JsonPrimitive;
import com.idstid.group1.emergencynotifications.Beacon;
import com.idstid.group1.emergencynotifications.Enviromentalvalues;
import com.idstid.group1.emergencynotifications.Notification;
import com.idstid.group1.emergencynotifications.Token;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

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
@Path("enviromentalvalues")
public class EnviromentalvaluesFacadeREST extends AbstractFacade<Enviromentalvalues> {
    
    @EJB
    private BeaconFacadeREST beaconFacade;

    @PersistenceContext(unitName = "EmergencyNotificationsPU")
    private EntityManager em;

    public EnviromentalvaluesFacadeREST() {
        super(Enviromentalvalues.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Enviromentalvalues entity) {
        super.create(entity);
        Beacon beacon = entity.getIdbeacon();
        
        if (entity.getTemperature() > Notification.TEMP_MAX ){
            if (beacon.getEmergency() == null || !beacon.getEmergency().equals(Notification.NOTIFICATION_TEMP)){
                List<Token> tokens = (List<Token>) getEntityManager().
                    createNamedQuery("Token.findAll").getResultList();

                beacon.setEmergency(Notification.NOTIFICATION_TEMP);
                beaconFacade.edit(beacon.getIdbeacon(), beacon);
                Notification.send(tokens, Notification.buildMessage(beacon.getIdbeacon(), Notification.NOTIFICATION_TEMP));
            }
        } else if (entity.getHumidity() > Notification.HUM_MAX ){
            if (beacon.getEmergency() == null || !beacon.getEmergency().equals(Notification.NOTIFICATION_HUM)){

                List<Token> tokens = (List<Token>) getEntityManager().
                    createNamedQuery("Token.findAll").getResultList();

                beacon.setEmergency(Notification.NOTIFICATION_HUM);
                beaconFacade.edit(beacon.getIdbeacon(), beacon);
                Notification.send(tokens, Notification.buildMessage(beacon.getIdbeacon(), Notification.NOTIFICATION_HUM));
            }
            
        } else if (abs(entity.getAccx()) > Notification.ACC_MAX || abs(entity.getAccy()) > Notification.ACC_MAX || abs(entity.getAccz()) > Notification.ACC_MAX){
            if (beacon.getEmergency() == null || !beacon.getEmergency().equals(Notification.NOTIFICATION_ACC)){
                List<Token> tokens = (List<Token>) getEntityManager().
                    createNamedQuery("Token.findAll").getResultList();
                beacon.setEmergency(Notification.NOTIFICATION_ACC);
                beaconFacade.edit(beacon.getIdbeacon(), beacon);
                Notification.send(tokens, Notification.buildMessage(beacon.getIdbeacon(), Notification.NOTIFICATION_ACC));
            }
        } else {
            beacon.setEmergency(null);
            beaconFacade.edit(beacon.getIdbeacon(), beacon);
        };
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Enviromentalvalues entity) {
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
    public Enviromentalvalues find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("/lasts")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Enviromentalvalues> findLasts() {
       return getEntityManager()
                .createNamedQuery("Enviromentalvalues.findLasts").
                getResultList();         
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Enviromentalvalues> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Enviromentalvalues> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
