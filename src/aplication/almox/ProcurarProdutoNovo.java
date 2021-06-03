/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.almox;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.MontadoraDao;
import dao.ProdutoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class ProcurarProdutoNovo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableProcurarProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcurarProdutoNovo frame = new ProcurarProdutoNovo();
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
	@SuppressWarnings("rawtypes")
	public ProcurarProdutoNovo() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProcurarProdutoNovo.class.getResource("/image/user_interface/search_product.png")));
		setTitle("SELECIONAR ITEM");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		MontadoraDao montadoraDao = DaoFactory.createMontadoraDao();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 564, 340);
		contentPane.add(scrollPane);
		
		tableProcurarProduto = new JTable();
		scrollPane.setViewportView(tableProcurarProduto);
		tableProcurarProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableFilterHeader filterHeader = new TableFilterHeader(tableProcurarProduto, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableProcurarProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelProcurarProduto = new DefaultTableModel();
		
		tableProcurarProduto.setModel(produtoDao.tableProcurarProdutoConsumo(
				EntradaItemNovo.textFieldMarcaRecebida.getText(),
				EntradaItemNovo.textFieldCodRecebido.getText(),
				EntradaItemNovo.textFieldDescricao.getText(), modelProcurarProduto));
		
		tableProcurarProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				int i = tableProcurarProduto.getSelectedRow();

				if (i >= 0) {

					JOptionPane.showMessageDialog(null, "ITEM SELECIONADO", "ITEM", 1);					
					
					if (montadoraDao.findByMontadora(tableProcurarProduto.getValueAt(i, 1).toString()).getIdMontadora() != 1) {
						EntradaItemNovo.lblMontadora.setEnabled(true);
						EntradaItemNovo.lblCodMontadora.setEnabled(true);
						EntradaItemNovo.rdbtnMontadora.setSelected(true);
						EntradaItemNovo.comboBoxMontadora.setEnabled(true);
						EntradaItemNovo.comboBoxMontadora.setSelectedItem(tableProcurarProduto.getValueAt(i, 1).toString());
						EntradaItemNovo.textFieldCodMontadora.setEnabled(true);
						EntradaItemNovo.textFieldCodMontadora.setText(tableProcurarProduto.getValueAt(i, 2).toString());
					} else {
						EntradaItemNovo.lblMontadora.setEnabled(false);
						EntradaItemNovo.lblCodMontadora.setEnabled(false);
						EntradaItemNovo.rdbtnConsumo.setSelected(true);
						EntradaItemNovo.comboBoxMontadora.setEnabled(false);
						EntradaItemNovo.comboBoxMontadora.setSelectedItem("");
						EntradaItemNovo.textFieldCodMontadora.setEnabled(false);
						EntradaItemNovo.textFieldCodMontadora.setText("");
					}
					
					EntradaItemNovo.textFieldMarcaRecebida.setText(tableProcurarProduto.getValueAt(i, 3).toString());
					EntradaItemNovo.textFieldCodRecebido.setText(tableProcurarProduto.getValueAt(i, 4).toString());
					EntradaItemNovo.textFieldDescricao.setText(tableProcurarProduto.getValueAt(i, 5).toString());
							
					dispose();
				}
			}
		});
		
		int i = tableProcurarProduto.getRowCount();
		
		if (i >= 0) {
			JOptionPane.showMessageDialog(null, "PARA EDIÇÃO OU INSERÇÃO DE NOVAS MONTADORAS OU DADOS DO MATERIAL \n IR PARA A TELA 'EDIÇÃO DE ITEM' EM GESTÃO => EDIÇÃO DE ITEM", "ITEM",1);
		}
	}
}
