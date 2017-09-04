package lunarcomp;

public class Token<T> {
    private String token;
    private T lex;
    private int linha;

    public Token(String token, T lex, int linha) {
        this.token = token;
        this.lex = lex;
        this.linha = linha;
    }
    
    public String getToken() {
        return token;
    }

    public T getLex() {
        return lex;
    }

    public int getLinha() {
        return linha;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLex(T lex) {
        this.lex = lex;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
}
