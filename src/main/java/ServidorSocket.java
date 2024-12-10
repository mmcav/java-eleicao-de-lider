import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket implements Runnable {
    private ServerSocket serverSocket;
    private Processo me;

    public ServidorSocket(Processo me, Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.me = me;
        System.out.println("Servidor iniciado na porta " + port + ". Aguardando conexões...");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClienteHandler(socket, this.me));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
