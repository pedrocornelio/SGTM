package aplication.oficina;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.AplicacaoDao;
import dao.DaoFactory;
import dao.MontadoraDao;
import dao.ViaturaDao;
import entities.Aplicacao;
import entities.Viatura;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NovasAplicacoesOficina extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldModelo;
	private JTextField textFieldAno;
	private JTextField textFieldPlaca;
	private JTable tableAplicacao;
	private JComboBox comboBoxMontadora;
	private JButton btnPlus;
	private JButton btnMinus;
	public DefaultTableModel modelAplicacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovasAplicacoesOficina frame = new NovasAplicacoesOficina();
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
	public NovasAplicacoesOficina() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NovasAplicacoesOficina.class.getResource("/image/desktop_package.png")));
		
		setTitle("APLICA\u00C7\u00D5ES");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 480, 390);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		
		MontadoraDao<Object> montadoraDao = DaoFactory.createMontadoraDao();
		AplicacaoDao aplicacaoDao = DaoFactory.createAplicacaoDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		
		JLabel label = new JLabel("APLICA\u00C7\u00C3O(\u00D5ES)");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(30, 11, 119, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("MONTADORA");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(30, 46, 86, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("MODELO");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(30, 81, 86, 23);
		contentPane.add(label_2);
		
		comboBoxMontadora = new JComboBox();
		comboBoxMontadora.setBackground(Color.WHITE);
		DefaultComboBoxModel modelcombobox = new DefaultComboBoxModel();
		montadoraDao.comboBoxMontadora(modelcombobox);
		comboBoxMontadora.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxMontadora.setModel(modelcombobox);
		comboBoxMontadora.setBounds(126, 46, 150, 23);
		contentPane.add(comboBoxMontadora);
		
		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldModelo.getText().toUpperCase();
				textFieldModelo.setText(temp);
			}
		});
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(126, 81, 150, 23);
		contentPane.add(textFieldModelo);
		
		JLabel label_3 = new JLabel("ANO");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Arial", Font.PLAIN, 12));
		label_3.setBounds(30, 116, 86, 23);
		contentPane.add(label_3);
		
		textFieldAno = new JTextField();
		textFieldAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldAno.getText().toUpperCase();
				textFieldAno.setText(temp);
			}
		});
		textFieldAno.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldAno.setColumns(10);
		textFieldAno.setBounds(126, 116, 150, 23);
		contentPane.add(textFieldAno);
		
		JLabel label_4 = new JLabel("OU");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial", Font.PLAIN, 12));
		label_4.setBounds(292, 85, 22, 14);
		contentPane.add(label_4);
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textFieldPlaca.getText().toUpperCase();
				textFieldPlaca.setText(temp);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char number = e.getKeyChar();
				if (!(Character.isLetterOrDigit(number) || (number == KeyEvent.VK_BACK_SPACE) || number == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		textFieldPlaca.setBounds(338, 81, 98, 23);
		contentPane.add(textFieldPlaca);
		
		JLabel label_5 = new JLabel("<HTML><CENTER>VTR DE <BR> REFER\u00CANCIA </HTML>");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Arial", Font.PLAIN, 12));
		label_5.setBounds(338, 11, 98, 35);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("PLACA");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Arial", Font.PLAIN, 12));
		label_6.setBounds(338, 57, 98, 23);
		contentPane.add(label_6);
		
		JButton btnSearchPlaca = new JButton("");
		btnSearchPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Viatura vtr = viaturaDao.findByPlaca((String) textFieldPlaca.getText());
				 comboBoxMontadora.setSelectedItem(vtr.getMontadora().getNomeMontadora());
				 textFieldModelo.setText(vtr.getModelo());
				 textFieldAno.setText(vtr.getAno());
			}
		});
		btnSearchPlaca.setIcon(new ImageIcon(NovasAplicacoesOficina.class.getResource("/image/search.png")));
		btnSearchPlaca.setMargin(new Insets(2, 2, 2, 2));
		btnSearchPlaca.setBounds(368, 114, 38, 35);
		contentPane.add(btnSearchPlaca);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 160, 394, 132);
		contentPane.add(scrollPane);
		
		tableAplicacao = new JTable();
		scrollPane.setViewportView(tableAplicacao);
		TableFilterHeader filterHeader = new TableFilterHeader(tableAplicacao, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		filterHeader.setBackground(Color.white);
		tableAplicacao.setFont(new Font("Arial", Font.PLAIN, 12));
		modelAplicacao = new DefaultTableModel();
		tableAplicacao.setModel(aplicacaoDao.tableAplicacao(modelAplicacao, Requisitar.produtoAplicacao.getId_produto()));
		
		btnPlus = new JButton("");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				Aplicacao aplicacao = new Aplicacao(textFieldModelo.getText(),
						textFieldAno.getText(),
						montadoraDao.findByMontadora((String) comboBoxMontadora.getSelectedItem()),
						Requisitar.produtoAplicacao);
				
				aplicacaoDao.insert(aplicacao);
				
				modelAplicacao = new DefaultTableModel();
				tableAplicacao.setModel(aplicacaoDao.tableAplicacao(modelAplicacao, Requisitar.produtoAplicacao.getId_produto()));
								
			}
		});
		btnPlus.setIcon(new ImageIcon(NovasAplicacoesOficina.class.getResource("/image/insert.png")));
		btnPlus.setBounds(425, 180, 30, 30);
		contentPane.add(btnPlus);
		
		btnMinus = new JButton("");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (tableAplicacao.getSelectedRow() != -1) {
					
					aplicacaoDao.delete(montadoraDao.findByMontadora((String) tableAplicacao.getValueAt(tableAplicacao.getSelectedRow(), 0)).getIdMontadora(),
							(String) tableAplicacao.getValueAt(tableAplicacao.getSelectedRow(), 1),
							(String) tableAplicacao.getValueAt(tableAplicacao.getSelectedRow(), 2));
					
					modelAplicacao.removeRow(tableAplicacao.getSelectedRow());
				}
				
			}
		});
		btnMinus.setIcon(new ImageIcon(NovasAplicacoesOficina.class.getResource("/image/trash.png")));
		btnMinus.setBounds(425, 235, 30, 30);
		contentPane.add(btnMinus);
		
		JButton btnAtualizarar = new JButton("ATUALIZAR");
		btnAtualizarar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnAtualizarar.setIcon(new ImageIcon(NovasAplicacoesOficina.class.getResource("/image/desktop_package.png")));
		btnAtualizarar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAtualizarar.setBounds(148, 304, 140, 35);
		contentPane.add(btnAtualizarar);
		
		

	}
}
