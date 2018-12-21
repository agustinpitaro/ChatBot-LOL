package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Color;
import java.awt.Toolkit;

public class GameChooserWindow {

	private JFrame frmChooseYourDestiny;
	private JTextField ChooseTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameChooserWindow window = new GameChooserWindow();
					window.frmChooseYourDestiny.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameChooserWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChooseYourDestiny = new JFrame();
		frmChooseYourDestiny.setIconImage(Toolkit.getDefaultToolkit().getImage(new File("").getAbsoluteFile() + "/TP Objetos/src/icono.png"));
		frmChooseYourDestiny.getContentPane().setBackground(new Color(51, 51, 51));
		frmChooseYourDestiny.setTitle("Choose your destiny!");
		frmChooseYourDestiny.setBounds(100, 100, 450, 300);
		frmChooseYourDestiny.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChooseYourDestiny.getContentPane().setLayout(null);
		JButton BotonLOL = new JButton("");
		BotonLOL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					frmChooseYourDestiny.dispose();
					ChatWindow.main(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		BotonLOL.setHorizontalAlignment(SwingConstants.CENTER);
		BotonLOL.setIcon(new ImageIcon(new File("").getAbsoluteFile() + "/TP Objetos/src/LoL.jpg" ));
		BotonLOL.setBounds(0, 39, 220, 211);
		frmChooseYourDestiny.getContentPane().add(BotonLOL);
		
		JButton BotonHS = new JButton("");
		BotonHS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		BotonHS.setVerticalAlignment(SwingConstants.TOP);
		BotonHS.setHorizontalAlignment(SwingConstants.CENTER);
		BotonHS.setIcon(new ImageIcon(new File("").getAbsoluteFile() + "/TP Objetos/src/Hearthstone.jpg"));
		BotonHS.setBounds(219, 39, 215, 211);
		frmChooseYourDestiny.getContentPane().add(BotonHS);
		
		ChooseTxt = new JTextField();
		ChooseTxt.setFont(new Font("Verdana", Font.BOLD, 18));
		ChooseTxt.setEditable(false);
		ChooseTxt.setHorizontalAlignment(SwingConstants.CENTER);
		ChooseTxt.setText("Elije tu juego");
		ChooseTxt.setBounds(126, 0, 161, 34);
		frmChooseYourDestiny.getContentPane().add(ChooseTxt);
		ChooseTxt.setColumns(10);
	}
}
