package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Relogio extends JLabel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Relogio() {
        Timer t = new Timer(1000, this);
        t.start();
  }

  public void actionPerformed(ActionEvent ae) {
        String mascaraHora = "HH:mm:ss";
        String hora;
        Date agora = new Date();
        SimpleDateFormat formata = new SimpleDateFormat(mascaraHora);
        hora = formata.format(agora);
        setText(hora);
    }

}
