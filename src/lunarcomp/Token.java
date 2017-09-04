package lunarcomp;

public class Token {
    private String token;
    private String lex;
    private int lin;
    private int col;

    public Token(String token, String lex, int lin, int col) {
        this.token = token;
        this.lex = lex;
        this.lin = lin;
        this.col = col;
    }
    
    public String getToken() {
        return token;
    }

    public String getLex() {
        return lex;
    }

    public int getLin() {
        return lin;
    }

    public int getCol() {
        return col;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public void setLin(int lin) {
        this.lin = lin;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    
}
