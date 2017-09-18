
package lunarcomp;

public class RetornoSyntax {
    private boolean aceito;
    String token;

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RetornoSyntax(boolean aceito, String token) {
        this.aceito = aceito;
        this.token = token;
    }
    
    
}
