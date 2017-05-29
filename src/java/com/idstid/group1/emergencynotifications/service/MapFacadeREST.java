/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.service;

import com.idstid.group1.emergencynotifications.Appuser;
import com.idstid.group1.emergencynotifications.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author kekko
 */
@Stateless
@Path("map")
public class MapFacadeREST extends AbstractFacade<Map> {

    @PersistenceContext(unitName = "EmergencyNotificationsPU")
    private EntityManager em;

    public MapFacadeREST() {
        super(Map.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Map entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Map entity) {
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
    public Map find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("/mapname/{mapname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map find(@PathParam("mapname") String mapname) {
        return (Map) getEntityManager()
                .createNamedQuery("Map.findByMapname").
                setParameter("mapname", mapname).getSingleResult();
    }
    
    @GET
    @Path("{mapname}/png")
    @Produces("image/png")
    public Response getPng(@PathParam("mapname") String mapname, @HeaderParam("If-Modified-Since") String modified) {
        File repositoryFile = new File("../uploads/" + mapname + ".png");
        return returnFile(repositoryFile, modified);
    }
    
        /**
     * 
     * Sends the file if modified and "not modified" if not modified
     * future work may put each file with a unique id in a separate folder in tomcat
     *   * use that static URL for each file
     *   * if file is modified, URL of file changes
     *   * -> client always fetches correct file 
     * 
     *     method header for calling method public Response getXY(@HeaderParam("If-Modified-Since") String modified) {
     * 
     * @param file to send
     * @param modified - HeaderField "If-Modified-Since" - may be "null"
     * @return Response to be sent to the client
     */
    public static Response returnFile(File file, String modified) {
        if (!file.exists()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // do we really need to send the file or can send "not modified"?
        if (modified != null) {
            Date modifiedDate = null;

            // we have to switch the locale to ENGLISH as parseDate parses in the default locale
            Locale old = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);

            try {
                modifiedDate = DateUtils.parseDate(modified, DEFAULT_PATTERNS);
            } catch (ParseException ex) {
                Logger.getLogger(MapFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
  
            Locale.setDefault(old);

            if (modifiedDate != null) {
                // modifiedDate does not carry milliseconds, but fileDate does
                // therefore we have to do a range-based comparison
                // 1000 milliseconds = 1 second
                if (file.lastModified()-modifiedDate.getTime() < DateUtils.MILLIS_PER_SECOND) {
                    return Response.status(Status.NOT_MODIFIED).build();
                }
            }
        }        
        // we really need to send the file

        try {
            Date fileDate = new Date(file.lastModified());
            return Response.ok(new FileInputStream(file)).lastModified(fileDate).build();
        } catch (FileNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /*** copied from org.apache.http.impl.cookie.DateUtils, Apache 2.0 License ***/

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1123 format.
     */
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1036 format.
     */
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in ANSI C
     * <code>asctime()</code> format.
     */
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";

    public static final String[] DEFAULT_PATTERNS = new String[] {
        PATTERN_RFC1036,
        PATTERN_RFC1123,
        PATTERN_ASCTIME
    };

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Map> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Map> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
