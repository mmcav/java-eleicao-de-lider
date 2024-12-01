public class Processo {
    private String identificador;
    private String host;
    private String port;

    public Processo(String identificador, String host, String port) {
        this.identificador = identificador;
        this.host = host;
        this.port = port;
    }

    public String getIdentificador() {
        return this.identificador;
    }
    
    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }
    
}
