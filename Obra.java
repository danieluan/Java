package po.gamed;



import java.io.Serializable;

public abstract class Obra implements Serializable {

    private static int sequencial = 0;

    private String id;
    private String titulo;
    private String categoria;
    private int totalExemplares;
    private int exemplaresDisponiveis;
    private double preco;
    private String url;

    public Obra(String titulo, String categoria, int totalExemplares, double preco, int anoRegistro, String url) {
        this.id = String.format("%04d/%d", ++sequencial, anoRegistro);
        this.titulo = titulo;
        this.categoria = categoria;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
        this.preco = preco;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getCategoria() {
        return categoria;
    }

    public int getTotalExemplares() {
        return totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public double getPreco() {
        return preco;
    }

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url= url;
    }
    public void reduzirDisponiveis() {
        this.exemplaresDisponiveis--;
    }

    public void aumentarDisponiveis() {
        this.exemplaresDisponiveis++;
    }

    public abstract int workReturnDay();
}
