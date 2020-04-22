import java.net.CookieHandler;
import java.net.CookieManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SignupNGTest {
    
public SignupNGTest() {
    }
    static CookieManager cookieManager = new CookieManager();
    @BeforeClass
    public static void setupSession(){
        CookieHandler.setDefault(cookieManager);
    }
    @BeforeClass
    public static void startJetty() throws Exception {
        String[] args = new String[]{
            "jetty.home=../jetty",
            "STOP.PORT=2021", "STOP.KEY=AutomaticTofu"
        };
        var LG = new StdErrLog();
        LG.setLevel(AbstractLogger.LEVEL_OFF);
        Log.setLog(LG);
        org.eclipse.jetty.start.Main.main(args);
    }
    @BeforeMethod
    public void clearCookies(){
        cookieManager.getCookieStore().removeAll();
    }

    @AfterClass
    public static void stopJetty() throws Exception {
        String[] args = new String[]{ "jetty.home=../jetty",
            "STOP.PORT=2021", "STOP.KEY=AutomaticTofu",
            "--stop"
        };
        org.eclipse.jetty.start.Main.main(args);
    }
    @Test
    public void testDoGet() throws Exception {
       
       String test = Utils.fetch("/srv/signup?user=Bob&password=bobiscool&name=Bob");
       assertTrue(test.contains("Signed up as Bob\nReal name is Bob"));
    }
    @Test
    public void testDoGet1() throws Exception {
       
       String test = Utils.fetch("/srv/signup?user=Bob&password=bobiscool&name=Bob&/srv/signup?user=Bob&password=bobiscool&name=Bob");
       assertTrue(test.contains("User already created"));
    }
    @Test
    public void testDoGet2() throws Exception {
       
       String test = Utils.fetch("/srv/signup?");
       assertTrue(test.contains("No username provided"));
    }
    @Test
        public void testDoGet3() throws Exception {
       
       String test = Utils.fetch("/srv/signup?user=Bob");
       assertTrue(test.contains("No password provided"));
    }
        @Test
        public void testDoGet4() throws Exception {
       
       String test = Utils.fetch("/srv/signup?user=Bob&password=bobiscool");
       assertTrue(test.contains("No real name provided"));
    }
}