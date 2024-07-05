package box.solution.lukoki.boxSolution.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class MinhaLocalizacao {
    private String Cep;
    private String Logradouro;
    private String Complemento;
    private String Bairro;
    private String Uf;
    private String Ibge;
    private String Gia;
    private String Ddd;
    private String Siafi;

    public MinhaLocalizacao(DadosLocalizacao dadosLocalizacao) {
        Cep = dadosLocalizacao.Cep();
        Logradouro = dadosLocalizacao.Logradouro();
        Complemento = dadosLocalizacao.Complemento();
        Bairro = dadosLocalizacao.Bairro();
        Uf = dadosLocalizacao.Uf();
        Ibge = dadosLocalizacao.Ibge();
        Gia = dadosLocalizacao.Gia();
        Ddd = dadosLocalizacao.Ddd();
        Siafi = dadosLocalizacao.Siafi();
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getUf() {
        return Uf;
    }

    public void setUf(String uf) {
        Uf = uf;
    }

    public String getIbge() {
        return Ibge;
    }

    public void setIbge(String ibge) {
        Ibge = ibge;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getDdd() {
        return Ddd;
    }

    public void setDdd(String ddd) {
        Ddd = ddd;
    }

    public String getSiafi() {
        return Siafi;
    }

    public void setSiafi(String siafi) {
        Siafi = siafi;
    }
}
