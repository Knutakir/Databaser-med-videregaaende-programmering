import java.util.ArrayList;

class OppgaveOversikt{

	private ArrayList<Student> studenter = new ArrayList<>();

	public boolean regNyStudent(String navn){
		for(int i = 0; i < studenter.size(); i++){
			if(navn.equals(studenter.get(i).getNavn())){
				return false;
			}
		}

		studenter.add(new Student(navn));
		return true;
	}

	public int finnAntStud(){
		return studenter.size();
	}

	public int finnAntOppgaver(String navn){
		for(int i = 0; i < studenter.size(); i++){
			if(navn.equals(studenter.get(i).getNavn())){
				return studenter.get(i).getAntOppg();
			}
		}
		return -1;
	}

	public void økAntOppg(String navn, int økning){
		for(int i = 0; i < studenter.size(); i++){
			if(navn.equals(studenter.get(i).getNavn())){
				studenter.get(i).setAntOppg(økning);
			}
		}
	}

	public String[] finnAlleNavn(){ //navnene til alle studentene
		String[] tabellen = new String[studenter.size()];
		for(int i = 0; i < studenter.size(); i++){
			tabellen[i] = studenter.get(i).getNavn();
		}
		return tabellen;
	}

	public String toString(){
		String utskrift = "";

		for(int i = 0; i < studenter.size(); i++){
			utskrift += "\n" + studenter.get(i).toString();
		}
		return utskrift;
	}
}