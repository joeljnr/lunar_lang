
package lunarcomp;

public class Erro {
    private String token;
    private int linha;
    private String descricao;

    public Erro(String token, int linha, String descricao) {
        this.token = token;
        this.linha = linha;
        this.descricao = descricao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String exibeErro() {
        return linha + ": " + descricao + "\n";
    }
}
