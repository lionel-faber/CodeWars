/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lionel
 */
@WebServlet(name="servlet_tier1", urlPatterns = {"/servlet_tier1"})
public class servlet1 extends HttpServlet {

    String message, phno, name;
    String[] ans = new String[26];
    String[] corr = new String[26];
    int i = 1;
    int total = 0;
    java.sql.Connection connect;
    java.sql.Statement stmt = null;
    java.sql.ResultSet rs = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try{
           String url="jdbc:mysql://192.168.0.7:3306/codewars?user=root&password=lionelfaber";
           Class.forName("com.mysql.jdbc.Driver");
           connect = DriverManager.getConnection(url,"root","lionelfaber");
           message = "Thank you for your participation!";
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
       phno = request.getParameter("phone_no");
       name = request.getParameter("name");
       for(i = 1; i <= 25; i++)
        {
            String a = "group"+i; 
            String b = "answ"+i;
           ans[i] = request.getParameter(a);
           corr[i] = request.getParameter(b);
           if(ans[i].equals(corr[i]))
                total += 2;
        }
        try 
        {
            stmt = connect.createStatement();
            String query = "INSERT INTO result values('"+phno+"','"+name+"','"+total+"')";
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch(SQLException ex){
            
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>"+message+"</h1>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        total = 0;
   }


 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param   request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(servlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request   }
}
"
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(servlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
         