# Sistema Bancário Simples

### Integrantes

1. joão
2. Luan Soares
3. Marcos kawana

### Como Compilar e executar

Compile todas as classes:
`javac Main.java PackageClasses/*.java`
Apos compilar:
`Java main`

## Explicação do projeto
O projeto implementa um sistema bancário simples usando Java e boas práticas de programação orientada a objetos. Ele permite cadastrar clientes; criar contas bancárias; consultar contas; realizar operações como saque,depósito e transferência;

### Boas práticas de programação
O código é dividido em classes pequenas e organizadas, a qual cada uma tem uma responsabilidade, seguindo princípios do SRP (Single Responsibility Principle).

### Estrutura do projeto
* **`Clientes.java`**: Armazena nome, CPF e contas desse cliente.Serve como modelo (entidade) para cadastrar clientes no sistema. 
* **`Conta.java`**: Possui o número da conta, saldo, tipo de conta, cliente.Pode registrar transações no extrato. 
* **`GerenciadorDoSistema.java`**: Possui listas de clientes, listas de contas e as operações: cadastrar cliente, criar conta, buscar conta pelo número, realizar depósitos, saques e transferências chamando métodos de conta.
* **`TipoConta.java`**: É uma enumeração (enum) que define os tipos de conta possíveis para padronizá-las no sistema.
* **`Main.java`**: Exibe o menu interativo no terminal.Pede entrada do usuário.Chama métodos do GerenciadorDoSistema. Controla o loop do sistema até o usuário escolher sair.


## Instruções de execução

### Menu principal/operações

```bash
=== Menu ===
1) Cadastrar cliente
2) Cadastrar conta
3) Buscar Cliente por CPF
4) Buscar Clonta por número
5) Depositar
6) Sacar
7) Transferir
8) Consultar saldo
9) Listar Contas
10) Listar Clientes
11) Aplicar rendimento (apenas para contas poupança)
0) Sair
Opção:
```
### Exemplo de uso. Entrada/saída

```bash
Opção: 1
CPF: 33333333333
Nome: gustave
Cliente cadastrado com sucesso.
=== Cliente ===
Nome: gustave
CPF:  33333333333

Opção: 10
=== Clientes cadastrados ===
gustave (33333333333)

Opção: 2
Número da conta: 5456780
Informe o titular da conta.
CPF: 33333333333
Saldo inicial: 700
Tipo 1)CORRENTE  2)POUPANCA): 2
Conta criada com sucesso.
=== Conta ===
Número: 5456780
Tipo:   POUPANCA
Saldo:  700,00
Titular: gustave (33333333333)

Opção: 6
Número da conta: 5456780
Valor do saque: 500
Saque realizado.

Opção: 8
Número da conta: 5456780
Saldo da conta 5456780: 200,00
```
