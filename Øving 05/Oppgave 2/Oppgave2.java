import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.*;

class Oppgave2 extends JFrame {

	public static void main(String[] args){
		Oppgave2 vindu = new Oppgave2("Valutakalkulator");
		vindu.setVisible(true);
	}

	JTextField textField;
	JLabel tekst2;
	JButton knapp1;
	JButton knapp2;

	public Oppgave2(String tittelen){
		setTitle(tittelen);
		setSize(800,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		JLabel tekst = new JLabel("Beløp: ");

		textField = new JTextField(15);
		textField.setSize(100, 10);

		tekst2 = new JLabel("Resultatet av omregningen kommer her.");

		knapp1 = new JButton("Til svensk.");
		knapp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				tekst2.setText(endreFont(true));
			}
		});

		knapp2 = new JButton("Til norsk.");
		knapp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				tekst2.setText(endreFont(false));
			}
		});

		add(tekst);
		add(textField);
		add(tekst2);
		add(knapp1);
		add(knapp2);
		//pack();
	}

	private String endreFont(boolean tilSvenske){
		double penger = 0.0;
		try{
			penger = Double.parseDouble(textField.getText());
		} catch (NumberFormatException ex){
			textField.setText("");
			return "Du har skrevet inn noe feil";
		} catch (Exception e){
			textField.setText("");
			return "Du har skrevet inn noe feil";
		}

		if(tilSvenske){
			penger *= 0.969883242; //til svenske
		} else {
			penger *= 1.03105194; //til norske
		}

		return Double.toString(penger);
	}
}