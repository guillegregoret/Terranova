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

public class AñadirOrden {

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
					AñadirOrden window = new AñadirOrden();
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
	public AñadirOrden() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame();
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
				db.Query("INSERT INTO orden(DNI, CODIGO, SESIONES_PEDIDAS, SESIONES_RESTANTES, FECHA) VALUES ("
						+ "'"+dni
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
