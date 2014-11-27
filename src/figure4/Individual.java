package figure4;

public class Individual {

	private final int length = 10;
	private final int nbDimensions = 10;
	private int[][] binaryString = new int[nbDimensions][length];
	
	public Individual() {}
	
	public Individual(String type) {
		if ("allZeros".equals(type)){
			for (int dim=0 ; dim < nbDimensions ; dim++) {
				for(int count=0; count < length ; count++){ binaryString[dim][count] = 0; }
			}
		}
		else if ("allOnes".equals(type)){
			for (int dim=0 ; dim < nbDimensions ; dim++) {
				for(int count=0; count < length ; count++){ binaryString[dim][count] = 1; }
			}
		}
	}
	
	public Individual(Individual oldIndiv){
		for (int dim=0 ; dim < nbDimensions ; dim++) {
			System.arraycopy(oldIndiv.getBinaryString(dim), 0, binaryString[dim], 0, length);
		}
	}
	
	public int[] getBinaryString(int dim) {
		return binaryString[dim];
	}
	
	public int getNbDimensions() {
		return nbDimensions;
	}
	
	public int getLength() {
		return length;
	}

	public String toString() {
		String res = "";
		for (int dim=0 ; dim < nbDimensions ; dim++) {
			res += "{" + (dim+1) + "} ";
			for (int i=0 ; i < length ; i++) { res += String.valueOf(binaryString[dim][i]); }
			res += " ";
		}
		return res;
	}
	
	public void setBit(int dim, int i, int value){
		binaryString[dim][i] = value;
	}
	
	public void flipBit(int dim, int i) {
		if (binaryString[dim][i] == 0){
			binaryString[dim][i] = 1;
		}
		else {
			binaryString[dim][i] = 0;
		}
	}
	
	public int getBit(int dim, int i){
		return binaryString[dim][i];
	}
	
	public int getTotalValue() {
		int tot = 0;
		for (int dim=0 ; dim < nbDimensions ; dim++) {
			tot += getValue(dim);
		}
		return tot;
	}
	
	public int getValue(int dim) {
		int tot = 0;
		for (int i=0 ; i < length ; i++) {
			tot += binaryString[dim][i];
		}
		return tot;
	}
}
