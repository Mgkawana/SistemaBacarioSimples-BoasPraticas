import PackageClasses.*;
import java.util.*;


public class Main {
    
    private static final String CABECALHO_CONTAS = "=== Contas cadastradas ===";   // Clean Code: intenção clara
    private static final String CABECALHO_CLIENTES = "=== Clientes cadastrados ===";

    private static final Scanner sc = new Scanner(System.in);

    // “Banco” em memória
    private static final Map<String, Clientes> clientes = new HashMap<>();
    private static final Map<String, Conta> contas = new HashMap<>();

    public static void main(String[] args) {
        while (true) {

            System.out.print("\nPressione Enter para abrir o menu...");
            sc.nextLine();

            System.out.println("\n=== Menu ===");

            System.out.println("1) Cadastrar cliente");
            System.out.println("2) Cadastrar conta");

            System.out.println("3) Buscar Cliente por CPF");
            System.out.println("4) Buscar Clonta por número");

            System.out.println("5) Depositar");
            System.out.println("6) Sacar");
            System.out.println("7) Transferir");
            System.out.println("8) Consultar saldo");

            System.out.println("9) Listar Contas");
            System.out.println("10) Listar Clientes");
            
            System.out.println("11) Aplicar rendimento (apenas para contas poupança)");

            System.out.println("0) Sair");
            System.out.print("Opção: ");

            String op = sc.nextLine().trim();
            try {
                switch (op)
                {
                    case "1": cadastrarCliente(); break;                      
                    case "2": cadastrarConta(); break;
                    case "3": exibirCliente(buscarClientePorCPF(lervCpf())); break;
                    case "4": exibirConta(buscarContaPorNumero(lerNumeroConta())); break;
                    case "5": depositar(); break;
                    case "6": sacar(); break;
                    case "7": transferir(); break;
                    case "8": consultarSaldo(); break;
                    case "9": listarContas(); break;
                    case "10": listarClientes(); break;
                    case "11": aplicarRendimento(); break;
                    case "0": System.out.println("Saindo..."); return;
                    default: System.out.println("Opção inválida.");
                }
            
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Erro: " + e.getMessage());
            }
            catch (NoSuchElementException e)
            {
                System.out.println("Entrada encerrada.");
                return;
            }
        }
    }

    //----------------------------- Utilitárias ---------------------------------------

    private static String lervCpf()
    {
        System.out.print("CPF: ");
        String cpf = sc.nextLine().trim();
        if (cpf.isEmpty()) throw new IllegalArgumentException("CPF obrigatório.");
        return cpf;
    }

    private static String lerNome()
    {
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        return nome;
    }

    private static String lerNumeroConta()
    {
        System.out.print("Número da conta: ");
        String numero = sc.nextLine().trim();
        if (numero.isEmpty()) throw new IllegalArgumentException("Número da conta é obrigatório.");
        return numero;
    }

    private static float lerValorPositivo(String rotulo)
    {
        System.out.print(rotulo);
        float valor = Float.parseFloat(sc.nextLine().trim());
        if (valor <= 0f) throw new IllegalArgumentException("Valor deve ser maior que zero.");
        return valor;
    }

    //----------------------------- Funções de Cliente ---------------------------------------

    private static void cadastrarCliente()
    {
        String cpf = lervCpf();
        if (clientes.containsKey(cpf)) {
            throw new IllegalArgumentException("Já existe cliente com esse CPF.");
        }
        String nome = lerNome();

        Clientes novoCliente = new Clientes(cpf, nome);
        clientes.put(cpf, novoCliente)
        ;
        System.out.println("Cliente cadastrado com sucesso.");
        exibirCliente(novoCliente);
    }

    private static Clientes buscarClientePorCPF(String cpf)
    {
        Clientes clienteProcurada = clientes.get(cpf);
        if (clienteProcurada == null) throw new IllegalArgumentException("Cliente não encontrado para o CPF: " + cpf);
        return clienteProcurada;
    }

    //----------------------------- Funções de Cadastro de Conta ---------------------------------------

    private static void cadastrarConta()
    {
        String numero = lerNumeroConta();
        if (contas.containsKey(numero)) {
            throw new IllegalArgumentException("Já existe conta com esse número.");
        }

        System.out.println("Informe o titular da conta.");
        Clientes titular = buscarClientePorCPF(lervCpf());

        float saldoInicial = lerValorPositivo("Saldo inicial: ");

        System.out.print("Tipo 1)CORRENTE  2)POUPANCA): ");
        String tipoStr = sc.nextLine().trim();
        TipoConta tipo = "2".equals(tipoStr) ? TipoConta.POUPANCA : TipoConta.CORRENTE;

        Conta novaConta = new Conta(numero, titular, saldoInicial, tipo);
        contas.put(numero, novaConta);
        System.out.println("Conta criada com sucesso.");
        exibirConta(novaConta);
    }

    private static Conta buscarContaPorNumero(String numero) {
        Conta contaProcurada = contas.get(numero);
        if (contaProcurada == null) throw new IllegalArgumentException("Conta não encontrada: " + numero);
        return contaProcurada;
    }

    //----------------------------- Operações Financeiras ---------------------------------------

    private static void depositar() {
        Conta conta = buscarContaPorNumero(lerNumeroConta());
        float valor = lerValorPositivo("Valor do depósito: ");
        conta.Depositar(valor);
        System.out.println("Depósito realizado.");
    }

    private static void sacar() {
        Conta conta = buscarContaPorNumero(lerNumeroConta());
        float valor = lerValorPositivo("Valor do saque: ");
        conta.Sacar(valor);
        System.out.println("Saque realizado.");
    }

    private static void transferir() {
        System.out.println("Conta ORIGEM:");
        Conta origem = buscarContaPorNumero(lerNumeroConta());
        System.out.println("Conta DESTINO:");
        Conta destino = buscarContaPorNumero(lerNumeroConta());

        float valor = lerValorPositivo("Valor da transferência: ");
        origem.Sacar(valor);
        destino.Depositar(valor);
        System.out.println("Transferência concluída.");
    }

    //----------------------------- Consulta e Listagem ---------------------------------------

    private static void consultarSaldo() {
        Conta conta = buscarContaPorNumero(lerNumeroConta());
        System.out.printf(Locale.forLanguageTag("pt-BR"),
                "Saldo da conta %s: %.2f%n", conta.getNumeroConta(), conta.getSaldo());
    }

    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;                                              // Early return: fluxo simples
        }
        System.out.println(CABECALHO_CONTAS);
        contas.values().stream()
                .sorted(Comparator.comparing(Conta::getNumeroConta))
                .map(Main::formatarLinhaConta)                   // Separação de interesses: dado vs apresentação
                .forEach(System.out::println);
    }

    private static String formatarLinhaConta(Conta conta) {
        String saldo = String.format(Locale.forLanguageTag("pt-BR"), "%.2f", conta.getSaldo());
        Clientes t = conta.getCliente();
        return String.format("Conta: %s | Titular: %s (%s) | Tipo: %s | Saldo: %s",
                conta.getNumeroConta(), t.getNome(), t.getCPF(), conta.getTipoConta().name(), saldo);
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println(CABECALHO_CLIENTES);
        clientes.values().stream()
                .sorted(Comparator.comparing(Clientes::getNome))
                .map(cliente -> String.format("%s (%s)", cliente.getNome(), cliente.getCPF()))
                .forEach(System.out::println);
    }

    //----------------------------- Rendimento ---------------------------------------

    private static void aplicarRendimento() {
        Conta conta = buscarContaPorNumero(lerNumeroConta());
        if (conta.getTipoConta() != TipoConta.POUPANCA) {
            throw new IllegalArgumentException("Apenas contas POUPANÇA recebem rendimento.");
        }
        float percentual = lerValorPositivo("Percentual de rendimento (%): ");
        float acrescimo = conta.getSaldo() * (percentual / 100f);
        conta.Depositar(acrescimo);
        System.out.println("Rendimento aplicado.");
    }   

    //----------------------------- Exibições Pontual ---------------------------------------
    private static void exibirCliente(Clientes c) {
        System.out.println("=== Cliente ===");
        System.out.println("Nome: " + c.getNome());
        System.out.println("CPF:  " + c.getCPF());
    }

    private static void exibirConta(Conta conta) {
        System.out.println("=== Conta ===");
        System.out.println("Número: " + conta.getNumeroConta());
        System.out.println("Tipo:   " + conta.getTipoConta().name());
        System.out.printf(Locale.forLanguageTag("pt-BR"), "Saldo:  %.2f%n", conta.getSaldo());
        Clientes t = conta.getCliente();
        System.out.println("Titular: " + t.getNome() + " (" + t.getCPF() + ")");
    }



}
