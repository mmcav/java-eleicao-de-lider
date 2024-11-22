public abstract class Tipo {
    protected String porta;
    protected String nome;

    public Tipo(String porta, String nome) {
        this.porta = porta;
        this.nome = nome;
        System.out.println("Estou escutando na porta " + this.porta);
    }

    public abstract void run();
    
}
