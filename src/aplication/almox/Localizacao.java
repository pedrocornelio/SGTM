/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.almox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.LocalizacaoDao;
import dao.ProdutoDao;
import entities.Produto;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class Localizacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableLocalizacao;
	private JTextField textFieldCodCSM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Localizacao frame = new Localizacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**s
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Localizacao() {
		
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("LOCALIZA\u00C7\u00C3O");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Localizacao.class.getResource("/image/user_interface/position.png")));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		setLocationRelativeTo(null);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "DESEJA FECHAR A JANELA SEM SALVAR AS ALTERAÇÕES", "FECHAR JANELA", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		        	Almoxarifado.btnRefresh.doClick();
		        }
		    }
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		LocalizacaoDao localizacaoDao = DaoFactory.createLocalizacaoDao();

		JLabel lblNewLabel_1 = new JLabel("MUDAN\u00C7A DA LOCALIZA\u00C7\u00C3O DE ITENS");
		lblNewLabel_1.setBounds(0, 11, 634, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setAlignmentX(0.5f);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setBounds(0, 8, 634, 5);
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setBounds(0, 38, 634, 5);
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("LOCAL ATUAL");
		lblNewLabel.setBounds(25, 59, 111, 25);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton btnFechar = new JButton("  FECHAR");
		btnFechar.setBounds(254, 510, 127, 35);
		btnFechar.setIcon(new ImageIcon(Localizacao.class.getResource("/image/user_interface/position.png")));
		btnFechar.setMargin(new Insets(2, 5, 2, 5));
		btnFechar.setIconTextGap(0);
		btnFechar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//ATUALIZANDO AS TABELAS DO ESTOQUE
				Almoxarifado.btnRefresh.doClick();
				
				dispose();
			}
		});

		JLabel lblNovoLocal = new JLabel("NOVO LOCAL");
		lblNovoLocal.setBounds(178, 467, 88, 25);
		lblNovoLocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovoLocal.setFont(new Font("Arial", Font.PLAIN, 12));

		JComboBox comboBoxLocal = new JComboBox();
		comboBoxLocal.setBackground(Color.WHITE);
		comboBoxLocal.setBounds(273, 467, 90, 25);
		DefaultComboBoxModel localizacaoModelComboBox = new DefaultComboBoxModel();
		localizacaoDao.comboBoxLocalizacao(localizacaoModelComboBox);
		comboBoxLocal.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxLocal.setModel(localizacaoModelComboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 95, 609, 324);

		tableLocalizacao = new JTable();
		tableLocalizacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableFilterHeader filterHeader = new TableFilterHeader(tableLocalizacao, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		scrollPane.setViewportView(tableLocalizacao);

		DefaultTableModel model = new DefaultTableModel();

		tableLocalizacao.setModel(produtoDao.tableLocalizacao(model));
		tableLocalizacao.getColumnModel().getColumn(0).setMinWidth(40);
		tableLocalizacao.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableLocalizacao.getColumnModel().getColumn(0).setMaxWidth(45);
		tableLocalizacao.getColumnModel().getColumn(1).setMinWidth(40);
		tableLocalizacao.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableLocalizacao.getColumnModel().getColumn(1).setMaxWidth(55);
		tableLocalizacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableLocalizacao.getSelectedRow();
				textFieldCodCSM.setText(tableLocalizacao.getValueAt(i, 1).toString());
				comboBoxLocal.setSelectedItem(tableLocalizacao.getValueAt(i, 0).toString());
			}
		});

		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizar.setBounds(373, 461, 148, 35);
		btnAtualizar.setIcon(new ImageIcon(Localizacao.class.getResource("/image/user_interface/desktop_package.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tableLocalizacao.getSelectedRow();
				Produto produto = new Produto();
				produto = produtoDao.findById(Integer.parseInt(tableLocalizacao.getValueAt(i, 1).toString()));
				if (i >= 0) {
					tableLocalizacao.setValueAt(comboBoxLocal.getSelectedItem(), i, 0);
					produtoDao.updateLocalizacao(localizacaoDao.findByLocalizacao((String) comboBoxLocal.getSelectedItem()).getIdLocalizacao(), produto);
				}
				JOptionPane.showMessageDialog(null, "LOCALIZAÇÃO DO ITEM COD.CSM " + textFieldCodCSM.getText() + " ALTERADA PARA " + comboBoxLocal.getSelectedItem().toString(), "LOCALIZAÇÃO", 1);
				
				textFieldCodCSM.setText("");
				comboBoxLocal.setSelectedItem("");
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(separator_1);
		contentPane.add(separator);
		contentPane.add(lblNewLabel);
		contentPane.add(scrollPane);
		contentPane.add(lblNovoLocal);
		contentPane.add(comboBoxLocal);
		contentPane.add(btnAtualizar);
		contentPane.add(btnFechar);
		
		JLabel lblCsm = new JLabel("CSM ");
		lblCsm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCsm.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCsm.setAlignmentY(1.0f);
		lblCsm.setBounds(178, 430, 88, 25);
		contentPane.add(lblCsm);
		
		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodCSM.setEditable(false);
		textFieldCodCSM.setColumns(10);
		textFieldCodCSM.setAlignmentY(1.0f);
		textFieldCodCSM.setBounds(273, 430, 90, 25);
		contentPane.add(textFieldCodCSM);

	}
}
