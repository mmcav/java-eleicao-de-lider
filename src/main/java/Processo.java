public class Processo {
    private Integer identificador;
    private String host;
    private Integer port;

    private Boolean lider;

    public Processo(Integer identificador, String host, Integer port) {
        this.identificador = identificador;
        this.host = host;
        this.port = port;
        this.lider = false;
    }

    public Integer getIdentificador() {
        return this.identificador;
    }
    
    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public Boolean isLider() {
        return this.lider;
    }

    public void setIsLider(Boolean lider) {
        this.lider = lider;
    }
    
}
