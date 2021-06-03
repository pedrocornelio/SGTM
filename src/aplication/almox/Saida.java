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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import aplication.Principal;
import dao.CodigoMontadoraDao;
import dao.CodigoRecebidoDao;
import dao.DaoFactory;
import dao.HistoricoProdutoSaidaDao;
import dao.LoginDao;
import dao.MedidaDao;
import dao.MilitarDao;
import dao.MontadoraDao;
import dao.ProdutoDao;
import dao.RequisicaoDao;
import dao.UnidadeDao;
import entities.HistoricoProdutoSaida;
import entities.Login;
import entities.Produto;
import entities.Unidade;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Saida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMilitar;
	private JTextField textFieldnBM;
	private JTable tableProduto;
	public static JComboBox comboBoxMontadora;
	public static JComboBox comboBoxCodMontadora;
	public static JComboBox comboBoxMarca;
	public static JComboBox comboBoxCodRecebido;
	public static JTextField textFieldDescricao;
	private JTextField textFieldQuantidade;
	private JButton btnSaida;
	private static Login login;
	public static JLabel lblMontadora;
	public static JLabel lblCodMontadora;
	private JTextField textFieldCodCSM;
	private JTable tableProdutoSaida;
	private JButton btnPlus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Saida frame = new Saida();
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
	public Saida() throws ParseException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Saida.class.getResource("/image/user_interface/requisition_car.png")));
		setTitle("SA\u00CDDA");
		setFont(new Font("Arial", Font.PLAIN, 12));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 860, 650);
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

		UnidadeDao unidadeDao = DaoFactory.createUnidadeDao();
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		MilitarDao militarDao = DaoFactory.createMilitarDao();
		HistoricoProdutoSaidaDao historicoSaidaDao = DaoFactory.createHistoricoProdutoSaidaDao();
		MedidaDao medidaDao = DaoFactory.createMedidaDao();
		CodigoMontadoraDao codigoMontadoraDao = DaoFactory.createCodigoMontadoraDao();
		CodigoRecebidoDao codigoRecebidoDao = DaoFactory.createCodigoRecebidoDao();
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel("DADOS DE SA\u00CDDA DO ITEM");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);

		JLabel lblSolicitante = new JLabel("SOLICITANTE - UNIDADE DESTINO");
		lblSolicitante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSolicitante.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSolicitante.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblNBM = new JLabel("N\u00BA BM");
		lblNBM.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldnBM = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblMilitar = new JLabel("MILITAR");
		lblMilitar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldMilitar = new JTextField();
		textFieldMilitar.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar.setBackground(Color.WHITE);
		textFieldMilitar.setEditable(false);
		textFieldMilitar.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblUnidade = new JLabel("UNIDADE");
		lblUnidade.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUnidade.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox comboBoxOBM = new JComboBox();
		comboBoxOBM.setBackground(Color.WHITE);
		DefaultComboBoxModel unidadeModelComboBox = new DefaultComboBoxModel();
		unidadeDao.comboBoxUnidade(unidadeModelComboBox);
		comboBoxOBM.setModel(unidadeModelComboBox);
		comboBoxOBM.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton btnSearchMilitar = new JButton("");
		btnSearchMilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (militarDao.findByNBM(textFieldnBM.getText()) != null) {
					textFieldMilitar.setText(militarDao.findByNBM(textFieldnBM.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR NÚMERO BM", "IDENTIFICAÇÃO", 1);
				}
			}
		});
		btnSearchMilitar.setIcon(new ImageIcon(Saida.class.getResource("/image/user_interface/search_user.png")));

		JLabel lblItensRequisitados = new JLabel("ITENS REQUISITADOS");
		lblItensRequisitados.setHorizontalAlignment(SwingConstants.CENTER);
		lblItensRequisitados.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblCodCSM = new JLabel("C\u00D3DIGO CSM");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodCSM.setEditable(false);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));

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

		comboBoxCodMontadora = new JComboBox();
		DefaultComboBoxModel CodMontadoraModelComboBox = new DefaultComboBoxModel();
		comboBoxCodMontadora.setModel(CodMontadoraModelComboBox);
		comboBoxCodMontadora.setBackground(Color.WHITE);
		comboBoxCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblMarca = new JLabel("MARCA RECEBIDA*");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxMarca = new JComboBox();
		DefaultComboBoxModel marcaModelComboBox = new DefaultComboBoxModel();
		comboBoxMarca.setModel(marcaModelComboBox);
		comboBoxMarca.setBackground(Color.WHITE);
		comboBoxMarca.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblCodRecebido = new JLabel("C\u00D3DIGO RECEBIDO*");
		lblCodRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		comboBoxCodRecebido = new JComboBox();
		DefaultComboBoxModel CodRecebidoModelComboBox = new DefaultComboBoxModel();
		comboBoxCodRecebido.setModel(CodRecebidoModelComboBox);
		comboBoxCodRecebido.setBackground(Color.WHITE);
		comboBoxCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O*");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldDescricao = new JTextField();
		textFieldDescricao.setEditable(false);
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblQuant = new JLabel("QUANTIDADE*");
		lblQuant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuant.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuantidade.setColumns(1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 12));

		tableProduto = new JTable();
		TableFilterHeader filterHeader = new TableFilterHeader(tableProduto, AutoChoices.ENABLED);
		String[] columnNames = { "COD.CSM", "MONTADORA", "COD.MONT.", "MARCA", "COD.RECEBIDO", "DESCRIÇÃO", "QUANTIDADE", "" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProduto.setModel(model);
		tableProduto.getColumnModel().getColumn(0).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(0).setMaxWidth(45);
		tableProduto.getColumnModel().getColumn(6).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(6).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(6).setMaxWidth(45);
		tableProduto.getColumnModel().getColumn(7).setMinWidth(40);
		tableProduto.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableProduto.getColumnModel().getColumn(7).setMaxWidth(45);
		scrollPane.setViewportView(tableProduto);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		tableProdutoSaida = new JTable();
		DefaultTableModel modelProduto = new DefaultTableModel();
		tableProdutoSaida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableProdutoSaida.getSelectedRow();
				comboBoxMontadora.setSelectedItem(tableProdutoSaida.getValueAt(i, 2).toString());
				DefaultComboBoxModel CodMontadoraModelComboBox = new DefaultComboBoxModel();
				comboBoxCodMontadora.setModel(CodMontadoraModelComboBox);
				codigoMontadoraDao.comboBoxCodigoMontadora(CodMontadoraModelComboBox, Integer.parseInt(tableProdutoSaida.getValueAt(i, 1).toString()));

				textFieldCodCSM.setText(tableProdutoSaida.getValueAt(i, 1).toString());
				textFieldDescricao.setText(tableProdutoSaida.getValueAt(i, 2).toString());
				
				DefaultComboBoxModel marcaModelComboBox = new DefaultComboBoxModel();
				comboBoxMarca.setModel(marcaModelComboBox);
				codigoRecebidoDao.comboBoxMarca(marcaModelComboBox, Integer.parseInt(tableProdutoSaida.getValueAt(i, 1).toString()));

				DefaultComboBoxModel CodRecebidoModelComboBox = new DefaultComboBoxModel();
				comboBoxCodRecebido.setModel(CodRecebidoModelComboBox);
				codigoRecebidoDao.comboBoxCodigoRecebido(CodRecebidoModelComboBox, Integer.parseInt(tableProdutoSaida.getValueAt(i, 1).toString()));

			}
		});
		TableFilterHeader filterHeaderEstoque = new TableFilterHeader(tableProdutoSaida, AutoChoices.ENABLED);
		filterHeaderEstoque.setPosition(Position.TOP);
		filterHeaderEstoque.setBackground(Color.white);
		tableProdutoSaida.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProdutoSaida.setModel(requisicaoDao.tableProdutoRequisicao(modelProduto));
		tableProdutoSaida.setModel(modelProduto);
		tableProdutoSaida.getColumnModel().getColumn(1).setMinWidth(40);
		tableProdutoSaida.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableProdutoSaida.getColumnModel().getColumn(1).setMaxWidth(45);
		scrollPane_1.setViewportView(tableProdutoSaida);

		btnSaida = new JButton("SA\u00CDDA DE ITENS");
		btnSaida.setEnabled(false);
		btnSaida.setIcon(new ImageIcon(Saida.class.getResource("/image/user_interface/requisition_car.png")));
		btnSaida.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (Integer i = 0; i < tableProduto.getRowCount(); i++) {

					// PARA TRAVAR O MILITAR QUE ESTÁ INSERINDO O PRODUTO NO BANCO
					String nBM = Principal.textFieldUser.getText();
					login = loginDao.findByNBM(nBM);

					for (i = 0; i < tableProduto.getRowCount(); i++) {

						// SETAR O PRODUTO PARA SER ATUALIZADO
						Produto produto = new Produto();
						produto = produtoDao.findById(Integer.parseInt(tableProduto.getValueAt(i, 0).toString()));

						// ATUALIZA O PRODUTO
						produto.setQuantidade(produto.getQuantidade() - Integer.parseInt((String) tableProduto.getValueAt(i, 6)));
						produtoDao.updateQuantidade(produto);

						// CRIANDO O HISTORICO DE SAIADA DO ITEM
						Date data = new java.sql.Date(new java.util.Date().getTime());
						Time hora = new java.sql.Time(new java.util.Date().getTime());
						HistoricoProdutoSaida saida = new HistoricoProdutoSaida(null, data, hora,
								Integer.parseInt((String) tableProduto.getValueAt(i, 6)), login, produto, null,
								unidadeDao.findByUnidade((String) comboBoxOBM.getSelectedItem()),
								militarDao.findByNBM((String) textFieldnBM.getText()));
						historicoSaidaDao.insertHistSaidaUnidade(saida);

					}

					// CRIAÇÃO DO ARQUIVO EM DOCX
					try {
						@SuppressWarnings("resource")
						XWPFDocument document = new XWPFDocument();
						Date data = new java.sql.Date(new java.util.Date().getTime());
						Locale local = new Locale("pt", "BR");
						DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
						FileOutputStream out = new FileOutputStream(new File("c:\\SGTM\\" + data + " - RECIBO " + comboBoxOBM.getSelectedItem() + ".docx"));
						
						XWPFParagraph top = document.createParagraph();
						XWPFRun cbmmg = top.createRun();
						String name = "CBMMG";
						InputStream pic = new FileInputStream("c:/sgtm/recibo.png");
						cbmmg.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, name, Units.toEMU(155), Units.toEMU(80));
						top.setAlignment(ParagraphAlignment.CENTER);
						
						XWPFParagraph paragraph = document.createParagraph();
						XWPFRun cabecalho = paragraph.createRun();
						cabecalho.addBreak();
						cabecalho.setText("CENTRO DE SUPRIMENTO E MANUTENÇÃO");
						cabecalho.setFontSize(12);
						cabecalho.setFontFamily("Arial");
						paragraph.setAlignment(ParagraphAlignment.CENTER);
						
						XWPFParagraph paragraph2 = document.createParagraph();
						XWPFRun titulo = paragraph2.createRun();
						titulo.addBreak();
						titulo.addBreak();
						titulo.setText("RECIBO");
						titulo.setFontSize(16);
						paragraph2.setAlignment(ParagraphAlignment.CENTER);
						
						Unidade unidade = new Unidade();
						unidade = unidadeDao.findByUnidade(comboBoxOBM.getSelectedItem().toString());
						
						XWPFParagraph paragraph3 = document.createParagraph();
						XWPFRun corpo = paragraph3.createRun();
						corpo.setText("RECEBI DO NÚCLEO DE MOTOMECANIZAÇÃO DO CSM/BM – 1401254 – OS MATERIAIS ABAIXO RELACIONADOS PARA SEREM UTILIZADOS NA FROTA DA " + comboBoxOBM.getSelectedItem() + " - " + unidade.getCodigoUndiade() + ".");
						corpo.setFontSize(12);
						paragraph3.setAlignment(ParagraphAlignment.CENTER);
						
						XWPFTable table = document.createTable();
						table.setTableAlignment(TableRowAlign.CENTER);
						XWPFTableRow row0 = table.getRow(0);
						row0.addNewTableCell().setText("");
						row0.addNewTableCell().setText("MATERIAL");
						row0.addNewTableCell().setText("");
						
						XWPFTableRow row1 = table.createRow();
						row1.getCell(0).setText("CÓDIGO");
						row1.getCell(1).setText("ITEM");
						row1.getCell(2).setText("QUANT.");
						row1.getCell(3).setText("DOC.ORIGEM");
						
						for (Integer j = 0; j < tableProduto.getRowCount(); j++) {
							XWPFTableRow row2 = table.createRow();
							XWPFTableCell cell1 = row2.getCell(1);
							cell1.setText(tableProduto.getValueAt(j, 5).toString());
							XWPFTableCell cell2 = row2.getCell(2);
							cell2.setText(tableProduto.getValueAt(j, 6).toString() + " " + tableProduto.getValueAt(j, 7).toString());
						}
						
						XWPFParagraph paragraph4 = document.createParagraph();
						XWPFRun datar = paragraph4.createRun();
						datar.addBreak();
						datar.addBreak();
						datar.addBreak();
						datar.setText("CONTAGEM, " + dateFormat.format(data).toUpperCase());
						datar.setFontSize(12);
						paragraph4.setAlignment(ParagraphAlignment.CENTER);

						XWPFParagraph paragraph5 = document.createParagraph();
						XWPFRun assinatura = paragraph5.createRun();
						assinatura.addBreak();
						assinatura.addBreak();
						assinatura.addBreak();
						assinatura.setText("_______________________________________________");
						assinatura.addBreak();
						assinatura.setText(textFieldMilitar.getText() + ", Nº BM.: " + textFieldnBM.getText());
						assinatura.addBreak();
						assinatura.setText("SOLICITANTE");
						assinatura.addBreak();
						assinatura.addBreak();
						assinatura.addBreak();
						assinatura.setText("_______________________________________________");
						assinatura.addBreak();
						assinatura.setText(login.getNome() + ", Nº BM.: " + login.getnBM());
						assinatura.addBreak();
						assinatura.setText("ALMOXARIFE");
						assinatura.setFontSize(12);
						paragraph5.setAlignment(ParagraphAlignment.CENTER);

						document.write(out);
						pic.close();
						out.close();
						
						JOptionPane.showMessageDialog (null, "RECIBO GERADO COM SUCESSO, CONFERIR EM C:\\SGTM", "RECIBO",1);

					} catch (Exception event) {
						JOptionPane.showMessageDialog(null, "ERRO AO GERAR O RECIBO", "ERRO", 0);
					}

					// ATUALIZANDO AS TABELAS DO ESTOQUE
					Almoxarifado.btnRefresh.doClick();

					dispose();
				}
			}
		});

		btnPlus = new JButton("");
		btnPlus.setEnabled(false);
		btnPlus.setIcon(new ImageIcon(Saida.class.getResource("/image/user_interface/up.png")));
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String medida = new String();
				Produto produto = new Produto();
				
				produto = produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText()));
				medida = medidaDao.findById(produto.getMedida().getId_medida()).getMedida();

				// VERIFICAR A EXISTENCIA DE PRODUTO NO BANCO
				if (produto.getQuantidade() >= Integer.parseInt(textFieldQuantidade.getText())) {

					Object[] row = { textFieldCodCSM.getText(), comboBoxMontadora.getSelectedItem(),
							comboBoxCodMontadora.getSelectedItem(), comboBoxMarca.getSelectedItem(), comboBoxCodRecebido.getSelectedItem(),
							textFieldDescricao.getText(), textFieldQuantidade.getText(), medida };

					model.addRow(row);
					textFieldCodCSM.setText("");
					comboBoxMontadora.setSelectedItem("");
					comboBoxCodMontadora.setSelectedIndex(0);
					comboBoxMarca.setSelectedIndex(0);
					comboBoxCodRecebido.setSelectedIndex(0);
					textFieldDescricao.setText("");
					textFieldQuantidade.setText("");

					btnPlus.setEnabled(false);
					if (textFieldMilitar.getText().length() != 0) {
						btnSaida.setEnabled(true);
					}

				} else {
					JOptionPane.showMessageDialog(null, "QUANTIDADE NO ESTOQUE DO CSM É: "+ produto.getQuantidade() +"\nCONFERIR O VALOR SOLICITADO", "ALERTA", 1);
				}
			}
		});

		JButton btnMinus = new JButton("");
		btnMinus.setIcon(new ImageIcon(Saida.class.getResource("/image/user_interface/trash.png")));
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (tableProduto.getSelectedRow() != -1) {
					model.removeRow(tableProduto.getSelectedRow());
				}
				if (tableProduto.getSelectedRow() == -1) {
					btnSaida.setEnabled(false);
				}
			}
		});
		
		JLabel lblObrigatorio = new JLabel("*OBS: CAMPOS OBRIGAT\u00D3RIOS");
		lblObrigatorio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObrigatorio.setFont(new Font("Arial", Font.PLAIN, 10));
		
		JLabel lblEspaco = new JLabel("  ");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(lblQuant)))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBoxCodMontadora, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBoxCodRecebido, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(130)
							.addComponent(lblEspaco, GroupLayout.PREFERRED_SIZE, 52, Short.MAX_VALUE)
							.addGap(99)
							.addComponent(btnSaida)
							.addGap(123)
							.addComponent(lblObrigatorio, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblItensRequisitados, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSolicitante)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(270)
									.addComponent(lblMilitar, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(7)
									.addComponent(textFieldnBM, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(160)
									.addComponent(textFieldMilitar, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(105)
									.addComponent(btnSearchMilitar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(30)
									.addComponent(lblNBM, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(106)
									.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxOBM, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSolicitante, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblMilitar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(textFieldnBM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(textFieldMilitar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(btnSearchMilitar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblNBM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(comboBoxOBM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(lblItensRequisitados, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblQuant, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(comboBoxMontadora, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(comboBoxCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBoxCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(btnMinus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSaida, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblObrigatorio, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblEspaco)))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				int i = tableProdutoSaida.getSelectedRow();
				if (tableProdutoSaida.isRowSelected(i)) {
					validar();
				}
				
				
				if (tableProduto.getRowCount() != 0 && textFieldMilitar.getText().length() != 0) {
					btnSaida.setEnabled(true);
				} else {
					btnSaida.setEnabled(false);
				}
			}
		});

		textFieldQuantidade.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				validar();
			}

			@Override
			public void keyPressed(KeyEvent e) {
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
		});
	}
	
	public void validar() {
		if (comboBoxCodRecebido.getSelectedItem().toString().length() != 0
				& comboBoxMarca.getSelectedItem().toString().length() != 0 
				& textFieldDescricao.getText().length() != 0 
				& textFieldQuantidade.getText().length() > 0) {
			btnPlus.setEnabled(true);
		} else {
			btnPlus.setEnabled(false);
		}
	}
}
