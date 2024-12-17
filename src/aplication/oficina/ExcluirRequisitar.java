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
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.MontadoraDao;
import dao.RequisicaoDao;
import entities.Produto;
import entities.Requisicao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcluirRequisitar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnFechar;
	private JTextField textFieldOM;
	private JTextField textFieldCodCSM;
	private JTable tableProdutoRequisitar;
	private JScrollPane scrollPane_1;
	public static Produto produtoAplicacao;
	private JLabel lblNDescricao;
	private JTextField textFieldDescricao;
	private JButton btnExcluir;
	private JLabel lblData;
	private JTextField textFieldData;
	private JLabel lblSeparador;
	private JLabel lblSeparador_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirRequisitar frame = new ExcluirRequisitar();
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

	public ExcluirRequisitar() throws ParseException {
		setTitle("REQUISI\u00C7\u00C3O");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExcluirRequisitar.class.getResource("/image/requisition_edition.png")));
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 610);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		setContentPane(contentPane);
		
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
		
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		
		JLabel labelTitulo = new JLabel("EXCLUIR REQUISI\u00C7\u00C3O");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		labelTitulo.setAlignmentX(0.5f);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		
		btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(ExcluirRequisitar.class.getResource("/image/requisition_edition.png")));
		btnFechar.setMargin(new Insets(2, 5, 2, 5));
		btnFechar.setIconTextGap(0);
		btnFechar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblNOM = new JLabel("ORDEM DE MANUTEN\u00C7\u00C3O");
		lblNOM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNOM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldOM = new JTextField();
		textFieldOM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldOM.getText().toUpperCase();
				textFieldOM.setText(temp);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE) || textFieldOM.getText().length() > 14 ) {
					e.consume();
				}
			}
		});
		textFieldOM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldOM.setColumns(10);
		
		JLabel lblCodCSM = new JLabel("C\u00D3DIGO CSM");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodCSM.setEditable(false);
		
		scrollPane_1 = new JScrollPane();
		
		tableProdutoRequisitar = new JTable();
		DefaultTableModel modelRequisicao = new DefaultTableModel();
		scrollPane_1.setViewportView(tableProdutoRequisitar);
		tableProdutoRequisitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableProdutoRequisitar.getSelectedRow();
				
				textFieldOM.setText(tableProdutoRequisitar.getValueAt(i, 0).toString());
				textFieldData.setText(tableProdutoRequisitar.getValueAt(i, 1).toString());
				textFieldCodCSM.setText(tableProdutoRequisitar.getValueAt(i, 2).toString());
				textFieldDescricao.setText(tableProdutoRequisitar.getValueAt(i, 3).toString());
				
				btnExcluir.setEnabled(true);
				
			}
		});
		TableFilterHeader filterHeaderEstoque = new TableFilterHeader(tableProdutoRequisitar, AutoChoices.ENABLED);
		filterHeaderEstoque.setPosition(Position.TOP);
		filterHeaderEstoque.setBackground(Color.white);
		tableProdutoRequisitar.setFont(new Font("Arial", Font.PLAIN, 12));
		tableProdutoRequisitar.setModel(requisicaoDao.tableExcluirRequisicao(modelRequisicao));
		tableProdutoRequisitar.getColumnModel().getColumn(0).setMinWidth(100);
		tableProdutoRequisitar.getColumnModel().getColumn(0).setPreferredWidth(115);
		tableProdutoRequisitar.getColumnModel().getColumn(0).setMaxWidth(120);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setMinWidth(70);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableProdutoRequisitar.getColumnModel().getColumn(1).setMaxWidth(75);
		tableProdutoRequisitar.getColumnModel().getColumn(2).setMinWidth(30);
		tableProdutoRequisitar.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(2).setMaxWidth(45);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setMinWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableProdutoRequisitar.getColumnModel().getColumn(7).setMaxWidth(45);
		tableProdutoRequisitar.getColumnModel().getColumn(8).setMinWidth(20);
		tableProdutoRequisitar.getColumnModel().getColumn(8).setPreferredWidth(20);
		tableProdutoRequisitar.getColumnModel().getColumn(8).setMaxWidth(30);
		
		lblNDescricao = new JLabel("DESCRI\u00C7\u00C3O");
		lblNDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setEditable(false);
		textFieldDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldDescricao.setColumns(10);
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Requisicao requisicao = new Requisicao();
				requisicao = requisicaoDao.findByRequisicao(textFieldOM.getText().toString(), 
						textFieldData.getText().toString().substring(6)+"-"+textFieldData.getText().toString().substring(3,5)+"-"+textFieldData.getText().toString().substring(0,2),
						Integer.parseInt(textFieldCodCSM.getText().toString()));
				
				requisicaoDao.deleteRequisicao(requisicao);
				
				JOptionPane.showMessageDialog(null,"REQUISIÇÃO O.M. " + textFieldOM.getText() + " EXCLUÍDA","REQUISIÇÃO EXLUÍDA",1);
				
				DefaultTableModel modelRequisicao = new DefaultTableModel();
				tableProdutoRequisitar.setModel(requisicaoDao.tableExcluirRequisicao(modelRequisicao));
				tableProdutoRequisitar.getColumnModel().getColumn(0).setMinWidth(100);
				tableProdutoRequisitar.getColumnModel().getColumn(0).setPreferredWidth(115);
				tableProdutoRequisitar.getColumnModel().getColumn(0).setMaxWidth(120);
				tableProdutoRequisitar.getColumnModel().getColumn(1).setMinWidth(70);
				tableProdutoRequisitar.getColumnModel().getColumn(1).setPreferredWidth(70);
				tableProdutoRequisitar.getColumnModel().getColumn(1).setMaxWidth(75);
				tableProdutoRequisitar.getColumnModel().getColumn(2).setMinWidth(30);
				tableProdutoRequisitar.getColumnModel().getColumn(2).setPreferredWidth(40);
				tableProdutoRequisitar.getColumnModel().getColumn(2).setMaxWidth(45);
				tableProdutoRequisitar.getColumnModel().getColumn(8).setMinWidth(40);
				tableProdutoRequisitar.getColumnModel().getColumn(8).setPreferredWidth(40);
				tableProdutoRequisitar.getColumnModel().getColumn(8).setMaxWidth(45);
				tableProdutoRequisitar.getColumnModel().getColumn(9).setMinWidth(20);
				tableProdutoRequisitar.getColumnModel().getColumn(9).setPreferredWidth(20);
				tableProdutoRequisitar.getColumnModel().getColumn(9).setMaxWidth(30);
				
				btnExcluir.setEnabled(false);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(ExcluirRequisitar.class.getResource("/image/trash.png")));
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblData = new JLabel("DATA");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldData = new JTextField();
		textFieldData.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldData.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldData.setEditable(false);
		
		lblSeparador = new JLabel("");
		
		lblSeparador_1 = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
				.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblSeparador, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNOM)
							.addGap(10)
							.addComponent(textFieldOM, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNDescricao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(134)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(144)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addGap(81)
					.addComponent(lblSeparador_1, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblSeparador, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldOM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldCodCSM, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSeparador_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
