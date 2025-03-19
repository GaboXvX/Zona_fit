package gb.zona_fit;

import java.util.logging.Logger;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import gb.zona_fit.modelo.Cliente;
import gb.zona_fit.servicio.IClienteServicio;
import lombok.experimental.var;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;
    private static final Logger logger = Logger.getLogger(ZonaFitApplication.class.getName());
    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Iniciando la aplicacion");
        SpringApplication.run(ZonaFitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        zonafitApp();
    }

    private void zonafitApp() {
        logger.info("**********ZonaFitApp**********");
        var consola = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpcion(opcion, consola);
            logger.info(nl);
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("1. Listar Clientes");
        logger.info("2. Buscar Cliente");
        logger.info("3. Agregar Cliente");
        logger.info("4. Eliminar Cliente");
        logger.info("5. Salir");

        logger.info("Elija una opcion: ");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpcion(int opcion, Scanner consola) {
        boolean salir = false;
        switch (opcion) {
            case 1:
                logger.info("Listado de clientes:");
                try {
                    List<Cliente> clientes = clienteServicio.listarClientes();
                    clientes.forEach(cliente -> logger.info(cliente.toString()));
                } catch (Exception e) {
                    logger.severe("Error al listar los clientes: " + e.getMessage());
                }
                break;

            case 2:
                try {
                    logger.info(nl + "Ingrese el id del cliente a buscar:");
                    var id_cliente = consola.nextInt();
                    consola.nextLine(); // Consumir la nueva línea pendiente
                    Cliente cliente = clienteServicio.buscarCliente(id_cliente);
                    if (cliente != null) {
                        logger.info(cliente.toString());
                    } else {
                        logger.info("Cliente no encontrado.");
                    }
                } catch (Exception e) {
                    logger.severe("Error al buscar el cliente: " + e.getMessage());
                }
                break;

				case 3:
				try {
					logger.info(nl + "Ingrese los datos del cliente:");
			
					logger.info("Ingrese el nombre del cliente:");
					var nombre = consola.nextLine();
			
					logger.info("Ingrese el apellido del cliente:");
					var apellido = consola.nextLine();
			
					logger.info("Ingrese 1 si el cliente tiene membresía, sino ingrese 0:");
					var membresia = consola.nextInt();
					consola.nextLine(); 
			
					var cliente = new Cliente();
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
			
					clienteServicio.guardarCliente(cliente);
					logger.info("Cliente guardado correctamente");
				} catch (Exception e) {
					logger.severe("Error al agregar el cliente: " + e.getMessage());
				}
				break;
			

            case 4:
                try {
                    logger.info(nl + "Ingrese el id del cliente a eliminar:");
                    var cliente = new Cliente();
                    cliente.setId_cliente(consola.nextInt());
                    consola.nextLine(); // Consumir la nueva línea pendiente
                    clienteServicio.eliminarCliente(cliente);
                    logger.info("Cliente eliminado correctamente");
                } catch (Exception e) {
                    logger.severe("Error al eliminar el cliente: " + e.getMessage());
                }
                break;

            case 5:
                logger.info("Saliendo de la aplicación");
                salir = true;
                break;

            default:
                logger.warning("Opción no válida.");
                break;
        }
        return salir;
    }
}
