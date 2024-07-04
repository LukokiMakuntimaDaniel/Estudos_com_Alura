package lukoki.modelos.estudantes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {
    public String nome;
    private Date dataNacimento;
    private int altura;

    public  Pessoa(String nome,int altura){
        this.nome = nome;
        this.altura = altura;
    }



    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setDataNacimento(String dataNacimento) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dataNacimento = formato.parse(dataNacimento);
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNacimento() {
        return dataNacimento;
    }

    public int getAltura() {
        return altura;
    }

    public String getNome() {
        return nome;
    }

    public void comer(){
        System.out.println("ola como vai");
    }
}
