package po.gamed;



public class Livro extends Obra {

    private String isbn;
    private String autor;

    public Livro(String titulo, String categoria, int totalExemplares, double preco, int anoRegistro, String autor, String isbn, String url) {
        super(titulo, categoria, totalExemplares, preco, anoRegistro, url);
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public int workReturnDay() {
        // Exemplo de regra polimórfica
        if (getCategoria().equalsIgnoreCase("Obras de referência")) {
            return 0; // Não pode requisitar
        } else if (getCategoria().equalsIgnoreCase("Obras de ficção")) {
            return 14;
        } else {
            return 7;
        }
    }
}
