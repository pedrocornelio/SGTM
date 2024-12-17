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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.HistoricoEdicaoProdutoDao;
import dao.LoginDao;
import dao.MedidaDao;
import dao.MontadoraDao;
import dao.OriginalidadeDao;
import dao.ProdutoDao;
import entities.HistoricoEdicaoProduto;
import entities.Login;
import entities.Produto;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class EdicaoItem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableProdutoEntrada;
	private JTextField textFieldCodCSM;
	private JTextField textFieldCodMontadora;
	private JTextField textFieldMarca;
	private JTextField textFieldCodRecebido;
	private JTextField textFieldDescricao;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMontadora;
	private JRadioButton rdbtnGenuina;
	private JRadioButton rdbtnOriginal;
	private JRadioButton rdbtnPriLinha;
	private JButton btnEquivalencia;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdbtnConsumo;
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JComboBox<?> comboBoxMedida;
	private JButton btnFornecedor;
	public static Produto produtoEdicao;
	private JLabel lblEspaco;
	private JLabel lblEspaco_1;
	private static Login login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EdicaoItem frame = new EdicaoItem();
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
	public EdicaoItem() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EdicaoItem.class.getResource("/image/issue.png")));
		setTitle("EDI\u00C7\u00C3O DE ITEM");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 860, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);
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
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		MedidaDao medidaDao = DaoFactory.createMedidaDao();
		OriginalidadeDao originalidadeDao = DaoFactory.createOriginalidadeDao();
		HistoricoEdicaoProdutoDao histEdicaoProdutoDao = DaoFactory.createHistoricoEdicaoProdutoDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		
		JLabel lblEdioEInsero = new JLabel("EDI\u00C7\u00C3O E INSER\u00C7\u00C3O DE INFORMA\u00C7\u00D5ES DE ITEM");
		lblEdioEInsero.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdioEInsero.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEdioEInsero.setAlignmentX(0.5f);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		
		JLabel lblSeleoDoItem = new JLabel("SELE\u00C7\u00C3O DO ITEM");
		lblSeleoDoItem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSeleoDoItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleoDoItem.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();

		tableProdutoEntrada = new JTable();
		DefaultTableModel modelProduto = new DefaultTableModel();
		tableProdutoEntrada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableProdutoEntrada.getSelectedRow();
				Produto produto = new Produto();
				produto = produtoDao.findById(Integer.parseInt(tableProdutoEntrada.getValueAt(i, 0).toString()));
				if (tableProdutoEntrada.getValueAt(i, 2) != null ){
					comboBoxMontadora.setSelectedItem(tableProdutoEntrada.getValueAt(i, 2).toString());
				} else {
					comboBoxMontadora.setSelectedItem(null);
				}
				if (tableProdutoEntrada.getValueAt(i, 3) != null ){
					textFieldCodMontadora.setText(tableProdutoEntrada.getValueAt(i, 3).toString());
				} else {
					textFieldCodMontadora.setText(null);
				}
				textFieldCodCSM.setText(tableProdutoEntrada.getValueAt(i, 0).toString());
				textFieldDescricao.setText(tableProdutoEntrada.getValueAt(i, 1).toString());
				textFieldMarca.setText(tableProdutoEntrada.getValueAt(i, 4).toString());
				textFieldCodRecebido.setText(tableProdutoEntrada.getValueAt(i, 5).toString());
				comboBoxMedida.setSelectedItem(produto.getMedida().getMedida());
				
				if(produto.getOriginalidade().getIdOriginalidade() == 1) {
					rdbtnGenuina.doClick();
				} else {
					if(produto.getOriginalidade().getIdOriginalidade() == 2) {
						rdbtnOriginal.doClick();
					} else {
						if(produto.getOriginalidade().getIdOriginalidade() == 3) {
							rdbtnPriLinha.doClick();
						} if(produto.getOriginalidade().getIdOriginalidade() == 4) {
							rdbtnConsumo.doClick();
						}
					}
				}
				
				btnEquivalencia.setEnabled(true);
				btnAtualizar.setEnabled(true);
				btnFornecedor.setEnabled(true);
			}
		});
		TableFilterHeader filterHeaderEstoque = new TableFilterHeader(tableProdutoEntrada, AutoChoices.ENABLED);
		filterHeaderEstoque.setPosition(Position.TOP);
		filterHeaderEstoque.setBackground(Color.white);
		tableProdutoEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProdutoEntrada.setModel(produtoDao.tableProdutoEntrada(modelProduto));
		tableProdutoEntrada.getColumnModel().getColumn(0).setMinWidth(40);
		tableProdutoEntrada.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProdutoEntrada.getColumnModel().getColumn(0).setMaxWidth(45);
		scrollPane.setViewportView(tableProdutoEntrada);
		
		JLabel lblCodCSM = new JLabel("C\u00D3DIGO CSM");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodCSM.setEditable(false);
		
		JLabel lblMontadora = new JLabel("MONTADORA");
		lblMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		
		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.setEnabled(false);
		comboBoxMontadora.setEditable(true);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setModel(modelcombobox);
		
		JLabel lblCodMontadora = new JLabel("C\u00D3DIGO MONTADORA");
		lblCodMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodMontadora = new JTextField();
		textFieldCodMontadora.setEditable(false);
		textFieldCodMontadora.setForeground(Color.BLACK);
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
		textFieldCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel labelMarca = new JLabel("MARCA RECEBIDA");
		labelMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldMarca = new JTextField();
		textFieldMarca.setEditable(false);
		textFieldMarca.setForeground(Color.BLACK);
		textFieldMarca.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				String temp = textFieldMarca.getText().toUpperCase();
				textFieldMarca.setText(temp);
			}
		});
		textFieldMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblCdoRecebido = new JLabel("C\u00D3DIGO RECEBIDO");
		lblCdoRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdoRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodRecebido = new JTextField();
		textFieldCodRecebido.setEditable(false);
		textFieldCodRecebido.setForeground(Color.BLACK);
		textFieldCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		rdbtnGenuina = new JRadioButton("GENU\u00CDNA");
		buttonGroup.add(rdbtnGenuina);
		rdbtnGenuina.setFont(new Font("Arial", Font.PLAIN, 12));
		
		rdbtnOriginal = new JRadioButton("ORIGINAL");
		buttonGroup.add(rdbtnOriginal);
		rdbtnOriginal.setFont(new Font("Arial", Font.PLAIN, 12));
		
		rdbtnPriLinha = new JRadioButton("1\u00AA LINHA");
		buttonGroup.add(rdbtnPriLinha);
		rdbtnPriLinha.setFont(new Font("Arial", Font.PLAIN, 12));
		
		rdbtnConsumo = new JRadioButton("CONSUMO");
		buttonGroup.add(rdbtnConsumo);
		rdbtnConsumo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnEquivalencia = new JButton("EQUIVAL\u00CANCIA");
		btnEquivalencia.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEquivalencia.setEnabled(false);
		btnEquivalencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoEdicao = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				Equivalencia exibir = new Equivalencia();
				exibir.setVisible(true);
			}
		});
		btnEquivalencia.setIcon(new ImageIcon(EdicaoItem.class.getResource("/image/equivalence.png")));
		btnEquivalencia.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto();
				produto = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				
				// PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
				String nBM = "173.069-6";//Principal.textFieldUser.getText();
				login = loginDao.findByNBM(nBM);
				
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
				
				// CRAINDO HISTÓRICO DE EDIÇÃO
				Date data = new java.sql.Date(new java.util.Date().getTime());
				Time hora = new java.sql.Time(new java.util.Date().getTime());
				HistoricoEdicaoProduto histEdicaoProduto = new HistoricoEdicaoProduto(null, data, hora,
						produto.getDescricao(), produto, produto.getOriginalidade(), produto.getMedida(),
						null, null, null, login);
				
				histEdicaoProdutoDao.insertDescOriginalMedida(histEdicaoProduto);
				
				// SETAR DADOS PARA ATUALIZAR
				produto.setDescricao(textFieldDescricao.getText());
				produto.setOriginalidade(originalidadeDao.findById(original));
				produto.setMedida(medidaDao.findByMedida(comboBoxMedida.getSelectedItem().toString()));

				// COMANDO PARA ATUALIZAR
				produtoDao.updateProduto(produto);
				
				JOptionPane.showMessageDialog(null, "AS INFORMAÇÕES DO ITEM COD.CSM.  "+ produto.getId_produto() +"  SÃO: " 
						+"\nDESCRIÇÃO: " + produto.getDescricao()
						+"\n =>  "  + produto.getMedida().getMedida()
						+"\n =>  " + produto.getOriginalidade().getOriginalidade(),
				"ITEM ATUALIZADO", 1);
				
				textFieldCodCSM.setText("");
				textFieldCodMontadora.setText("");
				textFieldCodRecebido.setText("");
				textFieldDescricao.setText("");
				textFieldMarca.setText("");
				comboBoxMedida.setSelectedItem("unidade");
				comboBoxMontadora.setSelectedItem("");
				
				DefaultTableModel modelProduto = new DefaultTableModel();
				tableProdutoEntrada.setModel(produtoDao.tableProdutoEntrada(modelProduto));
				tableProdutoEntrada.getColumnModel().getColumn(0).setMinWidth(40);
				tableProdutoEntrada.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableProdutoEntrada.getColumnModel().getColumn(0).setMaxWidth(45);
				
			}
		});
		btnAtualizar.setEnabled(false);
		btnAtualizar.setIcon(new ImageIcon(EdicaoItem.class.getResource("/image/desktop_package.png")));
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnEditar = new JButton("FECHAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setIcon(new ImageIcon(EdicaoItem.class.getResource("/image/issue.png")));
		
		comboBoxMedida = new JComboBox();
		comboBoxMedida.setBackground(Color.WHITE);
		DefaultComboBoxModel modelComboBoxMedida = new DefaultComboBoxModel();
		medidaDao.comboBoxMedida(modelComboBoxMedida);
		comboBoxMedida.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMedida.setModel(modelComboBoxMedida);
		
		btnFornecedor = new JButton("FORNECEDOR");
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoEdicao = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				aplication.almox.Fornecedor exibir = new aplication.almox.Fornecedor();
				exibir.setVisible(true);
			}
		});
		btnFornecedor.setEnabled(false);
		btnFornecedor.setFont(new Font("Arial", Font.BOLD, 14));
		btnFornecedor.setIcon(new ImageIcon(EdicaoItem.class.getResource("/image/provider.png")));
		
		lblEspaco = new JLabel("    ");
		
		lblEspaco_1 = new JLabel("    ");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(lblEdioEInsero, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblSeleoDoItem, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCdoRecebido, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(142)
									.addComponent(comboBoxMedida, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(99)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnPriLinha, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnGenuina, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
									.addGap(22)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOriginal)
										.addComponent(rdbtnConsumo))))
							.addGap(17))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(65))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnEquivalencia, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFornecedor, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
							.addGap(50))))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(133)
					.addComponent(lblEspaco, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(155)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblEspaco_1, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(261))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblEdioEInsero, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblSeleoDoItem, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEspaco)
								.addComponent(lblEspaco_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdoRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(comboBoxMedida, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(22)
									.addComponent(rdbtnPriLinha))
								.addComponent(rdbtnGenuina)
								.addComponent(rdbtnOriginal)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(22)
									.addComponent(rdbtnConsumo)))
							.addGap(16)
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btnFornecedor, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEquivalencia, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		contentPane.setLayout(gl_contentPane);
		
		textFieldCodRecebido.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0) {
					btnAtualizar.setEnabled(true);
				} else {
					btnAtualizar.setEnabled(false);
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
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0) {
					btnAtualizar.setEnabled(true);
				} else {
					btnAtualizar.setEnabled(false);
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

	}
}
