import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class Tipo {
    protected String porta;
    protected String nome;
    protected List<Processo> processos;
    protected ServidorSocket serverSocket;

    public Tipo(String porta, String nome) {
        this.porta = porta;
        this.nome = nome;
        System.out.println("Estou escutando na porta " + this.porta);

        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("app.config")) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de configuração não encontrado.");
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        System.out.println(prop.getProperty("app.name"));

        Integer totalProcesso = Integer.parseInt(prop.getProperty("app.processo.total"));
        processos = new ArrayList<>();
        for (int i = 1; i <= totalProcesso; i++) {
            String identificador = prop.getProperty("app.processo." + i + ".identificador");
            String host = prop.getProperty("app.processo." + i + ".host");
            String port = prop.getProperty("app.processo." + i + ".port");
            Processo processo = new Processo(identificador, host, port);
            processos.add(processo);
        }
        iniciarConexao();
    }

    protected void iniciarConexao() {
        try {
            serverSocket = new ServidorSocket(Integer.valueOf(this.porta), this.nome);
            Thread thread = new Thread(serverSocket);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void run();
    
}
