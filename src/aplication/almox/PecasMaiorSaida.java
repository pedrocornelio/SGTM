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
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import dao.DaoFactory;
import dao.ProdutoDao;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

public class PecasMaiorSaida extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePecasMaiorSaida;
	private JTextField textFieldDataInicio;
	private JTextField textFieldDataFim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PecasMaiorSaida frame = new PecasMaiorSaida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public PecasMaiorSaida() throws ParseException {
		setTitle("PEÇAS COM MAIS SAÍDA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PecasMaiorSaida.class.getResource("/image/most_used.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		setContentPane(contentPane);
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
		
		
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		
		JLabel lblTitulo = new JLabel("PE\u00C7AS COM MAIS SA\u00CDDA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTitulo.setAlignmentX(0.5f);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tablePecasMaiorSaida = new JTable();
		tablePecasMaiorSaida.setFont(new Font("Arial", Font.PLAIN, 12));
		TableFilterHeader filterHeader = new TableFilterHeader(tablePecasMaiorSaida, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		scrollPane.setViewportView(tablePecasMaiorSaida);
		
		textFieldDataInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textFieldDataInicio.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDataInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldDataInicio.setColumns(10);
		
		JLabel lblDataInicio = new JLabel("DATA IN\u00CDCIO");
		lblDataInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDataFim = new JLabel("DATA FIM");
		lblDataFim.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFim.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textFieldDataFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textFieldDataFim.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDataFim.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldDataFim.setColumns(10);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dataInicio = textFieldDataInicio.getText().substring(6) + "-" +
						textFieldDataInicio.getText().substring(3, 5) + "-" +
						textFieldDataInicio.getText().substring(0, 2);
				
				String dataFim = textFieldDataFim.getText().substring(6) + "-" +
						textFieldDataFim.getText().substring(3, 5) + "-" +
						textFieldDataFim.getText().substring(0, 2);
				
				DefaultTableModel modelPecasMaiorSaida = new DefaultTableModel();
				tablePecasMaiorSaida.setModel(produtoDao.tablePecasMaiorSaida(modelPecasMaiorSaida, dataInicio, dataFim));
				tablePecasMaiorSaida.getColumnModel().getColumn(0).setMinWidth(35);
				tablePecasMaiorSaida.getColumnModel().getColumn(0).setPreferredWidth(45);
				tablePecasMaiorSaida.getColumnModel().getColumn(0).setMaxWidth(50);
				tablePecasMaiorSaida.getColumnModel().getColumn(5).setMinWidth(35);
				tablePecasMaiorSaida.getColumnModel().getColumn(5).setPreferredWidth(40);
				tablePecasMaiorSaida.getColumnModel().getColumn(5).setMaxWidth(45);
			}
		});
		btnSearch.setIcon(new ImageIcon(PecasMaiorSaida.class.getResource("/image/search.png")));
		
		JButton btnFechar = new JButton(" FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ATUALIZANDO AS TABELAS DO ESTOQUE
				Almoxarifado.btnRefresh.doClick();

				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(PecasMaiorSaida.class.getResource("/image/most_used.png")));
		btnFechar.setMargin(new Insets(2, 5, 2, 5));
		btnFechar.setIconTextGap(0);
		btnFechar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.setAlignmentY(1.0f);
		
		JLabel lblEspaco = new JLabel("");
		
		JLabel lblEspaco_1 = new JLabel("");
		
		JLabel lblEspaco_2 = new JLabel("");
		
		JLabel lblEspaco_3 = new JLabel("");
		
		JButton print = new JButton("");
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("IMPRESSÃO DA TABELA");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					tablePecasMaiorSaida.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("Cannot print %s%n", e1.getMessage());
				}
			}
		});
		print.setIcon(new ImageIcon(PecasMaiorSaida.class.getResource("/image/pdf.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblEspaco, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(23)
					.addComponent(lblDataInicio, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textFieldDataInicio, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(lblDataFim, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textFieldDataFim, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(lblEspaco_1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(print, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
					.addGap(21))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addComponent(lblEspaco_2, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addGap(74)
					.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(81)
					.addComponent(lblEspaco_3, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addGap(68))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblEspaco, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblEspaco_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(print, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(textFieldDataFim, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblDataFim, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(textFieldDataInicio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblDataInicio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspaco_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspaco_3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
