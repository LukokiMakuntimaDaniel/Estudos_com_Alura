package Compiler;

import java.util.List;

public class ClassDeclaration extends TypeDeclaration {
    private String className;
    private List<MembroClasse> classMembers;

    public ClassDeclaration(String className, List<MembroClasse> classMembers) {
        this.className = className;
        this.classMembers = classMembers;
    }

    // Getters e outros métodos

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<MembroClasse> getClassMembers() {
        return classMembers;
    }

    public void setClassMembers(List<MembroClasse> classMembers) {
        this.classMembers = classMembers;
    }
    
}

