
package lunarcomp.file;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Random;

//... classe Arquivo (onde vai estar o mÈtodo para ordernar, etc) ....
public class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()  
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return (retorno);
    }

    //insere um Registro no final do arquivo, passado por par‚metro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (i < filesize())
        {
            System.out.print("Posicao " + i + ": ");
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public int filesize() {
        int size = 0;
        try{
            size = (int)arquivo.length()/Registro.length();
        }catch(IOException e){}
        return size;
    }

    //mÈtodo principal
    public static void main(String args[])
    {
        
        Arquivo a = new Arquivo("arquivo.dat");
        Registro reg = null;
        
        a.exibirArq();
    }
}

    

