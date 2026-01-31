package po.gamed;


import java.io.Serializable;

public class Utente implements Serializable {

    private static int sequencial = 0;

    private int id;
    private String nome;
    private String email;

    public Utente(String nome, String email) {
        this.id = ++sequencial;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
