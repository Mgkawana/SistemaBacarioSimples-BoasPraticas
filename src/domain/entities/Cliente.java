package src.domain.entities;

import src.domain.tipos.CPF;

public class Cliente {
    private final String Nome;
    private final CPF Cpf;
    public Cliente(String nome, CPF cpf) {
        Nome = nome;
        Cpf = cpf;
    }
    public String getNome() {
        return Nome;
    }

    public CPF getCpf() {
        return Cpf;
    }
    @Override
    public String toString() {
        return "Nome - "+ Nome;
    }
}
