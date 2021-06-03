package aplication.almox;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CodigoMontadoraDao;
import dao.CodigoRecebidoDao;
import dao.DaoFactory;
import dao.MontadoraDao;
import entities.CodigoMontadora;
import entities.CodigoRecebido;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTable;

public class Equivalencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldRefCodMontadora;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMontadora;
	private JTextField textFieldCodigoMontadora;
	private JTextField textFieldMarca;
	private JTextField textFieldCodigoRecebido;
	private JTable tableCodMontadora;
	private JTable tableCodRecebido;
	private JTextField textFieldRefCodRecebido;
	private JLabel lblRef_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Equivalencia frame = new Equivalencia();
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
	public Equivalencia() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Equivalencia.class.getResource("/image/user_interface/equivalence.png")));
		setTitle("EQUIVAL\u00CANCIA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		CodigoMontadoraDao codigoMontadoraDao = DaoFactory.createCodigoMontadoraDao();
		CodigoRecebidoDao codigoRecebidoDao = DaoFactory.createCodigoRecebidoDao();
		
		JLabel lblTitulo = new JLabel("TITULO");
		lblTitulo.setText("ITEM COD.CSM: " + EdicaoItem.produtoEdicao.getId_produto() + " - " + EdicaoItem.produtoEdicao.getDescricao());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTitulo.setBounds(0, 11, 584, 23);
		contentPane.add(lblTitulo);
		
		JLabel lblCodigoMontadora = new JLabel("C\u00D3DIGO MONTADORA");
		lblCodigoMontadora.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodigoMontadora.setBounds(21, 45, 145, 23);
		contentPane.add(lblCodigoMontadora);
		
		JLabel lblRef = new JLabel("REF:");
		lblRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRef.setBounds(440, 79, 40, 20);
		contentPane.add(lblRef);
		
		textFieldRefCodMontadora = new JTextField();
		textFieldRefCodMontadora.setEditable(false);
		textFieldRefCodMontadora.setColumns(10);
		textFieldRefCodMontadora.setBounds(484, 79, 40, 20);
		contentPane.add(textFieldRefCodMontadora);
		
		JLabel lblCodMontadora = new JLabel("MONTADORA");
		lblCodMontadora.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodMontadora.setBounds(402, 110, 160, 20);
		contentPane.add(lblCodMontadora);
		
		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setModel(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setBounds(402, 130, 160, 23);
		contentPane.add(comboBoxMontadora);
		
		JLabel lblNotaFiscal = new JLabel("C\u00D3D.MONTADORA");
		lblNotaFiscal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaFiscal.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNotaFiscal.setBounds(402, 162, 160, 20);
		contentPane.add(lblNotaFiscal);
		
		textFieldCodigoMontadora = new JTextField();
		textFieldCodigoMontadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				
				String temp = textFieldCodigoMontadora.getText().toUpperCase();
				textFieldCodigoMontadora.setText(temp);
			}
		});
		textFieldCodigoMontadora.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodigoMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodigoMontadora.setBounds(402, 181, 160, 23);
		contentPane.add(textFieldCodigoMontadora);
		
		JLabel labelOrcamento = new JLabel("MARCA");
		labelOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
		labelOrcamento.setFont(new Font("Arial", Font.PLAIN, 12));
		labelOrcamento.setBounds(402, 304, 160, 20);
		contentPane.add(labelOrcamento);
		
		textFieldMarca = new JTextField();
		textFieldMarca.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				String temp = textFieldMarca.getText().toUpperCase();
				textFieldMarca.setText(temp);
			}
		});
		textFieldMarca.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMarca.setBounds(402, 324, 160, 23);
		contentPane.add(textFieldMarca);
		
		JLabel lblPreco = new JLabel("C\u00D3D.RECEBIDO");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPreco.setBounds(402, 352, 160, 20);
		contentPane.add(lblPreco);
		
		textFieldCodigoRecebido = new JTextField();
		textFieldCodigoRecebido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				
				String temp = textFieldCodigoRecebido.getText().toUpperCase();
				textFieldCodigoRecebido.setText(temp);
			}
		});
		textFieldCodigoRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCodigoRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodigoRecebido.setBounds(402, 377, 160, 23);
		contentPane.add(textFieldCodigoRecebido);
		
		JButton btnAtualizarCodRecebido = new JButton("ATUALIZAR");
		btnAtualizarCodRecebido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CodigoRecebido codigoRecebido = new CodigoRecebido(Integer.parseInt(textFieldRefCodRecebido.getText().toString()),
						textFieldMarca.getText().toString(),
						textFieldCodigoRecebido.getText().toString(),
						EdicaoItem.produtoEdicao); 
				codigoRecebidoDao.updateCodigoRecebido(codigoRecebido);
				
				JOptionPane.showMessageDialog(null, "AS INFORMAÇÕES DE CÓDIGO RECEBIDO FORAM ATUALIZADAS", "IINFORMAÇÕES ATUALIZADO", 1);
				
				textFieldRefCodRecebido.setText("");
				textFieldMarca.setText("");
				textFieldCodigoRecebido.setText("");
				
				DefaultTableModel modelCodRecebido = new DefaultTableModel();
				tableCodRecebido.setModel(codigoRecebidoDao.tableEquivalenciaCodRecebido(modelCodRecebido, EdicaoItem.produtoEdicao.getId_produto()));
				tableCodRecebido.getColumnModel().getColumn(0).setMinWidth(20);
				tableCodRecebido.getColumnModel().getColumn(0).setPreferredWidth(30);
				tableCodRecebido.getColumnModel().getColumn(0).setMaxWidth(40);
			}
		});
		btnAtualizarCodRecebido.setIcon(new ImageIcon(Equivalencia.class.getResource("/image/user_interface/desktop_package.png")));
		btnAtualizarCodRecebido.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizarCodRecebido.setBounds(410, 415, 145, 35);
		contentPane.add(btnAtualizarCodRecebido);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(Equivalencia.class.getResource("/image/user_interface/equivalence.png")));
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.setBounds(231, 415, 123, 35);
		contentPane.add(btnFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 79, 360, 125);
		contentPane.add(scrollPane);
		
		tableCodMontadora = new JTable();
		tableCodMontadora.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableCodMontadora.getSelectedRow();
				textFieldRefCodMontadora.setText(tableCodMontadora.getValueAt(i, 0).toString());
				comboBoxMontadora.setSelectedItem(tableCodMontadora.getValueAt(i, 1).toString());
				if (tableCodMontadora.getValueAt(i, 2) != null) {
					textFieldCodigoMontadora.setText(tableCodMontadora.getValueAt(i, 2).toString());
				} else {
					textFieldCodigoMontadora.setText(null);
				}
			}
		});
		scrollPane.setViewportView(tableCodMontadora);
		TableFilterHeader filterHeader = new TableFilterHeader(tableCodMontadora, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		DefaultTableModel modelCodMontadora = new DefaultTableModel();
		tableCodMontadora.setModel(codigoMontadoraDao.tableEquivalenciaCodMontadora(modelCodMontadora, EdicaoItem.produtoEdicao.getId_produto()));
		tableCodMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		tableCodMontadora.getColumnModel().getColumn(0).setMinWidth(20);
		tableCodMontadora.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCodMontadora.getColumnModel().getColumn(0).setMaxWidth(40);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 275, 360, 125);
		contentPane.add(scrollPane_1);
		
		tableCodRecebido = new JTable();
		tableCodRecebido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableCodRecebido.getSelectedRow();
				textFieldRefCodRecebido.setText(tableCodRecebido.getValueAt(i, 0).toString());
				textFieldMarca.setText(tableCodRecebido.getValueAt(i, 1).toString());
				textFieldCodigoRecebido.setText(tableCodRecebido.getValueAt(i, 2).toString());
			}
		});
		scrollPane_1.setViewportView(tableCodRecebido);
		TableFilterHeader filterHeader_1 = new TableFilterHeader(tableCodRecebido, AutoChoices.ENABLED);
		filterHeader_1.setPosition(Position.TOP);
		filterHeader_1.setBackground(Color.white);
		DefaultTableModel modelCodRecebido = new DefaultTableModel();
		tableCodRecebido.setModel(codigoRecebidoDao.tableEquivalenciaCodRecebido(modelCodRecebido, EdicaoItem.produtoEdicao.getId_produto()));
		tableCodRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		tableCodRecebido.getColumnModel().getColumn(0).setMinWidth(20);
		tableCodRecebido.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCodRecebido.getColumnModel().getColumn(0).setMaxWidth(40);
		
		lblRef_1 = new JLabel("REF:");
		lblRef_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRef_1.setBounds(440, 275, 40, 20);
		contentPane.add(lblRef_1);
		
		textFieldRefCodRecebido = new JTextField();
		textFieldRefCodRecebido.setEditable(false);
		textFieldRefCodRecebido.setColumns(10);
		textFieldRefCodRecebido.setBounds(484, 275, 40, 20);
		contentPane.add(textFieldRefCodRecebido);
		
		JButton btnAtualizarCodMontadora = new JButton("ATUALIZAR");
		btnAtualizarCodMontadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CodigoMontadora codigoMontadora = new CodigoMontadora();
				
				if (textFieldCodigoMontadora.getText().toString() != null) {
					codigoMontadora = new CodigoMontadora(Integer.parseInt(textFieldRefCodMontadora.getText().toString()),
						textFieldCodigoMontadora.getText().toString(),
						montadoraDao.findByMontadora(comboBoxMontadora.getSelectedItem().toString()),
						EdicaoItem.produtoEdicao);
				} else {
					codigoMontadora = new CodigoMontadora(Integer.parseInt(textFieldRefCodMontadora.getText().toString()),
							null,
							montadoraDao.findByMontadora(comboBoxMontadora.getSelectedItem().toString()),
							EdicaoItem.produtoEdicao);
				}
				
				codigoMontadoraDao.updateCodigoMontadora(codigoMontadora);
				
				JOptionPane.showMessageDialog(null, "AS INFORMAÇÕES DE CÓDIGO MONTADORA FORAM ATUALIZADAS", "IINFORMAÇÕES ATUALIZADO", 1);
				
				textFieldRefCodMontadora.setText("");
				textFieldCodigoMontadora.setText("");
				comboBoxMontadora.setSelectedItem("");
				
				DefaultTableModel modelCodMontadora = new DefaultTableModel();
				tableCodMontadora.setModel(codigoMontadoraDao.tableEquivalenciaCodMontadora(modelCodMontadora, EdicaoItem.produtoEdicao.getId_produto()));
				tableCodMontadora.getColumnModel().getColumn(0).setMinWidth(20);
				tableCodMontadora.getColumnModel().getColumn(0).setPreferredWidth(30);
				tableCodMontadora.getColumnModel().getColumn(0).setMaxWidth(40);
			}
		});
		btnAtualizarCodMontadora.setIcon(new ImageIcon(Equivalencia.class.getResource("/image/user_interface/desktop_package.png")));
		btnAtualizarCodMontadora.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizarCodMontadora.setBounds(410, 215, 145, 35);
		contentPane.add(btnAtualizarCodMontadora);
		
		JLabel lblCodigoRecebido = new JLabel("C\u00D3DIGO RECEBIDO");
		lblCodigoRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoRecebido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodigoRecebido.setBounds(21, 241, 145, 23);
		contentPane.add(lblCodigoRecebido);
		
	}
}
