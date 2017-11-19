package lunarcomp;

public class ID {
    private String nome;
    private String tipo;
    
    private String valorstring;
    private int valorint;
    private float valorfloat;
    private char valorchar;
    private boolean valorboolean;
    
    private boolean inicializado;
    private int ultimouso;

    public ID(String nome, String tipo, boolean inicializado, int ultimouso) {
        this.nome = nome;
        this.tipo = tipo;
        
        this.inicializado = inicializado;
        this.ultimouso = ultimouso;
    }
    
    public void setValor(String tipo, String valor) {
            if(tipo.equals("string"))
            this.valorstring = valor;
        else if(tipo.equals("int"))
            this.valorint = Integer.parseInt(valor);
        else if(tipo.equals("float"))
            this.valorfloat = Float.parseFloat(valor);
        else if(tipo.equals("char"))
            this.valorchar = valor.charAt(0);
        else if(tipo.equals("bool"))
            this.valorboolean = Boolean.parseBoolean(valor);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getString() {
        return valorstring;
    }

    public void setString(String valorstring) {
        this.valorstring = valorstring;
    }

    public int getInt() {
        return valorint;
    }

    public void setInt(int valorint) {
        this.valorint = valorint;
    }

    public float getFloat() {
        return valorfloat;
    }

    public void setFloat(float valorfloat) {
        this.valorfloat = valorfloat;
    }

    public char getChar() {
        return valorchar;
    }

    public void setChar(char valorchar) {
        this.valorchar = valorchar;
    }

    public boolean isBooelan() {
        return valorboolean;
    }

    public void setBoolean(boolean valorboolean) {
        this.valorboolean = valorboolean;
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    public int getUltimouso() {
        return ultimouso;
    }

    public void setUltimouso(int ultimouso) {
        this.ultimouso = ultimouso;
    }
}
