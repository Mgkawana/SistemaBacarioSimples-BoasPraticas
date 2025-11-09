package src.domain.entities;

import src.domain.tipos.NumeroConta;

public class Corrente extends Conta {
    public Corrente(Cliente cliente, NumeroConta numero) {
        super(cliente, numero);
    }

    public Corrente(Cliente cliente, NumeroConta numero, float saldo) {
        super(cliente, numero, saldo);
    }
}
