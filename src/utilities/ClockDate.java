/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockDate {

	Date data = new Date();
	SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");

	private String dataFormatada = formatarData.format(data);
	private String dataHoraFormatada = formatarHora.format(data);
	//private Long horaFormatada = (Long) formatarHora.format(data);

	public String getDataFormatada() {
		return dataFormatada;
	}

	public String getDataHoraFormatada() {
		return dataHoraFormatada;
	}
	
	/*public Long horaformatada () {
		return dataHoraFormatada;
	}*/

}
