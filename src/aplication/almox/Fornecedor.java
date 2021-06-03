package aplication.almox;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.FornecedorDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class Fornecedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableFornecedor;
	private JTextField textFieldFornecedor;
	private JTextField textFieldNotaFiscal;
	private JTextField textFieldOrcamento;
	private JTextField textFieldPreco;
	private JTextField textFieldRef;
	private JButton btnAtualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedor frame = new Fornecedor();
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
	public Fornecedor() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedor.class.getResource("/image/user_interface/provider.png")));
		setTitle("FORNECEDOR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 372, 260);
		contentPane.add(scrollPane);
		
		JLabel lblRef = new JLabel("REF:");
		lblRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRef.setBounds(440, 47, 40, 20);
		contentPane.add(lblRef);
		setLocationRelativeTo(null);
		
		textFieldRef = new JTextField();
		textFieldRef.setEditable(false);
		textFieldRef.setBounds(484, 47, 40, 20);
		contentPane.add(textFieldRef);
		textFieldRef.setColumns(10);
		
		tableFornecedor = new JTable();
		tableFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableFornecedor.getSelectedRow();
				textFieldRef.setText(tableFornecedor.getValueAt(i, 0).toString());
				textFieldFornecedor.setText(tableFornecedor.getValueAt(i, 1).toString());
				textFieldNotaFiscal.setText(tableFornecedor.getValueAt(i, 2).toString());
				textFieldOrcamento.setText(tableFornecedor.getValueAt(i, 3).toString());
				textFieldPreco.setText(tableFornecedor.getValueAt(i, 4).toString());
			}
		});
		scrollPane.setViewportView(tableFornecedor);
		TableFilterHeader filterHeader = new TableFilterHeader(tableFornecedor, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelFornecedor = new DefaultTableModel();
		tableFornecedor.setModel(fornecedorDao.tableFornecedor(modelFornecedor, EdicaoItem.produtoEdicao.getId_produto()));
		tableFornecedor.getColumnModel().getColumn(0).setMinWidth(20);
		tableFornecedor.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableFornecedor.getColumnModel().getColumn(0).setMaxWidth(40);
		tableFornecedor.getColumnModel().getColumn(4).setMinWidth(40);
		tableFornecedor.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableFornecedor.getColumnModel().getColumn(4).setMaxWidth(55);
		
		JLabel lblFornecedores = new JLabel("FORNECEDORES");
		lblFornecedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFornecedores.setBounds(21, 46, 119, 23);
		contentPane.add(lblFornecedores);
		
		JLabel lblTitulo = new JLabel("TITULO");
		lblTitulo.setText("ITEM COD.CSM: " + EdicaoItem.produtoEdicao.getId_produto() + " - " + EdicaoItem.produtoEdicao.getDescricao());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTitulo.setBounds(0, 11, 584, 23);
		contentPane.add(lblTitulo);
		
		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entities.Fornecedor fornecedor = new entities.Fornecedor();
				fornecedor = fornecedorDao.findFornecedorById(Integer.parseInt(textFieldRef.getText().toString()));
				fornecedor.setFornecedor(textFieldFornecedor.getText());
				fornecedor.setNota_fiscal_fornecedor(textFieldNotaFiscal.getText());
				fornecedor.setOrcamento(textFieldOrcamento.getText());
				fornecedor.setPreco(Double.parseDouble(textFieldPreco.getText().replace(',', '.')));

				fornecedorDao.updateFornecedor(fornecedor, EdicaoItem.produtoEdicao);
				
				JOptionPane.showMessageDialog(null,"DADOS DO FORNECEDOR ATAULIZADO", "ATUALIZAÇÃO", 1);
				
				DefaultTableModel modelFornecedor = new DefaultTableModel();
				tableFornecedor.setModel(fornecedorDao.tableFornecedor(modelFornecedor, EdicaoItem.produtoEdicao.getId_produto()));
				tableFornecedor.getColumnModel().getColumn(0).setMinWidth(20);
				tableFornecedor.getColumnModel().getColumn(0).setPreferredWidth(30);
				tableFornecedor.getColumnModel().getColumn(0).setMaxWidth(40);
				tableFornecedor.getColumnModel().getColumn(4).setMinWidth(40);
				tableFornecedor.getColumnModel().getColumn(4).setPreferredWidth(50);
				tableFornecedor.getColumnModel().getColumn(4).setMaxWidth(55);
				
				textFieldRef.setText("");
				textFieldFornecedor.setText("");
				textFieldNotaFiscal.setText("");
				textFieldOrcamento.setText("");
				textFieldPreco.setText("");
			}
		});
		btnAtualizar.setIcon(new ImageIcon(Fornecedor.class.getResource("/image/user_interface/desktop_package.png")));
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizar.setBounds(410, 305, 145, 35);
		contentPane.add(btnAtualizar);
		
		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFornecedor.setBounds(437, 80, 90, 20);
		contentPane.add(lblFornecedor);
		
		JLabel lblNotaFiscal = new JLabel("NOTA FISCAL");
		lblNotaFiscal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNotaFiscal.setBounds(437, 135, 90, 20);
		contentPane.add(lblNotaFiscal);
		
		JLabel labelOrcamento = new JLabel("OR\u00C7AMENTO");
		labelOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
		labelOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		labelOrcamento.setBounds(437, 190, 90, 20);
		contentPane.add(labelOrcamento);
		
		JLabel lblPreco = new JLabel("PRE\u00C7O");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPreco.setBounds(437, 245, 90, 20);
		contentPane.add(lblPreco);
		
		textFieldFornecedor = new JTextField();
		textFieldFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldFornecedor.getText().toUpperCase();
				textFieldFornecedor.setText(temp);
			}
		});
		textFieldFornecedor.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldFornecedor.setBounds(402, 100, 160, 23);
		contentPane.add(textFieldFornecedor);
		
		textFieldNotaFiscal = new JTextField();
		textFieldNotaFiscal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNotaFiscal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldNotaFiscal.getText().toUpperCase();
				textFieldNotaFiscal.setText(temp);
			}
		});
		textFieldNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldNotaFiscal.setBounds(402, 160, 160, 23);
		contentPane.add(textFieldNotaFiscal);
		
		textFieldOrcamento = new JTextField();
		textFieldOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOrcamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldOrcamento.getText().toUpperCase();
				textFieldOrcamento.setText(temp);
			}
		});
		textFieldOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldOrcamento.setBounds(402, 215, 160, 23);
		contentPane.add(textFieldOrcamento);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE || number == KeyEvent.VK_COMMA)) {
					e.consume();
				}
			}
		});
		textFieldPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPreco.setBounds(402, 270, 160, 23);
		contentPane.add(textFieldPreco);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(Fornecedor.class.getResource("/image/user_interface/provider.png")));
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.setBounds(231, 355, 123, 35);
		contentPane.add(btnFechar);
		

	}
}
