import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class BuscarPaciente extends JDialog {

	public JFrame frmBuscarPaciente;
	private JTextField dni;
	private JTextField nombre;
	private JTextField apellido;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPaciente window = new BuscarPaciente();
					window.frmBuscarPaciente.setVisible(true);
					
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
	public BuscarPaciente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JTable getTablePacientes() {
		
		 try {
	    	    ConnectDatabase db = new ConnectDatabase();
				ResultSet rs = db.sqlstatment().executeQuery("SELECT * FROM PACIENTE WHERE NOMBRE LIKE '%"+nombre.getText()+"%' AND APELLIDO LIKE '%"+apellido.getText()+"%'AND DNI LIKE '%"+dni.getText()+"%'");
				Object[] transf = QueryToTable.getSingle().queryToTable(rs);
				return table = new JTable(new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]));		
			} catch(Exception e) {
				e.printStackTrace();
			}
			return table;
	}
	private void initialize() {
		frmBuscarPaciente = new JFrame();
		frmBuscarPaciente.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmBuscarPaciente.setResizable(false);
		//frmBuscarPaciente.setModalityType(ModalityType.APPLICATION_MODAL);
		frmBuscarPaciente.setAutoRequestFocus(false);
		frmBuscarPaciente.setTitle("Buscar Paciente - Terranova");
		frmBuscarPaciente.setBounds(100, 100, 652, 361);
		frmBuscarPaciente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBuscarPaciente.getContentPane().setLayout(null);
		
		dni = new JTextField();
		dni.setColumns(10);
		dni.setBounds(66, 42, 138, 20);
		frmBuscarPaciente.getContentPane().add(dni);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(66, 11, 138, 20);
		frmBuscarPaciente.getContentPane().add(nombre);
		
		JLabel label = new JLabel("Apellido");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(217, 12, 46, 17);
		frmBuscarPaciente.getContentPane().add(label);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(273, 11, 138, 20);
		frmBuscarPaciente.getContentPane().add(apellido);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(10, 14, 46, 14);
		frmBuscarPaciente.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("DNI");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_2.setBounds(10, 45, 34, 14);
		frmBuscarPaciente.getContentPane().add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 615, 206);
		frmBuscarPaciente.getContentPane().add(scrollPane);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

			     /* try {
			    	    ConnectDatabase db = new ConnectDatabase();
						ResultSet rs = db.sqlstatment().executeQuery("SELECT * FROM PACIENTE WHERE NOMBRE LIKE '%"+nombre.getText()+"%' AND APELLIDO LIKE '%"+apellido.getText()+"%'AND DNI LIKE '%"+dni.getText()+"%'");
						Object[] transf = QueryToTable.getSingle().queryToTable(rs);
						table = new JTable(new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]));
						
						
					} catch(Exception e) {
						e.printStackTrace();
					} */
					scrollPane.setViewportView(getTablePacientes());
	
				
			}
		});
		btnBuscar.setBounds(536, 11, 89, 52);
		frmBuscarPaciente.getContentPane().add(btnBuscar);
		
		JButton btnGet = new JButton("Continuar");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowClient showclient= new ShowClient((String) table.getValueAt(table.getSelectedRow(), 0));
				showclient.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
				showclient.frame.setVisible(true);
			}
		});
		btnGet.setBounds(536, 290, 89, 23);
		frmBuscarPaciente.getContentPane().add(btnGet);
	}
}
