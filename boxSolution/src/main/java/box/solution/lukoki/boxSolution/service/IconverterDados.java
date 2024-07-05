package box.solution.lukoki.boxSolution.service;

public interface IconverterDados {
    <T> T obterDados(String Json, Class<T> classe);
}
