public class Ingrediente {
    private String nome;
    private double valor;
    private double quantidade;
    private String unidade;

    // Construtor
    public Ingrediente(String nome, double valor, double quantidade, String unidade) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getUnidade() {
        return unidade;
    }


    public static double converter(double qtd, String origem, String destino) {
        double fatorOrigem = unidadeParaBase(origem);
        double fatorDestino = unidadeParaBase(destino);

        if (fatorOrigem == -1 || fatorDestino == -1) {
            return qtd;
        }

        double qtdEmBase = qtd * fatorOrigem;
        return qtdEmBase / fatorDestino;
    }

    private static double unidadeParaBase(String unidade) {
        switch (unidade) {
            case "kg": return 1000;
            case "hg": return 100;
            case "dag": return 10;
            case "g": return 1;
            case "dg": return 0.1;
            case "cg": return 0.01;
            case "mg": return 0.001;
            case "kl": return 1000000;
            case "hl": return 100000;
            case "dal": return 10000;
            case "l": return 1000;
            case "dl": return 100;
            case "cl": return 10;
            case "ml": return 1;
            case "UN": return 1;
            default: return -1;
        }
    }


    public double calcularCusto(double qtdUsada, String unidadeUsada) {
        double qtdConvertida = converter(qtdUsada, unidadeUsada, this.unidade);
        return (valor / quantidade) * qtdConvertida;
    }

    @Override
    public String toString() {
        return nome + " | " + quantidade + " " + unidade + " | R$" + String.format("%.2f", valor);
    }
}
