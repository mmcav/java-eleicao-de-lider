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
                Thread.sleep(1000 * 2);
                if (!Eleicao.getInstance().isEleicaoIniciada()) {
                    Processo processo = Processos.getInstance().getRandomProcesso();
                    if (!processo.isLider()) {
                        try {
                            ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                            socket.enviar("01|Olá, eu sou o processo consumidor " + this.nome);
                            String resposta = socket.receber();
                            System.out.println("Resposta: " + resposta);
                        } catch (IOException ex) {
                            Logger.getLogger(MultiPrograma.class.getName()).log(Level.SEVERE, "Erro na conexao com " + processo.getIdentificador() + ": " + ex.getMessage());
                        }
                    } else {
                        this.checkLider();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiPrograma.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}