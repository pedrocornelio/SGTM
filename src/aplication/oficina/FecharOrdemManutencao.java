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
import javax.swing.JSeparator;
import javax.swing.JTextField;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FecharOrdemManutencao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMilitarBaixa;
	private JTextField textFieldnBMBaixa;
	private JTextField textFieldnBMTriagem;
	private JTextField textFieldMilitarTriagem;
	private JTextField textFieldPlaca;
	private JTextField textFieldModelo;
	private JButton btnFechar;
	private JFormattedTextField textFieldnBMAlta;
	private JTextField textFieldMilitarAlta;
	private JFormattedTextField textFieldnBMLiberacao;
	private JTextField textFieldMilitarLiberacao;
	private JButton btnSearchMilitarAlta;
	private JButton btnSearchMilitarLiberacao;
	private JTextField textFieldnOM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FecharOrdemManutencao frame = new FecharOrdemManutencao();
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
	public FecharOrdemManutencao() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FecharOrdemManutencao.class.getResource("/image/gear_maintenance.png")));
		setTitle("FECHAR ORDEM DE MANUTEN\u00C7\u00C3O");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 530, 600);
		
		contentPane = new JPanel();
		contentPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldMilitarAlta.requestFocusInWindow();
			}
		});
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
		separator.setBounds(0, 8, 511, 5);
		contentPane.add(separator);

		JLabel lblOrdemManuteno = new JLabel("FECHAR ORDEM DE MANUTEN\u00C7\u00C3O");
		lblOrdemManuteno.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdemManuteno.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrdemManuteno.setAlignmentX(0.5f);
		lblOrdemManuteno.setBounds(0, 11, 511, 28);
		contentPane.add(lblOrdemManuteno);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(2, 5));
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 38, 511, 5);
		contentPane.add(separator_1);

		textFieldPlaca = new JTextField();
		textFieldPlaca.setEditable(false);
		textFieldPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldPlaca.setColumns(10);
		textFieldPlaca.setBounds(82, 118, 110, 23);
		contentPane.add(textFieldPlaca);
		textFieldPlaca.setText(aplication.oficina.OrdemManutencao.textFieldPlaca.getText().toString());
		
		JLabel lblPlaca = new JLabel("PLACA");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlaca.setBounds(20, 118, 49, 23);
		contentPane.add(lblPlaca);

		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModelo.setBounds(212, 118, 60, 23);
		contentPane.add(lblModelo);

		textFieldModelo = new JTextField();
		textFieldModelo.setEditable(false);
		textFieldModelo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldModelo.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(282, 118, 208, 23);
		contentPane.add(textFieldModelo);
		textFieldModelo.setText(viaturaDao.findByPlaca(aplication.oficina.OrdemManutencao.textFieldPlaca.getText().toString()).getModelo().toString());

		JLabel lblBaixa = new JLabel("MILITAR DA BAIXA");
		lblBaixa.setHorizontalAlignment(SwingConstants.LEFT);
		lblBaixa.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBaixa.setBounds(52, 158, 160, 23);
		contentPane.add(lblBaixa);

		JLabel lblNBM = new JLabel("N\u00BA BM");
		lblNBM.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM.setBounds(82, 179, 45, 23);
		contentPane.add(lblNBM);

		textFieldnBMBaixa = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBMBaixa.setEditable(false);
		textFieldnBMBaixa.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBMBaixa.setBounds(59, 204, 90, 23);
		contentPane.add(textFieldnBMBaixa);
		textFieldnBMBaixa.setText(militarDao.findByName(aplication.oficina.OrdemManutencao.textFieldBaixa.getText().toString()).getnBM().toString());

		textFieldMilitarBaixa = new JTextField();
		textFieldMilitarBaixa.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitarBaixa.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitarBaixa.setEditable(false);
		textFieldMilitarBaixa.setBounds(212, 204, 278, 23);
		contentPane.add(textFieldMilitarBaixa);
		textFieldMilitarBaixa.setText(aplication.oficina.OrdemManutencao.textFieldBaixa.getText().toString());

		JLabel lblMilitar = new JLabel("MILITAR");
		lblMilitar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar.setBounds(287, 179, 51, 23);
		contentPane.add(lblMilitar);

		textFieldnBMTriagem = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBMTriagem.setEditable(false);
		textFieldnBMTriagem.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBMTriagem.setBounds(59, 284, 90, 23);
		contentPane.add(textFieldnBMTriagem);
		textFieldnBMTriagem.setText(militarDao.findByName(aplication.oficina.OrdemManutencao.textFieldTriagem.getText().toString()).getnBM().toString());

		JLabel lblNBM_1 = new JLabel("N\u00BA BM");
		lblNBM_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_1.setBounds(82, 259, 45, 23);
		contentPane.add(lblNBM_1);

		JLabel lblTriagem = new JLabel("MILITAR DA TRIAGEM");
		lblTriagem.setHorizontalAlignment(SwingConstants.LEFT);
		lblTriagem.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTriagem.setBounds(52, 238, 160, 23);
		contentPane.add(lblTriagem);

		textFieldMilitarTriagem = new JTextField();
		textFieldMilitarTriagem.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitarTriagem.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitarTriagem.setEditable(false);
		textFieldMilitarTriagem.setBounds(212, 284, 278, 23);
		contentPane.add(textFieldMilitarTriagem);
		textFieldMilitarTriagem.setText(aplication.oficina.OrdemManutencao.textFieldTriagem.getText().toString());

		JLabel lblMilitar_1 = new JLabel("MILITAR");
		lblMilitar_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_1.setBounds(287, 259, 51, 23);
		contentPane.add(lblMilitar_1);

		btnFechar = new JButton("FECHAR OM");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// SETAR DATA E HORA FIM
				Date data_fim = new java.sql.Date(new java.util.Date().getTime());
				Time hora_fim = new java.sql.Time(new java.util.Date().getTime());
				
				// SETAR MILITAR ALTA
				Militar militarAlta = new Militar();
				militarAlta = militarDao.findByNBM(textFieldnBMAlta.getText().toString());

				// SETAR MILITAR LIBERAÇÃO
				Militar militarLiberacao = new Militar();
				militarLiberacao = militarDao.findByNBM(textFieldnBMLiberacao.getText().toString());

				// SETAR OS DADOS PARA UPDATE DA ORDEM DE MANUNTENÇÃO
				OrdemManutencao ordemManutencao = new OrdemManutencao();
				ordemManutencao = ordemManutencaoDao.findByNOM(textFieldnOM.getText().toString());
				ordemManutencao.setData_fim(data_fim);
				ordemManutencao.setHora_fim(hora_fim);
				ordemManutencao.setMilitarLiberacao(militarLiberacao);
				ordemManutencao.setMilitarAlta(militarAlta);
				
				ordemManutencaoDao.updateNOM(ordemManutencao);
				
				JOptionPane.showMessageDialog(null,"REQUISIÇÃO O.M. " + ordemManutencao.getnOM() + " FECHADA COM SUCESSO", "REQUISIÇÃO", 1);

				DefaultTableModel modelOrdemManutencao = new DefaultTableModel();
				aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(ordemManutencaoDao.tableOrdemManutencao(modelOrdemManutencao));
				aplication.oficina.OrdemManutencao.tableOrdemManutencao.setModel(modelOrdemManutencao);
				
				dispose();
				
			}
		});
		btnFechar.setMargin(new Insets(2, 5, 2, 5));
		btnFechar.setIconTextGap(0);
		btnFechar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFechar.setEnabled(false);
		btnFechar.setBounds(189, 497, 132, 35);
		contentPane.add(btnFechar);
		
		JLabel lblViatura = new JLabel("VIATURA");
		lblViatura.setHorizontalAlignment(SwingConstants.LEFT);
		lblViatura.setFont(new Font("Arial", Font.PLAIN, 14));
		lblViatura.setBounds(52, 88, 160, 23);
		contentPane.add(lblViatura);
		
		JLabel lblLiberacao = new JLabel("MILITAR DA LIBERA\u00C7\u00C3O");
		lblLiberacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblLiberacao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLiberacao.setBounds(52, 398, 160, 23);
		contentPane.add(lblLiberacao);
		
		JLabel lblAlta = new JLabel("MILITAR DA ALTA");
		lblAlta.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAlta.setBounds(52, 318, 160, 23);
		contentPane.add(lblAlta);
		
		JLabel lblNBM_2 = new JLabel("N\u00BA BM");
		lblNBM_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_2.setBounds(82, 339, 45, 23);
		contentPane.add(lblNBM_2);
		
		textFieldnBMAlta = new JFormattedTextField(new MaskFormatter("###.###-#"));
		//textFieldnBMAlta.requestFocusInWindow();
		textFieldnBMAlta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchMilitarAlta.doClick();
					textFieldnBMLiberacao.requestFocusInWindow();
				}
			}
		});
		textFieldnBMAlta.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBMAlta.setBounds(59, 364, 90, 23);
		contentPane.add(textFieldnBMAlta);
		
		JLabel lblNBM_3 = new JLabel("N\u00BA BM");
		lblNBM_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNBM_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNBM_3.setBounds(82, 419, 45, 23);
		contentPane.add(lblNBM_3);
		
		textFieldnBMLiberacao = new JFormattedTextField(new MaskFormatter("###.###-#"));
		textFieldnBMLiberacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchMilitarLiberacao.doClick();
					validar();
					btnFechar.doClick();
				}
			}
		});
		textFieldnBMLiberacao.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnBMLiberacao.setBounds(59, 444, 90, 23);
		contentPane.add(textFieldnBMLiberacao);
		
		textFieldMilitarLiberacao = new JTextField();
		textFieldMilitarLiberacao.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitarLiberacao.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitarLiberacao.setBounds(212, 444, 278, 23);
		contentPane.add(textFieldMilitarLiberacao);
		
		JLabel lblMilitar_3 = new JLabel("MILITAR");
		lblMilitar_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_3.setBounds(287, 419, 51, 23);
		contentPane.add(lblMilitar_3);
		
		textFieldMilitarAlta = new JTextField();
		textFieldMilitarAlta.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMilitarAlta.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldMilitarAlta.setBounds(212, 364, 278, 23);
		contentPane.add(textFieldMilitarAlta);
		
		JLabel lblMilitar_2 = new JLabel("MILITAR");
		lblMilitar_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMilitar_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMilitar_2.setBounds(287, 339, 51, 23);
		contentPane.add(lblMilitar_2);
		
		btnSearchMilitarAlta = new JButton("");
		btnSearchMilitarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBMAlta.getText()) != null) {
					textFieldMilitarAlta.setText(militarDao.findByNBM(textFieldnBMAlta.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitarAlta.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/search_user.png")));
		btnSearchMilitarAlta.setBounds(159, 358, 38, 35);
		contentPane.add(btnSearchMilitarAlta);
		
		btnSearchMilitarLiberacao = new JButton("");
		btnSearchMilitarLiberacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (militarDao.findByNBM(textFieldnBMLiberacao.getText()) != null) {
					textFieldMilitarLiberacao.setText(militarDao.findByNBM(textFieldnBMLiberacao.getText()).getNome());
				} else {
					JOptionPane.showMessageDialog(null, "NÚMERO BM ERRADO OU MILITAR NÃO IDENTIFICADO, FAVOR CONFERIR", "ERRO", 0);
				}
			}
		});
		btnSearchMilitarLiberacao.setIcon(new ImageIcon(CriarOrdemManutencao.class.getResource("/image/search_user.png")));
		btnSearchMilitarLiberacao.setBounds(159, 438, 38, 35);
		contentPane.add(btnSearchMilitarLiberacao);
		
		JLabel lblNOrdemManutencao = new JLabel("N\u00BA ORDEM MANUTEN\u00C7\u00C3O");
		lblNOrdemManutencao.setHorizontalAlignment(SwingConstants.LEFT);
		lblNOrdemManutencao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNOrdemManutencao.setBounds(90, 54, 179, 23);
		contentPane.add(lblNOrdemManutencao);
		
		textFieldnOM = new JTextField();
		textFieldnOM.setText("<dynamic>");
		textFieldnOM.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldnOM.setEditable(false);
		textFieldnOM.setColumns(10);
		textFieldnOM.setBounds(279, 55, 132, 23);
		contentPane.add(textFieldnOM);
		textFieldnOM.setText(aplication.oficina.OrdemManutencao.textFieldNOm.getText().toString());

	}
	
	public void validar() {
		if (textFieldMilitarAlta.getText().length() != 0
				& textFieldMilitarLiberacao.getText().length() != 0 ) {
			btnFechar.setEnabled(true);
		} else {
			btnFechar.setEnabled(false);
		}
	}

}
