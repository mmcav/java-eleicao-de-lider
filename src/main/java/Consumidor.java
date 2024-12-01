import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Tipo {
    public Consumidor(String porta, String nome) {
        super(porta, nome);
    }

    public void run() {
        while (true) {
            try {
                for (Processo processo : processos) {
                    try {
                        ClienteSocket socket = new ClienteSocket(processo.getHost(), Integer.valueOf(processo.getPort()));
                        socket.enviar("Olá, eu sou o processo consumidor " + this.nome);
                        String resposta = socket.receber();
                        System.out.println("Resposta: " + resposta);
                    } catch (IOException ex) {
                        Logger.getLogger(MultiPrograma.class.getName()).log(Level.SEVERE, "Erro na conexão com " + processo.getIdentificador() + ": " + ex.getMessage());
                    }
                }
                System.out.println("Vou dormir!");
                Thread.sleep(1000*60);
                System.out.println("Acordei!");
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiPrograma.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}