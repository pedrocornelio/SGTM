/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.DaoFactory;
import dao.LoginDao;
import dao.MilitarDao;
import entities.Login;
import entities.Militar;

public class AdicionarUsuario extends JFrame {

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
			public void run() {
				try {
					AdicionarUsuario frame = new AdicionarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn;
	private JTextField textFieldpassword;
	private JFormattedTextField textPaneNBM;
	public boolean LiberarAcessoButton;
	public boolean AlmoxHist;
	public boolean AlmoxEdicao;
	public boolean AlmoxAdmin;
	public boolean Oficina;
	public boolean Gerencia;
	public boolean Compras;
	public String Senha;
	private JTextField textFieldNome;
	

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public AdicionarUsuario() throws ParseException {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdicionarUsuario.class.getResource("/image/admin_add_user.png")));
		setTitle("ADICIONAR USU\u00C1RIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 250);
		setLocationRelativeTo(null);
		
		LoginDao loginDao = DaoFactory.createLoginDao();
		MilitarDao militarDao = DaoFactory.createMilitarDao();
		Login object = new Login();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ADCIONAR USU\u00C1RIO E ACESSO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setAlignmentX(0.5f);
		lblNewLabel.setBounds(0, 11, 574, 32);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 574, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 574, 11);
		contentPane.add(separator_1);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(10, 92, 46, 20);
		contentPane.add(lblSenha);

		JCheckBox chckbxLiberarAcesso = new JCheckBox("LIBERAR ACESSO");
		chckbxLiberarAcesso.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxLiberarAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxLiberarAcesso.isSelected() == true) {
					object.setLiberarAcesso(true);
				} else {
					object.setLiberarAcesso(false);
				}
			}
		});
		chckbxLiberarAcesso.setBounds(223, 91, 129, 23);
		contentPane.add(chckbxLiberarAcesso);

		JCheckBox chckbxAlmoxHist = new JCheckBox("ALMOX HIST");
		chckbxAlmoxHist.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxHist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxHist.isSelected() == true) {
					object.setAlmoxHist(true);
				} else {
					object.setAlmoxHist(false);
				}
			}
		});
		chckbxAlmoxHist.setBounds(354, 50, 119, 23);
		contentPane.add(chckbxAlmoxHist);

		JCheckBox chckbxAlmoxEdicao = new JCheckBox("ALMOX EDI\u00C7\u00C3O");
		chckbxAlmoxEdicao.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxEdicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxEdicao.isSelected() == true) {
					object.setAlmoxEdicao(true);
				} else {
					object.setAlmoxEdicao(false);
				}
			}
		});
		chckbxAlmoxEdicao.setBounds(354, 90, 119, 23);
		contentPane.add(chckbxAlmoxEdicao);

		JCheckBox chckbxAlmoxAdmin = new JCheckBox("ALMOX ADMIN");
		chckbxAlmoxAdmin.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxAdmin.isSelected() == true) {
					object.setAlmoxAdmin(true);
				} else {
					object.setAlmoxAdmin(false);
				}
			}
		});
		chckbxAlmoxAdmin.setBounds(354, 130, 119, 23);
		contentPane.add(chckbxAlmoxAdmin);

		JCheckBox chckbxOficina = new JCheckBox("OFICINA");
		chckbxOficina.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxOficina.isSelected() == true) {
					object.setOficina(true);
				} else {
					object.setOficina(false);
				}
			}
		});
		chckbxOficina.setBounds(471, 50, 109, 23);
		contentPane.add(chckbxOficina);

		JCheckBox chckbxGerencia = new JCheckBox("GERENCIA");
		chckbxGerencia.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxGerencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxGerencia.isSelected() == true) {
					object.setGerencia(true);
				} else {
					object.setGerencia(false);
				}
			}
		});
		chckbxGerencia.setBounds(471, 90, 109, 23);
		contentPane.add(chckbxGerencia);

		JCheckBox chckbxCompras = new JCheckBox("COMPRAS");
		chckbxCompras.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxCompras.isSelected() == true) {
					object.setCompras(true);
				} else {
					object.setCompras(false);
				}
			}
		});
		chckbxCompras.setBounds(471, 130, 109, 23);
		contentPane.add(chckbxCompras);

		textFieldpassword = new JTextField();
		textFieldpassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldpassword.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldpassword.setBounds(63, 92, 120, 20);
		contentPane.add(textFieldpassword);

		JButton btnSalvar = new JButton("ADICIONAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				object.setNome(textFieldNome.getText());
				object.setnBM(textPaneNBM.getText());
				object.setSenha(textFieldpassword.getText());
				System.out.println(object);
				loginDao.insert(object);
				Militar militar = militarDao.findByNBM(object.getnBM());
				if (militar == null) {
					Militar novoMilitar = new Militar();
					novoMilitar.setNome(object.getNome());
					novoMilitar.setnBM(object.getnBM());
					militarDao.insert(novoMilitar);
				}
				dispose();
				DefaultTableModel model = new DefaultTableModel();
				loginDao.tableList(model);
				LiberarAcesso.tableAcesso.setModel(model);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(0).setMinWidth(10);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(0).setPreferredWidth(100);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(1).setMinWidth(10);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(1).setPreferredWidth(61);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(1).setMaxWidth(61);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(2).setMinWidth(10);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(1).setPreferredWidth(81);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(2).setMaxWidth(81);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(3).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(3).setPreferredWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(4).setMinWidth(40);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(4).setPreferredWidth(40);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(5).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(5).setPreferredWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(6).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(6).setPreferredWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(7).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(7).setPreferredWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(8).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(8).setPreferredWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(9).setMinWidth(20);
				LiberarAcesso.tableAcesso.getColumnModel().getColumn(9).setPreferredWidth(20);
			}
		});
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvar.setIcon(new ImageIcon(AdicionarUsuario.class.getResource("/image/admin_add_user.png")));
		btnSalvar.setBounds(217, 162, 140, 35);
		contentPane.add(btnSalvar);

		JLabel labelNome = new JLabel("NOME");
		labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNome.setBounds(10, 52, 46, 20);
		contentPane.add(labelNome);

		JLabel labelNBM = new JLabel("N\u00BABM");
		labelNBM.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNBM.setBounds(10, 132, 46, 20);
		contentPane.add(labelNBM);

		textPaneNBM = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textPaneNBM.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPaneNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		textPaneNBM.setBounds(63, 132, 100, 20);
		contentPane.add(textPaneNBM);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNome.setBounds(63, 52, 200, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
	}
}
