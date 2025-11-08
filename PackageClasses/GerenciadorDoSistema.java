package PackageClasses;

public class GerenciadorDoSistema {


     public void Transferencia(Conta contaReceber, Conta contaDepositar, float valor)
     {
        contaDepositar.Sacar(valor);
        contaReceber.Depositar(valor);

        System.out.println("Tranferência realizada da " + contaDepositar.getNumeroConta() + " para a conta " + contaReceber.getNumeroConta());
     }

     public void ConsultarSaldo(Conta conta)
     {
         System.out.println("Sua conta de número tem o saldo de: " + conta.getSaldo());
     }

     public void AplicarRendimento(Conta conta)
     {
        if (conta.getTipoConta() == TipoConta.POUPANCA)
        {

        }
     }
}
