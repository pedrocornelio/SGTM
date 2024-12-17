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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.ProdutoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class BuscarEstoqueMinimo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEstoqueMinimo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarEstoqueMinimo frame = new BuscarEstoqueMinimo();
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
	public BuscarEstoqueMinimo() {
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		setTitle("ESTOQUE MÍNIMO");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarEstoqueMinimo.class.getResource("/image/max_min.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 614, 340);
		contentPane.add(scrollPane);
		
		tableEstoqueMinimo = new JTable();
		scrollPane.setViewportView(tableEstoqueMinimo);
		TableFilterHeader filterHeader = new TableFilterHeader(tableEstoqueMinimo, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableEstoqueMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelEstoqueMinimo = new DefaultTableModel();
		tableEstoqueMinimo.setModel(produtoDao.tableEstoqueMinimo(modelEstoqueMinimo));
		tableEstoqueMinimo.getColumnModel().getColumn(0).setMinWidth(40);
		tableEstoqueMinimo.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableEstoqueMinimo.getColumnModel().getColumn(6).setMinWidth(20);
		tableEstoqueMinimo.getColumnModel().getColumn(6).setPreferredWidth(25);
		tableEstoqueMinimo.getColumnModel().getColumn(6).setMaxWidth(30);
	}
}
