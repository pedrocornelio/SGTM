/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package aplication.almox;

import java.awt.Color;
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
import dao.RequisicaoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import java.awt.Dialog.ModalExclusionType;

public class BuscarRequisicaoPendente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRequisicaoPendente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarRequisicaoPendente frame = new BuscarRequisicaoPendente();
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
	public BuscarRequisicaoPendente() {
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		setTitle("REQUISI\u00C7\u00C3O PENDENTE");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarRequisicaoPendente.class.getResource("/image/requisition_packaeg.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 370);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RequisicaoDao requisicaoDao = DaoFactory.createRequisicaoDao();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 584, 310);
		contentPane.add(scrollPane);
		
		tableRequisicaoPendente = new JTable();
		scrollPane.setViewportView(tableRequisicaoPendente);
		TableFilterHeader filterHeader = new TableFilterHeader(tableRequisicaoPendente, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableRequisicaoPendente.setFont(new Font("Arial", Font.PLAIN, 12));
		DefaultTableModel modelRequisicaoPendente = new DefaultTableModel();
		tableRequisicaoPendente.setModel(requisicaoDao.tableBuscarRequisicao(modelRequisicaoPendente));
		tableRequisicaoPendente.getColumnModel().getColumn(1).setMinWidth(70);
		tableRequisicaoPendente.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableRequisicaoPendente.getColumnModel().getColumn(1).setMaxWidth(70);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setMinWidth(30);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableRequisicaoPendente.getColumnModel().getColumn(2).setMaxWidth(45);
		tableRequisicaoPendente.getColumnModel().getColumn(4).setMinWidth(45);
		tableRequisicaoPendente.getColumnModel().getColumn(4).setPreferredWidth(47);
		tableRequisicaoPendente.getColumnModel().getColumn(4).setMaxWidth(50);
		tableRequisicaoPendente.getColumnModel().getColumn(5).setMinWidth(40);
		tableRequisicaoPendente.getColumnModel().getColumn(5).setPreferredWidth(47);
		tableRequisicaoPendente.getColumnModel().getColumn(5).setMaxWidth(90);
		tableRequisicaoPendente.getColumnModel().getColumn(6).setMinWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(6).setPreferredWidth(20);
		tableRequisicaoPendente.getColumnModel().getColumn(6).setMaxWidth(20);
	}
}
