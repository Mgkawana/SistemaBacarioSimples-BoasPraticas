package PackageClasses;

import java.util.Objects;

public class Clientes {
    private String cpf;
    private String nome;

    public Clientes(String cpf, String nome)
    {
        SetCpf(cpf);

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
        Objects.requireNonNull(cpf, "CPF obrigatório");

        if (cpf.isBlank())
        {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        
        String cpfNormalizado = normalizarCpf(cpf);
        if (!validarFormatoCpf(cpf))
        {
            throw new IllegalArgumentException("Formatação do CPF inválida");
        }

        this.cpf = cpfNormalizado;
    }

    private String normalizarCpf(String cpf)
    {
        return cpf.replaceAll("//D", "");
    }

    private boolean validarFormatoCpf(String cpf)
    {
        return cpf.length() == 11 && cpf.chars().allMatch(Character::isDigit);
    }
}
