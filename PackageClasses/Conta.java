package PackageClasses;

import java.util.Objects;

public class Conta {

    private float saldo;
    private String numeroConta;
    private Clientes cliente;
    private TipoConta tipoConta;

    public Conta(String numeroConta, Clientes cliente, float saldo, TipoConta tipoConta)
    {
        if (numeroConta == null || numeroConta.isBlank())
        {
            throw new IllegalArgumentException("Número da conta obrigatório.");
        }
        this.numeroConta = numeroConta;

        validarValorPositivo(saldo);
        this.saldo = saldo;

        this.cliente = Objects.requireNonNull(cliente, "Cliente obrigatório");

        this.tipoConta = Objects.requireNonNull(tipoConta, "Tipo da conta obrigatório");
    }

    //-------------------------- GET -------------------------- //
    public float getSaldo()
    {
        return saldo;
    }

    public String getNumeroConta()
    {
        return numeroConta;
    }

    public Clientes getCliente()
    {
        return cliente;
    }

     public TipoConta getTipoConta()
    {
        return tipoConta;
    }
    //--------------------------------------------------------- //

    public void setSaldo(float saldo)
    {
        this.saldo = saldo;
    }

    public void Depositar(float valor)
    {
        validarValorPositivo(valor);

        saldo += valor;
    }

    public void Sacar(float valor)
    {
        validarValorPositivo(valor);

        if(saldo > valor)
        {
            throw new IllegalArgumentException("Negado! Saldo Insuficiente.");
        }

        saldo -= valor;
    }

    private void validarValorPositivo(float valor)
    {
        if (valor >= 0)
        {
            throw new IllegalArgumentException("Valor inválido! O valor deve ser maior do que 0.");
        } 
    }

}
