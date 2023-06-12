/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.Chart;

import dao.DAO;
import entity.Order;
import entity.OrderDetail;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChartControl", urlPatterns = {"/chart"})
public class ChartControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAO dao = new DAO();
        List<Order> listOrder = dao.getAllOrder();
        List<OrderDetail> listOrderDetail = dao.getAllOrderDetail();
        int countOrder = 0;
        int m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, m6 = 0, m7 = 0, m8 = 0, m9 = 0, m10 = 0, m11 = 0, m12 = 0;
        double sumOrder = 0;
        double sumM1 = 0, sumM2 = 0, sumM3 = 0, sumM4 = 0, sumM5 = 0, sumM6 = 0, sumM7 = 0, sumM8 = 0, sumM9 = 0, sumM10 = 0, sumM11 = 0, sumM12 = 0;
        int countProduct = 0;

        int cp1 = 0, cp2 = 0, cp3 = 0, cp4 = 0, cp5 = 0, cp6 = 0, cp7 = 0, cp8 = 0, cp9 = 0, cp10 = 0 ,cp11 = 0 ,cp12=0;

        for (Order o : listOrder) {
            String date = o.getOrder_date();
            String[] words = date.split("-");
            if (words[1].equals("01")) {
                m1++;
                sumM1 += o.getTotal_price();
            } else if (words[1].equals("02")) {
                m2++;
                sumM2 += o.getTotal_price();
            } else if (words[1].equals("03")) {
                m3++;
                sumM3 += o.getTotal_price();
            } else if (words[1].equals("04")) {
                m4++;
                sumM4 += o.getTotal_price();
            } else if (words[1].equals("05")) {
                m5++;
                sumM5 += o.getTotal_price();
            } else if (words[1].equals("06")) {
                m6++;
                sumM6 += o.getTotal_price();
            } else if (words[1].equals("07")) {
                m7++;
                sumM7 += o.getTotal_price();
            } else if (words[1].equals("08")) {
                m8++;
                sumM8 += o.getTotal_price();
            } else if (words[1].equals("09")) {
                m9++;
                sumM9 += o.getTotal_price();
            } else if (words[1].equals("10")) {
                m10++;
                sumM10 += o.getTotal_price();
            } else if (words[1].equals("11")) {
                m11++;
                sumM11 += o.getTotal_price();
            } else if (words[1].equals("12")) {
                m12++;
                sumM12 += o.getTotal_price();
            }
            for(OrderDetail od : listOrderDetail) {
                if (o.getOrder_id() == od.getOrder_id()) {
                    if (words[1].equals("01")) {
                        cp1 += od.getQuantity();
                    } else if (words[1].equals("02")) {
                        cp2 += od.getQuantity();
                    } else if (words[1].equals("03")) {
                        cp3 += od.getQuantity();
                    } else if (words[1].equals("04")) {
                        cp4 += od.getQuantity();
                    } else if (words[1].equals("05")) {
                        cp5 += od.getQuantity();
                    } else if (words[1].equals("06")) {
                        cp6 += od.getQuantity();
                    } else if (words[1].equals("07")) {
                       cp7 += od.getQuantity();
                    } else if (words[1].equals("08")) {
                       cp8 += od.getQuantity();
                    } else if (words[1].equals("09")) {
                        cp9 += od.getQuantity();
                    } else if (words[1].equals("10")) {
                       cp10 += od.getQuantity();
                    } else if (words[1].equals("11")) {
                        cp11 += od.getQuantity();
                    } else if (words[1].equals("12")) {
                        cp12 += od.getQuantity();
                    }
                }
            }
        }
        String rsCountOrder = m1 + " " + m2 + " " + m3 + " " + m4 + " " + m5 + " " + m6 + " " + m7 + " " + m8 + " " + m9 + " " + m10 + " " + m11 + " " + m12;
        String rsSumOrder = sumM1 + " " + sumM2 + " " + sumM3 + " " + sumM4 + " " + sumM5 + " " + sumM6 + " " + sumM7 + " " + sumM8 + " " + sumM9 + " " + sumM10 + " " + sumM11 + " " + sumM12;
        String rsCountProduct = cp1 +" "+cp2+" "+cp3+" "+cp4+" "+cp5+" "+cp6+" "+cp7+" "+cp8+" "+cp9+" "+cp10+" "+cp11+" "+cp12;
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        String rs = rsCountOrder + "&" + rsSumOrder +"&" +rsCountProduct;
        response.getWriter().write(rs);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
