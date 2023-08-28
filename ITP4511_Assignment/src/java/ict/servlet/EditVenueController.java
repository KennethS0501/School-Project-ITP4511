/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.db.VenueDB1;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.List;
import javax.servlet.RequestDispatcher;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author alvin
 */
@WebServlet(name = "EditVenueController", urlPatterns = {"/Staff/VenueManagement/EditVenue"})
public class EditVenueController extends HttpServlet {

    private VenueDB1 VDB;
    private static final long serialVersionUID = 1L;
    private String defaultPath = "";
    private String name = "default";
    // location to store file uploaded
//    private static final String UPLOAD_DIRECTORY = "upload";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        VDB = new VenueDB1(dbUrl, dbUser, dbPassword);
        defaultPath = getServletContext().getInitParameter("defaultPath");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String VenueId = "";
        String action = "";
        String VenueName = "";
        String VenueType = "";
        String VenueCapacity = "";
        String VenueLocation = "";
        String VenueDescription = "";
        String VenuePerson_in_charge = "";
        String VenueBooking_Fee = "";
        String VenueStatus = "";
        String fileName = "";

        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.isFormField()) {

                        String FieldName = item.getFieldName();
                        String value = item.getString();
                        System.out.println(FieldName);
                        if (FieldName.equals("action")) {
                            action = value;
                            System.out.println(action);

                        }
                        if ("AddVenue".equals(action)) {
                            if (FieldName.equals("VenueName")) {
                                VenueName = value;

                            } else if (FieldName.equals("VenueType")) {
                                VenueType = value;
                            } else if (FieldName.equals("VenueCapacity")) {
                                VenueCapacity = value;
                            } else if (FieldName.equals("VenueLocation")) {
                                VenueLocation = value;
                            } else if (FieldName.equals("VenueDescription")) {
                                VenueDescription = value;
                            } else if (FieldName.equals("VenuePerson_in_charge")) {
                                VenuePerson_in_charge = value;
                            } else if (FieldName.equals("VenueBooking_Fee")) {
                                VenueBooking_Fee = value;
                            }

                        } else if ("EditVenue".equals(action)) {

                            if (FieldName.equals("VenueId")) {
                                VenueId = value;

                            } else if (FieldName.equals("VenueName")) {
                                VenueName = value;

                            } else if (FieldName.equals("VenueType")) {
                                VenueType = value;
                            } else if (FieldName.equals("VenueCapacity")) {
                                VenueCapacity = value;
                            } else if (FieldName.equals("VenueLocation")) {
                                VenueLocation = value;
                            } else if (FieldName.equals("VenueDescription")) {
                                VenueDescription = value;
                            } else if (FieldName.equals("VenuePerson_in_charge")) {
                                VenuePerson_in_charge = value;
                            } else if (FieldName.equals("VenueBooking_Fee")) {
                                VenueBooking_Fee = value;
                            }
                        } else if ("DeleteVenue".equals(action)) {
                            if (FieldName.equals("Id")) {
                                VenueId = value;

                            }
                        } else if ("EnableOrDisable".equals(action)) {

                            if (FieldName.equals("VauneId")) {
                                VenueId = value;
                            } else if (FieldName.equals("status")) {
                                VenueStatus = value;
                            }
                        }

                    } else {

                    }
                }

                if ("AddVenue".equals(action)) {
                    System.out.println("====================AddVenue============================");
                    AddVenue(request, response, formItems, VenueName, VenueType, VenueCapacity, VenueLocation, VenueDescription, VenuePerson_in_charge, VenueBooking_Fee);
                } else if ("EditVenue".equals(action)) {
                    System.out.println("====================EditVenue============================");
                    EditVenue(
                            request,
                            response,
                            formItems,
                            VenueId,
                            VenueName,
                            VenueType,
                            VenueCapacity,
                            VenueLocation,
                            VenueDescription,
                            VenuePerson_in_charge,
                            VenueBooking_Fee
                    );
                } else if ("DeleteVenue".equals(action)) {
                    DeleteVenue(
                            request,
                            response,
                            formItems,
                            VenueId
                    );
                } else if ("EnableOrDisable".equals(action)) {
                    EnableOrDisable(request,
                            response,
                            formItems,
                            VenueId,
                            VenueStatus);
                }

            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

//        String action = request.getParameter("action");
//
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void AddVenue(HttpServletRequest request,
            HttpServletResponse response,
            List<FileItem> formItems,
            String VenueName,
            String VenueType,
            String VenueCapacity,
            String VenueLocation,
            String VenueDescription,
            String VenuePerson_in_charge,
            String VenueBooking_Fee) throws ServletException, IOException {

        try {
            // parses the request's content to extract file data
            String DBuploadPath = "";
            @SuppressWarnings("unchecked")
            List<FileItem> LOCALform = formItems;
            if (LOCALform != null && LOCALform.size() > 0) {
                // iterates over form's fields
                System.out.println("saaaaaaaaaasssssssssssssssssssssssssssss ");
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String uploadPath = defaultPath
                                + "\\\\" + fileName;

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {

                            System.out.println("saaaaaaaaaassiiiiiiiiiiiiiiiiiiiiiisssssssssssssssssssssssssss " + fileName);
                            String filePath = uploadPath;
                            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyssss " + filePath);
                            File storeFile = new File(filePath);
                            DBuploadPath = "../../Image" + "/" + fileName;
                            // saves the file on disk
                            item.write(storeFile);
                            VDB.InsertVenue(DBuploadPath, VenueName, VenueType, VenueCapacity, VenueLocation, VenueDescription, VenuePerson_in_charge, VenueBooking_Fee);
                        }

                    }
                }

            }
            System.out.println("yyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        String targetURL = "/Staff/VenueManagement/AddVenue.jsp";
        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);
    }

    private void EditVenue(
            HttpServletRequest request,
            HttpServletResponse response,
            List<FileItem> formItems,
            String VenueId,
            String VenueName,
            String VenueType,
            String VenueCapacity,
            String VenueLocation,
            String VenueDescription,
            String VenuePerson_in_charge,
            String VenueBooking_Fee) throws ServletException, IOException {

        try {
            // parses the request's content to extract file data
            String DBuploadPath = "";
            @SuppressWarnings("unchecked")
            List<FileItem> LOCALform = formItems;

            if (LOCALform != null && LOCALform.size() > 0) {
                // iterates over form's fields

                for (FileItem item : formItems) {
                    System.out.println("----------------------------------------------------------");
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        System.out.println("ss+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ss");

                        String VNAME = VDB.FindVenueName(Integer.parseInt(VenueId));
                        System.out.println(VNAME);
                        VNAME = VNAME.replace("../../Image/", "");
                        String uploadPath = defaultPath
                                + "\\\\" + VNAME;
                        System.out.println(uploadPath);

                        File uploadDir = new File(uploadPath);
                        if (uploadDir.exists()) {
                            System.out.println("s,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,++ss");
                            System.out.println(uploadDir.delete());

                            uploadPath = defaultPath;
                            uploadDir = new File(uploadPath);

                            String fileName = new File(item.getName()).getName();
                            String filePath = uploadPath + "\\" + fileName;
                            File storeFile = new File(filePath);
                            System.out.println(filePath);
                            DBuploadPath = "../../Image/" + fileName;
                            System.out.println(DBuploadPath);
                            item.write(storeFile);
                            VDB.EditVenue(DBuploadPath, VenueId, VenueName, VenueType, VenueCapacity, VenueLocation, VenueDescription, VenuePerson_in_charge, VenueBooking_Fee);
                        }
                    }
                }

            }
            System.out.println("yyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//          
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        String targetURL = "/Staff/VenueManagement/EditVenue.jsp";
        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);
    }

    private void DeleteVenue(HttpServletRequest request,
            HttpServletResponse response,
            List<FileItem> formItems, String VenueId) throws ServletException, IOException {
        try {
            // parses the request's content to extract file data
            String DBuploadPath = "";
            @SuppressWarnings("unchecked")
            List<FileItem> LOCALform = formItems;
           
            if (LOCALform != null && LOCALform.size() > 0) {
                // iterates over form's fields

                for (FileItem item : formItems) {
                    System.out.println("----------------------------------------------------------");
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        System.out.println("ss+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ss");

                        String VNAME = VDB.FindVenueName(Integer.parseInt(VenueId));
                        if (VNAME != null) {
                            System.out.println(VNAME);
                            VNAME = VNAME.replace("../../Image/", "");
                            String uploadPath = defaultPath
                                    + "\\\\" + VNAME;
                            System.out.println(uploadPath);

                            File uploadDir = new File(uploadPath);
                            if (uploadDir.exists()) {
                                System.out.println("s,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,++ss");
                                System.out.println(uploadDir.delete());

                                VDB.DeleteVenue(VenueId);
                                break;
                            }
                        }

                    }
                }

            }
            System.out.println("yyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//          
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        String targetURL = "/Staff/VenueManagement/DeleteVenue.jsp";
        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);
    }

    private void EnableOrDisable(HttpServletRequest request, HttpServletResponse response, List<FileItem> formItems, String VenueId, String VenueStatus) throws IOException, ServletException {
        try {
            // parses the request's content to extract file data

            @SuppressWarnings("unchecked")
            List<FileItem> LOCALform = formItems;

            if (LOCALform != null && LOCALform.size() > 0) {
                // iterates over form's fields
              
                for (FileItem item : formItems) {
                    System.out.println("----------------------------------------------------------");
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        System.out.println("ss+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ss");

                        VDB.EnableOrDisable(Integer.parseInt(VenueId), VenueStatus);

                    }
                }

            }
            System.out.println("yyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//          
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        String targetURL = "/Staff/VenueManagement/EnableOrDisableVenue.jsp";
        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);
    }
}
