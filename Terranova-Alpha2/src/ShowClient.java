import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class ShowClient extends JDialog {

	public JDialog frame;
	private JTable table;
	private JTextField apellido;
	private JTextField nombre;
	private JTextField dnitf;
	private JButton btnEliminarOrden;
	private JButton btnSesion;
	private JButton btnNuevaOrden;
	private DefaultTableModel dm;
	private JButton btnTest;
	//private String dni;

	/**
	 * Launch the application.
	 */
	public static void main(String dni) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowClient window = new ShowClient(dni);
					window.frame.setVisible(true);
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
	public ShowClient(String dni) {
		initialize(dni);
	}
	private JTable getTablePaciente(String dni) {
		
	      try {
				/*Class.forName("org.hsqldb.jdbc.JDBCDriver");
		        con = DriverManager.getConnection("jdbc:hsqldb:hsql://192.168.0.202/testdb", "SA", "");

		        Statement s = con.createStatement();*/
				ConnectDatabase db = new ConnectDatabase();
		        ResultSet ps = db.sqlstatment().executeQuery("SELECT * FROM PACIENTE WHERE DNI LIKE '"+dni+"'");
		        ps.next();
		        dnitf.setText(ps.getString("DNI"));
		        nombre.setText(ps.getString("NOMBRE"));
		        apellido.setText(ps.getString("APELLIDO"));
				ResultSet rs = db.sqlstatment().executeQuery("SELECT * FROM ORDEN WHERE DNI LIKE '"+dni+"'");
				Object[] transf = QueryToTable.getSingle().queryToTable(rs);
				
				this.dm = new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]);
				//table = new JTable(new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]));
				table = new JTable(dm);
				
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			return table;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String dni) {
		frame = new JDialog();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 80, 615, 193);
		frame.getContentPane().add(scrollPane);
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				table = getTablePaciente(dni);
				scrollPane.setViewportView(table);
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});

		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				table = getTablePaciente(dni);
				scrollPane.setViewportView(table);
			}
		});
		frame.setModalityType(ModalityType.DOCUMENT_MODAL);
		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frame.setBounds(100, 100, 691, 370);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		apellido = new JTextField();
		apellido.setEditable(false);
		apellido.setColumns(10);
		apellido.setBounds(293, 18, 138, 20);
		frame.getContentPane().add(apellido);
		
		JLabel label = new JLabel("Apellido");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(237, 19, 46, 17);
		frame.getContentPane().add(label);
		
		nombre = new JTextField();
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setBounds(86, 18, 138, 20);
		frame.getContentPane().add(nombre);
		
		dnitf = new JTextField();
		dnitf.setEditable(false);
		dnitf.setColumns(10);
		dnitf.setBounds(86, 49, 138, 20);
		frame.getContentPane().add(dnitf);
		
		JLabel label_1 = new JLabel("DNI");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(30, 52, 34, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Nombre");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_2.setBounds(30, 21, 46, 14);
		frame.getContentPane().add(label_2);
		
		table = getTablePaciente(dni);
		
	    /*  try {
				/*Class.forName("org.hsqldb.jdbc.JDBCDriver");
		        con = DriverManager.getConnection("jdbc:hsqldb:hsql://192.168.0.202/testdb", "SA", "");

		        Statement s = con.createStatement();
				ConnectDatabase db = new ConnectDatabase();
		        ResultSet ps = db.sqlstatment().executeQuery("SELECT * FROM PACIENTE WHERE DNI LIKE '"+dni+"'");
		        ps.next();
		        dnitf.setText(ps.getString("DNI"));
		        nombre.setText(ps.getString("NOMBRE"));
		        apellido.setText(ps.getString("APELLIDO"));
				ResultSet rs = db.sqlstatment().executeQuery("SELECT * FROM ORDEN WHERE DNI LIKE '"+dni+"'");
				Object[] transf = QueryToTable.getSingle().queryToTable(rs);
				
				this.dm = new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]);
				//table = new JTable(new DefaultTableModel((Vector<Vector<Object>>)transf[0], (Vector<String>)transf[1]));
				table = new JTable(dm);
				
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			} */
		
		scrollPane.setViewportView(table);
			
			
			btnEliminarOrden = new JButton("Eliminar Orden");
			btnEliminarOrden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					ConnectDatabase db = new ConnectDatabase();
			        try {
						ResultSet ps = db.sqlstatment().executeQuery("DELETE FROM ORDEN WHERE UID LIKE '"+table.getValueAt(table.getSelectedRow(), 1)+"'");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					table = getTablePaciente(dni);
					scrollPane.setViewportView(table);
				}
			});
			btnEliminarOrden.setBounds(407, 284, 114, 23);
			frame.getContentPane().add(btnEliminarOrden);
			
			btnSesion = new JButton("Nueva Sesi\u00F3n");
			btnSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConnectDatabase db = new ConnectDatabase();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate localDate = LocalDate.now();
					
			        try {
						ResultSet ps1 = db.sqlstatment().executeQuery("INSERT INTO SESION(UID,FECHA) VALUES('"+table.getValueAt(table.getSelectedRow(), 1)+"','"+dtf.format(localDate)+"')");
						ResultSet ps2 = db.sqlstatment().executeQuery("UPDATE ORDEN SET SESIONES_RESTANTES = SESIONES_RESTANTES - 1 WHERE ORDEN.UID LIKE '"+table.getValueAt(table.getSelectedRow(), 1)+"'");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					table = getTablePaciente(dni);
					scrollPane.setViewportView(table);
					
					
					
					
					
					
				}
			});
			btnSesion.setBounds(531, 284, 114, 23);
			frame.getContentPane().add(btnSesion);
			
			btnNuevaOrden = new JButton("Agregar Orden");
			btnNuevaOrden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AñadirOrden añadirorden = new AñadirOrden();
					añadirorden.setdni(dni);
					añadirorden.frame.setVisible(true);
					

				}
			});
			btnNuevaOrden.setBounds(283, 284, 114, 23);
			frame.getContentPane().add(btnNuevaOrden);
			
			btnTest = new JButton("Test");
			btnTest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//table.revalidate();
					//dm.fireTableDataChanged();
					table = getTablePaciente(dni);
					scrollPane.setViewportView(table);
					
				}
			});
			btnTest.setBounds(30, 284, 89, 23);
			frame.getContentPane().add(btnTest);
			




	}
}
