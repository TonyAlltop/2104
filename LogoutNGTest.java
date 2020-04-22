/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author user
 */
public class LogoutNGTest {
    
    public LogoutNGTest() {
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
       String test1 = Utils.fetch("/srv/login?user=Bob&password=bobiscool");
       String test2 = Utils.fetch("/srv/logout?");
       assertTrue(test2.contains("Not signed in"));
    }
        @Test
    public void testDoGet2() throws Exception {
       String test = Utils.fetch("/srv/logout");
       assertTrue(test.contains("Not signed in"));
    }

    
}
