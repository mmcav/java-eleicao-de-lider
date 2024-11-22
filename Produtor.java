import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor extends Tipo {
    public Produtor(String porta, String nome) {
        super(porta, nome);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Vou dormir!");
                Thread.sleep(1000*60);
                System.out.println("Acordei!");
                Thread.sleep(1000*60);
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}