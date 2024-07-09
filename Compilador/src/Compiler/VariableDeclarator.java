package Compiler;

public class VariableDeclarator {
    private String identifier;
    private String initializer;

    public VariableDeclarator(String identifier, String initializer) {
        this.identifier = identifier;
        this.initializer = initializer;
    }

    // Getters e outros métodos

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }
    
}

