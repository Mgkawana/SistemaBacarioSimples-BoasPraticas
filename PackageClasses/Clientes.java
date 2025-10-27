package PackageClasses;

import java.util.Objects;

public class Clientes {
    private String cpf;
    private String nome;

    public String getCPF()
    {
        return cpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void SetNome(String novoNome)
    {
        Objects.requireNonNull(nome, "Nome obrigatório");
        
        if (nome.isBlank())
        {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        nome = novoNome;
    }
}
