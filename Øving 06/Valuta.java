class Valuta {

	private String navn;
	private double kurs = 0.0;
	private int enhet = 0;

	public Valuta(String navn, double kurs, int enhet){
		this.navn = navn;
		this.kurs = kurs;
		this.enhet = enhet;
	}

	/*public String getNavn(){
		return navn;
	}*/

	public double getKurs(){
		return kurs;
	}

	public int getEnhet(){
		return enhet;
	}

	/*public double gjorOmFra(Valuta fraValuta, double antPenger){
		return (fraValuta.getKurs() / fraValuta.getEnhet()) * antPenger;
	}*/
	public static double beregn(Valuta fra, Valuta til, double penger){
		//Gjøre om til norske kroner
		double norskeKR = penger / til.getKurs() * til.getEnhet();
		return (norskeKR * fra.getKurs()) / fra.getEnhet();
	}

	public static void main(String[] args){
		Valuta SEK = new Valuta("Svenske kroner", 88.96, 100);
		Valuta USD = new Valuta("US Dollar", 6.23, 1);
		System.out.println(beregn(SEK, USD, 200));
	}

	public String toString(){
		return navn;
	}
}