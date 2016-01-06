class Bord {
	private String[] informasjon;

	public Bord(int antBord){
		informasjon = new String[antBord];
	}

	public int getAntallLedigeBord(){
		int tmp = 0;
		for(int i = 0; i < informasjon.length; i++){
			if(informasjon[i] != null){
				tmp++;
			}
		}
		return tmp;
	}

	public int getAntallOpptatt(){
		int tmp = 0;
		for(int i = 0; i < informasjon.length; i++){
			if(informasjon[i] == null) {
				tmp++;
			}
		}
		return tmp;
	}

	public int frigiBord(int[] bordene){
		int tmp = 0;
		if(informasjon.length >= bordene.length){
			for(int i = 0; i < bordene.length; i++){
				informasjon[bordene[i]] = null;
				tmp++;
			}
		}
		return tmp;
	}

	public boolean reserverBord(String navn, int[] bordnr){
		boolean tmp = true;
		for(int i = 0; i < bordnr.length; i++){
			if(!(informasjon[bordnr[i]] == null)){
				tmp = false;
			}
		}

		if(tmp){
			for(int i = 0; i < bordnr.length; i++){
				informasjon[bordnr[i]] = navn;
			}
		}
		return tmp;
	}

	public int[] finnBordReservert(String navn){
		int antallBordReg  = 0;
		for(int i = 0; i < informasjon.length; i++){
			if(navn.equals(informasjon[i])){
				antallBordReg++;
			}
		}

		int[] tabellen = new int[antallBordReg];
		int teller = 0;
		for(int i = 0; i < informasjon.length; i++){
			if(navn.equals(informasjon[i])){
				tabellen[teller] = i;
				teller++;
			}
		}
		return tabellen;
	}


}