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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.LoginDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class LiberarAcesso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAcesso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LiberarAcesso frame = new LiberarAcesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn;
	private JTextField textFieldpassword;
	private JTextPane textPaneNBM;
	public boolean LiberarAcesso;
	public boolean AlmoxHist;
	public boolean AlmoxEdicao;
	public boolean AlmoxAdmin;
	public boolean Oficina;
	public boolean Gerencia;
	public boolean Compras;
	public String Senha;

	/**
	 * Create the frame.
	 */
	public LiberarAcesso() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LiberarAcesso.class.getResource("/image/user_interface/admin.png")));
		setTitle("LIBERAR ACESSO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 580);
		setLocationRelativeTo(null);
		
		LoginDao loginDao = DaoFactory.createLoginDao();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 764, 265);

		tableAcesso = new JTable();
		tableAcesso.setFont(new Font("Arial", Font.PLAIN, 12));
		tableAcesso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableFilterHeader filterHeadertableAcesso = new TableFilterHeader(tableAcesso, AutoChoices.ENABLED);
		filterHeadertableAcesso.setPosition(Position.TOP);
		filterHeadertableAcesso.setBackground(Color.white);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tableAcesso);
		contentPane.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel();

		loginDao.tableList(model);
		tableAcesso.setModel(model);
		tableAcesso.getColumnModel().getColumn(0).setMinWidth(10);
		tableAcesso.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableAcesso.getColumnModel().getColumn(1).setMinWidth(10);
		tableAcesso.getColumnModel().getColumn(1).setPreferredWidth(61);
		tableAcesso.getColumnModel().getColumn(1).setMaxWidth(61);
		tableAcesso.getColumnModel().getColumn(2).setMinWidth(10);
		tableAcesso.getColumnModel().getColumn(1).setPreferredWidth(81);
		tableAcesso.getColumnModel().getColumn(2).setMaxWidth(81);
		tableAcesso.getColumnModel().getColumn(3).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(3).setPreferredWidth(20);
		tableAcesso.getColumnModel().getColumn(4).setMinWidth(40);
		tableAcesso.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableAcesso.getColumnModel().getColumn(5).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(5).setPreferredWidth(20);
		tableAcesso.getColumnModel().getColumn(6).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(6).setPreferredWidth(20);
		tableAcesso.getColumnModel().getColumn(7).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(7).setPreferredWidth(20);
		tableAcesso.getColumnModel().getColumn(8).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(8).setPreferredWidth(20);
		tableAcesso.getColumnModel().getColumn(9).setMinWidth(20);
		tableAcesso.getColumnModel().getColumn(9).setPreferredWidth(20);

		JLabel lblNewLabel = new JLabel("TABELA DE INFORMA\u00C7\u00D5ES DE USU\u00C1RIO E ACESSO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setAlignmentX(0.5f);
		lblNewLabel.setBounds(0, 11, 784, 32);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 784, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 784, 11);
		contentPane.add(separator_1);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(10, 400, 46, 20);
		contentPane.add(lblSenha);

		JCheckBox chckbxLiberarAcesso = new JCheckBox("LIBERAR ACESSO");
		chckbxLiberarAcesso.setEnabled(false);
		chckbxLiberarAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxLiberarAcesso.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 3);
					LiberarAcesso = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 3);
					LiberarAcesso = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxLiberarAcesso.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxLiberarAcesso.setBounds(240, 400, 129, 23);
		contentPane.add(chckbxLiberarAcesso);

		JCheckBox chckbxAlmoxHist = new JCheckBox("ALMOX HIST");
		chckbxAlmoxHist.setEnabled(false);
		chckbxAlmoxHist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxHist.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 4);
					AlmoxHist = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 4);
					AlmoxHist = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxAlmoxHist.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxHist.setBounds(375, 360, 129, 23);
		contentPane.add(chckbxAlmoxHist);

		JCheckBox chckbxAlmoxEdicao = new JCheckBox("ALMOX EDI\u00C7\u00C3O");
		chckbxAlmoxEdicao.setEnabled(false);
		chckbxAlmoxEdicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxEdicao.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 5);
					AlmoxEdicao = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 5);
					AlmoxEdicao = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxAlmoxEdicao.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxEdicao.setBounds(375, 400, 129, 23);
		contentPane.add(chckbxAlmoxEdicao);

		JCheckBox chckbxAlmoxAdmin = new JCheckBox("ALMOX ADMIN");
		chckbxAlmoxAdmin.setEnabled(false);
		chckbxAlmoxAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxAlmoxAdmin.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 6);
					AlmoxAdmin = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 6);
					AlmoxAdmin = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxAlmoxAdmin.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlmoxAdmin.setBounds(375, 440, 129, 23);
		contentPane.add(chckbxAlmoxAdmin);

		JCheckBox chckbxOficina = new JCheckBox("OFICINA");
		chckbxOficina.setEnabled(false);
		chckbxOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxOficina.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 7);
					Oficina = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 7);
					Oficina = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxOficina.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxOficina.setBounds(515, 360, 109, 23);
		contentPane.add(chckbxOficina);

		JCheckBox chckbxGerencia = new JCheckBox("GERENCIA");
		chckbxGerencia.setEnabled(false);
		chckbxGerencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxGerencia.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 8);
					Gerencia = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 8);
					Gerencia = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxGerencia.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxGerencia.setBounds(515, 400, 109, 23);
		contentPane.add(chckbxGerencia);

		JCheckBox chckbxCompras = new JCheckBox("COMPRAS");
		chckbxCompras.setEnabled(false);
		chckbxCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxCompras.isSelected() == true) {
					tableAcesso.setValueAt(true, tableAcesso.getSelectedRow(), 9);
					Compras = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					tableAcesso.setValueAt(false, tableAcesso.getSelectedRow(), 9);
					Compras = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(false);
				}
			}
		});
		chckbxCompras.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxCompras.setBounds(515, 440, 109, 23);
		contentPane.add(chckbxCompras);

		textFieldpassword = new JTextField();
		textFieldpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableAcesso.setValueAt(textFieldpassword.getText(), tableAcesso.getSelectedRow(), 2);
			}
		});
		textFieldpassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldpassword.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldpassword.setBounds(63, 400, 150, 20);
		contentPane.add(textFieldpassword);
		textFieldpassword.setColumns(10);

		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvar.setIcon(new ImageIcon(LiberarAcesso.class.getResource("/image/user_interface/admin.png")));
		btnSalvar.setBounds(340, 488, 120, 35);
		contentPane.add(btnSalvar);

		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.setEnabled(false);
		btnAtualizar
				.setIcon(new ImageIcon(LiberarAcesso.class.getResource("/image/user_interface/desktop_package.png")));
		btnAtualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAtualizar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAtualizar.setBounds(633, 390, 129, 35);
		contentPane.add(btnAtualizar);

		JLabel labelNome = new JLabel("NOME");
		labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNome.setBounds(10, 360, 46, 20);
		contentPane.add(labelNome);

		JLabel labelNBM = new JLabel("N\u00BABM");
		labelNBM.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNBM.setBounds(10, 440, 46, 20);
		contentPane.add(labelNBM);

		textPaneNBM = new JTextPane();
		textPaneNBM.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPaneNBM.setBorder(null);
		textPaneNBM.setEditable(false);
		textPaneNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		textPaneNBM.setBounds(63, 440, 150, 20);
		contentPane.add(textPaneNBM);
		
		JTextPane textPaneNome = new JTextPane();
		textPaneNome.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPaneNome.setBorder(null);
		textPaneNome.setEditable(false);
		textPaneNome.setBounds(63, 350, 150, 40);
		contentPane.add(textPaneNome);

		tableAcesso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				textFieldpassword.setText((String) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 2));
				textPaneNBM.setText((String) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 1));
				textPaneNome.setText((String) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 0));
				btnAtualizar.setEnabled(true);
				chckbxLiberarAcesso.setEnabled(true);
				chckbxAlmoxAdmin.setEnabled(true);
				chckbxAlmoxEdicao.setEnabled(true);
				chckbxAlmoxHist.setEnabled(true);
				chckbxOficina.setEnabled(true);
				chckbxGerencia.setEnabled(true);
				chckbxCompras.setEnabled(true);

				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 3) == true) {
					chckbxLiberarAcesso.setSelected(true);
					LiberarAcesso = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxLiberarAcesso.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 4) == true) {
					chckbxAlmoxHist.setSelected(true);
					AlmoxHist = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxAlmoxHist.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 5) == true) {
					chckbxAlmoxEdicao.setSelected(true);
					AlmoxEdicao = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxAlmoxEdicao.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 6) == true) {
					chckbxAlmoxAdmin.setSelected(true);
					AlmoxAdmin = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxAlmoxAdmin.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 7) == true) {
					chckbxOficina.setSelected(true);
					Oficina = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxOficina.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 8) == true) {
					chckbxGerencia.setSelected(true);
					Gerencia = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxGerencia.setSelected(false);
				}
				if ((boolean) tableAcesso.getValueAt(tableAcesso.getSelectedRow(), 9) == true) {
					chckbxCompras.setSelected(true);
					Compras = loginDao.findByNBM(textPaneNBM.getText()).setLiberarAcesso(true);
				} else {
					chckbxCompras.setSelected(false);
				}

			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Senha = textFieldpassword.getText() ;
				switch(JOptionPane.showConfirmDialog(null, "DESEJA SALVAR AS ALTERAÇÕES?","AVISO",JOptionPane.YES_NO_OPTION)) {
					case 0:
						loginDao.update(LiberarAcesso, AlmoxHist, AlmoxEdicao, AlmoxAdmin, Oficina, Gerencia, Compras, Senha, loginDao.findByNBM(textPaneNBM.getText()));
						btnAtualizar.setEnabled(false);
						chckbxLiberarAcesso.setEnabled(false);
						chckbxAlmoxAdmin.setEnabled(false);
						chckbxAlmoxEdicao.setEnabled(false);
						chckbxAlmoxHist.setEnabled(false);
						chckbxOficina.setEnabled(false);
						chckbxGerencia.setEnabled(false);
						chckbxCompras.setEnabled(false);
						LiberarAcesso = (false);
						AlmoxHist = (false);
						AlmoxEdicao = (false);
						AlmoxAdmin = (false);
						Oficina = (false);
						Gerencia = (false);
						Compras = (false);
						Senha = null;
						JOptionPane.showMessageDialog(null, "SENHA E ACESSOS DO USUÁRIO ATUALIZADOS", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					break;
					case 1:
						JOptionPane.showMessageDialog(null, "NENHUM DADO ALTERADO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				
			}
		});

	}
}
