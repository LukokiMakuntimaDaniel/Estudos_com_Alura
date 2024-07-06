package Analisadores;

import java.util.List;

public class ProgramNode extends ASTNode {
    public final List<CommandNode> comandos;

    public ProgramNode(List<CommandNode> comandos) {
        this.comandos = comandos;
    }

    @Override
    public String toString() {
        return "Program(" + comandos + ")";
    }
}
