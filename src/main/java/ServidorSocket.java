import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket implements Runnable {
    private ServerSocket serverSocket;
    private String identificador;

    public ServidorSocket(Integer port, String identificador) throws IOException {
        serverSocket = new ServerSocket(port);
        this.identificador = identificador;
        System.out.println("Servidor  iniciado na porta " + port + ". Aguardando conexões...");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClienteHandler(socket, this.identificador));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
