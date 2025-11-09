package src.domain.Interfaces;
import java.util.Optional;

import src.domain.entities.Conta;
import src.domain.tipos.NumeroConta;

import java.util.List;

public interface IContaRepositorio {



     List<Conta> ListarContas();
     Optional<Conta> BuscarConta(NumeroConta numero);
     void SalvarConta(Conta conta);
     boolean existeNumero(NumeroConta numero);

}
