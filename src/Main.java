import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Ingrediente> ingredientes = new ArrayList<>();
    private static List<Receita> receitas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Cadastrar ingrediente");
            System.out.println("2 - Cadastrar receita");
            System.out.println("3 - Mostrar ingredientes");
            System.out.println("4 - Mostrar receitas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarIngrediente();
                case 2 -> cadastrarReceita();
                case 3 -> mostrarIngredientes();
                case 4 -> mostrarReceitas();
                case 0 -> System.out.println("Encerrando programa...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarIngrediente() {
        System.out.print("Nome do ingrediente: ");
        String nome = scanner.nextLine();

        System.out.print("Valor total (R$): ");
        double valor = scanner.nextDouble();

        System.out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Escolha a unidade:");
        String unidade = escolherUnidade();

        Ingrediente ing = new Ingrediente(nome, valor, quantidade, unidade);

        System.out.println("\n--- CONFIRMAR CADASTRO DO INGREDIENTE ---");
        System.out.println(ing);
        System.out.println("1 - Confirmar | 2 - Cancelar");
        int confirmacao = scanner.nextInt();
        scanner.nextLine();

        if (confirmacao == 1) {
            ingredientes.add(ing);
            System.out.println("Ingrediente cadastrado!");
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    private static void cadastrarReceita() {
        System.out.print("Nome da receita: ");
        String nome = scanner.nextLine();

        Receita receita = new Receita(nome);
        List<Ingrediente> disponiveis = new ArrayList<>(ingredientes);

        int escolha;
        do {
            System.out.println("\nAdicionar ingrediente na receita?");
            System.out.println("1 - Usar ingrediente existente");
            System.out.println("2 - Cadastrar novo ingrediente");
            System.out.println("0 - Continuar sem adicionar");
            escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                if (disponiveis.isEmpty()) {
                    System.out.println("Nenhum ingrediente disponível!");
                } else {
                    for (int i = 0; i < disponiveis.size(); i++) {
                        System.out.println((i + 1) + " - " + disponiveis.get(i));
                    }
                    System.out.print("Escolha ingrediente: ");
                    int idx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idx >= 0 && idx < disponiveis.size()) {
                        Ingrediente ing = disponiveis.get(idx);

                        System.out.print("Quantidade usada: ");
                        double qtdUsada = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Unidade usada:");
                        String unidadeUsada = escolherUnidade();

                        receita.adicionarIngrediente(ing, qtdUsada, unidadeUsada);
                        disponiveis.remove(idx);
                    } else {
                        System.out.println("Índice inválido!");
                    }
                }
            } else if (escolha == 2) {
                cadastrarIngrediente();
                disponiveis = new ArrayList<>(ingredientes);
            }
        } while (escolha != 0);

        System.out.print("Descrição da receita: ");
        String desc = scanner.nextLine();
        receita.setDescricao(desc);

        System.out.print("Rendimento (UN): ");
        double unidades = scanner.nextDouble();
        scanner.nextLine();
        receita.setUnidadeReceita(unidades);

        System.out.print("Custos Indiretos (Mao de obra, eletricidade, gas, etc.)(R$): ");
        double custo = scanner.nextDouble();
        scanner.nextLine();
        receita.setCustoFabricacao(custo);

        System.out.print("Porcentagem de Lucro desejada (%): ");
        double lucro = scanner.nextDouble();
        scanner.nextLine();
        receita.setporcentagemLucro(lucro);

        System.out.println("\n--- CONFIRMAR CADASTRO DA RECEITA ---");
        System.out.println(receita);
        System.out.println("1 - Confirmar | 2 - Cancelar");
        int confirmacao = scanner.nextInt();
        scanner.nextLine();

        if (confirmacao == 1) {
            receitas.add(receita);
            System.out.println("Receita cadastrada!");
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    private static void mostrarIngredientes() {
        if (ingredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente cadastrado!");
            return;
        }
        System.out.println("\n--- LISTA DE INGREDIENTES ---");
        for (int i = 0; i < ingredientes.size(); i++) {
            System.out.println((i + 1) + " - " + ingredientes.get(i));
        }
        System.out.println("\nAperte X para voltar ao menu inicial");
        scanner.nextLine();
    }

    private static void mostrarReceitas() {
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada!");
            return;
        }
        System.out.println("\n--- LISTA DE RECEITAS ---");
        for (int i = 0; i < receitas.size(); i++) {
            System.out.println((i + 1) + " - " + receitas.get(i).getNome());
        }
        System.out.println("X - Voltar ao menu");
        String entrada = scanner.nextLine();

        if (entrada.equalsIgnoreCase("X")) {
            return;
        }

        try {
            int idx = Integer.parseInt(entrada) - 1;
            if (idx >= 0 && idx < receitas.size()) {
                System.out.println("\n--- DETALHES DA RECEITA ---");
                System.out.println(receitas.get(idx));
                System.out.println("\nAperte X para voltar ao menu inicial");
                scanner.nextLine();
            } else {
                System.out.println("Índice inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private static String escolherUnidade() {
        String[] unidades = {
                "UN", "kg", "hg", "dag", "g", "dg", "cg", "mg",
                "kl", "hl", "dal", "l", "dl", "cl", "ml"
        };

        for (int i = 0; i < unidades.length; i++) {
            System.out.println((i + 1) + " - " + unidades[i]);
        }
        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha >= 1 && escolha <= unidades.length) {
            return unidades[escolha - 1];
        }
        return "UN";
    } }
