import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Dialog.ModalExclusionType;

public class AņadirOrden {

	public JFrame frame;
	private JTextField codigo;
	private JTextField cantsesiones;
	private String dni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AņadirOrden window = new AņadirOrden();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setdni(String dni) {
		this.dni=dni;
	}

	/**
	 * Create the application.
	 */
	public AņadirOrden() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setAlwaysOnTop(true);
		String cod=null;
		int sesiones=0;

        try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
           e.printStackTrace();
        }
		frame.setBounds(100, 100, 409, 141);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		codigo = new JTextField();
		codigo.setBounds(55, 33, 142, 20);
		frame.getContentPane().add(codigo);
		codigo.setColumns(10);
		
		cantsesiones = new JTextField();
		cantsesiones.setBounds(331, 33, 46, 20);
		frame.getContentPane().add(cantsesiones);
		cantsesiones.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCdigo.setBounds(10, 34, 46, 17);
		frame.getContentPane().add(lblCdigo);
		
		JLabel lblCantidadDeSesiones = new JLabel("Cantidad de Sesiones");
		lblCantidadDeSesiones.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCantidadDeSesiones.setBounds(207, 35, 124, 14);
		frame.getContentPane().add(lblCantidadDeSesiones);
		
		JButton btnAadirOrden = new JButton("A\u00F1adir Orden");
		btnAadirOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
				ConnectDatabase db  = new ConnectDatabase();
				
				int count=0;
				ResultSet rs3;
				try {
					rs3 = db.sqlstatment().executeQuery("SELECT MAX(RIGHT(UID, 6)) FROM ORDEN WHERE DNI LIKE '"+dni+"'");
					while(rs3.next()){
					    count = rs3.getInt("C1")+1;
					    };
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*String Q = "INSERT INTO orden(DNI, UID,CODIGO, SESIONES_PEDIDAS, SESIONES_RESTANTES, FECHA) VALUES ("
				+ "'"+dni
				+"','"+dni+"-"+Integer.toString(count)
				+"','"+codigo.getText()
				+"','"+cantsesiones.getText()
				+"','"+cantsesiones.getText()
				+"','"+dtf.format(localDate)
				+"');"; */
				
				//System.out.println(Q);
				String UID = String.format("%06d", count);

				db.Query("INSERT INTO orden(DNI, UID,CODIGO, SESIONES_PEDIDAS, SESIONES_RESTANTES, FECHA) VALUES ("
						+ "'"+dni
						+"','"+dni+"-"+UID
						+"','"+codigo.getText()
						+"','"+cantsesiones.getText()
						+"','"+cantsesiones.getText()
						+"','"+dtf.format(localDate)
						+"');"); 

				
			}
		});
		btnAadirOrden.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAadirOrden.setBounds(269, 68, 110, 23);
		frame.getContentPane().add(btnAadirOrden);

	}

}
