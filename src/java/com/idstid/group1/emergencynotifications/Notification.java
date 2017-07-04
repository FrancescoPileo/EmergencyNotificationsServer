/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications;

import com.idstid.group1.emergencynotifications.service.EnviromentalvaluesFacadeREST;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author kekko
 */
public class Notification {
    
    static public final String NOTIFICATION_TEMP = "temperature";
    static public final String NOTIFICATION_HUM = "humidity";
    static public final String NOTIFICATION_ACC = "acceleration";
    
    static public final int TEMP_MAX = 90;
    static public final int HUM_MAX = 95;
    static public final double ACC_MAX = 1;
    
    
    public static JsonObject buildMessage(String idBeacon, String notification){
        return Json.createObjectBuilder().add("beacon", idBeacon)
                .add("notification", notification).build();
            
    }
    
    public static void send(List<Token> tokens, JsonObject message) {
        
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
    
}
