/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications.servlets;

import com.idstid.group1.emergencynotifications.Map;
import com.idstid.group1.emergencynotifications.Node;
import com.idstid.group1.emergencynotifications.service.MapFacadeREST;
import com.idstid.group1.emergencynotifications.service.NodeFacadeREST;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author kekko
 */
@WebServlet(name = "NodeReaderServlet", urlPatterns = {"/importnodes"})
@MultipartConfig
public class NodesImportServlet extends HttpServlet {
   
    public final static String RESOURCE_FOLDER = "../uploads/"; 
    
    private final static Logger LOGGER = Logger.getLogger(NodesImportServlet.class.getCanonicalName());
    
    private List <Node> nodeList;
    
    @EJB
    private MapFacadeREST mapFacade;
    
    @EJB
    private NodeFacadeREST nodeFacade;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = response.getWriter();
        nodeList = new ArrayList<>();
        List <Map> mapList;
        mapList = mapFacade.findAll();
        
        File myFile = new File(RESOURCE_FOLDER + "UNIVPM.xlsx"); 
        FileInputStream fis = new FileInputStream(myFile); 
        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis); 
        // Return first sheet from the XLSX workbook 
        XSSFSheet mySheet = myWorkBook.getSheetAt(1); 
        
        // Get iterator to all the rows in current sheet 
        Iterator<Row> rowIterator = mySheet.iterator(); 
        // Traversing over each row of XLSX file 
        while (rowIterator.hasNext()) {
            //LOGGER.log(Level.INFO, "file ok");
            Row row = rowIterator.next();
            // For each row, iterate through each columns 
            if (row.getRowNum() > 1){
                Node node = new Node();
                Iterator<Cell> cellIterator = row.cellIterator(); 
                while (cellIterator.hasNext()) { 
                    Cell cell = cellIterator.next(); 
                    switch (cell.getColumnIndex()) {
                        case 1:
                            node.setX((int) cell.getNumericCellValue());
                            break; 
                        case 2:
                            node.setY((int) cell.getNumericCellValue());
                            break;
                        case 5:
                            node.setNodename(cell.getStringCellValue());
                            break;
                        case 7:
                            Map map = findMapByName(mapList, cell.getStringCellValue());
                            node.setIdmap(map);
                            break;
                        default : 
                    } 
                }
                nodeList.add(node);
            }
        }
        writer.println("Import succesfully");
        storeNodes();
        if (writer != null) {
            writer.close();
        }
    }
    
    private Map findMapByName(List<Map> mapList, String mapName){
        for (Map map: mapList){
            if (map.getMapname().equals(mapName)){
                return map;
            }
        }
        return null;
    }
    
    private void storeNodes(){
        //nodeFacade.remove();
        for (Node node: nodeList) {
            Node oldNode =  nodeFacade.find(node.getNodename());
            if (oldNode != null){
                node.setIdnode(oldNode.getIdnode());
                nodeFacade.edit(oldNode.getIdnode(), node);
            } else {
                nodeFacade.create(node);
            }
            //LOGGER.log(Level.INFO, node.toString());
        }
        
    }
    
    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    

    
}
