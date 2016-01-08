class OppgaveOversikt{

	private Student[] studenter = new Student[5];
	private int antStud = 0;

	public boolean regNyStudent(String navn){
		for(int i = 0; i < antStud; i++){
			if(navn.equals(studenter[i].getNavn())){
				return false;
			}
		}
		if(studenter.length == antStud){
			Student[] tmp = new Student[studenter.length + 5];
			for(int i = 0; i < antStud; i++){
				tmp[i] = studenter[i];
			}
			studenter = tmp;
		}
		studenter[antStud] = new Student(navn);
		antStud++;
		return true;
	}

	public int finnAntStud(){
		return antStud;
	}

	public int finnAntOppgaver(String navn){
		for(int i = 0; i < antStud; i++){
			if(navn.equals(studenter[i].getNavn())){
				return studenter[i].getAntOppg();
			}
		}
		return -1;
	}

	public void økAntOppg(String navn, int økning){
		for(int i = 0; i < antStud; i++){
			if(navn.equals(studenter[i].getNavn())){
				studenter[i].setAntOppg(økning);
			}
		}
	}

	public String[] finnAlleNavn(){ //navnene til alle studentene
		String[] tabellen = new String[antStud];
		for(int i = 0; i < antStud; i++){
			tabellen[i] = studenter[i].getNavn();
		}
		return tabellen;
	}

	public String toString(){
		String utskrift = "";

		for(int i = 0; i < antStud; i++){
			utskrift += "\n" + studenter[i].toString();
		}
		return utskrift;
	}
}