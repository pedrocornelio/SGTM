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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import aplication.Principal;
import dao.CodigoMontadoraDao;
import dao.CodigoRecebidoDao;
import dao.DaoFactory;
import dao.FornecedorDao;
import dao.HistoricoProdutoEntradaDao;
import dao.LoginDao;
import dao.MontadoraDao;
import dao.ProdutoDao;
import entities.CodigoMontadora;
import entities.CodigoRecebido;
import entities.Fornecedor;
import entities.HistoricoProdutoEntrada;
import entities.Login;
import entities.Produto;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Entrada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable tableProduto;
	public static JComboBox comboBoxMontadora;
	public static JComboBox comboBoxCodMontadora;
	public static JComboBox comboBoxMarca;
	public static JComboBox comboBoxCodRecebido;
	public static JTextField textFieldDescricao;
	private JTextField textFieldFornecedor;
	private JTextField textFieldOrcamento;
	private JTextField textFieldQuantidade;
	private JTextField textFieldPreco;
	private JTextField textFieldNotaFiscal;
	private JButton btnEntrada;
	public static JLabel lblMontadora;
	public static JLabel lblCodMontadora;
	private static Login login;
	public static Produto produtoAplicacao;
	private JButton btnNovasAplicacoes;
	private JTable tableProdutoEntrada;
	private JTextField textFieldCodCSM;
	private JLabel lblCodCSM;
	private JLabel lblEspaco;
	private JCheckBox chckbxNovoCodMontadora;
	private JCheckBox chckbxNovoCodRecebido;
	private JLabel lblNovoCodigo;
	private JTextField textFieldCodMontadora;
	private JLabel lblNovoCodigo_1;
	private JTextField textFieldCodRecebido;
	private JButton btnPlus;
	private JTextField textFieldNovaMarca;
	private JCheckBox chckbxNovaMarca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ParseException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Entrada frame = new Entrada();
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
	public Entrada() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Entrada.class.getResource("/image/user_interface/insercion_car.png")));
		setTitle("ENTRADA DE ITEM EXISTENTE");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 920, 750);
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
		
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		MontadoraDao<Object> montadoraDao = DaoFactory.createMontadoraDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		HistoricoProdutoEntradaDao historicoEntradaDao = DaoFactory.createHistoricoProdutoEntradaDao();
		CodigoMontadoraDao codigoMontadoraDao = DaoFactory.createCodigoMontadoraDao();
		CodigoRecebidoDao codigoRecebidoDao = DaoFactory.createCodigoRecebidoDao();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel("DADOS DOS ITENS EXISTENTES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);

		JLabel lblTabelaDeItens = new JLabel("ITENS DE ENTRADA");
		lblTabelaDeItens.setHorizontalAlignment(SwingConstants.CENTER);
		lblTabelaDeItens.setFont(new Font("Arial", Font.PLAIN, 14));

		lblCodCSM = new JLabel("C\u00D3DIGO CSM*");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setEditable(false);
		textFieldCodCSM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));

		lblMontadora = new JLabel("MONTADORA");
		lblMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.setEditable(true);
		comboBoxMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setModel(modelcombobox);

		lblCodMontadora = new JLabel("C\u00D3DIGO MONTADORA");
		lblCodMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxCodMontadora = new JComboBox();
		comboBoxCodMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel codMontadoraModelComboBox = new DefaultComboBoxModel();
		comboBoxCodMontadora.setModel(codMontadoraModelComboBox);
		comboBoxCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblMarca = new JLabel("MARCA RECEBIDA*");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxMarca = new JComboBox();
		comboBoxMarca.setBackground(Color.WHITE);
		DefaultComboBoxModel marcaModelComboBox = new DefaultComboBoxModel();
		comboBoxMarca.setModel(marcaModelComboBox);
		comboBoxMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblCodRecebido = new JLabel("C\u00D3DIGO RECEBIDO*");
		lblCodRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxCodRecebido = new JComboBox();
		comboBoxCodRecebido.setBackground(Color.WHITE);
		DefaultComboBoxModel CodRecebidoModelComboBox = new DefaultComboBoxModel();
		comboBoxCodRecebido.setModel(CodRecebidoModelComboBox);
		comboBoxCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O*");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldDescricao = new JTextField();
		textFieldDescricao.setEditable(false);
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldFornecedor = new JTextField();
		textFieldFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldFornecedor.getText().toUpperCase();
				textFieldFornecedor.setText(temp);
			}
		});
		textFieldFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldFornecedor.setColumns(1);

		JLabel lblNFFornecedor = new JLabel("N.F. FORNECEDOR");
		lblNFFornecedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNFFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldNotaFiscal = new JTextField();
		textFieldNotaFiscal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldNotaFiscal.getText().toUpperCase();
				textFieldNotaFiscal.setText(temp);
			}
		});
		textFieldNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNotaFiscal.setColumns(1);

		JLabel labelOrcamento = new JLabel("OR\u00C7AMENTO");
		labelOrcamento.setHorizontalAlignment(SwingConstants.RIGHT);
		labelOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldOrcamento = new JTextField();
		textFieldOrcamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldOrcamento.getText().toUpperCase();
				textFieldOrcamento.setText(temp);
			}
		});
		textFieldOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldOrcamento.setColumns(1);

		JLabel lblQuantidade = new JLabel("QUANTIDADE*");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuantidade.setColumns(1);

		JLabel labelPreco = new JLabel("PRE\u00C7O*");
		labelPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPreco.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldPreco = new JTextField();
		textFieldPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPreco.setColumns(1);

		JScrollPane scrollPane_1 = new JScrollPane();

		tableProdutoEntrada = new JTable();
		DefaultTableModel modelProduto = new DefaultTableModel();
		tableProdutoEntrada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableProdutoEntrada.getSelectedRow();
				comboBoxMontadora.setSelectedItem(tableProdutoEntrada.getValueAt(i, 2).toString());
				DefaultComboBoxModel codMontadoraModelComboBox = new DefaultComboBoxModel();
				comboBoxCodMontadora.setModel(codMontadoraModelComboBox);
				codigoMontadoraDao.comboBoxCodigoMontadora(codMontadoraModelComboBox, Integer.parseInt(tableProdutoEntrada.getValueAt(i, 0).toString()));

				textFieldCodCSM.setText(tableProdutoEntrada.getValueAt(i, 0).toString());
				textFieldDescricao.setText(tableProdutoEntrada.getValueAt(i, 1).toString());
				
				DefaultComboBoxModel marcaModelComboBox = new DefaultComboBoxModel();
				comboBoxMarca.setModel(marcaModelComboBox);
				codigoRecebidoDao.comboBoxMarca(marcaModelComboBox, Integer.parseInt(tableProdutoEntrada.getValueAt(i, 0).toString()));

				DefaultComboBoxModel codRecebidoModelComboBox = new DefaultComboBoxModel();
				comboBoxCodRecebido.setModel(codRecebidoModelComboBox);
				codigoRecebidoDao.comboBoxCodigoRecebido(codRecebidoModelComboBox, Integer.parseInt(tableProdutoEntrada.getValueAt(i, 0).toString()));

				btnNovasAplicacoes.setEnabled(true);
			}
		});
		TableFilterHeader filterHeaderEstoque = new TableFilterHeader(tableProdutoEntrada, AutoChoices.ENABLED);
		filterHeaderEstoque.setPosition(Position.TOP);
		filterHeaderEstoque.setBackground(Color.white);
		tableProdutoEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProdutoEntrada.setModel(produtoDao.tableProdutoEntrada(modelProduto));
		tableProdutoEntrada.setModel(modelProduto);
		tableProdutoEntrada.getColumnModel().getColumn(0).setMinWidth(40);
		tableProdutoEntrada.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProdutoEntrada.getColumnModel().getColumn(0).setMaxWidth(45);
		scrollPane_1.setViewportView(tableProdutoEntrada);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 12));

		tableProduto = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableProduto, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		String[] columnNames = {"COD.CSM", "MONTADORA", "COD.MONT.", "MARCA", "COD.RECEBIDO", "DESCRIÇÃO", "FORNECEDOR", "N.F.", "ORÇAMENTO", "QUANTIDADE", "PREÇO"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		tableProduto.setModel(model);
		scrollPane.setViewportView(tableProduto);

		btnPlus = new JButton("");
		btnPlus.setEnabled(false);
		btnPlus.setIcon(new ImageIcon(Saida.class.getResource("/image/user_interface/up.png")));
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldPreco.getText().length() == 0 ) {
					textFieldPreco.setText("0");
				}
				
				if (chckbxNovoCodMontadora.isSelected()==true && chckbxNovoCodRecebido.isSelected()==true && chckbxNovaMarca.isSelected()==true) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							textFieldCodMontadora.getText(), 
							textFieldNovaMarca.getText(),
							textFieldCodRecebido.getText(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==false && chckbxNovoCodRecebido.isSelected()==true && chckbxNovaMarca.isSelected()==true) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							comboBoxCodMontadora.getSelectedItem(),
							textFieldNovaMarca.getText(),
							textFieldCodRecebido.getText(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==true && chckbxNovoCodRecebido.isSelected()==false && chckbxNovaMarca.isSelected()==true) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							textFieldCodMontadora.getText(), 
							textFieldNovaMarca.getText(),
							comboBoxCodRecebido.getSelectedItem(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==true && chckbxNovoCodRecebido.isSelected()==true && chckbxNovaMarca.isSelected()==false) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							textFieldCodMontadora.getText(), 
							comboBoxMarca.getSelectedItem(),
							textFieldCodRecebido.getText(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==false && chckbxNovoCodRecebido.isSelected()==false && chckbxNovaMarca.isSelected()==true) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							comboBoxCodMontadora.getSelectedItem(), 
							textFieldNovaMarca.getText(),
							comboBoxCodRecebido.getSelectedItem(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==true && chckbxNovoCodRecebido.isSelected()==false && chckbxNovaMarca.isSelected()==false) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							textFieldCodMontadora.getText(), 
							comboBoxMarca.getSelectedItem(),
							comboBoxCodRecebido.getSelectedItem(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==false && chckbxNovoCodRecebido.isSelected()==true && chckbxNovaMarca.isSelected()==false) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							comboBoxCodMontadora.getSelectedItem(), 
							comboBoxMarca.getSelectedItem(),
							textFieldCodRecebido.getText(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}
				
				if (chckbxNovoCodMontadora.isSelected()==false && chckbxNovoCodRecebido.isSelected()==false && chckbxNovaMarca.isSelected()==false) {
					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							comboBoxCodMontadora.getSelectedItem(), 
							comboBoxMarca.getSelectedItem(),
							comboBoxCodRecebido.getSelectedItem(),
							textFieldDescricao.getText(), textFieldFornecedor.getText(), textFieldNotaFiscal.getText(),
							textFieldOrcamento.getText(), textFieldQuantidade.getText(), textFieldPreco.getText() };
					model.addRow(row);
				}

				DefaultComboBoxModel codMontadoraModelComboBox = new DefaultComboBoxModel();
				comboBoxCodMontadora.setModel(codMontadoraModelComboBox);
				DefaultComboBoxModel marcaModelComboBox = new DefaultComboBoxModel();
				comboBoxMarca.setModel(marcaModelComboBox);
				DefaultComboBoxModel codRecebidoModelComboBox = new DefaultComboBoxModel();
				comboBoxCodRecebido.setModel(codRecebidoModelComboBox);
				
				textFieldCodCSM.setText("");
				comboBoxMontadora.setSelectedItem("");
				textFieldCodMontadora.setText("");
				textFieldNovaMarca.setText("");
				textFieldCodRecebido.setText("");
				textFieldDescricao.setText("");
				textFieldFornecedor.setText("");
				textFieldNotaFiscal.setText("");
				textFieldOrcamento.setText("");
				textFieldQuantidade.setText("");
				textFieldPreco.setText("");
				chckbxNovoCodMontadora.setSelected(false);
				comboBoxCodMontadora.setEnabled(true);
				textFieldCodMontadora.setEnabled(false);
				chckbxNovaMarca.setSelected(false);
				comboBoxMarca.setEnabled(true);
				textFieldNovaMarca.setEnabled(false);
				chckbxNovoCodRecebido.setSelected(false);
				comboBoxCodRecebido.setEnabled(true);
				textFieldCodRecebido.setEnabled(false);

				btnPlus.setEnabled(false);
				btnEntrada.setEnabled(true);
				btnNovasAplicacoes.setEnabled(false);
			}
		});

		JButton btnMinus = new JButton("");
		btnMinus.setIcon(new ImageIcon(Entrada.class.getResource("/image/user_interface/trash.png")));
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (tableProduto.getSelectedRow() != -1) {
					model.removeRow(tableProduto.getSelectedRow());
				}
				if (tableProduto.getSelectedRow() == -1) {
					btnEntrada.setEnabled(false);
				}
			}
		});

		btnNovasAplicacoes = new JButton("APLICA\u00C7\u00D5ES");
		btnNovasAplicacoes.setFont(new Font("Arial", Font.BOLD, 14));
		btnNovasAplicacoes.setEnabled(false);
		btnNovasAplicacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovasAplicacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				produtoAplicacao = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				NovasAplicacoesAlmox exibir = new NovasAplicacoesAlmox();
				exibir.setVisible(true);
			}
		});
		btnNovasAplicacoes.setIcon(new ImageIcon(Entrada.class.getResource("/image/user_interface/desktop_package.png")));

		btnEntrada = new JButton("ENTRADA DE ITENS");
		btnEntrada.setEnabled(false);
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (Integer i = 0; i < tableProduto.getRowCount(); i++) {
						// PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
						String nBM = Principal.textFieldUser.getText();
						login = loginDao.findByNBM(nBM);

						for (i = 0; i < tableProduto.getRowCount(); i++) {
							// SETAR O PRODUTO PARA SER ATUALIZADO
							Produto produto = new Produto();
							produto = produtoDao.findById(Integer.parseInt(tableProduto.getValueAt(i, 0).toString()));

							// ATUALIZA O PRODUTO
							produto.setQuantidade(produto.getQuantidade() + Integer.parseInt(tableProduto.getValueAt(i, 9).toString()));
							produtoDao.updateQuantidade(produto);
							
							//CRIA CODIGO MONTADORA NOVO
							CodigoMontadora codigoMontadora = new CodigoMontadora();
							if (tableProduto.getValueAt(i, 2) == "") {
								codigoMontadora = new CodigoMontadora (null, 
										null, 
										montadoraDao.findByMontadora(tableProduto.getValueAt(i, 1).toString()), 
										produto);
								if (codigoMontadoraDao.findCodigoMontadoraNull(produto.getId_produto()).getId_codigo_montadora() == null) {
									codigoMontadoraDao.insert(codigoMontadora, 
											codigoMontadora.getMontadora(), 
											produto);
								}
							} else {
								codigoMontadora = new CodigoMontadora (null, 
										tableProduto.getValueAt(i, 2).toString(), 
										montadoraDao.findByMontadora(tableProduto.getValueAt(i, 1).toString()), 
										produto);
								if (codigoMontadoraDao.findCodigoMontadora(produto.getId_produto(), codigoMontadora.getCodigo_montadora()).getId_codigo_montadora() == null) {
									codigoMontadoraDao.insert(codigoMontadora, 
											codigoMontadora.getMontadora(), 
											produto);
								}
							}
							
							
							
							//CRIA CODIGO RECEBIDO NOVO
							CodigoRecebido codigoRecebido = new CodigoRecebido (null, 
									tableProduto.getValueAt(i, 3).toString(), 
									tableProduto.getValueAt(i, 4).toString(), 
									produto);
							if (codigoRecebidoDao.findByCodigoRecebido(produto.getId_produto(), codigoRecebido.getMarca(), codigoRecebido.getCodigo_recebido()).getId_codigo_recebido() == null) {
								codigoRecebidoDao.insert(codigoRecebido,  
										produto);
							}

							// INSERE O FORNECEDOR
							Double preco = Double.parseDouble((tableProduto.getValueAt(i, 10).toString()).replace(',', '.'));
							Fornecedor fornecedor = new Fornecedor();
							fornecedor = new Fornecedor(tableProduto.getValueAt(i, 6).toString(),
									tableProduto.getValueAt(i, 7).toString(),
									tableProduto.getValueAt(i, 8).toString(), preco, produto);
							fornecedorDao.insert(fornecedor);
							
							// CRIANDO O HISTORICO DE ENTRADA DO ITEM NOVO
							Date data = new java.sql.Date(new java.util.Date().getTime());
							Time hora = new java.sql.Time(new java.util.Date().getTime());
							HistoricoProdutoEntrada entrada = new HistoricoProdutoEntrada(null, data, hora,
									Integer.parseInt(tableProduto.getValueAt(i, 9).toString()), 
									produto, login, fornecedor);
							historicoEntradaDao.insert(entrada);
						}
						
						JOptionPane.showMessageDialog(null, "OS ITENS FORAM ADICIONADOS COM SUCESSO", "INSERÇÃO", 1);

						// ATUALIZANDO AS TABELAS DO ESTOQUE
						Almoxarifado.btnRefresh.doClick();

						dispose();

				}
			}
		});
		btnEntrada.setIcon(new ImageIcon(Entrada.class.getResource("/image/user_interface/insercion_car.png")));
		btnEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblObrigatorio = new JLabel("*OBS: CAMPOS OBRIGAT\u00D3RIOS");
		lblObrigatorio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObrigatorio.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblEspaco_1 = new JLabel("   ");
		
		lblEspaco = new JLabel("   ");
		
		chckbxNovoCodMontadora = new JCheckBox("NOVO");
		chckbxNovoCodMontadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNovoCodMontadora.isSelected() == true) {
					textFieldCodMontadora.setEnabled(true);
					comboBoxCodMontadora.setEnabled(false);
				} else {
					textFieldCodMontadora.setEnabled(false);
					comboBoxCodMontadora.setEnabled(true);
				}
			}
		});
		
		chckbxNovaMarca = new JCheckBox("NOVO");
		chckbxNovaMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNovaMarca.isSelected() == true) {
					textFieldNovaMarca.setEnabled(true);
					comboBoxMarca.setEnabled(false);
				} else {
					textFieldNovaMarca.setEnabled(false);
					comboBoxMarca.setEnabled(true);
				}
			}
		});
		
		chckbxNovoCodRecebido = new JCheckBox("NOVO");
		chckbxNovoCodRecebido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNovoCodRecebido.isSelected() == true) {
					textFieldCodRecebido.setEnabled(true);
					comboBoxCodRecebido.setEnabled(false);
				} else {
					textFieldCodRecebido.setEnabled(false);
					comboBoxCodRecebido.setEnabled(true);
				}
			}
		});
		
		lblNovoCodigo = new JLabel("NOVO CODIGO");
		lblNovoCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovoCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodMontadora = new JTextField();
		textFieldCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
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
		});
		textFieldCodMontadora.setEnabled(false);
		textFieldCodMontadora.setColumns(10);
		
		lblNovoCodigo_1 = new JLabel("NOVO CODIGO");
		lblNovoCodigo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovoCodigo_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodRecebido = new JTextField();
		textFieldCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodRecebido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldCodRecebido.getText().toUpperCase();
				textFieldCodRecebido.setText(temp);
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldCodRecebido.setEnabled(false);
		textFieldCodRecebido.setColumns(10);
		
		JLabel lblNovaMarca = new JLabel("NOVA MARCA");
		lblNovaMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldNovaMarca = new JTextField();
		textFieldNovaMarca.setEnabled(false);
		textFieldNovaMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNovaMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldNovaMarca.getText().toUpperCase();
				textFieldNovaMarca.setText(temp);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(337)
					.addComponent(lblTabelaDeItens, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
					.addGap(216))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(175)
					.addComponent(lblEspaco_1, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(159)
					.addComponent(btnEntrada)
					.addGap(19)
					.addComponent(lblEspaco, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(lblObrigatorio, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNovoCodigo, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNovaMarca, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNovoCodigo_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFornecedor, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNFFornecedor, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelOrcamento, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxCodMontadora, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNovaMarca, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxCodRecebido, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldFornecedor, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNotaFiscal, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldOrcamento, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(labelPreco)
									.addGap(9)
									.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxNovoCodMontadora)
								.addComponent(chckbxNovaMarca)
								.addComponent(chckbxNovoCodRecebido))
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNovasAplicacoes, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(80))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(lblTabelaDeItens, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblNovoCodigo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblNovaMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblNovoCodigo_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblFornecedor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblNFFornecedor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(labelOrcamento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(comboBoxCodMontadora, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(textFieldNovaMarca, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(comboBoxCodRecebido, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(textFieldFornecedor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(textFieldNotaFiscal, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(textFieldOrcamento, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelPreco, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(89)
									.addComponent(chckbxNovoCodMontadora)
									.addGap(43)
									.addComponent(chckbxNovaMarca)
									.addGap(45)
									.addComponent(chckbxNovoCodRecebido)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNovasAplicacoes, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblEspaco_1))
						.addComponent(btnEntrada, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(lblEspaco))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblObrigatorio, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				if (tableProdutoEntrada.getSelectedRow()>-1) {
					validar();
				}
				
				if (tableProduto.getRowCount() != 0) {
					btnEntrada.setEnabled(true);
				} else {
					btnEntrada.setEnabled(false);
				}
			}
		});

		textFieldQuantidade.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				validar();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		textFieldPreco.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validar();
			}

			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE
						|| number == KeyEvent.VK_COMMA)) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		
		
	}
	
	public void validar() {
		if (chckbxNovoCodMontadora.isSelected() == true) {
			if (textFieldCodMontadora.getText().length() != 0
					& comboBoxCodRecebido.getSelectedItem().toString().length() != 0
					|| textFieldCodRecebido.getText().length() != 0
					& comboBoxMarca.getSelectedItem().toString().length() != 0
					|| textFieldNovaMarca.getText().length() != 0 
					& textFieldDescricao.getText().length() != 0 
					& textFieldQuantidade.getText().length() > 0
					& textFieldPreco.getText().length() > 0) {
				btnPlus.setEnabled(true);
			} else {
				btnPlus.setEnabled(false);
			}
		} else {
			if (comboBoxCodRecebido.getSelectedItem().toString().length() != 0
					|| textFieldCodRecebido.getText().length() != 0
					& comboBoxMarca.getSelectedItem().toString().length() != 0
					|| textFieldNovaMarca.getText().length() != 0 
					& textFieldDescricao.getText().length() != 0 
					& textFieldQuantidade.getText().length() > 0
					& textFieldPreco.getText().length() > 0) {
				btnPlus.setEnabled(true);
			} else {
				btnPlus.setEnabled(false);
			}
		}	
	}
}
