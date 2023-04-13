package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmListado extends JFrame {

	private JTextArea txtSalida;
	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListado frame = new FrmListado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnListado = new JButton("Ingresar");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnListado.setBounds(324, 29, 89, 23);
		contentPane.add(btnListado);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Filtro :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea txtSalida = new JTextArea();
		txtSalida.setBounds(20, 58, 419, 219);
		contentPane.add(txtSalida);
		
	}
	
	
	void registrar() {
		//leer los campos
		String usuario = txtUsuario.getText();
		//Validacion 
		//select * from tb_xx where usr = ? and cla = ?
		// 1. establecer conexion -> que unidad de persistencia usarás
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
				// permite que las entidades trabajen con la unidad..
				EntityManager em = fabrica.createEntityManager();
				
				
				List<Usuario> lstUsuarios =
						em.createQuery("select u from Usuario u where u.nom_usua like concat ('%',:xtipo,'%')", Usuario.class).
						setParameter("xtipo", usuario).
						getResultList();
				
				for (Usuario u : lstUsuarios) {
					System.out.println("Código..: " + u.getCod_usua());
					System.out.println("Nombre..: " + u.getNom_usua() + " " + u.getApe_usua());
					System.out.println("Tipo....: " + u.getIdtipo());
					System.out.println(" " + u.getObjTipo().getDescripcion());
					System.out.println("----------------------");
					
				}
				
	}
	 void imprimir(String s) {
		txtSalida.append(s + "\n");
	}
	

}
