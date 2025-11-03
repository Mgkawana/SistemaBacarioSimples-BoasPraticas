package PackageClasses;

public class Conta {

    private float saldo;
    private String numeroConta;
    private Clientes cliente;
    private TipoConta tipoConta;

    public Conta(String numeroConta, Clientes cliente, float saldo, TipoConta tipoConta)
    {

        this.saldo = saldo;
        this.cliente = cliente;
        this.tipoConta = tipoConta;

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
        if (valor >= 0)
        {
            throw new IllegalArgumentException("Valor inválido! O valor deve ser maior que 0");
        }

        saldo -= valor;
    }

    public void Sacar(float valor)
    {
        if (valor >= 0)
        {
            throw new IllegalArgumentException("Valor inválido! O valor deve ser maior do que 0.");
        }  
        else if(saldo > valor)
        {
            throw new IllegalArgumentException("Negado! Saldo Insuficiente.");
        }
    }

}
