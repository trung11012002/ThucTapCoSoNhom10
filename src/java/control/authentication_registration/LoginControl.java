/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control.authentication_registration;

import dao.DAO;
import entity.AES;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name="LoginControl", urlPatterns={"/login"})
public class LoginControl extends HttpServlet {
   
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
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String remember = request.getParameter("remember");
        
        final String secretKey = "ssshhhhhhhhhhhtrung123!!!!";
        
        DAO dao = new DAO();
        User a = dao.checkUserExist(email);
        String passwordDecrypted = AES.decrypt(a.getUser_password(), secretKey);
        if(a != null && password.equals(passwordDecrypted)) {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setMaxInactiveInterval(60*60*24);
            
            //Cookie remember
            Cookie cu = new Cookie("email" , email);
            Cookie cp = new Cookie("pass" , password);
            Cookie cr = new Cookie("remember" ,remember);
            if(remember == null) {
                cu.setMaxAge(0);
                cp.setMaxAge(0);
                cr.setMaxAge(0);
            }else {
                cu.setMaxAge(60*60*24);
                cp.setMaxAge(60*60*24);
                cr.setMaxAge(60*60*24);
            }
            response.addCookie(cu);
            response.addCookie(cp);
            response.addCookie(cr);
            request.getRequestDispatcher("home").forward(request, response);
               
        }else {
            String ms = "wrong";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("Login.jsp").forward(request, response);       
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
