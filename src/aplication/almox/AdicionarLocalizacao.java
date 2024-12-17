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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoFactory;
import dao.LocalizacaoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class AdicionarLocalizacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable tableAlmox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarLocalizacao frame = new AdicionarLocalizacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn;
	private JTextField textLocal;

	/**
	 * Create the frame.
	 */
	public AdicionarLocalizacao() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdicionarLocalizacao.class.getResource("/image/almox_new_place.png")));
		setTitle("ADICIONAR LOCALIZA\u00C7\u00C3O");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 540);
		setLocationRelativeTo(null);
		
		LocalizacaoDao localizacaoDao = DaoFactory.createLocalizacaoDao();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 54, 160, 265);

		tableAlmox = new JTable();
		tableAlmox.setFont(new Font("Arial", Font.PLAIN, 12));
		tableAlmox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableFilterHeader filterHeadertableAcesso = new TableFilterHeader(tableAlmox, AutoChoices.ENABLED);
		filterHeadertableAcesso.setPosition(Position.TOP);
		filterHeadertableAcesso.setBackground(Color.white);
		contentPane.setLayout(null);
		scrollPane.setViewportView(tableAlmox);
		contentPane.add(scrollPane);

		table(localizacaoDao);

		JLabel lblNewLabel = new JLabel("ADICIONAR LOCALIZA\u00C7\u00C3O");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setAlignmentX(0.5f);
		lblNewLabel.setBounds(0, 11, 284, 32);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 284, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(UIManager.getColor("Tree.selectionBackground"));
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 284, 11);
		contentPane.add(separator_1);

		textLocal = new JTextField();
		textLocal.setHorizontalAlignment(SwingConstants.CENTER);
		textLocal.setFont(new Font("Arial", Font.PLAIN, 12));
		textLocal.setBounds(83, 330, 130, 20);
		contentPane.add(textLocal);
		textLocal.setColumns(10);

		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		btnFechar.setHorizontalAlignment(SwingConstants.LEFT);
		btnFechar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.setIcon(new ImageIcon(AdicionarLocalizacao.class.getResource("/image/almox_new_place.png")));
		btnFechar.setBounds(77, 454, 130, 35);
		contentPane.add(btnFechar);

		JLabel labelNome = new JLabel("NOME");
		labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNome.setBounds(30, 330, 46, 20);
		contentPane.add(labelNome);
		
		JButton btnDeletar = new JButton("DELETAR");
		btnDeletar.setIcon(new ImageIcon(AdicionarLocalizacao.class.getResource("/image/almox_delete_place.png")));
		btnDeletar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDeletar.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeletar.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeletar.setEnabled(false);
		btnDeletar.setBounds(67, 408, 150, 35);
		contentPane.add(btnDeletar);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(JOptionPane.showConfirmDialog(null, "DESEJA ADICIONAR A LOCALIZA플O?","AVISO",JOptionPane.YES_NO_OPTION)) {
					case 0:
						localizacaoDao.insert(textLocal.getText());
						table(localizacaoDao);
						textLocal.setText("");
						JOptionPane.showMessageDialog(null, "LOCALIZA플O ADICIONADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					break;
					case 1:
						JOptionPane.showMessageDialog(null, "NENHUM DADO ALTERADO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});
		btnAdicionar.setIcon(new ImageIcon(AdicionarLocalizacao.class.getResource("/image/almox_add_place.png")));
		btnAdicionar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAdicionar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAdicionar.setEnabled(true);
		btnAdicionar.setBounds(67, 364, 150, 35);
		contentPane.add(btnAdicionar);

		tableAlmox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableAlmox.getSelectedRow();
				btnAdicionar.setEnabled(false);
				btnDeletar.setEnabled(true);
				textLocal.setText(tableAlmox.getValueAt(i, 0).toString());
			}
		});

		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR A LOCALIZA플O?","AVISO",JOptionPane.YES_NO_OPTION)) {
					case 0:
					try {
						localizacaoDao.delete(textLocal.getText());
						table(localizacaoDao);
						btnAdicionar.setEnabled(true);
						btnDeletar.setEnabled(false);
						JOptionPane.showMessageDialog(null, "LOCALIZA플O EXCLUIDA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "LOCALIZA플O N홒 PODE SER EXCLUIDA POIS POSSUI PRODUTOS VINCULADOS A ELA", "AVISO", JOptionPane.INFORMATION_MESSAGE);
						e.printStackTrace();
					}
						
					break;
					case 1:
						textLocal.setText("");
						btnAdicionar.setEnabled(true);
						btnDeletar.setEnabled(false);
						JOptionPane.showMessageDialog(null, "NENHUM DADO ALTERADO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}

		});
	}

	private void table(LocalizacaoDao localizacaoDao) {
		DefaultTableModel model = new DefaultTableModel();
		localizacaoDao.tableAlmoxarifado(model);
		tableAlmox.setModel(model);
	}
}
