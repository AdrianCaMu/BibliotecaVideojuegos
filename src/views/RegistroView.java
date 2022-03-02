package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Biblioteca.Biblioteca;
import models.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistroView {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmarPassword;
	private JButton btnRegister;
	private JLabel lblRegistrar;
	private JButton btnSalir;

	public RegistroView() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		configureUIComponents();
		configureListeners();
	}

	private void configureUIComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblRegistrar = new JLabel("Registro de usuarios");
		lblRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistrar.setBounds(96, 11, 242, 37);
		frame.getContentPane().add(lblRegistrar);

		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUsername.setBounds(209, 84, 180, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdPassword.setBounds(209, 113, 180, 20);
		frame.getContentPane().add(pwdPassword);

		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdConfirmPassword.setBounds(209, 144, 180, 20);
		frame.getContentPane().add(pwdConfirmPassword);

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(23, 82, 108, 20);
		frame.getContentPane().add(lblUsername);

		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(23, 113, 117, 20);
		frame.getContentPane().add(lblPassword);

		lblConfirmarPassword = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfirmarPassword.setBounds(21, 144, 188, 20);
		frame.getContentPane().add(lblConfirmarPassword);

		btnRegister = new JButton("Registrar");
		btnRegister.setBounds(252, 202, 89, 23);
		frame.getContentPane().add(btnRegister);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(85, 202, 89, 23);
		frame.getContentPane().add(btnSalir);

	}

	private void configureListeners() {
		btnRegister.addActionListener(new ActionListener() {
			// OnClick
			public void actionPerformed(ActionEvent e) {
				registrar(tfUsername.getText(), new String(pwdPassword.getPassword()), new String(pwdConfirmPassword.getPassword()));
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});

		pwdConfirmPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Para que al pulsar la tecla enter, te deje acceder
					registrar(tfUsername.getText(), new String(pwdPassword.getPassword()), new String(pwdConfirmPassword.getPassword()));
				}
			}
		});
	}

	public boolean registrar(String user, String contrasenya, String contrasenya2) {
		String username = user;
		String password = contrasenya;
		String confirmarPassword = contrasenya2;
		
		if (password.length() >= 8) {
			if (password.equals(confirmarPassword)) {
				if (!username.isEmpty() && !password.isEmpty() && !confirmarPassword.isEmpty()) {

					Usuario u = new Usuario(username, password);
					boolean comprobar = comprobarNombreUsu(u);
					if (comprobar == true) {
						Biblioteca.usuarios.add(u);
						JOptionPane.showMessageDialog(btnRegister, "Usuario registrado correctamente");
						new LoginView();
						frame.dispose();
						return true;
						
					} else {
						JOptionPane.showMessageDialog(btnRegister, "nombre de usuario ya registrado, pruebe con otro");
					}
				} else {
					JOptionPane.showMessageDialog(lblRegistrar, "Rellena todos los campos");
				}
			} else {
				JOptionPane.showMessageDialog(lblRegistrar, "Las contraseñas no coinciden");
			}
		} else {
			JOptionPane.showMessageDialog(lblRegistrar, "Las contraseñas tienen que tener mas de 8 caracteres");
		}
		return false;
	}

	private boolean comprobarNombreUsu(Usuario u) {

		for (Usuario usu : Biblioteca.usuarios) {
			if (usu.getNombre().equals(u.getNombre())) {
				return false;
			}
		}

		return true;
	}
}
