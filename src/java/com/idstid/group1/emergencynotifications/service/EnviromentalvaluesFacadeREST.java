/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;


import com.google.gson.JsonPrimitive;
import com.idstid.group1.emergencynotifications.Enviromentalvalues;
import com.idstid.group1.emergencynotifications.Token;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (entity.getTemperature() > 50){
            List<Token> tokens = (List<Token>) getEntityManager().
                createNamedQuery("Token.findAll").getResultList();
            sendNotification(tokens, "Too Hot");
        }
    }
    
    private void sendNotification(List<Token> tokens, String message) {
        
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Token token: tokens){
            jab.add(token.getTokenvalue());
        }
        
        JsonObject jo = Json.createObjectBuilder()
                .add("registration_ids", jab)
                .add("data", Json.createObjectBuilder().add("message", message))
        .build();
        
        System.out.print(jo.toString());
        
        
        URL urlObj;
        try {
            urlObj = new URL("https://fcm.googleapis.com/fcm/send");
          
     
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setDoOutput(true);
            
            
            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "key=AAAA1-iEnv8:APA91bFYTAy-wuDeVnzhTwwLiAqvT85Noms_"
                    + "clzOZZ1FOcV5fRSLKlsV1YT1rGr201LMQaHclHJGv0bpu0WpDa5-QpxcpG-"
                    + "382tYrO5few2nkWcpmrLbJOTMJ56iYYVPG330o_ugzL9Y");

            // Send post request
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jo.toString());
            wr.flush();
            
            int responseCode = con.getResponseCode();
            String responseMex = con.getResponseMessage();
            System.out.println(responseCode);
            System.out.println(responseMex);
        
        } catch (Exception ex) {
            Logger.getLogger(EnviromentalvaluesFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
