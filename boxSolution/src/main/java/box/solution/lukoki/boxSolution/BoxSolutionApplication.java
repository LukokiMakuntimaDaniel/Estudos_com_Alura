package box.solution.lukoki.boxSolution;

import box.solution.lukoki.boxSolution.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoxSolutionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BoxSolutionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI consumoAPI = new ConsumoAPI();
		String json = consumoAPI.obterDados("https://viacep.com.br/ws/01001000/json/");
		System.out.println(json);
	}
}
