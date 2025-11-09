# Sistema Bancário Simples

### Integrantes

1. joão Pereira
2. Luan Soares
3. Marcos kawana

### Como Compilar e executar

Compile:
`javac presentation/Main.java`


Apos compilar, execute:
`java presentation.Main`

## Explicação do projeto
O projeto implementa um sistema bancário simples usando Java e boas práticas de programação orientada a objetos. Ele permite cadastrar clientes; criar contas bancárias; consultar contas; realizar operações como saque,depósito e transferência.

### Boas práticas de programação
O projeto possui arquitetura em camadas(UI → Service → Repository → Domain). Em relação ao SOLID são aplicados os conceitos do S(single responsability) pois cada classe tem apenas uma responsabilidade; I(Interface Segregation) Repositórios têm interfaces próprias;D(DIP — Dependency Inversion Principle) O Menu não depende de classes concretas. Além dessas, outras questões que o tornam um código limpo envolvem: Herança para evitar código duplicado entre conta corrente e poupança; Encapsulamento forte; Uso de DTO e ValueObjects e Repositório separado do domínio, o que facilita modificações futuras. 

### Estrutura do projeto
* **`Entities`**: São as classes como cliente e conta(corrente e poupança). Armazenam nome, CPF e contas desse cliente.
* **`Value objects`**: Possui CPF e NumeroConta com  suas próprias regras de validação.
* **`Interfaces`**: IClienteRepositorio e IContaRepositorio.
* **`Infra`**:ClienteRepImpl e ContaRepImpl, Implementam as interfaces e armazenam informações (lista em memória).
*  **`Serviços`**: Contêm a lógica de criação,depósito, saque, tranferência, validação e emissão de relatório.
*  **`DTO`**:RelatorioBancoDTO, usado para retornar dados agregados da camada de serviço sem expor entidades internas.
*  **`UI`**: Menu.java, recebe entradas do usuário, exibe opções e chama os serviços.
*  **`Presentation`**: Main.java, apenas inicializa as camadas e chama o menu.

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
12) Relatório consolidado (quantidades e saldos)
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
