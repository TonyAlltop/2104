
import java.net.URL;

public class Downloader implements DownloadInterface {
    public byte[] downloadUrl(String url) throws Exception{
        var u1 = new URL(url);
        var conn = u1.openConnection();
        byte[] data = conn.getInputStream().readAllBytes();
        return data;
    }
}
