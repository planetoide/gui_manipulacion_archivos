package leerarchivo;

/**
* Aplicacion para la manipulacion de archivos .txt
*
* @author  Elvis Martinez Flores 
* @version 1.0
* @since   2018-05-12 
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Interfaz {

	private JFrame frame;
	private JTextField textField;
	private JFrame f;
	private File fi;
	private JTextField textF;

	/**
	 * Lanzar la aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor para lanzar la aplicacion
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Inicializar el contenido del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Aplicacion lectura y escritura de archivos");
		
		JTextArea areaTexto = new JTextArea();
		areaTexto.setBackground(Color.WHITE);
		areaTexto.setBounds(12, 92, 408, 415);
		frame.getContentPane().add(areaTexto);
		
		JButton botonAbrir = new JButton("Abrir Archivo");
		botonAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*inicia la seleccion del archivo para leer*/
				JFileChooser fc=new JFileChooser(); 
				fc.showOpenDialog(null); 
				File archivo = fc.getSelectedFile();
				
				try{ 
					FileReader fr = new FileReader (archivo); 
					BufferedReader br = new BufferedReader(fr); 
					String texto=""; 
					String linea=""; 
					while(((linea=br.readLine())!=null) ){ 
						texto+=linea+"\n"; 
					} 
					areaTexto.setText(texto); 
				} catch(Exception e){ 
					System.out.println("no se ha podido");
				}
				/*finaliza el evento de lectura del archivo de leer*/
			}
		});
		botonAbrir.setBounds(255, 54, 165, 25);
		frame.getContentPane().add(botonAbrir);
		
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nombreArchivo=textField.getText(); 
				String carpeta = System.getProperty("user.dir"); 
				String direccionCompleta= carpeta+"/"+nombreArchivo+".txt"; 
				FileWriter ubicacion = null; 
				try { 
					ubicacion = new FileWriter(direccionCompleta); 
				} catch (IOException ex) { 
					System.out.println(ex);
				} 
				try{ 
					BufferedWriter escritor = new BufferedWriter(ubicacion); 
					escritor.write(areaTexto.getText()); 
					escritor.close(); 
					JOptionPane.showMessageDialog(null,"se ha creado el archivo " + textField.getText());
				} catch(Exception e){ 
					System.out.println(e);
				}
			}
		});
		btnCrear.setBounds(12, 18, 97, 25);
		frame.getContentPane().add(btnCrear);
		
		
		/**/
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				f = new JFrame();
				f.setBounds(100, 150, 450, 150);
				f.getContentPane().setLayout(null);
				f.setTitle("Eliminar archivo");
				f.setVisible(true);
				
				JLabel eliminar = new JLabel("Seleccione el archivo que desea eliminar");
				eliminar.setBounds(110, 22, 300, 16);
				f.getContentPane().add(eliminar);
				
				JButton btnDel = new JButton("Seleccionar");				
				btnDel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JFileChooser fc=new JFileChooser(); 
						fc.showOpenDialog(null); 
						File fi = fc.getSelectedFile(); 
						if (fi.delete()) JOptionPane.showMessageDialog(null, "El fichero ha sido borrado satisfactoriamente"); 
						else JOptionPane.showMessageDialog(null, "El fichero no puede ser borrado");		
					}
				});		
				btnDel.setBounds(140, 56, 150, 25);
				f.getContentPane().add(btnDel);
			}
		});
		btnEliminar.setBounds(12, 56, 97, 25);
		frame.getContentPane().add(btnEliminar);
		/**/
			
		/*inicio renombrar*/
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				f = new JFrame();
				f.setBounds(100, 150, 450, 150);
				//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.getContentPane().setLayout(null);
				f.setTitle("Cambiar nombre");
				f.setVisible(true);
				
				JButton btnSeleccionar = new JButton("Seleccionar");				
				btnSeleccionar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JFileChooser fc=new JFileChooser(); 
						fc.showOpenDialog(null); 
						fi = fc.getSelectedFile();
						textF.setText(fi.getName());			
					}
				});		
				btnSeleccionar.setBounds(12, 19, 150, 25);
				f.getContentPane().add(btnSeleccionar);
				
				
				JButton rename = new JButton("Cambiar");				
				rename.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String nvo=fi.getParent()+"\\"+textF.getText()+".txt"; 
						File f2 = new File(textF.getText()); 
						boolean correcto = fi.renameTo(f2); 
						if (correcto==true) JOptionPane.showMessageDialog(null,"El renombrado ha sido correcto"); 
						else JOptionPane.showMessageDialog(null,"El renombrado no se ha podido realizar");
					}
				});		
				rename.setBounds(195, 19, 150, 25);
				f.getContentPane().add(rename);
				
				textF = new JTextField();
				textF.setBounds(195, 60, 225, 22);
				f.getContentPane().add(textF);
				textF.setColumns(10);
				
				JLabel nuevoN = new JLabel("Asignar nuevo nombre:");
				nuevoN.setBounds(12, 60, 225, 16);
				f.getContentPane().add(nuevoN);
			}
		});
		btnRenombrar.setBounds(121, 54, 122, 25);
		frame.getContentPane().add(btnRenombrar);
		/*fin renombrar*/
		
		textField = new JTextField();
		textField.setBounds(195, 19, 225, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(121, 22, 62, 16);
		frame.getContentPane().add(lblNombre);
	}
}
