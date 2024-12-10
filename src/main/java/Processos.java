import java.util.Map;
import java.util.Random;

public class Processos {
    private static Processos uniqueInstances;

    private Map<Integer, Processo> processos;

    private Processo me;
    private Processo lider;

    private Integer totalProcesso;

    private Processos() {
    }

    public static synchronized Processos getInstance() {
        if (uniqueInstances == null) {
            uniqueInstances = new Processos();
        }
        return uniqueInstances;
    }
    
    public Map<Integer, Processo> getProcessos() {
        return processos;
    }

    public Processo getMe() {
        return me;
    }

    public Processo getLider() {
        return lider;
    }

    public Integer getTotalProcesso() {
        return totalProcesso;
    }

    public void setLider(Processo lider) {
        this.lider = lider;
    }
    
    public void config(Map<Integer, Processo> processos, Processo me, Processo lider, Integer totalProcesso) {
        this.processos = processos;
        this.me = me;
        this.lider = lider;
        this.totalProcesso = totalProcesso;
    }

    public Processo getRandomProcesso() {
        Random rand = new Random();
        int index = rand.nextInt(processos.size());
        index = (index == 0) ? processos.size() : index;
        return processos.get(index);
    }
}
