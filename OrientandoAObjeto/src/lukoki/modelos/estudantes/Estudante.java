package lukoki.modelos.estudantes;

public class Estudante extends Pessoa {
    private int numeroEstudante;

    public int getNumeroEstudante() {
        return numeroEstudante;
    }

    public  Estudante(int numeroEstudante, String nome, int altura){
        super(nome,altura);
        this.numeroEstudante = numeroEstudante;
    }

    public void setNumeroEstudante(int numeroEstudante) {
        this.numeroEstudante = numeroEstudante;
    }

    @Override
    public void comer(){
        System.out.println("eu tambem sei comer");
    }



}
