import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/logout"})
public class Logout extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        var rname = req.getParameter("name"); 
        var pw = resp.getWriter();
        if( rname == null ){
            pw.printf("Not signed in");
        } 
        else{
           pw.printf("Logged out"); 
        req.getSession().removeAttribute("name");
        resp.setContentType("text/plain");
        pw.printf("Logged out");
        }
    
    }

}
