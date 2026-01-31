package po.gamed;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String nome;
    private boolean requisitavel;

    public Categoria(String nome, boolean requisitavel) {
        this.nome = nome;
        this.requisitavel = requisitavel;
    }

    public String getNome() {
        return nome;
    }

    public boolean isRequisitavel() {
        return requisitavel;
    }

    @Override
    public String toString() {
        return nome;
    }
}
