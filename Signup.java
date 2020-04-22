
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/signup"})
public class Signup extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var user = req.getParameter("user");
        var pass = req.getParameter("password");
        var rname = req.getParameter("name");
        var already = false;
        
        
        if( user == null){
            pw.printf("No username provided");
        } 
        else if(pass == null){
            pw.printf("No password provided");
        }
        else if(rname == null){
            pw.printf("No real name provided");
        }
        else {
            for (int i=0; i < Main.userlist.size(); i++)
            {
                if(user.equals(Main.userlist.get(i)))
                {
                    pw.printf("User already created");
                    already = true;
                }
                else
                {
                    already = false;
                }
            }
            if(!already)
            {
                Main.userlist.add(user);
                Main.passlist.add(pass);
                Main.rnamelist.add(rname);
                Main.signedin = true;
                var sess = req.getSession();
                sess.setAttribute("name", user );
                pw.printf("Signed up as "+user + "\n");
                pw.printf("Real name is "+rname);
            }
        }
    }

}
