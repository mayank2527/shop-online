import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveChanges extends HttpServlet {
    Connection con; PreparedStatement ps,ps1;
    @Override
    public void init(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        String qr="update prodinfo set ptitle=?, pdesc=?, price=? where pcode=?";        
        ps=con.prepareStatement(qr);        
        ps1=con.prepareStatement("delete from prodinfo where pcode=?");
        }catch(Exception e){}
    }
    @Override
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //?pcode=112&title=dvd&desc=philips5.1&price=28000
        String s=request.getParameter("btn");
        String s1=request.getParameter("pcode");
        String s2=request.getParameter("title");
        String s3=request.getParameter("desc");
        String s4=request.getParameter("price");
       
 //"update prodinfo set ptitle=?, pdesc=?, price=? where pcode=?";        
        try{
        if(s.equals("SaveChanges"))    
        {
        ps.setString(1, s2);
        ps.setString(2, s3);
        ps.setInt(3, Integer.parseInt(s4));
        ps.setInt(4, Integer.parseInt(s1));
        ps.executeUpdate();
         out.println("<h3>Product-Details-Changed-Successfully</h3>");
         out.println("<h4><a href=update.jsp>Change-More</a></h4>");
         out.println("<h4><a href=adminhome.jsp>Admin-Home</a></h4>");
        }
        else
        {
        ps1.setInt(1, Integer.parseInt(s1));
        ps1.executeUpdate();
         out.println("<h3>Product-Removed-Successfully</h3>");
         out.println("<h4><a href=update.jsp>Change-More</a></h4>");
         out.println("<h4><a href=adminhome.jsp>Admin-Home</a></h4>");            
        }
        }catch(Exception e){
        out.println(e);
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
