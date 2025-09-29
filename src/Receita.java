import java.util.ArrayList;
import java.util.List;

public class Receita {
    private String nome;
    private String descricao;
    private double custoFabricacao;
    private List<String> ingredientes;
    private double custoIngredientes;
    private double UnidadeReceita;
    private double porcentagemLucro;
    public Receita(String nome) {
        this.nome = nome;
        this.ingredientes = new ArrayList<>();
        this.custoIngredientes = 0;
    }

    public void adicionarIngrediente(Ingrediente ing, double quantidadeUsada, String unidadeUsada) {
        double custo = ing.calcularCusto(quantidadeUsada, unidadeUsada);
        custoIngredientes += custo;
        ingredientes.add(quantidadeUsada + " " + unidadeUsada + " de " + ing.getNome() +
                " (Custo: R$" + String.format("%.2f", custo) + ")");
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCustoFabricacao(double custoFabricacao) {
        this.custoFabricacao = custoFabricacao;
    }

    public void setUnidadeReceita(double UnidadeReceita) {
        this.UnidadeReceita = UnidadeReceita;
    }
    public void setporcentagemLucro(double porcentagemLucro) {
        this.porcentagemLucro = porcentagemLucro;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        double custoTotal = custoIngredientes + custoFabricacao;
        double custoUnidade = custoTotal / UnidadeReceita;
        double margemLucro = custoUnidade * porcentagemLucro / 100;
        double valorSugerido = custoUnidade + margemLucro;


        return "Receita: " + nome +
                "\nDescrição: " + descricao +
                "\nRendimento (UN): " + UnidadeReceita +
                "\nIngredientes:\n" + String.join("\n", ingredientes) +
                "\nCusto dos Ingredientes: R$" + String.format("%.2f", custoIngredientes) +
                "\nCustos Indiretos: R$" + String.format("%.2f", custoFabricacao) +
                "\nCusto Total: R$" + String.format("%.2f", custoTotal)+
                "\nCusto por Unidade: R$" + String.format("%.2f", custoUnidade) +
        "\nValor de venda sugerido: R$" + String.format("%.2f", valorSugerido) ;
    }
}
