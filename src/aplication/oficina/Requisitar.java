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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.DaoFactory;
import dao.MedidaDao;
import dao.MilitarDao;
import dao.MontadoraDao;
import dao.OrdemManutencaoDao;
import dao.ProdutoDao;
import dao.RequisicaoDao;
import dao.ViaturaDao;
import entities.Militar;
import entities.OrdemManutencao;
import entities.Produto;
import entities.Requisicao;
import entities.Viatura;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Requisitar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField textFieldCodMontadora;
	public static JTextField textFieldMarca;
	public static JTextField textFieldCodRecebido;
	public static JTextField textFieldDescricao;
	private JTextField textFieldQuantidade;
	public static JComboBox comboBoxMontadora;
	public static JLabel lblMontadora;
	public static JLabel lblCodMontadora;
	private JTable tableProduto;
	private JTextField textFieldPlaca;
	private JButton btnSearchPlaca;
	private JButton btnRequisitar;
	private JTextField textFieldModelo;
	private static Militar militarLogin;
	private JTextField textFieldOM;
	private JTextField textFieldMilitar;
	private JFormattedTextField textFieldnBM;
	private JTextField textFieldCodCSM;
	private JTable tableProdutoRequisitar;
	private JScrollPane scrollPane_1;
	private JButton btnNovasAplicacoes;
	public static Produto produtoAplicacao;
	private JLabel lblEspaco;
	TableFilterHeader filterHeaderEstoque;
	private JLabel lblEspaco_1;
	private JLabel lblEspaco_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Requisitar frame = new Requisitar();
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

	public Requisitar() throws ParseException {
		setTitle("REQUISI\u00C7\u00C3O");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Requisitar.class.getResource("/image/user_interface/requisition_packaeg.png")));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 860, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "DESEJA FECHAR A JANELA SEM SALVAR AS ALTERAÇÕES",
						"FECHAR JANELA", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			}
		});

		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		MilitarDao militarDao = DaoFactory.createMilitarDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();
		OrdemManutencaoDao ordemManutencaoDao = DaoFactory.createOrdemServicoDao();
		MedidaDao medidaDao = DaoFactory.createMedidaDao();

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);

		JLabel labelTitulo = new JLabel("REQUISI\u00C7\u00C3O");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		labelTitulo.setAlignmentX(0.5f);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);

		JLabel label = new JLabel("ITENS REQUISITADOS");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 14));

		lblMontadora = new JLabel("MONTADORA");
		lblMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setModel(modelcombobox);

		lblCodMontadora = new JLabel("C\u00D3DIGO MONTADORA");
		lblCodMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

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
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel labelMarca = new JLabel("MARCA RECEBIDA");
		labelMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldMarca = new JTextField();
		textFieldMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldCodRecebido = new JTextField();
		textFieldCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblCdoRecebido = new JLabel("C\u00D3DIGO RECEBIDO");
		lblCdoRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdoRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldDescricao = new JTextField();
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblQuant = new JLabel("QUANTIDADE");
		lblQuant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuant.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuantidade.setColumns(1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 12));

		tableProduto = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableProduto, AutoChoices.ENABLED);
		String[] columnNames = { "COD.CSM", "MONTADORA", "COD.MONT.", "MARCA", "COD.RECEBIDO", "DESCRIÇÃO",
				"QUANTIDADE", "" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProduto.setModel(model);
		tableProduto.getColumnModel().getColumn(0).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(0).setMaxWidth(45);
		tableProduto.getColumnModel().getColumn(1).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(1).setMaxWidth(45);
		tableProduto.getColumnModel().getColumn(7).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(7).setMaxWidth(45);
		scrollPane.setViewportView(tableProduto);

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (tableProduto.getRowCount() != 0 && textFieldMilitar.getText().length() != 0
						&& textFieldOM.getText().length() != 0) {
					btnRequisitar.setEnabled(true);
				} else {
					btnRequisitar.setEnabled(false);
				}
			}
		});

		JButton btnPlus = new JButton("");
		btnPlus.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/up.png")));
		btnPlus.setEnabled(false);
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String unidade = new String();
				Produto produto = new Produto();

				produto = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				unidade = medidaDao.findById(produto.getMedida().getId_medida()).getMedida();

				// VERIFICAR A EXISTENCIA DE PRODUTO NO BANCO
				if (produto.getQuantidade() >= Integer.parseInt(textFieldQuantidade.getText())) {

					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							textFieldCodMontadora.getText(), textFieldMarca.getText(), textFieldCodRecebido.getText(),
							textFieldDescricao.getText(), textFieldQuantidade.getText(), unidade };

					model.addRow(row);
					textFieldCodCSM.setText("");
					comboBoxMontadora.setSelectedItem("");
					textFieldCodMontadora.setText("");
					textFieldMarca.setText("");
					textFieldCodRecebido.setText("");
					textFieldDescricao.setText("");
					textFieldQuantidade.setText("");

					btnPlus.setEnabled(false);
					if (textFieldMilitar.getText().length() != 0) {
						btnRequisitar.setEnabled(true);
					}

				} else {
					JOptionPane.showMessageDialog(null, "QUANTIDADE NO ESTOQUE DO CSM É: " + produto.getQuantidade()
							+ "\nCONFERIR O VALOR SOLICITADO", "ALERTA", 1);
				}
			}
		});

		JButton btnMinus = new JButton("");
		btnMinus.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/trash.png")));
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (tableProduto.getSelectedRow() != -1) {
					model.removeRow(tableProduto.getSelectedRow());
				}
			}
		});

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
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);

		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));

		btnRequisitar = new JButton("REQUISITAR");
		btnRequisitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// CRIANDO DATA E HORA
				Date data = new java.sql.Date(new java.util.Date().getTime());
				Time hora = new java.sql.Time(new java.util.Date().getTime());

				// SETAR ORDEM DE SERVIÇO
				OrdemManutencao om = new OrdemManutencao(null, textFieldOM.getText(), data, hora, null, null, null, null, null, null, null, null, null);
				if (ordemManutencaoDao.findByOM(om).getId_om() != null) {
					om = ordemManutencaoDao.findByOM(om);
				} else {
					ordemManutencaoDao.insert(om);
				}

				for (Integer i = 0; i < tableProduto.getRowCount(); i++) {

					// PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
					militarLogin = militarDao.findByNBM(textFieldnBM.getText());

					// SETAR O PRODUTO
					Produto produto = new Produto();
					produto = produtoDao.findById(Integer.parseInt(tableProduto.getValueAt(i, 0).toString()));

					// SETAR REQUISIÇÃO
					Requisicao requisitar = new Requisicao(null, false, data, hora,
							Integer.parseInt((String) tableProduto.getValueAt(i, 6)), produto,
							viaturaDao.findByPlaca(textFieldPlaca.getText()), om, null, militarLogin);

					requisicaoDao.insert(requisitar);
				}

				JOptionPane.showMessageDialog(null,"REQUISIÇÃO O.M. " + textFieldOM.getText() + " REALIZADA COM SUCESSO", "REQUISIÇÃO", 1);

				dispose();
			}
		});
		btnRequisitar
				.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/requisition_packaeg.png")));
		btnRequisitar.setMargin(new Insets(2, 5, 2, 5));
		btnRequisitar.setIconTextGap(0);
		btnRequisitar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRequisitar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRequisitar.setEnabled(false);

		btnSearchPlaca = new JButton("");
		btnSearchPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Viatura vtr = viaturaDao.findByPlaca((String) textFieldPlaca.getText());
				textFieldModelo.setText(vtr.getModelo());
			}
		});
		btnSearchPlaca.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/search.png")));

		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setEditable(false);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(10);

		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNOM = new JLabel("ORDEM DE MANUTEN\u00C7\u00C3O");
		lblNOM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNOM.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldOM = new JTextField();
		textFieldOM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldOM.getText().toUpperCase();
				textFieldOM.setText(temp);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();

				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE) || textFieldOM.getText().length() > 14) {
					e.consume();
				}
			}
		});
		textFieldOM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldOM.setColumns(10);

		JLabel label_1 = new JLabel("N\u00BA BM");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldnBM = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel label_2 = new JLabel("MILITAR");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldMilitar = new JTextField();
		textFieldMilitar.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar.setEditable(false);
		textFieldMilitar.setBackground(Color.WHITE);

		JButton btnSearchMilitar = new JButton("");
		btnSearchMilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM.getText()) != null) {
					textFieldMilitar.setText(militarDao.findByNBM(textFieldnBM.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR",
							"ERRO", 0);
				}
			}
		});
		btnSearchMilitar.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/search_user.png")));

		JLabel lblCodCSM = new JLabel("C\u00D3DIGO CSM");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodCSM.setEditable(false);

		scrollPane_1 = new JScrollPane();

		tableProdutoRequisitar = new JTable();
		DefaultTableModel modelProduto = new DefaultTableModel();
		scrollPane_1.setViewportView(tableProdutoRequisitar);
		tableProdutoRequisitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableProdutoRequisitar.getSelectedRow();
				if (tableProdutoRequisitar.getValueAt(i, 4) != null) {
					comboBoxMontadora.setSelectedItem(tableProdutoRequisitar.getValueAt(i, 3).toString());
					textFieldCodMontadora.setText(tableProdutoRequisitar.getValueAt(i, 4).toString());
				} else {
					comboBoxMontadora.setSelectedItem(null);
					textFieldCodMontadora.setText(null);
				}
				textFieldCodCSM.setText(tableProdutoRequisitar.getValueAt(i, 1).toString());
				textFieldDescricao.setText(tableProdutoRequisitar.getValueAt(i, 2).toString());
				textFieldMarca.setText(tableProdutoRequisitar.getValueAt(i, 5).toString());
				textFieldCodRecebido.setText(tableProdutoRequisitar.getValueAt(i, 6).toString());

				btnNovasAplicacoes.setEnabled(true);
			}
		});
		filterHeaderEstoque = new TableFilterHeader(tableProdutoRequisitar, AutoChoices.ENABLED);
		filterHeaderEstoque.setPosition(Position.TOP);
		filterHeaderEstoque.setBackground(Color.white);
		tableProdutoRequisitar.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProdutoRequisitar.setModel(requisicaoDao.tableProdutoRequisicao(modelProduto));
		tableProdutoRequisitar.setModel(modelProduto);
		tableProdutoRequisitar.getColumnModel().getColumn(0).setMinWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(0).setMaxWidth(45);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setMinWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setMaxWidth(45);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setMinWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setMaxWidth(45);

		btnNovasAplicacoes = new JButton("APLICA\u00C7\u00D5ES");
		btnNovasAplicacoes.setFont(new Font("Arial", Font.BOLD, 14));
		btnNovasAplicacoes.setEnabled(false);
		btnNovasAplicacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovasAplicacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				produtoAplicacao = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				NovasAplicacoesOficina exibir = new NovasAplicacoesOficina();
				exibir.setVisible(true);
			}
		});
		btnNovasAplicacoes
				.setIcon(new ImageIcon(Requisitar.class.getResource("/image/user_interface/desktop_package.png")));

		lblEspaco = new JLabel(" ");

		lblEspaco_1 = new JLabel("");

		lblEspaco_2 = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNOM, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(115).addComponent(lblPlaca,
										GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(102).addComponent(lblModelo,
										GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGap(8)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldOM, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 198,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 198,
										GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addComponent(btnSearchPlaca, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGap(48)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(2)
										.addComponent(textFieldnBM, GroupLayout.PREFERRED_SIZE, 90,
												GroupLayout.PREFERRED_SIZE)
										.addGap(8).addComponent(btnSearchMilitar, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldMilitar, GroupLayout.PREFERRED_SIZE, 270,
										GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(20).addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(500).addComponent(labelMarca,
								GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(499).addComponent(lblQuant,
								GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(499).addComponent(lblCdoRecebido,
								GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(499).addComponent(lblDescricao,
								GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(499).addComponent(lblMontadora,
								GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(500).addComponent(lblCodCSM,
								GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(500).addComponent(lblCodMontadora,
								GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addGap(130)))
						.addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 160,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 58,
										GroupLayout.PREFERRED_SIZE))
						.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(41)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE).addGap(356)
						.addComponent(lblEspaco, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE).addGap(39)
						.addComponent(btnNovasAplicacoes, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(33))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(20)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE).addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(108)
						.addComponent(lblEspaco_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE).addGap(135)
						.addComponent(btnRequisitar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(106).addComponent(lblEspaco_2, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addGap(141)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(3)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(30).addComponent(separator_1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(12)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textFieldOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(25).addComponent(btnSearchPlaca,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(15)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(14)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(8).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(7).addComponent(textFieldnBM,
										GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSearchMilitar, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE))
								.addGap(9).addComponent(textFieldMilitar, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(100)
								.addComponent(labelMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(76)
								.addComponent(lblQuant, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(133).addComponent(lblCdoRecebido,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(166).addComponent(lblDescricao,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(34).addComponent(lblMontadora,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(67).addComponent(lblCodMontadora,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE).addGroup(
								gl_contentPane.createSequentialGroup()
										.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(11)
										.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10).addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)))
				.addGap(6)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(5).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNovasAplicacoes, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEspaco)))
				.addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(15)
								.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGap(40)
								.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(12).addComponent(btnRequisitar,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblEspaco_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblEspaco_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		// VALIDAÇÃO PARA LIBERAR O BOTÃO PLUS
		textFieldMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuantidade.getText().length() != 0) {
					btnPlus.setEnabled(true);
				} else {
					btnPlus.setEnabled(false);
				}

				String temp = textFieldMarca.getText().toUpperCase();
				textFieldMarca.setText(temp);
			}
		});

		textFieldCodRecebido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuantidade.getText().length() != 0) {
					btnPlus.setEnabled(true);
				} else {
					btnPlus.setEnabled(false);
				}

				String temp = textFieldCodRecebido.getText().toUpperCase();
				textFieldCodRecebido.setText(temp);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE)) {
					e.consume();
				}

			}
		});

		textFieldDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuantidade.getText().length() != 0) {
					btnPlus.setEnabled(true);
				} else {
					btnPlus.setEnabled(false);
				}

				String temp = textFieldDescricao.getText().toUpperCase();
				textFieldDescricao.setText(temp);
			}
		});

		textFieldQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldMarca.getText().length() != 0 & textFieldCodRecebido.getText().length() != 0
						& textFieldDescricao.getText().length() != 0 & textFieldQuantidade.getText().length() != 0) {
					btnPlus.setEnabled(true);
				} else {
					btnPlus.setEnabled(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE)
						|| number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
	}
}
