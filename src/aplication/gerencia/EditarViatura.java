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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import aplication.Principal;
import dao.DaoFactory;
import dao.HistoricoEdicaoViaturaDao;
import dao.LoginDao;
import dao.MontadoraDao;
import dao.UnidadeDao;
import dao.ViaturaDao;
import entities.HistoricoEdicaoViatura;
import entities.Login;
import entities.Viatura;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EditarViatura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableViatura;
	private JTextField textFieldPlaca;
	private JTextField textFieldModelo;
	private JTextField textFieldAno;
	private JTextField textFieldChassi;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMontadora;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxOBM;
	private JButton btnAtualizar;
	private JTextField textFieldRef;
	private static Login login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarViatura frame = new EditarViatura();
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
	public EditarViatura() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarViatura.class.getResource("/image/manager_vtr_edit.png")));
		setTitle("EDITAR VIATURA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		HistoricoEdicaoViaturaDao historicoEdicaoViaturaDao = DaoFactory.createHistoricoEdicaoViaturaDao();
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		
		JLabel lblTitulo = new JLabel("EDITAR VIATURA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tableViatura = new JTable();
		scrollPane.setViewportView(tableViatura);
		TableFilterHeader filterHeader = new TableFilterHeader(tableViatura, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableViatura.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelViatura = new DefaultTableModel();
		tableViatura.setModel(viaturaDao.tableViatura(modelViatura));
		tableViatura.getColumnModel().getColumn(0).setMinWidth(35);
		tableViatura.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableViatura.getColumnModel().getColumn(0).setMaxWidth(45);
		tableViatura.getColumnModel().getColumn(3).setMinWidth(30);
		tableViatura.getColumnModel().getColumn(3).setPreferredWidth(35);
		tableViatura.getColumnModel().getColumn(3).setMaxWidth(40);
		tableViatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableViatura.getSelectedRow();
				textFieldRef.setText(tableViatura.getValueAt(i, 0).toString());
				//SETAR A VIATURA
				Viatura viatura = new Viatura();
				viatura = viaturaDao.findByPlaca(tableViatura.getValueAt(i, 1).toString());
				textFieldPlaca.setText(viatura.getPlaca());
				textFieldModelo.setText(viatura.getModelo());
				textFieldAno.setText(viatura.getAno());
				textFieldChassi.setText(viatura.getChassi());
				comboBoxMontadora.setSelectedItem(viatura.getMontadora().toString());
				if (viatura.getUnidade().getNomeUnidade() == "") {
					comboBoxOBM.setSelectedItem("");
				} else {
					comboBoxOBM.setSelectedItem(viatura.getUnidade().getNomeUnidade());
				}
				btnAtualizar.setEnabled(true);
			}
		});
		
		JLabel lblTabelaDeViaturas = new JLabel("TABELA DE VIATURAS");
		lblTabelaDeViaturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTabelaDeViaturas.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldModelo.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldPlaca.getText().toUpperCase();
				textFieldPlaca.setText(temp);
			}
		});
		textFieldPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(1);
		
		JLabel lblDadosDaViatura = new JLabel("DADOS DA VIATURA");
		lblDadosDaViatura.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDadosDaViatura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDadosDaViatura.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldAno.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldModelo.getText().toUpperCase();
				textFieldModelo.setText(temp);
			}
		});
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(1);
		
		JLabel lblAno = new JLabel("ANO");
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldAno = new JTextField();
		textFieldAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldChassi.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldAno.getText().toUpperCase();
				textFieldAno.setText(temp);
			}
		});
		textFieldAno.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAno.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldAno.setColumns(1);
		
		JLabel lblChassi = new JLabel("CHASSI");
		lblChassi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChassi.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldChassi = new JTextField();
		textFieldChassi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxMontadora.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldChassi.getText().toUpperCase();
				textFieldChassi.setText(temp);
			}
		});
		textFieldChassi.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChassi.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldChassi.setColumns(1);
		
		JLabel lblMontadora_1 = new JLabel("MONTADORA");
		lblMontadora_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxOBM.requestFocusInWindow();
				}
			}
		});
		DefaultComboBoxModel montadoraModelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(montadoraModelcombobox);
		comboBoxMontadora.setModel(montadoraModelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setBackground(Color.WHITE);
		
		JLabel lblUnidade = new JLabel("UNIDADE");
		lblUnidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidade.setFont(new Font("Arial", Font.PLAIN, 12));
		
		comboBoxOBM = new JComboBox();
		comboBoxOBM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnAtualizar.requestFocusInWindow();
				}
			}
		});
		DefaultComboBoxModel unidadeModelComboBox = new DefaultComboBoxModel();
		unidadeDao.comboBoxUnidade(unidadeModelComboBox);
		comboBoxOBM.setModel(unidadeModelComboBox);
		comboBoxOBM.setFont(new Font("Arial", Font.BOLD, 12));
		comboBoxOBM.setBackground(Color.WHITE);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(EditarViatura.class.getResource("/image/manager_vtr_edit.png")));
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
				String nBM = Principal.textFieldUser.getText();
				login = loginDao.findByNBM(nBM);
				
				//SETAR A VIATURA
				Viatura viatura = new Viatura();
				viatura = viaturaDao.findById(Integer.parseInt(textFieldRef.getText().toString()));
				final Viatura viaturaHistorico = new Viatura(null, viatura.getPlaca(), viatura.getModelo(), viatura.getAno(), viatura.getChassi(), viatura.getMontadora(), viatura.getUnidade(), null);
				viatura.setPlaca(textFieldPlaca.getText().toString());
				viatura.setModelo(textFieldModelo.getText().toString());
				viatura.setAno(textFieldAno.getText().toString());
				viatura.setChassi(textFieldChassi.getText().toString());
				viatura.setMontadora(montadoraDao.findByMontadora(comboBoxMontadora.getSelectedItem().toString()));
				viatura.setUnidade(unidadeDao.findByUnidade(comboBoxOBM.getSelectedItem().toString()));

				//SALVAR ALTERAÇÃO NO HISTÓRICO
				Date data = new java.sql.Date(new java.util.Date().getTime());
				Time hora = new java.sql.Time(new java.util.Date().getTime());
				HistoricoEdicaoViatura historicoEdicaoViatura = new HistoricoEdicaoViatura(null, data, hora, 
						viaturaHistorico.getPlaca(), viaturaHistorico.getModelo(), viaturaHistorico.getAno(),
						viaturaHistorico.getChassi(), viaturaHistorico.getMontadora(),
						viaturaHistorico.getUnidade(), viatura, login);
				historicoEdicaoViaturaDao.insert(historicoEdicaoViatura);
				
				//ATUALIZAR VIATURA
				viaturaDao.updateViatura(viatura);
				
				JOptionPane.showMessageDialog(null, "VIATURA PLACA: "+ textFieldPlaca.getText().toString() +" EDITADA COM SUCESSO", "EDIÇÃO DE VIATURA", 1);
				
				textFieldRef.setText("");
				textFieldPlaca.setText("");
				textFieldModelo.setText("");
				textFieldAno.setText("");
				textFieldChassi.setText("");
				comboBoxMontadora.setSelectedItem("");
				comboBoxOBM.setSelectedItem("");
				btnAtualizar.setEnabled(false);
				
				DefaultTableModel modelViatura = new DefaultTableModel();
				tableViatura.setModel(viaturaDao.tableViatura(modelViatura));
				tableViatura.getColumnModel().getColumn(0).setMinWidth(35);
				tableViatura.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableViatura.getColumnModel().getColumn(0).setMaxWidth(45);
				tableViatura.getColumnModel().getColumn(3).setMinWidth(30);
				tableViatura.getColumnModel().getColumn(3).setPreferredWidth(35);
				tableViatura.getColumnModel().getColumn(3).setMaxWidth(40);
				
			}
		});
		btnAtualizar.setEnabled(false);
		btnAtualizar.setIcon(new ImageIcon(EditarViatura.class.getResource("/image/desktop_package.png")));
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblEspacador = new JLabel("");
		
		JLabel lblEspacador_1 = new JLabel("");
		
		textFieldRef = new JTextField();
		textFieldRef.setEditable(false);
		textFieldRef.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRef.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldRef.setColumns(1);
		
		JLabel lblRef = new JLabel("REF");
		lblRef.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRef.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(99)
					.addComponent(lblTabelaDeViaturas, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addGap(384))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAno, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMontadora_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
								.addGap(13)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldAno, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldChassi, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxOBM, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(28)
										.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
								.addGap(19))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDadosDaViatura, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addGap(91)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRef, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(textFieldRef, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(141)
					.addComponent(lblEspacador, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addGap(64)
					.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(149)
					.addComponent(lblEspacador_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addGap(103))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblTabelaDeViaturas, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDadosDaViatura, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRef, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(textFieldRef, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(5)
									.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(lblAno, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(lblMontadora_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(textFieldAno, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(textFieldChassi, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(comboBoxOBM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(21)
									.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEspacador, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEspacador_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(25))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
