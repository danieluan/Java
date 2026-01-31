package po.gamed;


public class DVD extends Obra {

    private String realizador;
    private String dndac;

    public DVD(String titulo, String categoria, int totalExemplares, double preco, int anoRegistro, String realizador, String dndac, String url) {
        super(titulo, categoria, totalExemplares, preco, anoRegistro, url);
        this.realizador = realizador;
        this.dndac = dndac;
    }

    public String getRealizador() {
        return realizador;
    }

    public String getDndac() {
        return dndac;
    }

    @Override
    public int workReturnDay() {
        if (getCategoria().equalsIgnoreCase("Obras de referência")) {
            return 0;
        } else if (getCategoria().equalsIgnoreCase("Obras de ficção")) {
            return 14;
        } else {
            return 7;
        }
    }
}
