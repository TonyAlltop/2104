
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    
    static ArrayList<String> userlist = new ArrayList<>(Arrays.asList("admin"));
    static ArrayList<String> passlist = new ArrayList<>(Arrays.asList("admin"));
    static ArrayList<String> rnamelist = new ArrayList<>(Arrays.asList("admin"));
    static Boolean signedin = false;
    

    public static void main(String[] args){
        org.eclipse.jetty.start.Main.main(args);
    }
}