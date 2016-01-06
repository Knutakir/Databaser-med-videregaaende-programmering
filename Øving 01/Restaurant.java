import java.util.Calendar;

class Restaurant {
	private String navn;
	private int etableringsaar;
	private Bord bordet;

	public Restaurant(String navn, int etableringsaar, int antBord	){
		this.navn = navn;
		this.etableringsaar = etableringsaar;
		bordet = new Bord(antBord);
	}

	public String getNavn(){
		return navn;
	}

	public int getEtableringsaar(){
		return etableringsaar;
	}

	public void setNavn(String navn){
		this.navn = navn;
	}

	public int finnAlder(){
		int aarNo = Calendar.getInstance().get(Calendar.YEAR);
		return aarNo - etableringsaar;
	}

	public int antallLedigeBord(){
		return bordet.getAntallLedigeBord();
	}

	public int antallOpptatt(){
		return bordet.getAntallOpptatt();
	}

	public boolean reserverBord(String person, int[] reserverBord){
		if(bordet.reserverBord(person, reserverBord)){
			//bord reservert
			return true;
		} else {
			//bord ikke reservert
			return false;
		}
	}

	public int[] finnBordReservert(String person){
		return bordet.finnBordReservert(person);
	}

	public int frigiBord(int[] bordene){
		return bordet.frigiBord(bordene);
	}

}