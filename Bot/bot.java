import java.io.IOException;

public class bot {
    public static void main(String[] args) throws IOException {
        API api = new API("localhost", 8818);
        api.run("TOKEN");
    }
}