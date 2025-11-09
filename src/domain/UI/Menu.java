package src.domain.UI;

import src.domain.DTO.RelatorioBancoDTO;
import src.domain.entities.Cliente;
import src.domain.entities.Conta;
import src.domain.servicos.ClienteService;
import src.domain.servicos.ContaService;
import src.domain.tipos.CPF;
import src.domain.tipos.NumeroConta;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private final ContaService contaSrv;
    private final ClienteService clienteSrv;

    public Menu(ContaService contaSrv, ClienteService clienteSrv) {
        this.contaSrv = contaSrv;
        this.clienteSrv = clienteSrv;
    }

    public void Executar() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("""
                === Menu ===
                1) Cadastrar cliente
                2) Cadastrar conta
                3) Buscar Cliente por CPF
                4) Buscar Conta por número
                5) Depositar
                6) Sacar
                7) Transferir
                8) Consultar saldo
                9) Listar Contas
                10) Listar Clientes
                11) Aplicar rendimento (apenas para contas poupança)
                12) Relatório consolidado (quantidades e saldos)
                0) Sair
            """);

            op = lerInt(sc, "Opção: ");
            try {
                switch (op) {
                    case 1 -> cadastrarCliente(sc);
                    case 2 -> cadastrarConta(sc);
                    case 3 -> buscarCliente(sc);
                    case 4 -> buscarConta(sc);
                    case 5 -> depositar(sc);
                    case 6 -> sacar(sc);
                    case 7 -> transferir(sc);
                    case 8 -> consultarSaldo(sc);
                    case 9 -> listarContas();
                    case 10 -> listarClientes();
                    case 11 -> aplicarRendimento(sc);
                    case 12 -> relatorioConsolidado();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

            if (op != 0) {
                // cada tela em que é leitura é necessário apertar enter para prosseguir
                if (Set.of(3, 4, 8, 9, 10, 12).contains(op)) {
                    esperarEnter(sc);
                } else {
                    dormirELimpar(2000);
                }
            }
        } while (op != 0);
    }



    private void cadastrarCliente(Scanner sc) {
        String nome   = lerLinha(sc, "Nome: ");
        String cpfStr = lerLinha(sc, "CPF: ");
        clienteSrv.CadastrarCliente(nome, CPF.of(cpfStr));
        System.out.println("Cliente cadastrado.");
    }

    private void cadastrarConta(Scanner sc) {
        String cpfStr = lerLinha(sc, "CPF do cliente: ");
        String tipo   = lerLinha(sc, "Tipo (CORRENTE/POUPANCA): ");
        String numStr = lerLinha(sc, "Número da conta (XXXXX-X ou XXXXXX): ");

        float saldoInicial = 0f;
        while (true) {
            String sn = lerLinha(sc, "Deseja fazer depósito inicial? (S/N): ").strip();
            if (sn.equalsIgnoreCase("S")) {
                saldoInicial = lerFloat(sc, "Valor do depósito inicial: ");
                break;
            } else if (sn.equalsIgnoreCase("N")) {
                break; // fica 0f
            } else {
                System.out.println("Opção inválida. Digite S ou N.");
            }
        }

        contaSrv.CriarConta(CPF.of(cpfStr), tipo, NumeroConta.of(numStr), saldoInicial);
        System.out.println("Conta criada.");
    }

    private void buscarCliente(Scanner sc) {
        String cpfStr = lerLinha(sc, "CPF: ");
        Optional<Cliente> cli = clienteSrv.BuscarCliente(CPF.of(cpfStr));
        System.out.println(cli.map(Object::toString).orElse("Cliente não encontrado."));
    }

    private void buscarConta(Scanner sc) {
        String numStr = lerLinha(sc, "Número da conta: ");
        Optional<Conta> conta = contaSrv.BuscarConta(NumeroConta.of(numStr));
        System.out.println(conta.map(Object::toString).orElse("Conta não encontrada."));
    }

    private void depositar(Scanner sc) {
        String numStr = lerLinha(sc, "Número da conta: ");
        float valor   = lerFloat(sc, "Valor do depósito: ");
        contaSrv.Depositar(NumeroConta.of(numStr), valor);
        System.out.println("Depósito realizado.");
    }

    private void sacar(Scanner sc) {
        String numStr = lerLinha(sc, "Número da conta: ");
        float valor   = lerFloat(sc, "Valor do saque: ");
        contaSrv.SacarConta(NumeroConta.of(numStr), valor);
        System.out.println("Saque realizado.");
    }

    private void transferir(Scanner sc) {
        String origem  = lerLinha(sc, "Conta origem: ");
        String destino = lerLinha(sc, "Conta destino: ");
        float valor    = lerFloat(sc, "Valor da transferência: ");
        contaSrv.Transferir(NumeroConta.of(origem), NumeroConta.of(destino), valor);
        System.out.println("Transferência realizada.");
    }

    private void consultarSaldo(Scanner sc) {
        String numStr = lerLinha(sc, "Número da conta: ");
        float saldo = contaSrv.ConsultarSaldo(NumeroConta.of(numStr));
        System.out.printf("Saldo: %.2f%n", saldo);
    }

    private void listarContas() {
        List<Conta> contas = contaSrv.Listar();
        contas.forEach(System.out::println);
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteSrv.ListarClientes();
        clientes.forEach(System.out::println);
    }

    private void aplicarRendimento(Scanner sc) {
        float pct = lerFloat(sc, "Percentual (%): ");
        contaSrv.AplicarRendimento(pct);
        System.out.println("Rendimento aplicado nas poupanças.");
    }

    private void relatorioConsolidado() {
        RelatorioBancoDTO dto = contaSrv.GerarRelatorioConsolidado();
        System.out.println("=== Relatório ===");
        dto.getPorTipo().forEach(t ->
                System.out.printf("Tipo: %s | Qtde: %d | Saldo total: %.2f%n",
                        t.getTipo(), t.getQuantidade(), t.getSaldoTotal())
        );
        System.out.printf("Total de contas: %d%n", dto.getTotalContas());
        System.out.printf("Saldo total do banco: %.2f%n", dto.getSaldoTotalBanco());
    }

    // auxiliadores

    private static int lerInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private static float lerFloat(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(sc.nextLine().trim());
    }

    private static String lerLinha(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static void esperarEnter(Scanner sc) {
        System.out.print("\nPressione ENTER para voltar ao menu...");
        sc.nextLine();
    }

    private static void dormirELimpar(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("\n".repeat(50));
    }
}
