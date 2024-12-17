/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.oficina;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.OrdemManutencaoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class OrdemManutencao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable tableOrdemManutencao;
	public static JTextField textFieldNOm;
	private JTextField textFieldData;
	public static JTextField textFieldPlaca;
	private JTextPane textPaneRelatos;
	public static JTextField textFieldBaixa;
	public static JTextField textFieldTriagem;
	private JButton btnFecharOm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemManutencao frame = new OrdemManutencao();
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
	public OrdemManutencao() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrdemManutencao.class.getResource("/image/maintenance_store.png")));
		setTitle("ORDEM MANUTEÇÃO");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "DESEJA FECHAR A JANELA SEM SALVAR AS ALTERAÇÕES", "FECHAR JANELA", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		        }
		    }
		});
		
		OrdemManutencaoDao ordemManutencaoDao = DaoFactory.createOrdemServicoDao();
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 784, 5);
		contentPane.add(separator);
		
		JLabel lblOrdemManuteno = new JLabel("DADOS DA ORDEM DE MANUTEN\u00C7\u00C3O");
		lblOrdemManuteno.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManuteno.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrdemManuteno.setAlignmentX(0.5f);
		lblOrdemManuteno.setBounds(0, 11, 784, 28);
		contentPane.add(lblOrdemManuteno);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 784, 5);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 98, 490, 410);
		contentPane.add(scrollPane);
		
		tableOrdemManutencao = new JTable();
		scrollPane.setViewportView(tableOrdemManutencao);
		tableOrdemManutencao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableOrdemManutencao.getSelectedRow();
				textFieldNOm.setText(tableOrdemManutencao.getValueAt(i, 0).toString());
				textFieldData.setText(tableOrdemManutencao.getValueAt(i, 1).toString());
				textFieldPlaca.setText(tableOrdemManutencao.getValueAt(i, 2).toString());
				textPaneRelatos.setText(tableOrdemManutencao.getValueAt(i, 3).toString());
				textFieldBaixa.setText(tableOrdemManutencao.getValueAt(i, 4).toString());
				textFieldTriagem.setText(tableOrdemManutencao.getValueAt(i, 5).toString());
				
				btnFecharOm.setEnabled(true);
				
			}
		});
		TableFilterHeader filterHeader = new TableFilterHeader(tableOrdemManutencao, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableOrdemManutencao.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelOrdemManutencao = new DefaultTableModel();
		tableOrdemManutencao.setModel(ordemManutencaoDao.tableOrdemManutencao(modelOrdemManutencao));
		tableOrdemManutencao.setModel(modelOrdemManutencao);
		tableOrdemManutencao.getColumnModel().getColumn(0).setMinWidth(60);
		tableOrdemManutencao.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableOrdemManutencao.getColumnModel().getColumn(0).setMaxWidth(95);
		tableOrdemManutencao.getColumnModel().getColumn(1).setMinWidth(60);
		tableOrdemManutencao.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableOrdemManutencao.getColumnModel().getColumn(1).setMaxWidth(95);
		
		JLabel lblOrdemManutencao = new JLabel("ORDEM DE MANUTEN\u00C7\u00C3O");
		lblOrdemManutencao.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdemManutencao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblOrdemManutencao.setBounds(20, 64, 173, 23);
		contentPane.add(lblOrdemManutencao);
		
		JButton btnCriarOm = new JButton(" CRIAR OM");
		btnCriarOm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarOrdemManutencao exibir = null;
				try {
					exibir = new CriarOrdemManutencao();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		btnCriarOm.setIcon(new ImageIcon(OrdemManutencao.class.getResource("/image/gear_maintenance.png")));
		btnCriarOm.setMargin(new Insets(2, 5, 2, 5));
		btnCriarOm.setIconTextGap(0);
		btnCriarOm.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCriarOm.setFont(new Font("Arial", Font.BOLD, 14));
		btnCriarOm.setBounds(585, 98, 150, 35);
		contentPane.add(btnCriarOm);
		
		JLabel lblNOm = new JLabel("N\u00BA OM");
		lblNOm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNOm.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNOm.setBounds(510, 148, 57, 23);
		contentPane.add(lblNOm);
		
		textFieldNOm = new JTextField();
		textFieldNOm.setEditable(false);
		textFieldNOm.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNOm.setColumns(10);
		textFieldNOm.setBounds(577, 148, 188, 23);
		contentPane.add(textFieldNOm);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Arial", Font.PLAIN, 12));
		lblData.setBounds(510, 182, 57, 23);
		contentPane.add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		textFieldData.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldData.setColumns(10);
		textFieldData.setBounds(577, 182, 188, 23);
		contentPane.add(textFieldData);
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(510, 216, 57, 23);
		contentPane.add(lblPlaca);
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.setEditable(false);
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		textFieldPlaca.setBounds(577, 216, 188, 23);
		contentPane.add(textFieldPlaca);
		
		btnFecharOm = new JButton(" FECHAR OM");
		btnFecharOm.setIcon(new ImageIcon(OrdemManutencao.class.getResource("/image/gear_maintenance.png")));
		btnFecharOm.setEnabled(false);
		btnFecharOm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FecharOrdemManutencao exibir = null;
				try {
					exibir = new FecharOrdemManutencao();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exibir.setVisible(true);			
			}
		});
		btnFecharOm.setMargin(new Insets(2, 5, 2, 5));
		btnFecharOm.setIconTextGap(0);
		btnFecharOm.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFecharOm.setFont(new Font("Arial", Font.BOLD, 14));
		btnFecharOm.setBounds(585, 473, 150, 35);
		contentPane.add(btnFecharOm);
		
		JLabel lblRelato = new JLabel("RELATO");
		lblRelato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRelato.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRelato.setBounds(510, 283, 57, 23);
		contentPane.add(lblRelato);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(577, 250, 188, 89);
		contentPane.add(scrollPane_1);
		
		textPaneRelatos = new JTextPane();
		scrollPane_1.setViewportView(textPaneRelatos);
		textPaneRelatos.setBackground(SystemColor.control);
		textPaneRelatos.setEditable(false);
		textPaneRelatos.setBorder(new LineBorder(SystemColor.inactiveCaption));
		textPaneRelatos.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblBaixa = new JLabel("BAIXA");
		lblBaixa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaixa.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBaixa.setBounds(510, 350, 57, 23);
		contentPane.add(lblBaixa);
		
		textFieldBaixa = new JTextField();
		textFieldBaixa.setEditable(false);
		textFieldBaixa.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldBaixa.setColumns(10);
		textFieldBaixa.setBounds(577, 350, 188, 23);
		contentPane.add(textFieldBaixa);
		
		JLabel lblTriagem = new JLabel("TRIAGEM");
		lblTriagem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTriagem.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTriagem.setBounds(510, 384, 57, 23);
		contentPane.add(lblTriagem);
		
		textFieldTriagem = new JTextField();
		textFieldTriagem.setEditable(false);
		textFieldTriagem.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldTriagem.setColumns(10);
		textFieldTriagem.setBounds(577, 384, 188, 23);
		contentPane.add(textFieldTriagem);
		
		JButton btnFecharOm_1 = new JButton(" SERVI\u00C7O OM");
		btnFecharOm_1.setIcon(new ImageIcon(OrdemManutencao.class.getResource("/image/om_service.png")));
		btnFecharOm_1.setMargin(new Insets(2, 5, 2, 5));
		btnFecharOm_1.setIconTextGap(0);
		btnFecharOm_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFecharOm_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnFecharOm_1.setBounds(585, 425, 150, 35);
		contentPane.add(btnFecharOm_1);
		
	}
}
