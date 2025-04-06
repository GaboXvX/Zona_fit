package gb.zona_fit.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import gb.zona_fit.modelo.Cliente;
import gb.zona_fit.servicio.ClienteServicio;
import gb.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloClientes;
    private Integer id_cliente;
    @Autowired
public ZonaFitForma(ClienteServicio clienteServicio){
this.clienteServicio=clienteServicio;
iniciarForma();
        guardarButton.addActionListener(e -> guardarCliente());


        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarCliente();
            }
        });
        eliminarButton.addActionListener(e ->eliminarCliente()
        );
        limpiarButton.addActionListener(e -> limpiarFormulario()

        );
    }

public void iniciarForma() {
       setContentPane(panelPrincipal);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(900,700);
       setLocationRelativeTo(null);

}

public void guardarCliente(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        } else if (apellidoTexto.getText().equals("")) {
            mostrarMensaje("proporciona un apellido");
            apellidoTexto.requestFocusInWindow();
            return;

        } else if (membresiaTexto.getText().equals("")) {
            mostrarMensaje("proporciona una membresía");
            membresiaTexto.requestFocusInWindow();
            return;


        }
        var nombre=nombreTexto.getText();
        var apellido=apellidoTexto.getText();
        var membresia= Integer.parseInt(membresiaTexto.getText());
        var cliente=new Cliente(this.id_cliente,nombre,apellido,membresia);
        this.clienteServicio.guardarCliente(cliente);
        if(this.id_cliente==null){
            mostrarMensaje("se agrego un nuevo cliente");
        }else{
            mostrarMensaje("sea actualizo un cliente");
        }
        limpiarFormulario();
        listarClientes();
}
private void eliminarCliente(){
    if(nombreTexto.getText().equals("")){
        mostrarMensaje("proporciona un nombre");
        nombreTexto.requestFocusInWindow();
        return;
    } else if (apellidoTexto.getText().equals("")) {
        mostrarMensaje("proporciona un apellido");
        apellidoTexto.requestFocusInWindow();
        return;

    } else if (membresiaTexto.getText().equals("")) {
        mostrarMensaje("proporciona una membresía");
        membresiaTexto.requestFocusInWindow();
        return;


    }
    var nombre=nombreTexto.getText();
    var apellido=apellidoTexto.getText();
    var membresia= Integer.parseInt(membresiaTexto.getText());
    var cliente=new Cliente(this.id_cliente,nombre,apellido,membresia);
    this.clienteServicio.eliminarCliente(cliente);
    mostrarMensaje("se ha eliminado el cliente selecionado");
    limpiarFormulario();
    listarClientes();
}
private void limpiarFormulario(){
        nombreTexto.setText("");
    apellidoTexto.setText("");
    membresiaTexto.setText("");
    this.id_cliente=null;
    this.clientesTabla.getSelectionModel().clearSelection();
}
private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
}
    private void createUIComponents() {
this.tablaModeloClientes= new DefaultTableModel(0,4){
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
String [] cabeceros={"id","nombre","apellido","membresia"};
this.tablaModeloClientes.setColumnIdentifiers(cabeceros);
this.clientesTabla=new JTable(tablaModeloClientes);
this.clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
listarClientes();
    }
    public void listarClientes(){
        this.tablaModeloClientes.setRowCount(0);
        var clientes=this.clienteServicio.listarClientes();
        clientes.forEach(cliente->{
            Object[] renglonClientes={
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getMembresia()
            };
            this.tablaModeloClientes.addRow(renglonClientes);

        });
    }
    private void cargarCliente(){
        var renglon=clientesTabla.getSelectedRow();
        if(renglon!=-1){
            var id=clientesTabla.getModel().getValueAt(renglon,0).toString();
            this.id_cliente=Integer.parseInt(id);
            var nombre=clientesTabla.getModel().getValueAt(renglon,1).toString();
            nombreTexto.setText(nombre);
            var apellido=clientesTabla.getModel().getValueAt(renglon,2).toString();
            apellidoTexto.setText(apellido);
            var membresia=clientesTabla.getModel().getValueAt(renglon,3).toString();
            membresiaTexto.setText(membresia);
        }
    }
}

