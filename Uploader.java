
import java.net.HttpURLConnection;
import java.net.URL;

public class Uploader implements UploadInterface {
    public void uploadData( String url, byte[] data) throws Exception{
        var u2 = new URL(url);
        var conn2 = (HttpURLConnection) u2.openConnection();
        conn2.setDoOutput(true);
        conn2.setRequestMethod("POST");
        conn2.connect();
        var os = conn2.getOutputStream();
        os.write(data);
        os.flush();
        conn2.getInputStream().readAllBytes();
    }
}

