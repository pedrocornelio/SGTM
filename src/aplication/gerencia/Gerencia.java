/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.gerencia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import aplication.Principal;
import dao.DaoFactory;
import dao.HistoricoEdicaoViaturaDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import utilities.Ajuda;

public class Gerencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHistoricoEdicaoViatura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gerencia frame = new Gerencia();
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
	public Gerencia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gerencia.class.getResource("/image/user_interface/manager.png")));
		setTitle("GERENCIA");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		HistoricoEdicaoViaturaDao historicoEdicaoViaturaDao = DaoFactory.createHistoricoEdicaoViaturaDao();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelHistoricoEdicaoViatura = new JPanel();
		tabbedPane.addTab("HIST\u00D3RICO EDI\u00C7\u00C3O DE VIATURA", null, panelHistoricoEdicaoViatura, "INFORMA\u00C7\u00D5ES DE ALTERA\u00C7\u00D5ES REALIZADAS NOS DADOS DAS VIATURAS");
		
		JLabel lblInformaesDeEdio = new JLabel("INFORMA\u00C7\u00D5ES DE EDI\u00C7\u00C3O DE DADOS DA VIATURA");
		lblInformaesDeEdio.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon(Gerencia.class.getResource("/image/user_interface/refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelHistoricoEdicaoViatura = new DefaultTableModel();
				tableHistoricoEdicaoViatura.setModel(historicoEdicaoViaturaDao.tableHistoricoEdicaoViatura(modelHistoricoEdicaoViatura));
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setMinWidth(68);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setPreferredWidth(70);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setMaxWidth(72);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setMinWidth(60);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setPreferredWidth(60);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setMaxWidth(65);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setMinWidth(60);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setPreferredWidth(70);
				tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setMaxWidth(85);
			}
		});
		
		JButton print = new JButton("");
		print.setIcon(new ImageIcon(Gerencia.class.getResource("/image/user_interface/pdf.png")));
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableHistoricoEdicaoViatura.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});
		print.setAlignmentX(1.0f);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		//TABELA DE HISTÓRICO DE EDIÇÃO DE VIATURA
		tableHistoricoEdicaoViatura = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableHistoricoEdicaoViatura, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableHistoricoEdicaoViatura.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(tableHistoricoEdicaoViatura);
		DefaultTableModel modelHistoricoEdicaoViatura = new DefaultTableModel();
		tableHistoricoEdicaoViatura.setModel(historicoEdicaoViaturaDao.tableHistoricoEdicaoViatura(modelHistoricoEdicaoViatura));
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setMinWidth(68);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(0).setMaxWidth(72);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setMinWidth(60);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(2).setMaxWidth(65);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setMinWidth(60);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableHistoricoEdicaoViatura.getColumnModel().getColumn(4).setMaxWidth(85);
		
		GroupLayout gl_panelHistoricoEdicaoViatura = new GroupLayout(panelHistoricoEdicaoViatura);
		gl_panelHistoricoEdicaoViatura.setHorizontalGroup(
			gl_panelHistoricoEdicaoViatura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistoricoEdicaoViatura.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelHistoricoEdicaoViatura.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistoricoEdicaoViatura.createSequentialGroup()
							.addComponent(lblInformaesDeEdio, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
					.addGap(12))
		);
		gl_panelHistoricoEdicaoViatura.setVerticalGroup(
			gl_panelHistoricoEdicaoViatura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHistoricoEdicaoViatura.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelHistoricoEdicaoViatura.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHistoricoEdicaoViatura.createSequentialGroup()
							.addGap(5)
							.addComponent(lblInformaesDeEdio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(print, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
					.addGap(12))
		);
		panelHistoricoEdicaoViatura.setLayout(gl_panelHistoricoEdicaoViatura);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
					.addGap(7))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("VIATURA");
		mnNewMenu.setFont(new Font("Arial Black", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAdicaoVitura = new JMenuItem("ADI\u00C7\u00C3O DE VIATURA");
		mntmAdicaoVitura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		mntmAdicaoVitura.setToolTipText("JANELA PARA ADI\u00C7\u00C3O DE VIATRUAS DO CBMMG");
		mntmAdicaoVitura.setIcon(new ImageIcon(Gerencia.class.getResource("/image/user_interface/manager_vtr.png")));
		mntmAdicaoVitura.setFont(new Font("Arial", Font.BOLD, 14));
		mntmAdicaoVitura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarViatura exibir = new AdicionarViatura();
				exibir.setVisible(true);
			}
		});
		mnNewMenu.add(mntmAdicaoVitura);
		
		JMenuItem mntmEdicaoViatura = new JMenuItem("EDI\u00C7\u00C3O DE VIATURA");
		mntmEdicaoViatura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmEdicaoViatura.setIcon(new ImageIcon(Gerencia.class.getResource("/image/user_interface/manager_vtr_edit.png")));
		mntmEdicaoViatura.setToolTipText("JANELA PARA EDI\u00C7\u00C3O DOS DADOS DE VIATRUAS DO CBMMG");
		mntmEdicaoViatura.setFont(new Font("Arial", Font.BOLD, 14));
		mntmEdicaoViatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarViatura exibir = new EditarViatura();
				exibir.setVisible(true);
			}
		});
		mnNewMenu.add(mntmEdicaoViatura);
		
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
		mntmAjuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Ajuda exibir = new Ajuda();
				exibir.setVisible(true);
			}
		});
		
	}
}
