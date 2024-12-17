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
import java.sql.Time;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import aplication.Principal;
import dao.DaoFactory;
import dao.HistoricoProdutoSaidaDao;
import dao.LoginDao;
import dao.MilitarDao;
import dao.ProdutoDao;
import dao.RequisicaoDao;
import entities.HistoricoProdutoSaida;
import entities.Login;
import entities.Militar;
import entities.Produto;
import entities.Requisicao;
import entities.Viatura;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;


public class RequisicaoPendente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRequisicaoPendente;
	private JTextField textFieldPlaca;
	private JTextField textFieldQuant;
	private JTextField textFieldDescricao;
	private JTextField textFieldSolicitante;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnRetirar;
	private JButton btnAtualizar;
	private JTextField textFieldnOM;
	private JTextField textFieldModelo;
	private static Login login;
	private JTextField textFieldCodMontadora;
	private JTextField textFieldMarca;
	private JTextField textFieldCodRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequisicaoPendente frame = new RequisicaoPendente();
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
	public RequisicaoPendente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RequisicaoPendente.class.getResource("/image/requisition_packaeg.png")));
		setTitle("REQUISI\u00C7\u00C3O PENDENTE");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "DESEJA FECHAR A JANELA SEM SALVAR AS ALTERA합ES", "FECHAR JANELA", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		        	Almoxarifado.btnRefresh.doClick();
		        }
		    }
		});
		
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		MilitarDao militarDao = DaoFactory.createMilitarDao();
		HistoricoProdutoSaidaDao historicoSaidaDao = DaoFactory.createHistoricoProdutoSaidaDao();
		LoginDao loginDao = DaoFactory.createLoginDao();
		
		JLabel lblRe = new JLabel("REQUISI\u00C7\u00C3O PENDENTE");
		lblRe.setHorizontalAlignment(SwingConstants.CENTER);
		lblRe.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRe.setAlignmentX(0.5f);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tableRequisicaoPendente = new JTable();
		scrollPane.setViewportView(tableRequisicaoPendente);
		TableFilterHeader filterHeader = new TableFilterHeader(tableRequisicaoPendente, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableRequisicaoPendente.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelRequisicaoPendente = new DefaultTableModel();
		tableRequisicaoPendente.setModel(requisicaoDao.tableRequisicao(modelRequisicaoPendente));
		tableRequisicaoPendente.getColumnModel().getColumn(0).setMinWidth(50);
		tableRequisicaoPendente.getColumnModel().getColumn(0).setPreferredWidth(55);
		tableRequisicaoPendente.getColumnModel().getColumn(0).setMaxWidth(70);
		tableRequisicaoPendente.getColumnModel().getColumn(1).setMinWidth(50);
		tableRequisicaoPendente.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableRequisicaoPendente.getColumnModel().getColumn(1).setMaxWidth(130);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setMinWidth(65);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setMaxWidth(72);
		tableRequisicaoPendente.getColumnModel().getColumn(3).setMinWidth(30);
		tableRequisicaoPendente.getColumnModel().getColumn(3).setPreferredWidth(35);
		tableRequisicaoPendente.getColumnModel().getColumn(3).setMaxWidth(40);
		tableRequisicaoPendente.getColumnModel().getColumn(8).setMinWidth(45);
		tableRequisicaoPendente.getColumnModel().getColumn(8).setPreferredWidth(47);
		tableRequisicaoPendente.getColumnModel().getColumn(8).setMaxWidth(50);
		tableRequisicaoPendente.getColumnModel().getColumn(9).setMinWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(9).setPreferredWidth(30);
		tableRequisicaoPendente.getColumnModel().getColumn(9).setMaxWidth(40);
		tableRequisicaoPendente.getColumnModel().getColumn(10).setMinWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(10).setPreferredWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(10).setMaxWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(12).setMinWidth(0);
		tableRequisicaoPendente.getColumnModel().getColumn(12).setPreferredWidth(0);
		tableRequisicaoPendente.getColumnModel().getColumn(12).setMaxWidth(0);
		tableRequisicaoPendente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableRequisicaoPendente.getSelectedRow();
				//SETAR A REQUISI플O
				Requisicao requisicao = new Requisicao();
				requisicao = requisicaoDao.findById(Integer.parseInt(tableRequisicaoPendente.getValueAt(i, 12).toString()));
				
				if ((Boolean) tableRequisicaoPendente.getValueAt(i, 0) != true) {
					chckbxNewCheckBox.setSelected(false);
				} else {
					chckbxNewCheckBox.setSelected(true);
				}
				textFieldnOM.setText(requisicao.getOM().getnOM());
				textFieldPlaca.setText(requisicao.getViatura().getPlaca());
				textFieldDescricao.setText(requisicao.getProduto().getDescricao());
				if (tableRequisicaoPendente.getValueAt(i, 5) != null) {
					textFieldCodMontadora.setText(tableRequisicaoPendente.getValueAt(i, 5).toString());
				} else {
					textFieldCodMontadora.setText(null);
				}
				textFieldMarca.setText(tableRequisicaoPendente.getValueAt(i, 6).toString());
				textFieldCodRecebido.setText(tableRequisicaoPendente.getValueAt(i, 7).toString());
				textFieldQuant.setText(requisicao.getQuantidade_requisicao().toString()+" "+tableRequisicaoPendente.getValueAt(i, 10).toString());
				textFieldSolicitante.setText(requisicao.getMilitar().getNome());
				textFieldModelo.setText(requisicao.getViatura().getModelo());
			}
		});
		
		btnRetirar = new JButton("FECHAR");
		btnRetirar.setIcon(new ImageIcon(RequisicaoPendente.class.getResource("/image/requisition_packaeg.png")));
		btnRetirar.setMargin(new Insets(2, 5, 2, 5));
		btnRetirar.setIconTextGap(0);
		btnRetirar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRetirar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRetirar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ATUALIZANDO AS TABELAS DO ESTOQUE
				Almoxarifado.btnRefresh.doClick();
				
				dispose();
			}
		});
		
		JLabel lblTabelaDeRequisies = new JLabel("TABELA DE REQUISI\u00C7\u00D5ES EM ABERTO");
		lblTabelaDeRequisies.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPlaca.setBackground(Color.WHITE);
		textFieldPlaca.setEditable(false);
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldQuant = new JTextField();
		textFieldQuant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQuant.setBackground(Color.WHITE);
		textFieldQuant.setEditable(false);
		textFieldQuant.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuant.setColumns(1);
		
		JLabel lblnOM = new JLabel("N\u00BAOM");
		lblnOM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnOM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblSolicitante = new JLabel("SOLCITANTE");
		lblSolicitante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSolicitante.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDescricao.setBackground(Color.WHITE);
		textFieldDescricao.setEditable(false);
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldSolicitante = new JTextField();
		textFieldSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSolicitante.setBackground(Color.WHITE);
		textFieldSolicitante.setEditable(false);
		textFieldSolicitante.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldSolicitante.setColumns(1);
		
		chckbxNewCheckBox = new JCheckBox("REQUISI\u00C7\u00C3O ENTREGUE");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxNewCheckBox.isSelected() == true) {
					tableRequisicaoPendente.setValueAt(true, tableRequisicaoPendente.getSelectedRow(), 0);
				} else {
					tableRequisicaoPendente.setValueAt(false, tableRequisicaoPendente.getSelectedRow(), 0);
				}
			}
		});
		chckbxNewCheckBox.setFont(new Font("Arial", Font.BOLD, 12));
		
		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableRequisicaoPendente.getSelectedRow();
				//SETAR A REQUISI플O
				Requisicao requisicao = new Requisicao();
				requisicao = requisicaoDao.findById(Integer.parseInt(tableRequisicaoPendente.getValueAt(i, 12).toString()));
				
				//AVERIGUAR SE EST� ATENDIDO OU N홒
				if (chckbxNewCheckBox.isSelected() == true) {
					tableRequisicaoPendente.setValueAt(true, tableRequisicaoPendente.getSelectedRow(), 0);
					JOptionPane.showMessageDialog(null, "REQUISI플O ATENDIDA", "REQUISI플O",1);
					
					//SETAR O PRODUTO PARA SER ATUALIZADO
					Produto produto = new Produto();
					produto = produtoDao.findById(Integer.parseInt(tableRequisicaoPendente.getValueAt(i, 3).toString()));
					
					//ATUALIZA O PRODUTO
					produto.setQuantidade(produto.getQuantidade() - requisicao.getQuantidade_requisicao());
					produtoDao.updateQuantidade(produto);
					
					//PARA TRAVAR O MILITAR QUE EST� INSERINDO O PRODUTO NO BANCO
					String nBM = Principal.textFieldUser.getText();
					login = loginDao.findByNBM(nBM);
					
					//ATUALIZAR O ATENDIMENTO
					requisicaoDao.updateAtendido(requisicao, true, login);
					
					//SETAR A VIATURA
					Viatura viatura = new Viatura();
					viatura = requisicao.getViatura();
					
					//CRIANDO O HISTORICO DE SAIADA DO ITEM
					Date data = new java.sql.Date(new java.util.Date().getTime());
					Time hora = new java.sql.Time(new java.util.Date().getTime());
					Militar militar = new Militar();
					militar = militarDao.findByName(requisicao.getMilitar().getNome());
					HistoricoProdutoSaida saida = new HistoricoProdutoSaida(null, data, hora,
							requisicao.getQuantidade_requisicao(),
							login, produto,
							viatura, null,
							militar);
					historicoSaidaDao.insertHistSaidaViatura(saida);
					
					//ATUALIZA A TABELA REQUISI플O
					DefaultTableModel modelRequisicaoPendente = new DefaultTableModel();
					tableRequisicaoPendente.setModel(requisicaoDao.tableRequisicao(modelRequisicaoPendente));
					tableRequisicaoPendente.getColumnModel().getColumn(0).setMinWidth(50);
					tableRequisicaoPendente.getColumnModel().getColumn(0).setPreferredWidth(55);
					tableRequisicaoPendente.getColumnModel().getColumn(0).setMaxWidth(70);
					tableRequisicaoPendente.getColumnModel().getColumn(1).setMinWidth(50);
					tableRequisicaoPendente.getColumnModel().getColumn(1).setPreferredWidth(120);
					tableRequisicaoPendente.getColumnModel().getColumn(1).setMaxWidth(130);
					tableRequisicaoPendente.getColumnModel().getColumn(2).setMinWidth(65);
					tableRequisicaoPendente.getColumnModel().getColumn(2).setPreferredWidth(70);
					tableRequisicaoPendente.getColumnModel().getColumn(2).setMaxWidth(72);
					tableRequisicaoPendente.getColumnModel().getColumn(3).setMinWidth(30);
					tableRequisicaoPendente.getColumnModel().getColumn(3).setPreferredWidth(35);
					tableRequisicaoPendente.getColumnModel().getColumn(3).setMaxWidth(40);
					tableRequisicaoPendente.getColumnModel().getColumn(8).setMinWidth(45);
					tableRequisicaoPendente.getColumnModel().getColumn(8).setPreferredWidth(47);
					tableRequisicaoPendente.getColumnModel().getColumn(8).setMaxWidth(50);
					tableRequisicaoPendente.getColumnModel().getColumn(9).setMinWidth(20);
					tableRequisicaoPendente.getColumnModel().getColumn(9).setPreferredWidth(30);
					tableRequisicaoPendente.getColumnModel().getColumn(9).setMaxWidth(40);
					tableRequisicaoPendente.getColumnModel().getColumn(10).setMinWidth(20);
					tableRequisicaoPendente.getColumnModel().getColumn(10).setPreferredWidth(20);
					tableRequisicaoPendente.getColumnModel().getColumn(10).setMaxWidth(20);
					tableRequisicaoPendente.getColumnModel().getColumn(12).setMinWidth(0);
					tableRequisicaoPendente.getColumnModel().getColumn(12).setPreferredWidth(0);
					tableRequisicaoPendente.getColumnModel().getColumn(12).setMaxWidth(0);
					
					textFieldnOM.setText("");
					textFieldPlaca.setText("");
					textFieldDescricao.setText("");
					textFieldCodMontadora.setText("");
					textFieldMarca.setText("");
					textFieldCodRecebido.setText("");
					textFieldQuant.setText("");
					textFieldSolicitante.setText("");
					textFieldModelo.setText("");
					chckbxNewCheckBox.setSelected(false);

				} else {
					tableRequisicaoPendente.setValueAt(false, tableRequisicaoPendente.getSelectedRow(), 0);
					JOptionPane.showMessageDialog(null, "REQUISI플O N홒 ATENDIDA.\nMARCAR A CAIXA DE 'REQUISI플O ENTREGUE' !","REQUISI플O",1);
				}
			}
		});
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizar.setIcon(new ImageIcon(RequisicaoPendente.class.getResource("/image/desktop_package.png")));
		btnAtualizar.setAlignmentY(1.0f);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldnOM = new JTextField();
		textFieldnOM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldnOM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnOM.setEditable(false);
		textFieldnOM.setColumns(10);
		textFieldnOM.setBackground(Color.WHITE);
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setEditable(false);
		textFieldModelo.setColumns(10);
		textFieldModelo.setBackground(Color.WHITE);
		
		JLabel lblEspaco = new JLabel(" ");
		
		JLabel lblEspaco_1 = new JLabel(" ");
		
		JLabel lblCodMontadora = new JLabel("COD.MONTADORA");
		lblCodMontadora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodMontadora = new JTextField();
		textFieldCodMontadora.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodMontadora.setEditable(false);
		textFieldCodMontadora.setBackground(Color.WHITE);
		
		JLabel lblMarca = new JLabel("MARCA");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldMarca = new JTextField();
		textFieldMarca.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMarca.setEditable(false);
		textFieldMarca.setBackground(Color.WHITE);
		
		textFieldCodRecebido = new JTextField();
		textFieldCodRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodRecebido.setEditable(false);
		textFieldCodRecebido.setBackground(Color.WHITE);
		
		JLabel lblCodRecebido = new JLabel("COD.RECEBIDO");
		lblCodRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(lblTabelaDeRequisies, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblnOM, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldnOM, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSolicitante, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldSolicitante, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldQuant, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(116)
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
					.addGap(21))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(lblEspaco, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
					.addGap(200)
					.addComponent(btnRetirar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(137)
					.addComponent(lblEspaco_1, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
					.addGap(154))
				.addComponent(lblRe, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRe, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(lblTabelaDeRequisies, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblnOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldnOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSolicitante, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldSolicitante, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodMontadora, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodRecebido, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldQuant, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblEspaco, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRetirar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblEspaco_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(11))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
