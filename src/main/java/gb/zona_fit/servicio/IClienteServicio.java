package gb.zona_fit.servicio;

import java.util.List;

import gb.zona_fit.modelo.Cliente;

public interface IClienteServicio {
public List<Cliente> listarClientes();
  public  Cliente buscarCliente(Integer id_cliente);
public void guardarCliente(Cliente cliente);
public void eliminarCliente(Cliente cliente);
} 