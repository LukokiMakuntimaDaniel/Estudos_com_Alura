package box.solution.lukoki.boxSolution.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLocalizacao(
       @JsonAlias("cep") String Cep,
       @JsonAlias("logradouro") String Logradouro,
       @JsonAlias("complemento") String Complemento,
       @JsonAlias("bairro") String Bairro,
       @JsonAlias("localidade") String Localidade,
       @JsonAlias("uf") String Uf,
       @JsonAlias("ibge") String Ibge,
       @JsonAlias("gia") String Gia,
       @JsonAlias("ddd") String Ddd,
       @JsonAlias("siafi") String Siafi) {
}
