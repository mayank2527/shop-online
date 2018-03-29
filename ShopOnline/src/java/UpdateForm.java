import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateForm extends HttpServlet {
    Connection con; PreparedStatement ps; ResultSet rs;
    @Override
    public void init(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        String qr="select pcode,ptitle,pdesc,price from prodinfo where pcode=?";        
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
        String id=request.getParameter("pcode");
        try{
            ps.setInt(1,Integer.parseInt(id));
            rs=ps.executeQuery();
            rs.next();
            String s1=rs.getString(1);//code
            String s2=rs.getString(2);//title
            String s3=rs.getString(3);//desc
            String s4=rs.getString(4);//price
            out.println("<html>");
            out.println("<body>");
            out.println("<h2>Updation-Entry</h2>");
            out.println("<form action=SaveChanges>");
            out.println("<pre>");
            out.println("PCode    <input type=text name=pcode value="+s1+">");
            out.println("Title    <input type=text name=title value="+s2+">");
            out.println("Descr    <input type=text name=desc value="+s3+">");
            out.println("Price    <input type=text name=price value="+s4+">");
            out.println("         <input type=submit name=btn value=SaveChanges>");
            out.println("         <input type=submit name=btn value=RemoveProduct>");
            out.println("</pre>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            
            
            
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
