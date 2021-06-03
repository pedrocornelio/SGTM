/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.gerencia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DaoFactory;
import dao.MontadoraDao;
import dao.UnidadeDao;
import dao.ViaturaDao;
import entities.Viatura;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AdicionarViatura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPlaca;
	private JTextField textFieldModelo;
	private JTextField textFieldAno;
	private JTextField textFieldChassi;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMontadora;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxOBM;
	private JButton btnAddVTR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarViatura frame = new AdicionarViatura();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AdicionarViatura() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdicionarViatura.class.getResource("/image/user_interface/manager_vtr.png")));
		setTitle("ADICIONAR VIATURA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 444, 5);
		contentPane.add(separator);
		
		JLabel lblTitulo = new JLabel("ADICIONAR VIATURA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);
		lblTitulo.setBounds(0, 11, 444, 25);
		contentPane.add(lblTitulo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 444, 5);
		contentPane.add(separator_1);
		
		comboBoxOBM = new JComboBox();
		comboBoxOBM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnAddVTR.requestFocusInWindow();
				}
				validar();
			}
		});
		DefaultComboBoxModel unidadeModelComboBox = new DefaultComboBoxModel();
		unidadeDao.comboBoxUnidade(unidadeModelComboBox);
		comboBoxOBM.setModel(unidadeModelComboBox);
		comboBoxOBM.setBackground(Color.WHITE);
		comboBoxOBM.setFont(new Font("Arial", Font.BOLD, 12));
		comboBoxOBM.setBounds(136, 262, 275, 23);
		contentPane.add(comboBoxOBM);
		
		JLabel lblUnidade = new JLabel("UNIDADE");
		lblUnidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidade.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUnidade.setBounds(41, 262, 82, 23);
		contentPane.add(lblUnidade);
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldModelo.requestFocusInWindow();
				}
				validar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldPlaca.getText().toUpperCase();
				textFieldPlaca.setText(temp);
				validar();
			}
		});
		textFieldPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(1);
		textFieldPlaca.setBounds(136, 92, 275, 23);
		contentPane.add(textFieldPlaca);
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(41, 92, 82, 23);
		contentPane.add(lblPlaca);
		
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModelo.setBounds(41, 126, 82, 23);
		contentPane.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldAno.requestFocusInWindow();
				}
				validar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldModelo.getText().toUpperCase();
				textFieldModelo.setText(temp);
				validar();
			}
		});
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(1);
		textFieldModelo.setBounds(136, 126, 275, 23);
		contentPane.add(textFieldModelo);
		
		JLabel lblAno = new JLabel("ANO");
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAno.setBounds(41, 160, 82, 23);
		contentPane.add(lblAno);
		
		textFieldAno = new JTextField();
		textFieldAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldChassi.requestFocusInWindow();
				}
				validar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldAno.getText().toUpperCase();
				textFieldAno.setText(temp);
				validar();
			}
		});
		textFieldAno.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAno.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldAno.setColumns(1);
		textFieldAno.setBounds(136, 160, 275, 23);
		contentPane.add(textFieldAno);
		
		textFieldChassi = new JTextField();
		textFieldChassi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxMontadora.requestFocusInWindow();
				}
				validar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldChassi.getText().toUpperCase();
				textFieldChassi.setText(temp);
				validar();
			}
		});
		textFieldChassi.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChassi.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldChassi.setColumns(1);
		textFieldChassi.setBounds(136, 194, 275, 23);
		contentPane.add(textFieldChassi);
		
		JLabel lblChassi = new JLabel("CHASSI");
		lblChassi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChassi.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChassi.setBounds(41, 194, 82, 23);
		contentPane.add(lblChassi);
		
		JLabel lblDadosDaViatura = new JLabel("DADOS DA VIATURA");
		lblDadosDaViatura.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDadosDaViatura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDadosDaViatura.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDadosDaViatura.setBounds(10, 57, 153, 23);
		contentPane.add(lblDadosDaViatura);
		
		JLabel lblMontadora = new JLabel("MONTADORA");
		lblMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMontadora.setBounds(41, 228, 82, 23);
		contentPane.add(lblMontadora);
		
		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxOBM.requestFocusInWindow();
				}
				
				validar();
			}
		});
		DefaultComboBoxModel montadoraModelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(montadoraModelcombobox);
		comboBoxMontadora.setModel(montadoraModelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setBackground(Color.WHITE);
		comboBoxMontadora.setBounds(136, 228, 275, 22);
		contentPane.add(comboBoxMontadora);
		
		btnAddVTR = new JButton("ADICIONAR VIATURA");
		btnAddVTR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Viatura viatura = new Viatura(null, textFieldPlaca.getText(), textFieldModelo.getText(), 
						textFieldAno.getText(), textFieldChassi.getText(), 
						montadoraDao.findByMontadora(comboBoxMontadora.getSelectedItem().toString()), 
						unidadeDao.findByUnidade(comboBoxOBM.getSelectedItem().toString()), null);
				
				viaturaDao.insert(viatura);
				
				JOptionPane.showMessageDialog(null, "VIATURA PLACA: "+ textFieldPlaca.getText().toString() +" ADICIONADA COM SUCESSO", "ADIÇÃO DE VIATURA", 1);
				
				dispose();
				
			}
		});
		btnAddVTR.setIcon(new ImageIcon(AdicionarViatura.class.getResource("/image/user_interface/manager_vtr.png")));
		btnAddVTR.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddVTR.setEnabled(false);
		btnAddVTR.setBounds(117, 312, 210, 35);
		contentPane.add(btnAddVTR);
	}
	
	public void validar() {
		if (comboBoxMontadora.getSelectedItem().toString().length() != 0
				& comboBoxOBM.getSelectedItem().toString().length() != 0 
				& textFieldPlaca.getText().length() != 0 
				& textFieldModelo.getText().length() != 0
				& textFieldAno.getText().length() != 0
				& textFieldChassi.getText().length() != 0) {
			btnAddVTR.setEnabled(true);
		} else {
			btnAddVTR.setEnabled(false);
		}
	}

}