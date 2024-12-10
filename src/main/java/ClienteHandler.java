import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler implements Runnable {
    private Socket socket;
    private Processo me;

    public ClienteHandler(Socket socket, Processo me) {
        this.socket = socket;
        this.me = me;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String msg = in.readLine();
            System.out.println("Mensagem recebida: " + msg);
            String[] protocolo = msg.split("\\|");
            switch (protocolo[0]) {
                case "01":
                    out.println("Oi! E eu sou o processo " + this.me.getIdentificador());
                    break;
                case "02":
                    if (me.isLider()) {
                        out.println("Eu sou o lider! Processo " + this.me.getIdentificador());
                    } else {
                        out.println("Eu não sou o lider, eu sou o processo " + this.me.getIdentificador());
                    }
                    break;
                case "03":
                    out.println("OK");
                    Eleicao.getInstance().checkEleicao(protocolo[1]);
                    break;
                case "04":
                    out.println("OK");
                    Eleicao.getInstance().atualizarLider(Integer.valueOf(protocolo[1]));
                    break;
                default:
                    System.out.println("codigo: " + protocolo[0]);
                    out.println("09|Error");
                    break;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
}
