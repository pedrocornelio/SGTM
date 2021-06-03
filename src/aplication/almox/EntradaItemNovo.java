/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.almox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import aplication.Principal;
import dao.AplicacaoDao;
import dao.CodigoMontadoraDao;
import dao.CodigoRecebidoDao;
import dao.DaoFactory;
import dao.FornecedorDao;
import dao.HistoricoProdutoEntradaDao;
import dao.LocalizacaoDao;
import dao.LoginDao;
import dao.MedidaDao;
import dao.MontadoraDao;
import dao.OriginalidadeDao;
import dao.ProdutoDao;
import dao.ViaturaDao;
import entities.Aplicacao;
import entities.CodigoMontadora;
import entities.CodigoRecebido;
import entities.Fornecedor;
import entities.HistoricoProdutoEntrada;
import entities.Login;
import entities.Produto;
import entities.Viatura;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import javax.swing.UIManager;

@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class EntradaItemNovo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroupOriginalidade = new ButtonGroup();
	private JTextField textFieldFornecedor;
	private JTextField textFieldNotaFiscal;
	private JTextField textFieldOrcamento;
	private JTextField textFieldPreco;
	private JTextField textFieldQuant;
	public static JTextField textFieldMarcaRecebida;
	public static JTextField textFieldCodRecebido;
	public static JTextField textFieldDescricao;
	public static JTextField textFieldCodMontadora;
	private JTable table;
	private JTextField textFieldModelo;
	private JTextField textFieldAno;
	private final ButtonGroup buttonGroupUso = new ButtonGroup();
	private static Login login;
	private JTextField textFieldPlaca;
	public static JRadioButton rdbtnMontadora;
	public static JRadioButton rdbtnConsumo;
	public static JComboBox comboBoxMontadora;
	public static JLabel lblMontadora;
	public static JLabel lblCodMontadora;
	private JButton btnSearchPlaca;
	private JComboBox comboBoxMedida;
	private JComboBox comboBoxMontadoraAplicacao;
	private JRadioButton rdbtnGenuina;
	private JRadioButton rdbtnOriginal;
	private JRadioButton rdbtnPriLinha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EntradaItemNovo frame = new EntradaItemNovo();
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
	 * @throws ParseException
	 */
	public EntradaItemNovo() throws ParseException {

		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(EntradaItemNovo.class.getResource("/image/user_interface/insercion_warehouse.png")));
		setTitle("ENTRADA DE ITEM NOVO");
		setFont(new Font("Arial", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 860, 540);
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

		MontadoraDao<Object> montadoraDao = DaoFactory.createMontadoraDao();
		LocalizacaoDao localizacaoDao = DaoFactory.createLocalizacaoDao();
		AplicacaoDao aplicacaoDao = DaoFactory.createAplicacaoDao();
		OriginalidadeDao originalDao = DaoFactory.createOriginalidadeDao();
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		HistoricoProdutoEntradaDao historicoEntradaDao = DaoFactory.createHistoricoProdutoEntradaDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		MedidaDao medidaDao = DaoFactory.createMedidaDao();
		CodigoMontadoraDao codigoMontadoraDao = DaoFactory.createCodigoMontadoraDao();
		CodigoRecebidoDao codigoRecebidoDao = DaoFactory.createCodigoRecebidoDao();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(SystemColor.textHighlightText);
		separator.setBounds(0, 8, 854, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(SystemColor.textHighlightText);
		separator_1.setBounds(0, 38, 854, 5);
		contentPane.add(separator_1);

		JLabel lblTitulo = new JLabel("DADOS DO ITEM NOVO");
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setBounds(0, 11, 854, 32);
		contentPane.add(lblTitulo);

		lblMontadora = new JLabel("MONTADORA");
		lblMontadora.setForeground(Color.BLACK);
		lblMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMontadora.setBounds(18, 76, 137, 23);
		contentPane.add(lblMontadora);

		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldCodMontadora.requestFocusInWindow();
				}
			}
		});
		comboBoxMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setModel(modelcombobox);
		comboBoxMontadora.setBounds(176, 76, 177, 23);
		contentPane.add(comboBoxMontadora);

		lblCodMontadora = new JLabel("C\u00D3DIGO MONTADORA");
		lblCodMontadora.setForeground(Color.BLACK);
		lblCodMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodMontadora.setBounds(18, 108, 137, 23);
		contentPane.add(lblCodMontadora);

		textFieldCodMontadora = new JTextField();
		textFieldCodMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldCodMontadora.getText().toUpperCase();
				textFieldCodMontadora.setText(temp);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldMarcaRecebida.requestFocusInWindow();
				}
			}
		});
		textFieldCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodMontadora.setBounds(176, 108, 177, 23);
		textFieldCodMontadora.setText(null);
		contentPane.add(textFieldCodMontadora);

		JLabel lblMarcaRecebida = new JLabel("MARCA RECEBIDA*");
		lblMarcaRecebida.setForeground(Color.BLACK);
		lblMarcaRecebida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarcaRecebida.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMarcaRecebida.setBounds(18, 142, 137, 23);
		contentPane.add(lblMarcaRecebida);

		textFieldMarcaRecebida = new JTextField();
		textFieldMarcaRecebida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldCodRecebido.requestFocusInWindow();
				}
			}
		});
		textFieldMarcaRecebida.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMarcaRecebida.setBounds(176, 142, 177, 23);
		contentPane.add(textFieldMarcaRecebida);

		JLabel lblCdigoRecebido = new JLabel("C\u00D3DIGO RECEBIDO*");
		lblCdigoRecebido.setForeground(Color.BLACK);
		lblCdigoRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigoRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCdigoRecebido.setBounds(18, 179, 137, 23);
		contentPane.add(lblCdigoRecebido);

		textFieldCodRecebido = new JTextField();
		textFieldCodRecebido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldDescricao.requestFocusInWindow();
				}
			}
		});
		textFieldCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodRecebido.setBounds(176, 179, 177, 23);
		contentPane.add(textFieldCodRecebido);

		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O*");
		lblDescricao.setForeground(Color.BLACK);
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescricao.setBounds(18, 213, 137, 23);
		contentPane.add(lblDescricao);

		textFieldDescricao = new JTextField();
		textFieldDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					rdbtnGenuina.requestFocusInWindow();
				}
			}
		});
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldDescricao.setBounds(176, 213, 177, 23);
		contentPane.add(textFieldDescricao);

		rdbtnGenuina = new JRadioButton("GENU\u00CDNA");
		rdbtnGenuina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldQuant.requestFocusInWindow();
				}
			}
		});
		rdbtnGenuina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// OBTENDO A ORIGINALIDADE
				originalDao.findByOriginalidade(rdbtnGenuina.getLabel());
				rdbtnGenuina.setActionCommand(getName());
			}
		});
		rdbtnGenuina.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroupOriginalidade.add(rdbtnGenuina);
		rdbtnGenuina.setBounds(120, 243, 80, 23);
		contentPane.add(rdbtnGenuina);

		rdbtnOriginal = new JRadioButton("ORIGINAL");
		rdbtnOriginal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldQuant.requestFocusInWindow();
				}
			}
		});
		rdbtnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// OBTENDO A ORIGINALIDADE
				originalDao.findByOriginalidade(rdbtnOriginal.getLabel());
				rdbtnOriginal.setActionCommand(getName());
			}
		});
		rdbtnOriginal.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroupOriginalidade.add(rdbtnOriginal);
		rdbtnOriginal.setBounds(202, 243, 87, 23);
		contentPane.add(rdbtnOriginal);

		rdbtnPriLinha = new JRadioButton("1\u00AA LINHA");
		rdbtnPriLinha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldQuant.requestFocusInWindow();
				}
			}
		});
		rdbtnPriLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// OBTENDO A ORIGINALIDADE
				originalDao.findByOriginalidade(rdbtnPriLinha.getLabel());
				rdbtnPriLinha.setActionCommand(getName());
			}
		});
		rdbtnPriLinha.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroupOriginalidade.add(rdbtnPriLinha);
		rdbtnPriLinha.setBounds(291, 243, 75, 23);
		contentPane.add(rdbtnPriLinha);

		// AVERIGUANDO SE É MONTADORA
		rdbtnMontadora = new JRadioButton("MONTADORA");
		rdbtnMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxMontadora.requestFocusInWindow();
				}
			}
		});
		rdbtnMontadora.setSelected(true);
		buttonGroupUso.add(rdbtnMontadora);
		rdbtnMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnMontadora.setBounds(113, 50, 109, 23);
		contentPane.add(rdbtnMontadora);
		rdbtnMontadora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (rdbtnMontadora.isSelected()) {
					lblMontadora.setEnabled(true);
					comboBoxMontadora.setEnabled(true);
					textFieldCodMontadora.setEnabled(true);
					lblCodMontadora.setEnabled(true);
					rdbtnGenuina.setEnabled(true);
					rdbtnOriginal.setEnabled(true);
					rdbtnPriLinha.setEnabled(true);
				}
			}
		});

		// AVERIGUANDO SE É CONSUMO
		rdbtnConsumo = new JRadioButton("CONSUMO");
		rdbtnConsumo.setSelected(true);
		buttonGroupUso.add(rdbtnConsumo);
		rdbtnConsumo.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbtnConsumo.setBounds(244, 50, 109, 23);
		contentPane.add(rdbtnConsumo);
		rdbtnConsumo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnConsumo.isSelected()) {
					lblMontadora.setEnabled(false);
					comboBoxMontadora.setEnabled(false);
					comboBoxMontadora.setSelectedItem("");
					textFieldCodMontadora.setText(null);
					textFieldCodMontadora.setEnabled(false);
					lblCodMontadora.setEnabled(false);
					rdbtnGenuina.setEnabled(false);
					rdbtnOriginal.setEnabled(false);
					rdbtnPriLinha.setEnabled(false);
				}
			}
		});

		JLabel lblNotaFiscal = new JLabel("N.F. FORNECEDOR");
		lblNotaFiscal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNotaFiscal.setBounds(18, 410, 137, 23);
		contentPane.add(lblNotaFiscal);

		textFieldFornecedor = new JTextField();
		textFieldFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldNotaFiscal.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldFornecedor.getText().toUpperCase();
				textFieldFornecedor.setText(temp);
			}
		});
		textFieldFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldFornecedor.setBounds(176, 376, 177, 23);
		contentPane.add(textFieldFornecedor);

		textFieldNotaFiscal = new JTextField();
		textFieldNotaFiscal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldOrcamento.requestFocusInWindow();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldNotaFiscal.getText().toUpperCase();
				textFieldNotaFiscal.setText(temp);
			}
		});
		textFieldNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNotaFiscal.setBounds(175, 410, 177, 23);
		contentPane.add(textFieldNotaFiscal);

		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFornecedor.setBounds(18, 376, 137, 23);
		contentPane.add(lblFornecedor);

		JLabel lblOrcamento = new JLabel("OR\u00C7AMENTO");
		lblOrcamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrcamento.setBounds(18, 445, 137, 23);
		contentPane.add(lblOrcamento);

		textFieldOrcamento = new JTextField();
		textFieldOrcamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldOrcamento.getText().toUpperCase();
				textFieldOrcamento.setText(temp);
			}
		});
		textFieldOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldOrcamento.setBounds(176, 445, 177, 23);
		contentPane.add(textFieldOrcamento);

		JLabel lblLocalizao = new JLabel("LOCALIZA\u00C7\u00C3O");
		lblLocalizao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalizao.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLocalizao.setBounds(18, 341, 137, 23);
		contentPane.add(lblLocalizao);

		JComboBox comboBoxLocalizacao = new JComboBox();
		comboBoxLocalizacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldFornecedor.requestFocusInWindow();
				}
			}
		});
		comboBoxLocalizacao.setBackground(Color.WHITE);
		DefaultComboBoxModel localizacaoModelComboBox = new DefaultComboBoxModel();
		localizacaoDao.comboBoxLocalizacao(localizacaoModelComboBox);
		comboBoxLocalizacao.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxLocalizacao.setModel(localizacaoModelComboBox);
		comboBoxLocalizacao.setBounds(176, 343, 177, 22);
		contentPane.add(comboBoxLocalizacao);

		JLabel lblQuantidade = new JLabel("QUANTIDADE*");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
		lblQuantidade.setBounds(18, 273, 137, 23);
		contentPane.add(lblQuantidade);

		textFieldQuant = new JTextField();
		textFieldQuant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxMedida.requestFocusInWindow();
				}
			}
		});
		textFieldQuant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQuant.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuant.setBounds(176, 273, 87, 23);
		contentPane.add(textFieldQuant);

		JLabel lblPreco = new JLabel("PRE\u00C7O UNIT\u00C1RIO*");
		lblPreco.setForeground(Color.BLACK);
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPreco.setBounds(18, 307, 137, 23);
		contentPane.add(lblPreco);

		textFieldPreco = new JTextField();
		textFieldPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					comboBoxLocalizacao.requestFocusInWindow();
				}
			}
		});
		textFieldPreco.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPreco.setBounds(176, 307, 177, 23);
		contentPane.add(textFieldPreco);

		JLabel lblAplicacao = new JLabel("APLICA\u00C7\u00C3O(\u00D5ES)");
		lblAplicacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAplicacao.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAplicacao.setBounds(418, 50, 119, 23);
		contentPane.add(lblAplicacao);

		comboBoxMontadoraAplicacao = new JComboBox();
		comboBoxMontadoraAplicacao.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox1 = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox1);
		comboBoxMontadoraAplicacao.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadoraAplicacao.setModel(modelcombobox1);
		comboBoxMontadoraAplicacao.setBounds(514, 85, 150, 23);
		contentPane.add(comboBoxMontadoraAplicacao);

		JButton btnSearchProduto = new JButton("");
		btnSearchProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcurarProdutoNovo exibir = null;
				try {
					exibir = new ProcurarProdutoNovo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exibir.setVisible(true);
			}
		});
		btnSearchProduto.setIcon(new ImageIcon(EntradaItemNovo.class.getResource("/image/user_interface/search_product.png")));
		btnSearchProduto.setMargin(new Insets(2, 2, 2, 2));
		btnSearchProduto.setBounds(363, 142, 38, 35);
		contentPane.add(btnSearchProduto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setBounds(435, 225, 354, 172);
		contentPane.add(scrollPane);

		table = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		Object[] columnNames = { "MONTADORA", "MODELO", "ANO" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setMinWidth(15);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setMinWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(413, 38, 12, 463);
		contentPane.add(separator_2);

		JButton btnPlus = new JButton("");
		btnPlus.setIcon(new ImageIcon(EntradaItemNovo.class.getResource("/image/user_interface/up.png")));
		btnPlus.setBounds(799, 261, 30, 30);
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent plus) {
				String[] row = { (String) comboBoxMontadoraAplicacao.getSelectedItem(),
						textFieldModelo.getText(),
						textFieldAno.getText() };
				model.addRow(row);
				comboBoxMontadoraAplicacao.setSelectedItem(null);
				textFieldModelo.setText("");
				textFieldAno.setText("");
			}

		});
		contentPane.add(btnPlus);

		JButton btnMinus = new JButton("");
		btnMinus.setIcon(new ImageIcon(EntradaItemNovo.class.getResource("/image/user_interface/trash.png")));
		btnMinus.setBounds(799, 326, 30, 30);
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent minus) {
				if (table.getSelectedRow() != -1) {
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		contentPane.add(btnMinus);

		JLabel lblMontadora_1 = new JLabel("MONTADORA");
		lblMontadora_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMontadora_1.setBounds(418, 85, 86, 23);
		contentPane.add(lblMontadora_1);

		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModelo.setBounds(418, 120, 86, 23);
		contentPane.add(lblModelo);

		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldModelo.getText().toUpperCase();
				textFieldModelo.setText(temp);
			}
		});
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(514, 120, 150, 23);
		contentPane.add(textFieldModelo);

		JLabel lblAno = new JLabel("ANO");
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAno.setBounds(418, 155, 86, 23);
		contentPane.add(lblAno);

		textFieldAno = new JTextField();
		textFieldAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldAno.getText().toUpperCase();
				textFieldAno.setText(temp);
			}
		});
		textFieldAno.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldAno.setColumns(10);
		textFieldAno.setBounds(514, 155, 150, 23);
		contentPane.add(textFieldAno);

		JButton btnSaveInsert = new JButton("  INSERIR");
		btnSaveInsert.setEnabled(false);
		btnSaveInsert.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSaveInsert.setMargin(new Insets(2, 5, 2, 5));
		btnSaveInsert.setIconTextGap(0);
		btnSaveInsert.setIcon(new ImageIcon(EntradaItemNovo.class.getResource("/image/user_interface/insercion_warehouse.png")));
		btnSaveInsert.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaveInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// PARA DETERMINAR QUAL O ID_ORIGINALIDADE
				Integer original = 4;
				if (rdbtnGenuina.isSelected() == true) {
					original = 1;
					if (rdbtnOriginal.isSelected() == true) {
						original = 2;
					}
					if (rdbtnPriLinha.isSelected() == true) {
						original = 3;
					}
				}
				
				String itemEncontarado = null;
				
				if(textFieldCodMontadora.getText().length() > 0) {
					if(produtoDao.findByCodigoMontadora(textFieldCodMontadora.getText()) != null) {
						itemEncontarado = produtoDao.findByCodigoMontadora(textFieldCodMontadora.getText()).getId_produto().toString();
					} else {
						if(produtoDao.findByCodigoRecebido(textFieldCodRecebido.getText()) != null) {
							itemEncontarado = produtoDao.findByCodigoRecebido(textFieldCodRecebido.getText()).getId_produto().toString();
						}
					}
				}
				
				if(itemEncontarado != null) {
					
					JOptionPane.showMessageDialog(null, "ITEM JÁ CADASTRADO NO BANCO, CÓDIGO CSM:"+itemEncontarado, "ITEM DUPLICADO", 0);
				
					Entrada exibir = null;
					try {
						exibir = new Entrada();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					exibir.setVisible(true);

					dispose();
				} else {

					// PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
					String nBM = Principal.textFieldUser.getText();
					login = loginDao.findByNBM(nBM);

					// PARA SETAR A QUANTIDADE
					Integer quantidade;
					if (textFieldQuant.getText() == "") {
						quantidade = 0;
					} else {
						quantidade = new Integer(textFieldQuant.getText());
					}

					// PARA SETAR O PREÇO
					Double preco;
					if (textFieldPreco.getText() == "") {
						preco = 0.0;
					} else {
						preco = Double.parseDouble(textFieldPreco.getText().replace(',', '.'));
					}

					// CRIANDO O ITEM NOVO
					Produto produto = new Produto ();
					if (montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()).getIdMontadora() == 1 ) {
						produto = new Produto(null, textFieldDescricao.getText(), originalDao.findById(original), quantidade,
							localizacaoDao.findByLocalizacao((String) comboBoxLocalizacao.getSelectedItem()),
							0, medidaDao.findByMedida(comboBoxMedida.getSelectedItem().toString()));
						// CRIAR CODIGO MONTADORA
						CodigoMontadora codigoMontadora = new CodigoMontadora(null, null, null, produto);
						// CRIAR CODIGO RECEBIDO
						CodigoRecebido codigoRecebido = new CodigoRecebido(null, textFieldMarcaRecebida.getText(), textFieldCodRecebido.getText(), produto);
						produtoDao.insert(produto, codigoMontadora, codigoRecebido, produto.getOriginalidade(), produto.getLocalizacao(), produto.getMedida());
						codigoMontadoraDao.insert(codigoMontadora, montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()), produto);
						codigoRecebidoDao.insert(codigoRecebido, produto);
					} else {
						produto = new Produto(null, textFieldDescricao.getText(), originalDao.findById(original), quantidade,
								localizacaoDao.findByLocalizacao((String) comboBoxLocalizacao.getSelectedItem()),
								0, medidaDao.findByMedida(comboBoxMedida.getSelectedItem().toString()));
						// CRIAR CODIGO MONTADORA
						CodigoMontadora codigoMontadora = new CodigoMontadora();
						System.out.println(textFieldCodMontadora.getText().toString());
						System.out.println(textFieldCodMontadora.getText());
						if (textFieldCodMontadora.getText().length() > 2) {
							codigoMontadora = new CodigoMontadora(null, textFieldCodMontadora.getText(),
									montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()), produto);
						} else {
							codigoMontadora = new CodigoMontadora(null, null,
								montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()), produto);
						}
						
						// CRIAR CODIGO RECEBIDO
						CodigoRecebido codigoRecebido = new CodigoRecebido(null, textFieldMarcaRecebida.getText(),
								textFieldCodRecebido.getText(), produto);
						produtoDao.insert(produto, codigoMontadora, codigoRecebido, produto.getOriginalidade(), produto.getLocalizacao(), produto.getMedida());
						codigoMontadoraDao.insert(codigoMontadora, montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()), produto);
						codigoRecebidoDao.insert(codigoRecebido, produto);
					}

					// CRIANDO O FORNECEDOR DO ITEM NOVO
					Fornecedor fornecedor = new Fornecedor(textFieldFornecedor.getText(), textFieldNotaFiscal.getText(), textFieldOrcamento.getText(), preco, produto);
					fornecedorDao.insert(fornecedor);

					// CRIANDO O HISTORICO DE ENTRADA DO ITEM NOVO
					Date data = new java.sql.Date(new java.util.Date().getTime());
					Time hora = new java.sql.Time(new java.util.Date().getTime());
					HistoricoProdutoEntrada entrada = new HistoricoProdutoEntrada(
							null, data, hora, quantidade,
							produto, login, fornecedor);
					historicoEntradaDao.insert(entrada);

					// CRIANDO A APLICAO DO ITEM NOVO
					for (Integer i = 0; i < table.getRowCount(); i++) {
						Aplicacao item = new Aplicacao((String) table.getValueAt(i, 1), (String) table.getValueAt(i, 2),
								montadoraDao.findByMontadora((String) table.getValueAt(i, 0)), produto);
						aplicacaoDao.insert(item);
					}

					// ATUALIZANDO AS TABELAS DO ESTOQUE
					Almoxarifado.btnRefresh.doClick();
					
					JOptionPane.showMessageDialog(null, "O ITEM "+ produto.getDescricao() +" FOI ADICIONADO COM SUCESSO AO COD.CSM "+produto.getId_produto(), "INSERÇÃO", 1);

					dispose();

				}
			}
		});
		btnSaveInsert.setBounds(552, 445, 113, 35);
		contentPane.add(btnSaveInsert);

		JLabel lblNewLabel = new JLabel("OU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(675, 120, 22, 14);
		contentPane.add(lblNewLabel);

		JLabel lblVTR = new JLabel("<HTML><CENTER>VTR DE <BR> REFER\u00CANCIA </HTML>");
		lblVTR.setHorizontalAlignment(SwingConstants.CENTER);
		lblVTR.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVTR.setBounds(726, 50, 98, 32);
		contentPane.add(lblVTR);

		JLabel lblNewLabel_1 = new JLabel("TABELA DE VISUALIA\u00C7\u00C3O DE APLICA\u00C7\u00C3O(\u00D5ES) DO ITEM");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(435, 200, 354, 24);
		contentPane.add(lblNewLabel_1);

		textFieldPlaca = new JTextField();
		textFieldPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldPlaca.getText().toUpperCase();
				textFieldPlaca.setText(temp);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE) || textFieldPlaca.getText().length() > 6) {
					e.consume();
				}
			}
		});
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		textFieldPlaca.setBounds(726, 120, 98, 23);
		contentPane.add(textFieldPlaca);

		btnSearchPlaca = new JButton("");
		btnSearchPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Viatura vtr = viaturaDao.findByPlaca((String) textFieldPlaca.getText());
				comboBoxMontadoraAplicacao.setSelectedItem(vtr.getMontadora().getNomeMontadora());
				textFieldModelo.setText(vtr.getModelo());
				textFieldAno.setText(vtr.getAno());
			}
		});
		btnSearchPlaca.setIcon(new ImageIcon(EntradaItemNovo.class.getResource("/image/user_interface/search.png")));
		btnSearchPlaca.setMargin(new Insets(2, 2, 2, 2));
		btnSearchPlaca.setBounds(755, 153, 38, 35);
		contentPane.add(btnSearchPlaca);

		JLabel labelPlaca = new JLabel("PLACA");
		labelPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		labelPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPlaca.setBounds(726, 93, 98, 23);
		contentPane.add(labelPlaca);
		
		comboBoxMedida = new JComboBox();
		comboBoxMedida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					textFieldPreco.requestFocusInWindow();
				}
			}
		});
		comboBoxMedida.setBackground(Color.WHITE);
		DefaultComboBoxModel modelComboBoxMedida = new DefaultComboBoxModel();
		medidaDao.comboBoxMedida(modelComboBoxMedida);
		comboBoxMedida.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMedida.setModel(modelComboBoxMedida);
		comboBoxMedida.setBounds(275, 273, 78, 23);
		contentPane.add(comboBoxMedida);
		
		JLabel lblObrigatorio = new JLabel("*OBS: CAMPOS OBRIGAT\u00D3RIOS");
		lblObrigatorio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObrigatorio.setFont(new Font("Arial", Font.PLAIN, 10));
		lblObrigatorio.setBounds(517, 410, 182, 23);
		contentPane.add(lblObrigatorio);

		// VALIDAÇÃO PARA LIBERAR O BOTÃO INSERT
		textFieldMarcaRecebida.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (textFieldMarcaRecebida.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuant.getText().length() != 0
						& textFieldPreco.getText().length() != 0) {
					btnSaveInsert.setEnabled(true);
				} else {
					btnSaveInsert.setEnabled(false);
				}
				
				String temp = textFieldMarcaRecebida.getText().toUpperCase();
				textFieldMarcaRecebida.setText(temp);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		textFieldCodRecebido.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarcaRecebida.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuant.getText().length() != 0
						& textFieldPreco.getText().length() != 0) {
					btnSaveInsert.setEnabled(true);
				} else {
					btnSaveInsert.setEnabled(false);
				}
				
				String temp = textFieldCodRecebido.getText().toUpperCase();
				textFieldCodRecebido.setText(temp);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		textFieldDescricao.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarcaRecebida.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuant.getText().length() != 0
						& textFieldPreco.getText().length() != 0) {
					btnSaveInsert.setEnabled(true);
				} else {
					btnSaveInsert.setEnabled(false);
				}
				
				String temp = textFieldDescricao.getText().toUpperCase();
				textFieldDescricao.setText(temp);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		});

		textFieldQuant.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarcaRecebida.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuant.getText().length() != 0
						& textFieldPreco.getText().length() != 0) {
					btnSaveInsert.setEnabled(true);
				} else {
					btnSaveInsert.setEnabled(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		textFieldPreco.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarcaRecebida.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuant.getText().length() != 0
						& textFieldPreco.getText().length() != 0) {
					btnSaveInsert.setEnabled(true);
				} else {
					btnSaveInsert.setEnabled(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_COMMA)) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

	}
}
