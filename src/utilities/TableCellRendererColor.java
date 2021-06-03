package utilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererColor extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Component componente;
	
	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String status = (String)table.getModel().getValueAt(row, 10);
		
		if (isSelected) {
            this.setForeground(Color.BLUE.darker());
            this.setBackground(table.getSelectionBackground());
        }

		if ("-1".equals(status)) {
			setBackground(Color.RED);
            setForeground(Color.WHITE);
		}else {
			if ("0".equals(status)) {
				setBackground(Color.YELLOW);
	            setForeground(Color.BLACK);
			} else {
				setBackground(table.getBackground());
				setForeground(table.getForeground());
			}
		}

		return componente;
	}

}
