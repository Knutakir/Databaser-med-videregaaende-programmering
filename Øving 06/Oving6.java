import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

class Oving6 extends JFrame {


	public static void main(String[] args) {
		Oving6 vindu = new Oving6("Øving 6 - Valutaberegner");
		vindu.setVisible(true);
	}

	private JList liste;
	private JList liste2;
	private DefaultListModel model = new DefaultListModel();

	private Valuta[] valutaliste = {
				new Valuta("Ny valuta", 0.0, 1),
			    new Valuta("Euro", 8.10, 1), new Valuta("US Dollar", 6.23, 1),
			    new Valuta("Britiske pund", 12.27, 1), new Valuta("Svenske kroner", 88.96, 100),
			    new Valuta("Danske kroner", 108.75, 100), new Valuta("Yen", 5.14, 100),
			    new Valuta("Islandske kroner", 9.16, 100), new Valuta("Norske kroner", 100, 100)
	};

	private void leggTilIListe(){
		for(int i = 0; i < valutaliste.length; i++){
			model.add(i, valutaliste[i]);
		}
	}

	public Oving6(String tittelen) {
		leggTilIListe();
		setTitle(tittelen);
		setSize(325,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel(new BorderLayout());

		JLabel toppTekst = new JLabel("Velg fravaluta og tilvaluta fra listene: ");
		JLabel tekst = new JLabel("Fra:");
		JLabel tekst2 = new JLabel("Til:");

		liste = new JList(model);
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				trykketListe1();
			}
		});
		liste2 = new JList(model);
		liste2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste2.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				trykketListe2();
			}
		});

		JButton knapp = new JButton("Gjør om");
		knapp.
		JPanel panel3 = new JPanel(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(liste);
		JScrollPane scrollPane2 = new JScrollPane(liste2);
		panel1.add(scrollPane, BorderLayout.WEST);
		panel1.add(scrollPane2, BorderLayout.EAST);

		panel2.add(toppTekst, BorderLayout.NORTH);
		panel2.add(tekst, BorderLayout.WEST);
		panel2.add(tekst2, BorderLayout.EAST);
		panel2.add(panel1, BorderLayout.SOUTH);
		panel3.add(panel2, BorderLayout.NORTH);
		panel3.add(knapp, BorderLayout.SOUTH);
		add(panel3);
	}

	private void trykketListe2(){
		if(liste2.getSelectedValue().toString().equals("Ny valuta")){
			leggTilValuta();
		}
	}

	private void trykketListe1(){
		if(liste.getSelectedValue().toString().equals("Ny valuta")){
			leggTilValuta();
		}
	}

	private void leggTilValuta(){
		String navn = showInputDialog("Valutanavn: ");
		double kurs = Double.parseDouble(showInputDialog("Kurs: "));
		int enhet = Integer.parseInt(showInputDialog("Enhet: "));

		Valuta val = new Valuta(navn, kurs, enhet);
		Valuta[] tmp = new Valuta[valutaliste.length + 1];

		for(int i = 0; i < valutaliste.length; i++){
			tmp[i] = valutaliste[i];
		}
		tmp[valutaliste.length] = val;
		valutaliste = tmp;
		model.add(model.size(), val);
	}

	/*private double endreValuta(int hvaForEn){
		//showMessageDialog(null, "do shit2");
		int nummer1, nummer2 = 0;
		for(int i = 0; i < valutaliste.length; i++){
			if(liste.getSelectedValue().equals("")){

			}
		}
		showMessageDialog(null, valutaliste[8].gjorOmFra(valutaliste[1], 1));
		if(hvaForEn == 0){ //den første listen

		} else { //den andre listen

		}
		return 0.0;
	}*/
}