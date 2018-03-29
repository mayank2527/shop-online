import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyUser extends HttpServlet {

    Connection con; PreparedStatement ps;

    public void init(){
        try{
             String qr="select * from userinfo where userid=? and password=?";
             Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
             ps=con.prepareStatement(qr);
            
        }catch(Exception e){}
    }
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //VerifyUser?uid=aaa&pwd=bbb&utype=buyer
        String s1=request.getParameter("uid");
        String s2=request.getParameter("pwd");
        String s3=request.getParameter("utype");
        
        if(s3.equals("buyer")){
            try{
                ps.setString(1, s1);
                ps.setInt(2, Integer.parseInt(s2));
                ResultSet rs=ps.executeQuery();
                boolean b=rs.next();
                if(b==true){
                    response.sendRedirect("buyerhome.jsp");
                }else{
                    out.println("Invalid Buyer Details");
                }
            }catch(Exception e){
                out.println(e);
            }
 
        }else if(s3.equals("admin")){
            if(s1.equals("admin") && s2.equals("indore")){
                response.sendRedirect("adminhome.jsp");
            }else{
                out.println("Wrong Admin Details");
            }
            
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
