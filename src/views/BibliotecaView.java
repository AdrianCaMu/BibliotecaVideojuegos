package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Biblioteca.Biblioteca;
import models.VideoJuegos;

public class BibliotecaView {

	private JFrame frame;
	private JTextField tfNombre;
	private JTextField tfGenero;
	private JTextField tfDesarolladores;
	private JTextField tfPegi;
	private JLabel lblVideojuegos;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private int indice = 0;
	private JButton btnCrear;
	private JButton btnBorrar;
	private JButton btnUpdate;
	private JButton btnCancelarActualizar;
	private JButton btnGuardar;
	private JTextField tfHoras;

	/**
	 * Create the application.
	 */
	public BibliotecaView() {
		indice = 0;
		initialize();
		frame.revalidate();
		frame.repaint();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		configureUIComponents();
		configureListeners();
	}
	
	public void configureUIComponents() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 581, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblVideojuegos = new JLabel("Videojuegos");
		lblVideojuegos.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblVideojuegos.setBounds(191, 11, 223, 45);
		frame.getContentPane().add(lblVideojuegos);

		btnAtras = new JButton("<");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAtras.setBounds(10, 11, 59, 45);
		frame.getContentPane().add(btnAtras);

		btnSiguiente = new JButton(">");
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSiguiente.setBounds(496, 16, 59, 45);
		frame.getContentPane().add(btnSiguiente);

		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(101, 133, 243, 26);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		tfGenero = new JTextField();
		tfGenero.setEditable(false);
		tfGenero.setColumns(10);
		tfGenero.setBounds(101, 170, 243, 26);
		frame.getContentPane().add(tfGenero);

		tfDesarolladores = new JTextField();
		tfDesarolladores.setEditable(false);
		tfDesarolladores.setColumns(10);
		tfDesarolladores.setBounds(101, 207, 243, 26);
		frame.getContentPane().add(tfDesarolladores);

		tfPegi = new JTextField();
		tfPegi.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tfPegi.setEditable(false);
		tfPegi.setBounds(398, 123, 86, 70);
		frame.getContentPane().add(tfPegi);
		tfPegi.setColumns(10);

		btnCrear = new JButton("A\u00F1adir Videojuego");
		btnCrear.setBounds(42, 351, 145, 23);
		frame.getContentPane().add(btnCrear);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(398, 351, 126, 23);
		frame.getContentPane().add(btnBorrar);

		btnUpdate = new JButton("Actualizar");
		btnUpdate.setBounds(226, 351, 145, 23);
		frame.getContentPane().add(btnUpdate);

		btnCancelarActualizar = new JButton("Deshacer cambios");
		btnCancelarActualizar.setBounds(330, 385, 154, 23);
		frame.getContentPane().add(btnCancelarActualizar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(118, 385, 145, 23);
		frame.getContentPane().add(btnGuardar);
		
		tfHoras = new JTextField();
		tfHoras.setEditable(false);
		tfHoras.setColumns(10);
		tfHoras.setBounds(101, 244, 243, 26);
		frame.getContentPane().add(tfHoras);
		btnCancelarActualizar.setVisible(false);
		btnGuardar.setVisible(false);
		printJuego();
	}

	public void configureListeners() {
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printAtras();
			}

		});
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printDelante();
			}
		});
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CrearVideojuegoView();
				frame.dispose();
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interruptorEditar();

			}
		});
		
		btnCancelarActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interruptorEditar();
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJuego(tfNombre.getText(), tfGenero.getText(), tfDesarolladores.getText(), Integer.parseInt(tfPegi.getText()), Integer.parseInt(tfHoras.getText()));
				interruptorEditar();
			}
		});
	}

	private void printJuego() {
		if (Biblioteca.juegos.size() > 0) {
			VideoJuegos a = Biblioteca.juegos.get(indice);
			tfNombre.setText(a.getNombre());
			tfGenero.setText(a.getGenero());
			tfDesarolladores.setText(a.getDesarrolladora());
			tfPegi.setText(String.valueOf(a.getPegi()));
			tfHoras.setText(String.valueOf(a.getHoras()));

		}

		if (indice == Biblioteca.juegos.size() - 1) {
			btnSiguiente.setVisible(false);

		} else {
			btnSiguiente.setVisible(true);
		}
		if (indice == 0) {
			btnAtras.setVisible(false);

		} else {
			btnAtras.setVisible(true);
		}
	}
	
	public void eliminar() {
		int confirmar = JOptionPane.showConfirmDialog(btnBorrar,
				"¿Estás seguro de que quieres borrar este registro?");
		if (confirmar == 0) { // Quiere borrar
			Biblioteca.juegos.remove(indice);
			if(indice == 0) {
				printDelante();
			}
			 if (Biblioteca.juegos.size() > 0) {
				printAtras();
			} else {
				JOptionPane.showMessageDialog(btnBorrar, "Te has quedado sin videojuegos");
				printVacio();
			}
		}
	}

	private void printVacio() {
		tfNombre.setText("");
		tfGenero.setText("");
		tfDesarolladores.setText("");
		tfPegi.setText("");
		tfHoras.setText("");
	}

	private void printAtras() {
		indice--;

		printJuego();
	}

	private void printDelante() {
		indice++;

		printJuego();
	}

	private void interruptorEditar() {
		tfNombre.setEditable(!tfNombre.isEditable());
		tfGenero.setEditable(!tfGenero.isEditable());
		tfDesarolladores.setEditable(!tfDesarolladores.isEditable());
		tfPegi.setEditable(!tfPegi.isEditable());
		tfHoras.setEditable(!tfHoras.isEditable());
		btnAtras.setVisible(!btnAtras.isVisible());
		btnSiguiente.setVisible(!btnSiguiente.isVisible());
		btnCrear.setVisible(!btnCrear.isVisible());
		btnBorrar.setVisible(!btnBorrar.isVisible());
		btnCancelarActualizar.setVisible(!btnCancelarActualizar.isVisible());
		btnGuardar.setVisible(!btnGuardar.isVisible());
		btnUpdate.setVisible(!btnUpdate.isVisible());
		printJuego();
	}

	public void updateJuego(String nombre, String genero, String Desarrolladora, int Pegi, int Hora) {
		VideoJuegos a = Biblioteca.juegos.get(indice);
		a.setNombre(nombre);
		a.setGenero(genero);
		a.setDesarrolladora(Desarrolladora);
		a.setPegi(Pegi);
		a.setHoras(Hora);
	}


}
