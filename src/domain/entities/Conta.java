package src.domain.entities;

import src.domain.tipos.NumeroConta;

public abstract class Conta  {
    protected float Saldo;
    private final NumeroConta Numero;
    private final  Cliente ClienteAss;//cliente associado
    public Conta(Cliente cliente, NumeroConta numero) {
        ClienteAss = cliente;
        Numero = numero;
    }
    // vamo perguntar se ao criar conta, fazer um deposito, se sim usa esse construtor se não valor padrão de saldo = 0 ->>>
    public Conta(Cliente cliente, NumeroConta numero, float saldo) {
        if (saldo < 0f) throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        ClienteAss = cliente;
        Numero = numero;
        Saldo = saldo;
    }
    public void Depositar(float valor) {
        if (valor <= 0f) throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        Saldo += valor;
    }

    public void Saque(float valor) {
        if (valor <= 0f) throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        if (valor > Saldo) throw new IllegalArgumentException("Saldo insuficiente.");
        Saldo -= valor;
    }

    @Override
    public String toString() {
        return (ClienteAss+" - Numero: "+ Numero);
    }
    public float getSaldo() {
        return Saldo;
    }
    public NumeroConta getNumero() {
        return Numero;
    }





}
