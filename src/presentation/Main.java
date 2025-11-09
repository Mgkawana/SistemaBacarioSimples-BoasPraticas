package src.presentation;

import src.domain.infra.ClienteRepImpl;
import src.domain.infra.ContaRepImpl;
import src.domain.UI.Menu;
import src.domain.servicos.ClienteService;
import src.domain.servicos.ContaService;

public class Main {
    public static void main(String[] args) {

        var clienteRepo = new ClienteRepImpl();
        var contaRepo   = new ContaRepImpl();


        var clienteService = new ClienteService(clienteRepo);
        var contaService   = new ContaService(contaRepo, clienteRepo);


        var menu = new Menu(contaService, clienteService);
        menu.Executar();
    }
}
