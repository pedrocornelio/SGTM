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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;

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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.ProdutoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import javax.swing.UIManager;

public class GestaoQuantidade extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMin;
	private JTable tableGestaoQuantidade;
	private JTextField textFieldCodCSM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GestaoQuantidade frame = new GestaoQuantidade();
					frame.setVisible(true);
				} catch (Exception event) {
					event.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestaoQuantidade() {
		setResizable(false);
		
		setTitle("GEST\u00C3O DE QUANTIDADE M\u00CDNIMA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestaoQuantidade.class.getResource("/image/max_min.png")));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 630);
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

		JLabel lblTitulo = new JLabel("GEST\u00C3O DE QUANTIDADE");
		lblTitulo.setBounds(0, 11, 664, 25);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setBounds(0, 38, 664, 5);
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);

		JLabel lblNovoLocal = new JLabel("QUANTIDADE M\u00CDNIMA");
		lblNovoLocal.setBounds(133, 494, 137, 25);
		lblNovoLocal.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNovoLocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovoLocal.setFont(new Font("Arial", Font.PLAIN, 12));

		textFieldMin = new JTextField();
		textFieldMin.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMin.setBounds(285, 494, 88, 25);
		textFieldMin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textFieldMin.setColumns(10);

		JButton btnAlterar = new JButton("  ALTERAR");
		btnAlterar.setBounds(266, 545, 127, 35);
		btnAlterar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnAlterar.setIcon(new ImageIcon(GestaoQuantidade.class.getResource("/image/max_min.png")));
		btnAlterar.setMargin(new Insets(2, 5, 2, 5));
		btnAlterar.setIconTextGap(0);
		btnAlterar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				//ATUALIZANDO AS TABELAS DO ESTOQUE
				Almoxarifado.btnRefresh.doClick();
				
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 83, 630, 360);

		tableGestaoQuantidade = new JTable();
		tableGestaoQuantidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableGestaoQuantidade.getSelectedRow();
				textFieldCodCSM.setText(tableGestaoQuantidade.getValueAt(i, 1).toString());
				textFieldMin.setText(tableGestaoQuantidade.getValueAt(i, 7).toString());
			}
		});
		TableFilterHeader filterHeader = new TableFilterHeader(tableGestaoQuantidade, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		DefaultTableModel modelGestaoQuantidade = new DefaultTableModel();
		tableGestaoQuantidade.setModel(produtoDao.tableQuantidadeMinima(modelGestaoQuantidade));
		scrollPane.setViewportView(tableGestaoQuantidade);

		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAtualizar.setBounds(382, 489, 148, 35);
		btnAtualizar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnAtualizar.setIcon(new ImageIcon(GestaoQuantidade.class.getResource("/image/desktop_package.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int i = tableGestaoQuantidade.getSelectedRow();
				if (i >= 0) {
					tableGestaoQuantidade.setValueAt(textFieldMin.getText(), i, 7);
					produtoDao.updateQuantidadeMinima(Integer.parseInt(textFieldMin.getText()),
							produtoDao.findById(Integer.parseInt(textFieldCodCSM.getText().toString())));
					JOptionPane.showMessageDialog(null, "A QUANTIDADE MÍNIMA DO ITEM COD.CSM: "+ textFieldCodCSM.getText().toString() +" FOI ATUALIZADA COM SUCESSO", "ATUALIZAÇÃO", 1);
					textFieldCodCSM.setText("");
					textFieldMin.setText("");
				}
			}
		});

		JLabel lblNewLabel = new JLabel("INFORMA\u00C7\u00D5ES DOS ITENS");
		lblNewLabel.setBounds(25, 52, 172, 25);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton print = new JButton("");
		print.setBounds(617, 458, 28, 28);
		print.setIcon(new ImageIcon(GestaoQuantidade.class.getResource("/image/pdf.png")));
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tableGestaoQuantidade.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setBounds(0, 8, 664, 5);
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		contentPane.add(separator);
		contentPane.add(lblTitulo);
		contentPane.add(separator_1);
		contentPane.add(scrollPane);
		contentPane.add(lblNewLabel);
		contentPane.add(btnAlterar);
		contentPane.add(lblNovoLocal);
		contentPane.add(textFieldMin);
		contentPane.add(btnAtualizar);
		contentPane.add(print);
		
		JLabel lblCodCSM = new JLabel("CSM");
		lblCodCSM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCodCSM.setAlignmentY(1.0f);
		lblCodCSM.setBounds(133, 454, 137, 25);
		contentPane.add(lblCodCSM);
		
		textFieldCodCSM = new JTextField();
		textFieldCodCSM.setEditable(false);
		textFieldCodCSM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCodCSM.setColumns(10);
		textFieldCodCSM.setAlignmentY(1.0f);
		textFieldCodCSM.setBounds(285, 454, 88, 25);
		contentPane.add(textFieldCodCSM);

	}
}
