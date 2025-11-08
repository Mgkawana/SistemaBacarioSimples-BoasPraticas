package PackageClasses;

import java.util.Objects;

public class Clientes {
    private String cpf;
    private String nome;

    public Clientes(String cpf, String nome)
    {
        this.cpf = cpf;

        SetNome(nome);
    }

    public String getCPF()
    {
        return cpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void SetNome(String nome)
    {
        Objects.requireNonNull(nome, "Nome obrigatório");
        
        if (nome.isBlank())
        {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        this.nome = nome;
    }

    public void SetCpf(String cpf)
    {
        Objects.requireNonNull(nome, "CPF obrigatório");

        if (nome.isBlank())
        {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        this.cpf = cpf;
    }
}
