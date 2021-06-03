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
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.DaoFactory;
import dao.MilitarDao;
import dao.ViaturaDao;
import entities.Viatura;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

public class DadosOrdemManutencao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMilitar;
	private JTextField textFieldnBM;
	private JTextField textFieldnBM_1;
	private JTextField textFieldnBM_2;
	private JTextField textFieldnBM_3;
	private JTextField textFieldMilitar_1;
	private JTextField textFieldMilitar_2;
	private JTextField textFieldPlaca;
	private JTextField textFieldModelo;
	private JTextField textFieldMilitar_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadosOrdemManutencao frame = new DadosOrdemManutencao();
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
	public DadosOrdemManutencao() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DadosOrdemManutencao.class.getResource("/image/user_interface/gear_maintenance.png")));
		setTitle("DADOS DA ORDEM DE MANUTEN\u00C7\u00C3O");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MilitarDao militarDao = DaoFactory.createMilitarDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 784, 5);
		contentPane.add(separator);
		
		JLabel lblOrdemManuteno = new JLabel("DADOS DA ORDEM DE MANUTEN\u00C7\u00C3O");
		lblOrdemManuteno.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManuteno.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrdemManuteno.setAlignmentX(0.5f);
		lblOrdemManuteno.setBounds(0, 11, 784, 28);
		contentPane.add(lblOrdemManuteno);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 784, 5);
		contentPane.add(separator_1);
		
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
		textFieldPlaca.setBounds(156, 60, 160, 23);
		contentPane.add(textFieldPlaca);
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(97, 60, 49, 23);
		contentPane.add(lblPlaca);
		
		JButton btnSearchPlaca = new JButton("");
		btnSearchPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Viatura vtr = viaturaDao.findByPlaca((String) textFieldPlaca.getText());
				textFieldModelo.setText(vtr.getModelo());
			}
		});
		btnSearchPlaca.setIcon(new ImageIcon(DadosOrdemManutencao.class.getResource("/image/user_interface/search.png")));
		btnSearchPlaca.setBounds(332, 54, 38, 35);
		contentPane.add(btnSearchPlaca);
		
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModelo.setBounds(392, 60, 60, 23);
		contentPane.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setEditable(false);
		textFieldModelo.setColumns(10);
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setBounds(462, 60, 198, 23);
		contentPane.add(textFieldModelo);
		
		JLabel lblBaixa = new JLabel("MILITAR DA BAIXA");
		lblBaixa.setHorizontalAlignment(SwingConstants.LEFT);
		lblBaixa.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBaixa.setBounds(20, 112, 160, 23);
		contentPane.add(lblBaixa);
		
		JLabel lblNBM = new JLabel("N\u00BA BM");
		lblNBM.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM.setBounds(50, 133, 45, 23);
		contentPane.add(lblNBM);
		
		textFieldnBM = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM.setBounds(27, 158, 90, 23);
		contentPane.add(textFieldnBM);
		
		JButton btnSearchMilitar = new JButton("");
		btnSearchMilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM.getText()) != null) {
					textFieldMilitar.setText(militarDao.findByNBM(textFieldnBM.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitar.setIcon(new ImageIcon(DadosOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar.setBounds(125, 152, 38, 35);
		contentPane.add(btnSearchMilitar);
		
		textFieldMilitar = new JTextField();
		textFieldMilitar.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar.setEditable(false);
		textFieldMilitar.setBackground(Color.WHITE);
		textFieldMilitar.setBounds(180, 158, 200, 23);
		contentPane.add(textFieldMilitar);
		
		JLabel lblMilitar = new JLabel("MILITAR");
		lblMilitar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar.setBounds(255, 133, 51, 23);
		contentPane.add(lblMilitar);
		
		JButton btnSearchMilitar_1 = new JButton("");
		btnSearchMilitar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM_1.getText()) != null) {
					textFieldMilitar_1.setText(militarDao.findByNBM(textFieldnBM_1.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitar_1.setIcon(new ImageIcon(DadosOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar_1.setBounds(510, 152, 38, 35);
		contentPane.add(btnSearchMilitar_1);
		
		textFieldnBM_1 = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM_1.setBounds(412, 158, 90, 23);
		contentPane.add(textFieldnBM_1);
		
		JLabel lblNBM_1 = new JLabel("N\u00BA BM");
		lblNBM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_1.setBounds(435, 133, 45, 23);
		contentPane.add(lblNBM_1);
		
		JLabel lblTriagem = new JLabel("MILITAR DA TRIAGEM");
		lblTriagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblTriagem.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTriagem.setBounds(405, 112, 160, 23);
		contentPane.add(lblTriagem);
		
		textFieldMilitar_1 = new JTextField();
		textFieldMilitar_1.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar_1.setEditable(false);
		textFieldMilitar_1.setBackground(Color.WHITE);
		textFieldMilitar_1.setBounds(565, 158, 201, 23);
		contentPane.add(textFieldMilitar_1);
		
		JLabel lblMilitar_1 = new JLabel("MILITAR");
		lblMilitar_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_1.setBounds(640, 133, 51, 23);
		contentPane.add(lblMilitar_1);
		
		JLabel lblLiberacao = new JLabel("MILITAR DA LIBERA\u00C7\u00C3O");
		lblLiberacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblLiberacao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLiberacao.setBounds(20, 387, 160, 23);
		contentPane.add(lblLiberacao);
		
		JLabel lblNBM_2 = new JLabel("N\u00BA BM");
		lblNBM_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_2.setBounds(50, 412, 45, 23);
		contentPane.add(lblNBM_2);
		
		textFieldnBM_2 = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM_2.setBounds(27, 437, 90, 23);
		contentPane.add(textFieldnBM_2);
		
		JButton btnSearchMilitar_2 = new JButton("");
		btnSearchMilitar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM_2.getText()) != null) {
					textFieldMilitar_2.setText(militarDao.findByNBM(textFieldnBM_2.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitar_2.setIcon(new ImageIcon(DadosOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar_2.setBounds(125, 431, 38, 35);
		contentPane.add(btnSearchMilitar_2);
		
		textFieldMilitar_2 = new JTextField();
		textFieldMilitar_2.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar_2.setEditable(false);
		textFieldMilitar_2.setBackground(Color.WHITE);
		textFieldMilitar_2.setBounds(180, 437, 200, 23);
		contentPane.add(textFieldMilitar_2);
		
		JLabel lblMilitar_2 = new JLabel("MILITAR");
		lblMilitar_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_2.setBounds(255, 412, 51, 23);
		contentPane.add(lblMilitar_2);
		
		JLabel lblAlta = new JLabel("MILITAR DA ALTA");
		lblAlta.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAlta.setBounds(405, 387, 160, 23);
		contentPane.add(lblAlta);
		
		JLabel lblNBM_3 = new JLabel("N\u00BA BM");
		lblNBM_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_3.setBounds(435, 412, 45, 23);
		contentPane.add(lblNBM_3);
		
		textFieldnBM_3 = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM_3.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM_3.setBounds(412, 437, 90, 23);
		contentPane.add(textFieldnBM_3);
		
		JButton btnSearchMilitar_3 = new JButton("");
		btnSearchMilitar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM_3.getText()) != null) {
					textFieldMilitar_3.setText(militarDao.findByNBM(textFieldnBM_3.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitar_3.setIcon(new ImageIcon(DadosOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar_3.setBounds(510, 431, 38, 35);
		contentPane.add(btnSearchMilitar_3);
		
		textFieldMilitar_3 = new JTextField();
		textFieldMilitar_3.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar_3.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar_3.setEditable(false);
		textFieldMilitar_3.setBackground(Color.WHITE);
		textFieldMilitar_3.setBounds(565, 437, 201, 23);
		contentPane.add(textFieldMilitar_3);
		
		JLabel lblMilitar_3 = new JLabel("MILITAR");
		lblMilitar_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_3.setBounds(640, 412, 51, 23);
		contentPane.add(lblMilitar_3);
		
		JTextPane textPaneRelatos = new JTextPane();
		textPaneRelatos.setBorder(new LineBorder(Color.GRAY));
		textPaneRelatos.setBounds(20, 243, 746, 108);
		contentPane.add(textPaneRelatos);
		
		JLabel lblRelatosVTR = new JLabel("RELATOS DA VIATURA");
		lblRelatosVTR.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelatosVTR.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRelatosVTR.setBounds(20, 209, 160, 23);
		contentPane.add(lblRelatosVTR);
		
		JButton btnCriar = new JButton("CRIAR");
		btnCriar.setMargin(new Insets(2, 5, 2, 5));
		btnCriar.setIconTextGap(0);
		btnCriar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCriar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCriar.setEnabled(false);
		btnCriar.setBounds(311, 497, 132, 35);
		contentPane.add(btnCriar);
		
		JCheckBox chckbxLiberacao = new JCheckBox("MESMO MILITAR DA TRIAGEM?");
		chckbxLiberacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxLiberacao.isSelected() == true) {
					btnSearchMilitar_2.setEnabled(false);
					if (!textFieldMilitar_1.getText().isEmpty()) {
						textFieldnBM_2.setText(textFieldnBM_1.getText().toString());
						textFieldMilitar_2.setText(textFieldMilitar_1.getText().toString());
					}
					else {
						JOptionPane.showMessageDialog(null, "É NECESSÁRIO INSERIR O MILITAR DE LIBERAÇÃO ANTES", "MILITAR NÃO SELECIONADO", 0);
						chckbxLiberacao.setSelected(false);
						btnSearchMilitar_2.setEnabled(true);
					}
				} else {
					btnSearchMilitar_2.setEnabled(true);
					textFieldnBM_2.setText(null);
					textFieldMilitar_2.setText(null);
				}
			}
		});
		chckbxLiberacao.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxLiberacao.setBounds(186, 387, 200, 23);
		contentPane.add(chckbxLiberacao);
		
		JCheckBox chckbxAlta = new JCheckBox("MESMO MILITAR DA BAIXA?");
		chckbxAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAlta.isSelected() == true) {
					btnSearchMilitar_3.setEnabled(false);
					if (!textFieldMilitar.getText().isEmpty()) {
						textFieldnBM_3.setText(textFieldnBM.getText().toString());
						textFieldMilitar_3.setText(textFieldMilitar.getText().toString());
					}
					else {
						JOptionPane.showMessageDialog(null, "É NECESSÁRIO INSERIR O MILITAR DA BAIXA ANTES", "MILITAR NÃO SELECIONADO", 0);
						chckbxAlta.setSelected(false);
						btnSearchMilitar_3.setEnabled(true);
					}
				} else {
					btnSearchMilitar_3.setEnabled(true);
					textFieldnBM_3.setText(null);
					textFieldMilitar_3.setText(null);
				}
			}
		});
		chckbxAlta.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxAlta.setBounds(530, 387, 200, 23);
		contentPane.add(chckbxAlta);
		setLocationRelativeTo(null);
		
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
		
		
	}
}
