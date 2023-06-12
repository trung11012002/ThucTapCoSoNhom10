/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control.forgotPassword;

import dao.DAO;
import entity.AES;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author admin
 */
@WebServlet(name="ForgotPassword", urlPatterns={"/forgotpassword"})
public class ForgotPassword extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        String checkcode = request.getParameter("checkcode");
        String email = request.getParameter("email");
        if(code.equals(checkcode)){
            DAO dao = new DAO();
            User a  = dao.checkUserExist(email);
            Random random = new Random();
            int randomNumber = random.nextInt(99999999) + 1;
            String randomString = String.format("%08d", randomNumber);
            String uid = a.getUser_id() +"";
            String user_name = a.getUser_name();
            String user_email = a.getUser_email();
            String user_password_raw = randomString;
            String user_address = a.getAddress();
            String isAdmin  =a.getIsAdmin()+"";
            final String secretKey = "ssshhhhhhhhhhhtrung123!!!!";
            String user_password = AES.encrypt(user_password_raw, secretKey);
            dao.EditUserByID(uid, user_name, user_email, user_password, user_address, isAdmin);
            request.setAttribute("newpass", randomString);
            request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
        }
        else {
            String ms = "Error";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
