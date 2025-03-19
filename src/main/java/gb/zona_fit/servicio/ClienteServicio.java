package gb.zona_fit.servicio;

import gb.zona_fit.modelo.Cliente;
import gb.zona_fit.repositorio.ClienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }

    @Override
    public Cliente buscarCliente(Integer id_cliente) {
 Cliente cliente=clienteRepositorio.findById(id_cliente).orElse(null);
 return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        List <Cliente> clientes=clienteRepositorio.findAll();
        return clientes;
    }

    
  
}
