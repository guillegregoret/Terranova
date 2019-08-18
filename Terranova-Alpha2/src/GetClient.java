import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JMenuBar;
import java.awt.TextField;
import java.awt.Dialog.ModalExclusionType;

public class GetClient extends JDialog{

	public JFrame frmAadirCliente;
	private JTextField nombre;
	private JTextField apellido;
	private JLabel lblDni;
	private JTextField dni;
	private JButton btnCancelar;
	private JTextField nroafiliado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetClient window = new GetClient();
					window.frmAadirCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try{
					  
					  JFrame.setDefaultLookAndFeelDecorated(true);
					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					}
					catch (Exception e)
					 {
					  e.printStackTrace();
					 }
			}
		});
	}
	


	/**
	 * Create the application.
	 */
	public GetClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAadirCliente = new JFrame();
		frmAadirCliente.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmAadirCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		frmAadirCliente.setTitle("Nuevo Paciente - Terranova .:v0.3:.");
		frmAadirCliente.setBounds(100, 100, 475, 215);
		frmAadirCliente.getContentPane().setLayout(null);
		

		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 138, 20);
		frmAadirCliente.getContentPane().add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(290, 11, 138, 20);
		frmAadirCliente.getContentPane().add(apellido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombre.setBounds(27, 14, 46, 14);
		frmAadirCliente.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblApellido.setBounds(234, 12, 46, 17);
		frmAadirCliente.getContentPane().add(lblApellido);
		
		lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDni.setBounds(27, 45, 34, 14);
		frmAadirCliente.getContentPane().add(lblDni);
		
		dni = new JTextField();
		dni.setColumns(10);
		dni.setBounds(83, 42, 138, 20);
		frmAadirCliente.getContentPane().add(dni);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(240, 138, 89, 23);
		frmAadirCliente.getContentPane().add(btnCancelar);
		
		JComboBox listobrassociales = new JComboBox();

		listobrassociales.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!(listobrassociales.getSelectedItem() == "No Aplica")){
					nroafiliado.setEnabled(true);
				}else {
					nroafiliado.setEnabled(false);
				}
			}
		});
		listobrassociales.setModel(new DefaultComboBoxModel(new String[] {"No Aplica", "ACA Salud", "Jer\u00E1rquicos", "OSDE", "PAMI"}));
		listobrassociales.setBounds(27, 73, 165, 22);
		frmAadirCliente.getContentPane().add(listobrassociales);
		
		nroafiliado = new JTextField();
		nroafiliado.setBounds(310, 74, 118, 20);
		frmAadirCliente.getContentPane().add(nroafiliado);
		nroafiliado.setColumns(10);
		nroafiliado.setEnabled(false);
		
		JLabel lblNmeroAfiliado = new JLabel("N\u00FAmero Afiliado");
		lblNmeroAfiliado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNmeroAfiliado.setBounds(211, 75, 89, 17);
		frmAadirCliente.getContentPane().add(lblNmeroAfiliado);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				ConnectDatabase db = new ConnectDatabase();
				db.Query("INSERT INTO paciente(DNI, NOMBRE, APELLIDO, OBRASOCIAL, NROAFILIADO) VALUES ("
						+ "'"+dni.getText()
						+"','"+nombre.getText()
						+"','"+apellido.getText()
						+"','"+listobrassociales.getSelectedItem()
						+"','"+nroafiliado.getText()+"');");

	
				
			}
		});
		btnGuardar.setBounds(339, 138, 89, 23);
		frmAadirCliente.getContentPane().add(btnGuardar);
		
		JButton btnAgregarOrden = new JButton("Agregar Orden");
		btnAgregarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AñadirOrden añadirorden = new AñadirOrden();
				añadirorden.setdni(dni.getText());
				añadirorden.frame.setVisible(true);
				 

				
			}
		});
		btnAgregarOrden.setBounds(27, 106, 118, 23);
		frmAadirCliente.getContentPane().add(btnAgregarOrden);
		
	}


}
