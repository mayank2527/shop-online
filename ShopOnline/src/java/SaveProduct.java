import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProduct extends HttpServlet {

    Connection con; PreparedStatement ps;
    
    @Override
    public void init(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        String qr="insert into prodinfo values(?,?,?,?,?)";
        ps=con.prepareStatement(qr);        
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
        //?pcode&title&desc&catg&price
        String s1=request.getParameter("pcode");
        String s2=request.getParameter("title");
        String s3=request.getParameter("desc");
        String s4=request.getParameter("catg");
        String s5=request.getParameter("price");
        try{
            ps.setInt(1,Integer.parseInt(s1));
            ps.setString(2,s2);
            ps.setString(3,s3);
            ps.setString(4,s4);
            ps.setInt(5,Integer.parseInt(s5));
            ps.executeUpdate();
            out.println("<h3>Product-Added-Successfully</h3>");
            out.println("<h4><a href=pentry.jsp>Add-More</a></h4>");
            out.println("<h4><a href=adminhome.jsp>Admin-Home</a></h4>");
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
