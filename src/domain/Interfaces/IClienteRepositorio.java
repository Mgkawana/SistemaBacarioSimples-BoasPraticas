package src.domain.Interfaces;

import src.domain.entities.Cliente;
import src.domain.tipos.CPF;

import java.util.List;
import java.util.Optional;

public interface IClienteRepositorio {
     List<Cliente> ListarClientes();
     Optional<Cliente> BuscarCliente(CPF cpf);
     void Cadastrar(Cliente c);
     boolean ExisteCliente(CPF cpf);
}
