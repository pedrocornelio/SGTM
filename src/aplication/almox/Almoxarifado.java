/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.almox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import aplication.Principal;
import dao.DaoFactory;
import dao.HistoricoProdutoEntradaDao;
import dao.HistoricoProdutoSaidaDao;
import dao.LoginDao;
import dao.ProdutoDao;
import dao.RequisicaoDao;
import entities.Login;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import utilities.Ajuda;
import utilities.Relogio;
import utilities.TableCellRendererColor;

public class Almoxarifado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Login login = null;
	static JTable tableEstoque;
	static JTable tableHistSaida;
	static JTable tableHistEntrada;
	private JTextField textFieldUsuLog;
	static JButton btnRefresh;
	static JTable tableHistEdicaoProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Almoxarifado frame = new Almoxarifado();
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
	@SuppressWarnings({ "deprecation" })
	public Almoxarifado() throws ParseException {
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Almoxarifado.class.getResource("/image/user_interface/warehouse.png")));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("ALMOXARIFADO");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 640);
		setLocationRelativeTo(null);

		LoginDao loginDao = DaoFactory.createLoginDao();
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();
		HistoricoProdutoEntradaDao historicoProdutoEntradaDao = DaoFactory.createHistoricoProdutoEntradaDao() ;
		HistoricoProdutoSaidaDao historicoProdutoSaidaDao = DaoFactory.createHistoricoProdutoSaidaDao() ;

		String nBM = Principal.textFieldUser.getText();
		String senha = Principal.passwordField.getText();
		login = loginDao.findByNBMSenha(nBM, senha);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnAlmox = new JMenu("ALMOXARIFADO");
		mnAlmox.setActionCommand("ALMOXARIFADO");
		mnAlmox.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnAlmox);

		JMenuItem mntmEntrada = new JMenuItem("ENTRADA DE ITEM EXISTENTE");
		mntmEntrada.setToolTipText("JANELA DE ENTRADA DE ITENS NO ALMOXARIFADO");
		mntmEntrada.setHorizontalAlignment(SwingConstants.LEFT);
		mntmEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		mntmEntrada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmEntrada.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/insercion_car.png")));
		mntmEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				Entrada exibir = null;
				if (login.getAlmoxAdmin() == true | login.getAlmoxEdicao() == true) {
					try {
						exibir = new Entrada();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});
		mnAlmox.add(mntmEntrada);

		JMenuItem mntmSaida = new JMenuItem("SA\u00CDDA DE ITEM");
		mntmSaida.setToolTipText("JANELA DE SA\u00CDDA DE ITENS DO ALMOXARIFADO");
		mntmSaida.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSaida.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/requisition_car.png")));
		mntmSaida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mntmSaida.setFont(new Font("Arial", Font.BOLD, 14));
		mntmSaida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				Saida exibir = null;
				if (login.getAlmoxAdmin() == true | login.getAlmoxEdicao() == true) {
					try {
						exibir = new Saida();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});
		
		JMenuItem mntmRequisicaoPendente = new JMenuItem("REQUISI\u00C7\u00C3O PENDENTE");
		mntmRequisicaoPendente.setToolTipText("JANELA DE REQUISIÇÕES PENDENTES DO ALMOXARIFADO");
		mntmRequisicaoPendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				RequisicaoPendente exibir = null;
				if (login.getAlmoxAdmin() == true | login.getAlmoxEdicao() == true) {
					exibir = new RequisicaoPendente();
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});
		mntmRequisicaoPendente.setFont(new Font("Arial", Font.BOLD, 14));
		mntmRequisicaoPendente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		mntmRequisicaoPendente.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/requisition_packaeg.png")));
		mnAlmox.add(mntmRequisicaoPendente);
		mnAlmox.add(mntmSaida);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel panelEstoque = new JPanel();
		panelEstoque.setToolTipText("");
		tabbedPane.addTab("ESTOQUE", null, panelEstoque, "INFORMA\u00C7\u00D5ES DOS ITENS NO ALMOXARIFADO");

		JScrollPane scrollPane = new JScrollPane();
		
		//TABELA ESTOQUE
		tableEstoque = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableEstoque, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableEstoque.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(tableEstoque);
		DefaultTableModel modelEstoque = new DefaultTableModel();
		tableEstoque.setModel(produtoDao.tableEstoque(modelEstoque));
		tableEstoque.getColumnModel().getColumn(0).setMinWidth(40);
		tableEstoque.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableEstoque.getColumnModel().getColumn(0).setMaxWidth(45);
		tableEstoque.getColumnModel().getColumn(1).setMinWidth(35);
		tableEstoque.getColumnModel().getColumn(1).setPreferredWidth(45);
		tableEstoque.getColumnModel().getColumn(1).setMaxWidth(50);
		tableEstoque.getColumnModel().getColumn(7).setMinWidth(35);
		tableEstoque.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableEstoque.getColumnModel().getColumn(7).setMaxWidth(45);
		tableEstoque.getColumnModel().getColumn(8).setMinWidth(20);
		tableEstoque.getColumnModel().getColumn(8).setPreferredWidth(20);
		tableEstoque.getColumnModel().getColumn(8).setMaxWidth(25);
		tableEstoque.getColumnModel().getColumn(10).setMinWidth(0);
		tableEstoque.getColumnModel().getColumn(10).setPreferredWidth(0);
		tableEstoque.getColumnModel().getColumn(10).setMaxWidth(0);
		
		tableEstoque.setDefaultRenderer(Object.class, new TableCellRendererColor());

		JButton print = new JButton("");
		print.setAlignmentX(Component.RIGHT_ALIGNMENT);
		print.setIcon(new ImageIcon(GestaoQuantidade.class.getResource("/image/user_interface/pdf.png")));
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableEstoque.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});

		JLabel lblNewLabel = new JLabel("INFORMA\u00C7\u00D5ES DE ESTOQUE DO ALMOXARIFADO");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel modelEstoque = new DefaultTableModel();
				tableEstoque.setModel(produtoDao.tableEstoque(modelEstoque));
				tableEstoque.getColumnModel().getColumn(0).setMinWidth(40);
				tableEstoque.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableEstoque.getColumnModel().getColumn(0).setMaxWidth(45);
				tableEstoque.getColumnModel().getColumn(1).setMinWidth(35);
				tableEstoque.getColumnModel().getColumn(1).setPreferredWidth(45);
				tableEstoque.getColumnModel().getColumn(1).setMaxWidth(50);
				tableEstoque.getColumnModel().getColumn(7).setMinWidth(35);
				tableEstoque.getColumnModel().getColumn(7).setPreferredWidth(40);
				tableEstoque.getColumnModel().getColumn(7).setMaxWidth(45);
				tableEstoque.getColumnModel().getColumn(8).setMinWidth(20);
				tableEstoque.getColumnModel().getColumn(8).setPreferredWidth(20);
				tableEstoque.getColumnModel().getColumn(8).setMaxWidth(25);
				tableEstoque.getColumnModel().getColumn(10).setMinWidth(0);
				tableEstoque.getColumnModel().getColumn(10).setPreferredWidth(0);
				tableEstoque.getColumnModel().getColumn(10).setMaxWidth(0);
				DefaultTableModel modelEntrada = new DefaultTableModel();
				tableHistEntrada.setModel(historicoProdutoEntradaDao.tableHistoricoProdutoEntrada(modelEntrada));
				tableHistEntrada.getColumnModel().getColumn(0).setMinWidth(68);
				tableHistEntrada.getColumnModel().getColumn(0).setPreferredWidth(70);
				tableHistEntrada.getColumnModel().getColumn(0).setMaxWidth(72);
				tableHistEntrada.getColumnModel().getColumn(2).setMinWidth(60);
				tableHistEntrada.getColumnModel().getColumn(2).setPreferredWidth(60);
				tableHistEntrada.getColumnModel().getColumn(2).setMaxWidth(60);
				tableHistEntrada.getColumnModel().getColumn(3).setMinWidth(35);
				tableHistEntrada.getColumnModel().getColumn(3).setPreferredWidth(40);
				tableHistEntrada.getColumnModel().getColumn(3).setMaxWidth(45);
				tableHistEntrada.getColumnModel().getColumn(9).setMinWidth(35);
				tableHistEntrada.getColumnModel().getColumn(9).setPreferredWidth(40);
				tableHistEntrada.getColumnModel().getColumn(9).setMaxWidth(45);
				DefaultTableModel modelSaida = new DefaultTableModel();
				tableHistSaida.setModel(historicoProdutoSaidaDao.tableHistoricoProdutoSaida(modelSaida));
				tableHistSaida.getColumnModel().getColumn(0).setMinWidth(30);
				tableHistSaida.getColumnModel().getColumn(0).setPreferredWidth(35);
				tableHistSaida.getColumnModel().getColumn(0).setMaxWidth(45);
				tableHistSaida.getColumnModel().getColumn(1).setMinWidth(68);
				tableHistSaida.getColumnModel().getColumn(1).setPreferredWidth(70);
				tableHistSaida.getColumnModel().getColumn(1).setMaxWidth(72);
				tableHistSaida.getColumnModel().getColumn(3).setMinWidth(60);
				tableHistSaida.getColumnModel().getColumn(3).setPreferredWidth(60);
				tableHistSaida.getColumnModel().getColumn(3).setMaxWidth(65);
				tableHistSaida.getColumnModel().getColumn(5).setMinWidth(60);
				tableHistSaida.getColumnModel().getColumn(5).setPreferredWidth(60);
				tableHistSaida.getColumnModel().getColumn(5).setMaxWidth(65);
				tableHistSaida.getColumnModel().getColumn(10).setMinWidth(35);
				tableHistSaida.getColumnModel().getColumn(10).setPreferredWidth(40);
				tableHistSaida.getColumnModel().getColumn(10).setMaxWidth(45);
				tableHistSaida.getColumnModel().getColumn(11).setMinWidth(20);
				tableHistSaida.getColumnModel().getColumn(11).setPreferredWidth(20);
				tableHistSaida.getColumnModel().getColumn(11).setMaxWidth(25);
			}
		});
		btnRefresh.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/refresh.png")));
		GroupLayout gl_panelEstoque = new GroupLayout(panelEstoque);
		gl_panelEstoque.setHorizontalGroup(
			gl_panelEstoque.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEstoque.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelEstoque.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEstoque.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
					.addGap(9))
		);
		gl_panelEstoque.setVerticalGroup(
			gl_panelEstoque.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEstoque.createSequentialGroup()
					.addGroup(gl_panelEstoque.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEstoque.createSequentialGroup()
							.addGap(10)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelEstoque.createSequentialGroup()
							.addGap(15)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelEstoque.createSequentialGroup()
							.addGap(15)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addGap(11))
		);
		panelEstoque.setLayout(gl_panelEstoque);

		JPanel panelHistSaida = new JPanel();
		tabbedPane.addTab("HIST\u00D3RICO DE SA\u00CDDA", null, panelHistSaida,
				"INFORMA\u00C7\u00D5ES DE REQUISI\u00C7\u00D5ES DE ITENS ENTREGUES");

		JScrollPane scrollPane_1 = new JScrollPane();
		
		//TABELA HISTORICO DE SAIDA
		tableHistSaida = new JTable();
		TableFilterHeader filterHeaderHistSaida = new TableFilterHeader(tableHistSaida, AutoChoices.ENABLED);
		filterHeaderHistSaida.setPosition(Position.TOP);
		filterHeaderHistSaida.setBackground(Color.white);
		DefaultTableModel modelSaida = new DefaultTableModel();
		tableHistSaida.setModel(historicoProdutoSaidaDao.tableHistoricoProdutoSaida(modelSaida));
		tableHistSaida.getColumnModel().getColumn(0).setMinWidth(30);
		tableHistSaida.getColumnModel().getColumn(0).setPreferredWidth(35);
		tableHistSaida.getColumnModel().getColumn(0).setMaxWidth(45);
		tableHistSaida.getColumnModel().getColumn(1).setMinWidth(68);
		tableHistSaida.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableHistSaida.getColumnModel().getColumn(1).setMaxWidth(72);
		tableHistSaida.getColumnModel().getColumn(3).setMinWidth(60);
		tableHistSaida.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableHistSaida.getColumnModel().getColumn(3).setMaxWidth(65);
		tableHistSaida.getColumnModel().getColumn(5).setMinWidth(60);
		tableHistSaida.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableHistSaida.getColumnModel().getColumn(5).setMaxWidth(65);
		tableHistSaida.getColumnModel().getColumn(10).setMinWidth(35);
		tableHistSaida.getColumnModel().getColumn(10).setPreferredWidth(40);
		tableHistSaida.getColumnModel().getColumn(10).setMaxWidth(45);
		tableHistSaida.getColumnModel().getColumn(11).setMinWidth(20);
		tableHistSaida.getColumnModel().getColumn(11).setPreferredWidth(20);
		tableHistSaida.getColumnModel().getColumn(11).setMaxWidth(25);
		scrollPane_1.setViewportView(tableHistSaida);

		JButton print_1 = new JButton("");
		print_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		print_1.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/pdf.png")));
		print_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableHistSaida.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});
		
		JLabel lblInformaesDeHistrico = new JLabel("INFORMA\u00C7\u00D5ES DE HIST\u00D3RICO DE SA\u00CDDA");
		lblInformaesDeHistrico.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnRefresh_1 = new JButton("");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefresh.doClick();
			}
		});
		btnRefresh_1.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/refresh.png")));
		GroupLayout gl_panelHistSaida = new GroupLayout(panelHistSaida);
		gl_panelHistSaida.setHorizontalGroup(
			gl_panelHistSaida.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistSaida.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelHistSaida.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistSaida.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInformaesDeHistrico, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
							.addComponent(print_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
					.addGap(9))
		);
		gl_panelHistSaida.setVerticalGroup(
			gl_panelHistSaida.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistSaida.createSequentialGroup()
					.addGroup(gl_panelHistSaida.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistSaida.createSequentialGroup()
							.addGap(10)
							.addComponent(btnRefresh_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHistSaida.createSequentialGroup()
							.addGap(13)
							.addComponent(print_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHistSaida.createSequentialGroup()
							.addGap(15)
							.addComponent(lblInformaesDeHistrico, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(6))
		);
		panelHistSaida.setLayout(gl_panelHistSaida);

		JPanel panelHistEntrada = new JPanel();
		tabbedPane.addTab("HIST\u00D3RICO DE ENTRADA", null, panelHistEntrada,"INFORMA\u00C7\u00D5ES DE INSER\u00C7\u00D5ES ANTERIORES");

		JScrollPane scrollPane_2 = new JScrollPane();
		
		//TABELA HISTORICO DE ENTRADA
		tableHistEntrada = new JTable();
		TableFilterHeader filterHeaderHistEntrada = new TableFilterHeader(tableHistEntrada, AutoChoices.ENABLED);
		filterHeaderHistEntrada.setPosition(Position.TOP);
		filterHeaderHistEntrada.setBackground(Color.white);
		tableHistEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_2.setViewportView(tableHistEntrada);
		DefaultTableModel modelEntrada = new DefaultTableModel();
		tableHistEntrada.setModel(historicoProdutoEntradaDao.tableHistoricoProdutoEntrada(modelEntrada));
		tableHistEntrada.getColumnModel().getColumn(0).setMinWidth(68);
		tableHistEntrada.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableHistEntrada.getColumnModel().getColumn(0).setMaxWidth(72);
		tableHistEntrada.getColumnModel().getColumn(2).setMinWidth(60);
		tableHistEntrada.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableHistEntrada.getColumnModel().getColumn(2).setMaxWidth(60);
		tableHistEntrada.getColumnModel().getColumn(3).setMinWidth(35);
		tableHistEntrada.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableHistEntrada.getColumnModel().getColumn(3).setMaxWidth(45);
		tableHistEntrada.getColumnModel().getColumn(9).setMinWidth(35);
		tableHistEntrada.getColumnModel().getColumn(9).setPreferredWidth(40);
		tableHistEntrada.getColumnModel().getColumn(9).setMaxWidth(45);
		

		JButton print_2 = new JButton("");
		print_2.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/pdf.png")));
		print_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableHistEntrada.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});
		
		JLabel lblInformaesDeHistrico_1 = new JLabel("INFORMA\u00C7\u00D5ES DE HIST\u00D3RICO DE ENTRADA");
		lblInformaesDeHistrico_1.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnRefresh_2 = new JButton("");
		btnRefresh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefresh.doClick();
			}
		});
		btnRefresh_2.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/refresh.png")));
		GroupLayout gl_panelHistEntrada = new GroupLayout(panelHistEntrada);
		gl_panelHistEntrada.setHorizontalGroup(
			gl_panelHistEntrada.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistEntrada.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelHistEntrada.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelHistEntrada.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInformaesDeHistrico_1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnRefresh_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
							.addComponent(print_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
					.addGap(9))
		);
		gl_panelHistEntrada.setVerticalGroup(
			gl_panelHistEntrada.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistEntrada.createSequentialGroup()
					.addGroup(gl_panelHistEntrada.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistEntrada.createSequentialGroup()
							.addGap(8)
							.addComponent(print_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHistEntrada.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelHistEntrada.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelHistEntrada.createSequentialGroup()
									.addGap(5)
									.addComponent(lblInformaesDeHistrico_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnRefresh_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addGap(6))
		);
		panelHistEntrada.setLayout(gl_panelHistEntrada);

		JLabel lblLogado = new JLabel("USU\u00C1RIO LOGADO:");
		lblLogado.setFont(new Font("Arial", Font.PLAIN, 12));
		
		//USUARIO LOGADO
		textFieldUsuLog = new JTextField();
		textFieldUsuLog.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldUsuLog.setEditable(false);
		textFieldUsuLog.setColumns(10);
		textFieldUsuLog.setText(login.getNome());

		JMenu mnGestao = new JMenu("GEST\u00C3O");
		mnGestao.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnGestao);

		JMenuItem mntmNovoItem = new JMenuItem(" ENTRADA DE ITEM NOVO");
		mntmNovoItem.setToolTipText("MENU DE ENTRADA DE NOVOS ITENS NO ALMOXARIFADO");
		mntmNovoItem.setFont(new Font("Arial", Font.BOLD, 14));
		mntmNovoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
		mntmNovoItem.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/insercion_warehouse.png")));
		mnGestao.add(mntmNovoItem);
		mntmNovoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				EntradaItemNovo exibir = null;
				if (login.getAlmoxAdmin() == true) {
					try {
						exibir = new EntradaItemNovo();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});

		JMenuItem mntmGestQuant = new JMenuItem(" GEST\u00C3O DE QUANTIDADE");
		mntmGestQuant.setToolTipText("MENU DE GEST\u00C3O DA QUANTIDADE M\u00CDNIMA DOS ITENS DO ALMOXARIFADO");
		mntmGestQuant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
		mntmGestQuant.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/max_min.png")));
		mntmGestQuant.setFont(new Font("Arial", Font.BOLD, 14));
		mnGestao.add(mntmGestQuant);
		mntmGestQuant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				GestaoQuantidade exibir = null;
				if (login.getAlmoxAdmin() == true) {
					exibir = new GestaoQuantidade();
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});

		JMenuItem mntmLocalizacao = new JMenuItem(" LOCALIZA\u00C7\u00C3O");
		mntmLocalizacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				Localizacao exibir = null;
				if (login.getAlmoxAdmin() == true) {
					exibir = new Localizacao();
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});
		mnGestao.add(mntmLocalizacao);
		mntmLocalizacao.setToolTipText("MENU DE ALTERA\u00C7\u00C3O DA LOCALIZA\u00C7\u00C3O DOS ITENS DO ALMOXARIFADO");
		mntmLocalizacao.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/position.png")));
		mntmLocalizacao.setFont(new Font("Arial", Font.BOLD, 14));
		mntmLocalizacao.setHorizontalAlignment(SwingConstants.LEFT);
		mntmLocalizacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));
		
		JMenuItem mntmEdicaoDeItem = new JMenuItem("EDI\u00C7\u00C3O DE ITEM");
		mntmEdicaoDeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent open) {
				EdicaoItem exibir = null;
				if (login.getAlmoxAdmin() == true) {
					exibir = new EdicaoItem();
					exibir.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO NÃO POSSUI PRIVILÉGIO DE ACESSO", "ACESSO NEGADO", 0);
				}
			}
		});
		mntmEdicaoDeItem.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/issue.png")));
		mntmEdicaoDeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmEdicaoDeItem.setToolTipText("MENU DE ALTERA\u00C7\u00C3O DE INFORMA\u00C7\u00D5ES DOS ITENS DO ALMOXARIFADO");
		mntmEdicaoDeItem.setHorizontalAlignment(SwingConstants.LEFT);
		mntmEdicaoDeItem.setFont(new Font("Arial", Font.BOLD, 14));
		mnGestao.add(mntmEdicaoDeItem);
		
		JLabel lblRelogio = new Relogio();
		lblRelogio.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelogio.setFont(new Font("Arial", Font.BOLD, 14));
		
		JMenu mnConsulta = new JMenu("CONSULTA");
		mnConsulta.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnConsulta);
		
		JMenuItem mntmHistoricoEntrada = new JMenuItem("HIST\u00D3RICO DE ENTRADA");
		mntmHistoricoEntrada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		mntmHistoricoEntrada.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/almox_calendar_insert.png")));
		mntmHistoricoEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoEntrada exibir = null;
				try {
					exibir = new HistoricoEntrada();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		mntmHistoricoEntrada.setToolTipText("JANELA DE CONSULTA DE ENTRADA POR PER\u00CDODO");
		mntmHistoricoEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		mnConsulta.add(mntmHistoricoEntrada);
		
		JMenuItem mntmHistoricoSaida = new JMenuItem("HIST\u00D3RICO DE SA\u00CDDA");
		mntmHistoricoSaida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_MASK));
		mntmHistoricoSaida.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/almox_calendar_requisition.png")));
		mntmHistoricoSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoSaida exibir = null;
				try {
					exibir = new HistoricoSaida();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		mntmHistoricoSaida.setToolTipText("JANELA DE CONSULTA DE SA\u00CDDA POR PER\u00CDODO");
		mntmHistoricoSaida.setFont(new Font("Arial", Font.BOLD, 14));
		mnConsulta.add(mntmHistoricoSaida);
		
		JMenu mnInfo = new JMenu("INFO");
		mnInfo.setFont(new Font("Arial Black", Font.BOLD, 12));
		menuBar.add(mnInfo);
		
		JMenuItem mntmCredit = new JMenuItem("CR\u00C9DITOS");
		mntmCredit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCredit.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/credit_team.png")));
		mntmCredit.setToolTipText("JANELA COM OS CR\u00C9DITOS DOS ENVOLVIDOS NO PROJETO");
		mntmCredit.setFont(new Font("Arial", Font.BOLD, 14));
		mnInfo.add(mntmCredit);
		
		JMenuItem mntmAjuda = new JMenuItem("AJUDA");
		mntmAjuda.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/help.png")));
		mntmAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAjuda.setToolTipText("JANELA DE AJUDA");
		mntmAjuda.setFont(new Font("Arial", Font.BOLD, 14));
		mntmAjuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Ajuda exibir = new Ajuda();
				exibir.setVisible(true);
			}
		});
		mnInfo.add(mntmAjuda);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogado)
					.addGap(4)
					.addComponent(textFieldUsuLog, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblRelogio, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(349, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblRelogio, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblLogado))
						.addComponent(textFieldUsuLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panelHistEdicaoProduto = new JPanel();
		tabbedPane.addTab("HIST\u00D3RICO EDI\u00C7\u00C3O", null, panelHistEdicaoProduto, "INFORMA\u00C7\u00D5ES DE EDI\u00C7\u00D5ES ANTERIORES");
		
		JLabel lblInformaesDeHistrico_2 = new JLabel("INFORMA\u00C7\u00D5ES DE HIST\u00D3RICO DE EDI\u00C7\u00C3O DE PRODUTO");
		lblInformaesDeHistrico_2.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnRefresh_3 = new JButton("");
		btnRefresh_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefresh.doClick();
			}
		});
		btnRefresh_3.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/refresh.png")));
		panelHistEntrada.setLayout(gl_panelHistEntrada);
		
		JButton print_3 = new JButton("");
		print_3.setIcon(new ImageIcon(Almoxarifado.class.getResource("/image/user_interface/pdf.png")));
		print_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableHistEntrada.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});

		JScrollPane scrollPane_3 = new JScrollPane();
		
		//TABELA HISTORICO DE ENTRADA
		tableHistEdicaoProduto = new JTable();
		TableFilterHeader filterHeaderHistEdicaoProduto = new TableFilterHeader(tableHistEdicaoProduto, AutoChoices.ENABLED);
		filterHeaderHistEdicaoProduto.setPosition(Position.TOP);
		filterHeaderHistEdicaoProduto.setBackground(Color.white);
		tableHistEdicaoProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_3.setViewportView(tableHistEdicaoProduto);
			
		
		GroupLayout gl_panelHistEdicaoProduto = new GroupLayout(panelHistEdicaoProduto);
		gl_panelHistEdicaoProduto.setHorizontalGroup(
			gl_panelHistEdicaoProduto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelHistEdicaoProduto.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInformaesDeHistrico_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh_3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
							.addComponent(print_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane_3))
					.addGap(9))
		);
		gl_panelHistEdicaoProduto.setVerticalGroup(
			gl_panelHistEdicaoProduto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
					.addGroup(gl_panelHistEdicaoProduto.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
							.addGap(10)
							.addComponent(btnRefresh_3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
							.addGap(15)
							.addComponent(print_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHistEdicaoProduto.createSequentialGroup()
							.addGap(15)
							.addComponent(lblInformaesDeHistrico_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addComponent(scrollPane_3)
					.addGap(6))
		);
		panelHistEdicaoProduto.setLayout(gl_panelHistEdicaoProduto);
		getContentPane().setLayout(groupLayout);
		
		//AVALIAÇÃO DE REQUISIÇÃO PENDENTE
		DefaultTableModel modelRequisicaoPendente = new DefaultTableModel();
		requisicaoDao.tableRequisicao(modelRequisicaoPendente);
		Integer k = modelRequisicaoPendente.getRowCount();
		if ( k > 0) {
			BuscarRequisicaoPendente buscarRequisicao = new BuscarRequisicaoPendente();
		buscarRequisicao.setVisible(true);
		}

	}
}
