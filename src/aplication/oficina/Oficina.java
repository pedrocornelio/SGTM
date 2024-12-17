/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.oficina;

import static javax.swing.KeyStroke.getKeyStroke;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import utilities.Ajuda;

public class Oficina extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHistoricoVTR;
	private JTable tableHistAlta;
	private JTable tablePatio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Oficina frame = new Oficina();
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
	public Oficina() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Oficina.class.getResource("/image/gear.png")));
		setTitle("OFICINA");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setVisible(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu mnViatura = new JMenu("VIATURA");
		mnViatura.setVisible(false);
		mnViatura.setToolTipText("JANELA DE LAN\u00C7AMENTO DE MANUTEN\u00C7\u00C3O");
		mnViatura.setFont(new Font("Arial Black", Font.PLAIN, 12));
		menuBar.add(mnViatura);
		
		JMenuItem mntmOrdemManutencao = new JMenuItem("ORDEM DE MANUTEN\u00C7\u00C3O");
		mntmOrdemManutencao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemManutencao exibir = new OrdemManutencao();
				exibir.setVisible(true);
			}
		});
		mntmOrdemManutencao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		mntmOrdemManutencao.setIcon(new ImageIcon(Oficina.class.getResource("/image/maintenance_store.png")));
		mntmOrdemManutencao.setFont(new Font("Arial", Font.BOLD, 14));
		mnViatura.add(mntmOrdemManutencao);
		
		JMenu mnPecas = new JMenu("PE\u00C7AS");
		mnPecas.setFont(new Font("Arial Black", Font.PLAIN, 12));
		menuBar.add(mnPecas);
		
		JMenuItem mntmRequisicao = new JMenuItem("REQUISI\u00C7\u00C2O");
		mntmRequisicao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		mntmRequisicao.setToolTipText("JANELA DE SOLICITA\u00C7\u00C3O DE PE\u00C7AS PARA MANUTEN\u00C7\u00C3O");
		mntmRequisicao.setIcon(new ImageIcon(Oficina.class.getResource("/image/requisition_packaeg.png")));
		mntmRequisicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Requisitar exibir = null;
				try {
					exibir = new Requisitar();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		mntmRequisicao.setFont(new Font("Arial", Font.BOLD, 14));
		mnPecas.add(mntmRequisicao);
		
		JMenuItem mntmExcluirRequisio = new JMenuItem("EXCLUR REQUISI\u00C7\u00C2O");
		mntmExcluirRequisio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmExcluirRequisio.setIcon(new ImageIcon(Oficina.class.getResource("/image/requisition_edition.png")));
		mntmExcluirRequisio.setToolTipText("JANELA PARA EXCLUS\u00C3O DE REQUISI\u00C7\u00C3O DE PE\u00C7AS");
		mntmExcluirRequisio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcluirRequisitar exibir = null;
				try {
					exibir = new ExcluirRequisitar();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		mntmExcluirRequisio.setFont(new Font("Arial", Font.BOLD, 14));
		mnPecas.add(mntmExcluirRequisio);
		
		JMenu mnInfo = new JMenu("INFO");
		mnInfo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		menuBar.add(mnInfo);
		
		JMenuItem mntmCredit = new JMenuItem("CR\u00C9DITOS");
		mntmCredit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCredit.setIcon(new ImageIcon(Oficina.class.getResource("/image/credit_team.png")));
		mntmCredit.setToolTipText("JANELA COM OS CR\u00C9DITOS DE TODOS OS ENVOLVIDOS NO PROJETO");
		mntmCredit.setFont(new Font("Arial", Font.BOLD, 14));
		mnInfo.add(mntmCredit);
		
		JMenuItem mntmAjuda = new JMenuItem("AJUDA");
		mntmAjuda.setIcon(new ImageIcon(Oficina.class.getResource("/image/help.png")));
		mntmAjuda.setAccelerator(getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAjuda.setToolTipText("JANELA DE AJUDA");
		mntmAjuda.setFont(new Font("Arial", Font.BOLD, 14));
		mnInfo.add(mntmAjuda);
		mntmAjuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Ajuda exibir = new Ajuda();
				exibir.setVisible(true);
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		
		JPanel panelPatio = new JPanel();
		panelPatio.setFont(new Font("Arial", Font.PLAIN, 12));
		tabbedPane.addTab("P\u00C1TIO", (Icon) null, panelPatio, "INFORMA\u00C7\u00D5ES DAS VIATURAS NO PAT\u00CDO DO CSM");
		tabbedPane.setEnabledAt(0, true);
		
		JLabel lblInformaesDasViaturas = new JLabel("INFORMA\u00C7\u00D5ES DAS VIATURAS NO P\u00C1TIO");
		lblInformaesDasViaturas.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		tablePatio = new JTable();
		tablePatio.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_2.setViewportView(tablePatio);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon(Oficina.class.getResource("/image/refresh.png")));
		
		JButton print = new JButton("");
		print.setIcon(new ImageIcon(Oficina.class.getResource("/image/pdf.png")));
		print.setAlignmentX(1.0f);
		GroupLayout gl_panelPatio = new GroupLayout(panelPatio);
		gl_panelPatio.setHorizontalGroup(
			gl_panelPatio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPatio.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelPatio.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelPatio.createSequentialGroup()
							.addComponent(lblInformaesDasViaturas, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
					.addGap(12))
		);
		gl_panelPatio.setVerticalGroup(
			gl_panelPatio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPatio.createSequentialGroup()
					.addGroup(gl_panelPatio.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPatio.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_panelPatio.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPatio.createSequentialGroup()
									.addGap(5)
									.addComponent(lblInformaesDasViaturas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelPatio.createSequentialGroup()
							.addGap(10)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
					.addGap(12))
		);
		panelPatio.setLayout(gl_panelPatio);

		JPanel pecas = new JPanel();
		pecas.setVisible(false);
		pecas.setFont(new Font("Arial", Font.PLAIN, 12));
		tabbedPane.addTab("PEÇAS", null, pecas, null);
		pecas.setLayout(null);

		JPanel panelHistoricoVTR = new JPanel();
		panelHistoricoVTR.setVisible(false);
		tabbedPane.addTab("HIST\u00D3RICO VIATURA", null, panelHistoricoVTR, "INFORMA\u00C7\u00D5ES DE ENTRADA E SA\u00CDDA DE VTR");
		panelHistoricoVTR.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 49, 607, 397);
		panelHistoricoVTR.add(scrollPane);

		tableHistoricoVTR = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableHistoricoVTR, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableHistoricoVTR.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableHistoricoVTR);
		tableHistoricoVTR.setFont(new Font("Arial", Font.PLAIN, 12));
		tableHistoricoVTR.setModel(new DefaultTableModel(new Object[][] {
				{ null, "12/12/2021", "AO SE DAR DUPLO CLIQUE", null, null },
				{ null, null, "NA OS/PE\u00C7A, TER\u00C1 O HIST\u00D3RICO DA OS", null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "OS N\u00BA", "DATA", "PE\u00C7A", "SERVI\u00C7O", "KM" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableHistoricoVTR.getColumnModel().getColumn(0).setResizable(false);
		tableHistoricoVTR.getColumnModel().getColumn(0).setPreferredWidth(55);
		tableHistoricoVTR.getColumnModel().getColumn(0).setMinWidth(24);
		tableHistoricoVTR.getColumnModel().getColumn(0).setMaxWidth(55);
		tableHistoricoVTR.getColumnModel().getColumn(1).setPreferredWidth(65);
		tableHistoricoVTR.getColumnModel().getColumn(1).setMinWidth(24);
		tableHistoricoVTR.getColumnModel().getColumn(1).setMaxWidth(65);
		tableHistoricoVTR.getColumnModel().getColumn(2).setPreferredWidth(249);
		tableHistoricoVTR.getColumnModel().getColumn(2).setMinWidth(24);
		tableHistoricoVTR.getColumnModel().getColumn(3).setPreferredWidth(106);
		tableHistoricoVTR.getColumnModel().getColumn(3).setMinWidth(24);
		tableHistoricoVTR.getColumnModel().getColumn(4).setMinWidth(24);
		
		
		JLabel lblInformacoesHistricoBaixa = new JLabel("INFORMA\u00C7\u00D5ES DE HIST\u00D3RICO DE BAIXA");
		lblInformacoesHistricoBaixa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInformacoesHistricoBaixa.setBounds(12, 12, 283, 25);
		panelHistoricoVTR.add(lblInformacoesHistricoBaixa);
		
		JButton btnRefresh_1 = new JButton("");
		btnRefresh_1.setIcon(new ImageIcon(Oficina.class.getResource("/image/refresh.png")));
		btnRefresh_1.setBounds(303, 7, 35, 35);
		panelHistoricoVTR.add(btnRefresh_1);
		
		JButton print_1 = new JButton("");
		print_1.setIcon(new ImageIcon(Oficina.class.getResource("/image/pdf.png")));
		print_1.setAlignmentX(1.0f);
		print_1.setBounds(589, 10, 28, 28);
		panelHistoricoVTR.add(print_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE));
		
		JPanel panelHistoricoAlta = new JPanel();
		tabbedPane.addTab("HIST\u00D3RICO DE ALTA", null, panelHistoricoAlta, null);
		panelHistoricoAlta.setLayout(null);
		
		JLabel lblInformacoesHistricoAlta = new JLabel("INFORMA\u00C7\u00D5ES DE HIST\u00D3RICO DE ALTA");
		lblInformacoesHistricoAlta.setBounds(12, 12, 283, 25);
		lblInformacoesHistricoAlta.setFont(new Font("Arial", Font.PLAIN, 13));
		panelHistoricoAlta.add(lblInformacoesHistricoAlta);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 49, 607, 397);
		panelHistoricoAlta.add(scrollPane_1);
		
		tableHistAlta = new JTable();
		scrollPane_1.setViewportView(tableHistAlta);
		
		JButton btnRefresh_1_1 = new JButton("");
		btnRefresh_1_1.setIcon(new ImageIcon(Oficina.class.getResource("/image/refresh.png")));
		btnRefresh_1_1.setBounds(303, 7, 35, 35);
		panelHistoricoAlta.add(btnRefresh_1_1);
		
		JButton print_1_1 = new JButton("");
		print_1_1.setIcon(new ImageIcon(Oficina.class.getResource("/image/pdf.png")));
		print_1_1.setAlignmentX(1.0f);
		print_1_1.setBounds(589, 10, 28, 28);
		panelHistoricoAlta.add(print_1_1);
		contentPane.setLayout(gl_contentPane);
	}
}
