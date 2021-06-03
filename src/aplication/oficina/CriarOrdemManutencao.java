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
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.DaoFactory;
import dao.MilitarDao;
import dao.OrdemManutencaoDao;
import dao.ViaturaDao;
import entities.Militar;
import entities.OrdemManutencao;
import entities.Servico;
import entities.Viatura;

public class CriarOrdemManutencao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMilitar;
	private JTextField textFieldnBM;
	private JTextField textFieldnBM_1;
	private JTextField textFieldMilitar_1;
	private JTextField textFieldPlaca;
	private JTextField textFieldModelo;
	private JButton btnCriar;
	private JTextPane textPaneRelatos;
	private JButton btnSearchMilitar;
	private JButton btnSearchMilitar_1;
	private JButton btnSearchPlaca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarOrdemManutencao frame = new CriarOrdemManutencao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public CriarOrdemManutencao() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CriarOrdemManutencao.class.getResource("/image/user_interface/gear_maintenance.png")));
		setTitle("CRIAR ORDEM DE MANUTEN\u00C7\u00C3O");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				validar();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		MilitarDao militarDao = DaoFactory.createMilitarDao();
		ViaturaDao viaturaDao = DaoFactory.createViaturaDao();
		OrdemManutencaoDao ordemManutencaoDao = DaoFactory.createOrdemServicoDao();
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "DESEJA FECHAR A JANELA SEM SALVAR AS ALTERAÇÕES",
						"FECHAR JANELA", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					
					DefaultTableModel modelOrdemManutencao = new DefaultTableModel();
					aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(ordemManutencaoDao.tableOrdemManutencao(modelOrdemManutencao));
					aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(modelOrdemManutencao);
					
					setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			}
		});

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(2, 5));
		separator.setForeground(SystemColor.textHighlight);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 8, 534, 5);
		contentPane.add(separator);

		JLabel lblOrdemManuteno = new JLabel("CRIAR ORDEM DE MANUTEN\u00C7\u00C3O");
		lblOrdemManuteno.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManuteno.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrdemManuteno.setAlignmentX(0.5f);
		lblOrdemManuteno.setBounds(0, 11, 534, 28);
		contentPane.add(lblOrdemManuteno);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 534, 5);
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
		textFieldPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchPlaca.doClick();
					textFieldnBM.requestFocusInWindow();
				}
			}
		});
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		textFieldPlaca.setBounds(169, 54, 198, 23);
		contentPane.add(textFieldPlaca);

		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(107, 54, 49, 23);
		contentPane.add(lblPlaca);
		
		btnSearchPlaca = new JButton("");
		btnSearchPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Viatura vtr = viaturaDao.findByPlaca((String) textFieldPlaca.getText());
				textFieldModelo.setText(vtr.getModelo());
			}
		});
		btnSearchPlaca.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/user_interface/search.png")));
		btnSearchPlaca.setBounds(377, 62, 38, 35);
		contentPane.add(btnSearchPlaca);

		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModelo.setBounds(99, 82, 60, 23);
		contentPane.add(lblModelo);

		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setEditable(false);
		textFieldModelo.setColumns(10);
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setBounds(169, 82, 198, 23);
		contentPane.add(textFieldModelo);

		JLabel lblBaixa = new JLabel("MILITAR DA BAIXA");
		lblBaixa.setHorizontalAlignment(SwingConstants.LEFT);
		lblBaixa.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBaixa.setBounds(52, 126, 160, 23);
		contentPane.add(lblBaixa);

		JLabel lblNBM = new JLabel("N\u00BA BM");
		lblNBM.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM.setBounds(82, 147, 45, 23);
		contentPane.add(lblNBM);

		textFieldnBM = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchMilitar.doClick();
					textFieldnBM_1.requestFocusInWindow();
				}
			}
		});
		textFieldnBM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM.setBounds(59, 172, 90, 23);
		contentPane.add(textFieldnBM);

		btnSearchMilitar = new JButton("");
		btnSearchMilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM.getText()) != null) {
					textFieldMilitar.setText(militarDao.findByNBM(textFieldnBM.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitar.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar.setBounds(157, 166, 38, 35);
		contentPane.add(btnSearchMilitar);

		textFieldMilitar = new JTextField();
		textFieldMilitar.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar.setEditable(false);
		textFieldMilitar.setBackground(Color.WHITE);
		textFieldMilitar.setBounds(212, 172, 250, 23);
		contentPane.add(textFieldMilitar);

		JLabel lblMilitar = new JLabel("MILITAR");
		lblMilitar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar.setBounds(287, 147, 51, 23);
		contentPane.add(lblMilitar);

		btnSearchMilitar_1 = new JButton("");
		btnSearchMilitar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBM_1.getText()) != null) {
					textFieldMilitar_1.setText(militarDao.findByNBM(textFieldnBM_1.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR",
							"ERRO", 0);
				}
			}
		});
		btnSearchMilitar_1.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/user_interface/search_user.png")));
		btnSearchMilitar_1.setBounds(157, 246, 38, 35);
		contentPane.add(btnSearchMilitar_1);

		textFieldnBM_1 = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBM_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchMilitar_1.doClick();
					textPaneRelatos.requestFocusInWindow();
				}
			}
		});
		textFieldnBM_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBM_1.setBounds(59, 252, 90, 23);
		contentPane.add(textFieldnBM_1);

		JLabel lblNBM_1 = new JLabel("N\u00BA BM");
		lblNBM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_1.setBounds(82, 227, 45, 23);
		contentPane.add(lblNBM_1);

		JLabel lblTriagem = new JLabel("MILITAR DA TRIAGEM");
		lblTriagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblTriagem.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTriagem.setBounds(52, 206, 160, 23);
		contentPane.add(lblTriagem);

		textFieldMilitar_1 = new JTextField();
		textFieldMilitar_1.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitar_1.setEditable(false);
		textFieldMilitar_1.setBackground(Color.WHITE);
		textFieldMilitar_1.setBounds(212, 252, 250, 23);
		contentPane.add(textFieldMilitar_1);

		JLabel lblMilitar_1 = new JLabel("MILITAR");
		lblMilitar_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_1.setBounds(287, 227, 51, 23);
		contentPane.add(lblMilitar_1);

		JLabel lblRelatosVTR = new JLabel("RELATOS DA VIATURA");
		lblRelatosVTR.setHorizontalAlignment(SwingConstants.LEFT);
		lblRelatosVTR.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRelatosVTR.setBounds(20, 303, 160, 23);
		contentPane.add(lblRelatosVTR);

		btnCriar = new JButton("CRIAR OM");
		btnCriar.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/user_interface/gear_maintenance.png")));
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// SETAR DATA E HORA FIM
				Date data_inicio = new java.sql.Date(new java.util.Date().getTime());
				Time hora = new java.sql.Time(new java.util.Date().getTime());

				OrdemManutencao ordemManutencao = new OrdemManutencao();
				ordemManutencao.setnOM(data_inicio.toString().replace("-", "") + textFieldPlaca.getText().toString());
				
				// SETAR MILITAR BAIXA
				Militar militarBaixa = new Militar();
				militarBaixa = militarDao.findByNBM(textFieldnBM.getText().toString());

				// SETAR MILITAR TRIAGEM
				Militar militarTriagem = new Militar();
				militarTriagem = militarDao.findByNBM(textFieldnBM_1.getText().toString());
				
				// SETAR O SERVICO
				Servico servico = new Servico(null, textPaneRelatos.getText().toString(), null, null, null);
				
				// SETAR VIATURA
				Viatura viatura = viaturaDao.findByPlaca((String) textFieldPlaca.getText());

				if (ordemManutencaoDao.findByOM(ordemManutencao).getId_om() == null) {
					OrdemManutencao om = new OrdemManutencao(null, ordemManutencao.getnOM(), 
							data_inicio, hora, null, null,
							textPaneRelatos.getText(), 
							militarBaixa, militarTriagem, null, null, 
							servico, viatura);
					ordemManutencaoDao.insert(om);
					JOptionPane.showMessageDialog(null,"REQUISIÇÃO O.M. " + ordemManutencao.getnOM() + " CRIADA COM SUCESSO", "REQUISIÇÃO", 1);
					
					DefaultTableModel modelOrdemManutencao = new DefaultTableModel();
					aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(ordemManutencaoDao.tableOrdemManutencao(modelOrdemManutencao));
					aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(modelOrdemManutencao);
					
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null,"REQUISIÇÃO O.M. " + ordemManutencao.getnOM() + " JÁ CRIADA", "REQUISIÇÃO", 0);
				}
				
				
				
			}
		});
		btnCriar.setMargin(new Insets(2, 5, 2, 5));
		btnCriar.setIconTextGap(0);
		btnCriar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCriar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCriar.setEnabled(false);
		btnCriar.setBounds(201, 497, 132, 35);
		contentPane.add(btnCriar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 337, 492, 140);
		contentPane.add(scrollPane);

		textPaneRelatos = new JTextPane();
		textPaneRelatos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validar();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String temp = textPaneRelatos.getText().toUpperCase();
				textPaneRelatos.setText(temp);
			}
		});
		scrollPane.setViewportView(textPaneRelatos);
		textPaneRelatos.setFont(new Font("Arial", Font.PLAIN, 12));

	}

	public void validar() {
		if (textFieldModelo.getText().length() != 0 & textFieldMilitar.getText().length() != 0
				& textFieldMilitar_1.getText().length() != 0 & textPaneRelatos.getText().length() > 10) {
			btnCriar.setEnabled(true);
		} else {
			btnCriar.setEnabled(false);
		}
	}
}
