package src.domain.servicos;


import src.domain.entities.Cliente;
import src.domain.Interfaces.IClienteRepositorio;
import src.domain.tipos.CPF;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final IClienteRepositorio Repo;
    public ClienteService(IClienteRepositorio repo) {
        Repo = repo;
    }
    public void CadastrarCliente(String nome, CPF cpf)
    {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (cpf == null) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        if (Repo.ExisteCliente(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        Repo.Cadastrar(new Cliente(nome, cpf));

    }
    public List<Cliente> ListarClientes()
    {
        return Repo.ListarClientes();
    }
    public Optional<Cliente> BuscarCliente(CPF cpf) {
        if (cpf == null) throw new IllegalArgumentException("CPF obrigatório.");
        return Repo.BuscarCliente(cpf);
    }
}
