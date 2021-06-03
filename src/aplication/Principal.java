/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import aplication.almox.Almoxarifado;
import aplication.gerencia.Gerencia;
import aplication.oficina.Oficina;
import dao.DaoFactory;
import dao.HistoricoLoginDao;
import dao.LoginDao;
import db.DB;
import entities.HistoricoLogin;
import utilities.Ajuda;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	public static JTextField textFieldUser;
	private JLabel lblSenha;
	public static JPasswordField passwordField;
	private ButtonGroup bg = new ButtonGroup();
	private JButton btnOkButton;
	private JRadioButton rdbtnAlmox;
	private JRadioButton rdbtnOficina;
	private JRadioButton rdbtnCompras;
	private JRadioButton rdbtnGerencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Principal() throws ParseException {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setName("frame1");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/image/user_interface/csm.png")));
		setResizable(false);
		setTitle("LOGIN");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(460,300);
		setLocationRelativeTo(null);
		
		LoginDao loginDao = DaoFactory.createLoginDao();
		HistoricoLoginDao historicoLoginDao = DaoFactory.createHistoricoLoginDao();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 12));
		setJMenuBar(menuBar);

		JMenu mnAdmin = new JMenu("ADMIN");
		mnAdmin.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnAdmin);

		JMenuItem mntmLiberarAcesso = new JMenuItem("LIBERAR ACESSO");
		mntmLiberarAcesso.setFont(new Font("Arial", Font.BOLD, 14));
		mntmLiberarAcesso.setToolTipText("JANELA ADMINISTRATIVA PARA CONTROLE DE ACESSO AOS M\u00D3DULOS");
		mntmLiberarAcesso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		mntmLiberarAcesso.setIcon(new ImageIcon(Principal.class.getResource("/image/user_interface/admin.png")));
		mnAdmin.add(mntmLiberarAcesso);
		mntmLiberarAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nBM = textFieldUser.getText();
				String senha = passwordField.getText();
				entities.Login login = loginDao.findByNBMSenha(nBM, senha);
				if (login.getLiberarAcesso() == true) {
					LiberarAcesso exibir = new LiberarAcesso();
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});

		JMenu mnInfo = new JMenu("INFO");
		mnInfo.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnInfo);

		JMenuItem mntmCredit = new JMenuItem("CR\u00C9DITOS");
		mntmCredit.setFont(new Font("Arial", Font.BOLD, 14));
		mntmCredit.setIcon(new ImageIcon(Principal.class.getResource("/image/user_interface/credit_team.png")));
		mntmCredit.setToolTipText("JANELA COM OS CR\u00C9DITOS DOS ENVOLVIDOS NO PROJETO");
		mntmCredit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnInfo.add(mntmCredit);

		JMenuItem mntmAjuda = new JMenuItem("AJUDA");
		mntmAjuda.setFont(new Font("Arial", Font.BOLD, 14));
		mntmAjuda.setIcon(new ImageIcon(Principal.class.getResource("/image/user_interface/help.png")));
		mntmAjuda.setToolTipText("JANELA DE AJUDA");
		mntmAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnInfo.add(mntmAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		mntmAjuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Ajuda exibir = new Ajuda();
				exibir.setVisible(true);
			}
		});

		textFieldUser = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldUser.nextFocus();
				}
			}
		});
		textFieldUser.setBounds(284, 35, 114, 28);
		textFieldUser.setColumns(10);

		JLabel lblusuario = new JLabel("USUÁRIO");
		lblusuario.setBounds(200, 35, 60, 29);
		lblusuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblusuario.setFont(new Font("Arial", Font.PLAIN, 14));

		lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(204, 84, 56, 29);
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnOkButton.doClick();
				}
			}
		});
		passwordField.setBounds(284, 84, 114, 28);

		btnOkButton = new JButton("ENTRAR");
		btnOkButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnOkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOkButton.setBounds(250, 182, 97, 34);	
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				entities.Login login = loginDao.findByNBMSenha(textFieldUser.getText(), passwordField.getText());
				Date data = new java.sql.Date(new java.util.Date().getTime());
				Time hora = new java.sql.Time(new java.util.Date().getTime());
				HistoricoLogin historicoLogin = new HistoricoLogin (null, data, hora, loginDao.findByNBM(textFieldUser.getText()));
				historicoLoginDao.insert(historicoLogin);
				if (event.getSource() == btnOkButton) {
					if (rdbtnOficina.isSelected()) {
						if (login.getOficina() == true) {
							Oficina exibir = null;
							try {
								exibir = new Oficina();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							exibir.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
						}
					}
					
					if (rdbtnAlmox.isSelected()) {
						if (login.getAlmoxAdmin() == true | login.getAlmoxEdicao() == true | login.getAlmoxHist() == true) {
							Almoxarifado exibir = null;
							try {
								exibir = new Almoxarifado();
							} catch (ParseException e) {
								e.printStackTrace();
							}
							exibir.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
						}
					}
					if (rdbtnCompras.isSelected()) {
						JOptionPane.showMessageDialog(null, "MÓDULO AINDA NÃO IMPLEMENTADO", "EM DESENVOLVIMENTO", 0);
						/*
						 * if (login.getRequisicao() == 1 ) { Requisicao exibir = new Requisica();
						 * exibir.setVisible(true); } else { JOptionPane.showInternalMessageDialog(null,
						 * "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO"); }
						 */
					}
					if (rdbtnGerencia.isSelected()) {
						if (login.getGerencia() == true) {
							Gerencia exibir = null;
							exibir = new Gerencia();
							exibir.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
						}
					}
				}
			}
		});

		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(Principal.class.getResource("/image/csmp.png")));
		lblImage.setBounds(10, 11, 169, 205);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(null);
		
		rdbtnAlmox = new JRadioButton("ALMOXARIFADO");
		rdbtnAlmox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnOkButton.doClick();
				}
			}
		});
		rdbtnAlmox.setSelected(true);
		rdbtnAlmox.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnAlmox.setBounds(210, 123, 115, 23);
		contentPane.add(rdbtnAlmox);
		bg.add(rdbtnAlmox);

		rdbtnOficina = new JRadioButton("OFICINA");
		rdbtnOficina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnOkButton.doClick();
				}
			}
		});
		rdbtnOficina.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnOficina.setBounds(326, 123, 80, 23);
		contentPane.add(rdbtnOficina);
		bg.add(rdbtnOficina);

		rdbtnCompras = new JRadioButton("COMPRAS");
		rdbtnCompras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnOkButton.doClick();
				}
			}
		});
		rdbtnCompras.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnCompras.setBounds(210, 152, 100, 23);
		contentPane.add(rdbtnCompras);
		bg.add(rdbtnCompras);

		rdbtnGerencia = new JRadioButton("GERENCIA");
		rdbtnGerencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnOkButton.doClick();
				}
			}
		});
		rdbtnGerencia.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnGerencia.setBounds(326, 152, 100, 23);
		contentPane.add(rdbtnGerencia);
		bg.add(rdbtnGerencia);
		
		contentPane.add(lblImage);
		contentPane.add(btnOkButton);
		contentPane.add(lblusuario);
		contentPane.add(lblSenha);
		contentPane.add(textFieldUser);
		contentPane.add(passwordField);

		Connection conn = DB.getConnection();
		JLabel lblDBStatus = new JLabel("");
		if (conn == null) {
			lblDBStatus.setIcon(new ImageIcon(Principal.class.getResource("/image/user_interface/dbOff.png")));
			lblDBStatus.setToolTipText("SEM CONEX\u00C3O COM O BANCO DE DADOS");
		} else {
			lblDBStatus.setIcon(new ImageIcon(Principal.class.getResource("/image/user_interface/dbOn.png")));
			lblDBStatus.setToolTipText("BANCO DE DADOS CONECTADO");
		}
		lblDBStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDBStatus.setBounds(390, 200, 35, 35);
		contentPane.add(lblDBStatus);

	}

}
