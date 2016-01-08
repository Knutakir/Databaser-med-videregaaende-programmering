class Student{

	private final String navn;
	private int antOppg;

	public Student(String navn){
		this.navn = navn;
		antOppg = 0;
	}

	public String getNavn(){
		return navn;
	}

	public int getAntOppg(){
		return antOppg;
	}

	public void setAntOppg(int antOppg){
		if(antOppg > 0){
			this.antOppg += antOppg;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String toString(){
		return ("Navn: " + navn + ", antall oppgaver løst: " + antOppg);
	}
}