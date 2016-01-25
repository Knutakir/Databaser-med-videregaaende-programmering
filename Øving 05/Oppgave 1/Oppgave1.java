import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.*;

class Oppgave1 extends JFrame {

	public static void main(String[] args){
		Oppgave1 vindu = new Oppgave1("Oppgave 1");
		vindu.setVisible(true);
	}

	JButton knapp1;
	JButton knapp2;
	JButton knapp3;
	JButton knapp4;


	public Oppgave1(String tittelen){
		setTitle(tittelen);
		setSize(800,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		JLabel tekst = new JLabel("Hei og hå");
		//tekst.setSize(50, 100);
		//tekst.setLocation(0,0);

		knapp1 = new JButton("Trykk meg");
		//knapp1.setSize(20, 10);
		//knapp1.setLocation(10, 10);
		knapp1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		knapp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				endreFont(knapp1);
			}
		});


		knapp2 = new JButton("Knapp2");
		knapp2.setFont(new Font("MS Serif", Font.PLAIN, 10));
		knapp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				endreFont(knapp2);
			}
		});

		knapp3 = new JButton("Ingunn trykk her");
		knapp3.setFont(new Font("Monospaced", Font.PLAIN, 10));
		knapp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				endreFont(knapp3);
			}
		});

		knapp4 = new JButton("Ingunn<3");
		knapp4.setFont(new Font("Dialog", Font.PLAIN, 10));
		knapp4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				endreFont(knapp4);
			}
		});

		add(tekst);
		add(knapp1);
		add(knapp2);
		add(knapp3);
		add(knapp4);
		//pack();
	}

	private void endreFont(JButton knappen){
		switch(knappen.getFont().getFontName()){
			case "Microsoft Sans Serif":
				knappen.setFont(new Font("MS Serif", Font.PLAIN, 10));
				break;
			case "Serif.plain":
				knappen.setFont(new Font("Monospaced", Font.PLAIN, 10));
				break;
			case "Monospaced.plain":
				knappen.setFont(new Font("Dialog", Font.PLAIN, 10));
				break;
			case "Dialog.plain":
				knappen.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
				break;
		}
	}
}