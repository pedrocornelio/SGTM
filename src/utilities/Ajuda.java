/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package utilities;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Ajuda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Ajuda frame = new Ajuda();
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
	public Ajuda() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ajuda.class.getResource("/image/help.png")));
		setTitle("AJUDA");
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblAjudaEErros = new JLabel("AJUDA E ERROS");
		lblAjudaEErros.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjudaEErros.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAjudaEErros.setAlignmentX(0.5f);
		lblAjudaEErros.setBounds(0, 11, 384, 32);
		contentPane.add(lblAjudaEErros);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 41, 384, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 11, 384, 5);
		contentPane.add(separator_1);

		JLabel lblNewLabel = new JLabel("QUALQUER D\u00DAVIDA, SUGEST\u00C3O OU ERRO");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 54, 259, 24);
		contentPane.add(lblNewLabel);

		JLabel lblRafael = new JLabel("> SD RAFAEL ALVES DA SILVA");
		lblRafael.setFont(new Font("Arial", Font.BOLD, 13));
		lblRafael.setBounds(40, 110, 266, 19);
		contentPane.add(lblRafael);

		JLabel lblTelRafael = new JLabel("TELEFONE: (31) 986-949-324");
		lblTelRafael.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelRafael.setBounds(55, 130, 266, 19);
		contentPane.add(lblTelRafael);

		JLabel lblEmailRafael = new JLabel("E-MAIL: 1726348@bombeiros.mg.gov.br");
		lblEmailRafael.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEmailRafael.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmailRafael.setBounds(55, 150, 266, 19);
		contentPane.add(lblEmailRafael);

		JLabel lblEmailPedro = new JLabel("E-MAIL: 1730696@bombeiros.mg.gov.br");
		lblEmailPedro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEmailPedro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmailPedro.setBounds(55, 224, 266, 19);
		contentPane.add(lblEmailPedro);

		JLabel lblTelPedro = new JLabel("TELEFONE: (31) 986-377-199");
		lblTelPedro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelPedro.setBounds(55, 204, 266, 19);
		contentPane.add(lblTelPedro);

		JLabel lblPedro = new JLabel("> SD PEDRO HENRIQUE CABRAL CORNELIO");
		lblPedro.setFont(new Font("Arial", Font.BOLD, 13));
		lblPedro.setBounds(40, 184, 266, 19);
		contentPane.add(lblPedro);

		JLabel lblEntrarEmContato = new JLabel("ENTRAR EM CONTATO COM:");
		lblEntrarEmContato.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEntrarEmContato.setBounds(20, 75, 259, 24);
		contentPane.add(lblEntrarEmContato);
		
		JButton btnManual = new JButton("");
		btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try { 

					   java.awt.Desktop desktop = java.awt.Desktop.getDesktop();           
					   desktop.open(new File("/sgtm/manual.pdf")); 

					} catch (IOException ex) {
					    
					} 

			}
		});
		btnManual.setIcon(new ImageIcon(Ajuda.class.getResource("/image/help.png")));
		btnManual.setBounds(318, 64, 35, 35);
		contentPane.add(btnManual);
		
		JLabel lblManual = new JLabel("MANUAL");
		lblManual.setFont(new Font("Arial", Font.BOLD, 11));
		lblManual.setHorizontalAlignment(SwingConstants.CENTER);
		lblManual.setBounds(304, 110, 62, 14);
		contentPane.add(lblManual);

	}
}
