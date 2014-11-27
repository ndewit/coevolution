package extension;

import java.util.Random;

public class Individual {

	private final int length = 100;
	private int[] binaryString = new int[length];
	
	public Individual() {}
	
	public Individual(String type) {
		if ("allZeros".equals(type)){
			for(int count=0; count < length ; count++){ binaryString[count] = 0; }
		}
		else if ("allOnes".equals(type)){
			for(int count=0; count < length ; count++){ binaryString[count] = 1; }			
		}
		else if ("random".equals(type)){
			Random r = new Random();
			for(int count=0; count < length ; count++){
				binaryString[count] = r.nextInt(2); }
		}
	}
	
	public Individual(Individual oldIndiv){
		System.arraycopy(oldIndiv.getBinaryString(), 0, binaryString, 0, length);
	}
	
	public int[] getBinaryString() {
		return binaryString;
	}
	
	public int getLength() {
		return length;
	}

	public String toString() {
		String res = "";
		for (int i=0 ; i < length ; i++) { res += String.valueOf(binaryString[i]); }
		return res;
	}
	
	public void setBit(int i, int value){
		binaryString[i] = value;
	}
	
	public void flipBit(int i) {
		if (binaryString[i] == 0){
			binaryString[i] = 1;
		}
		else {
			binaryString[i] = 0;
		}
	}
	
	public void setOneAtBit(int i) {
		binaryString[i] = 1;
	}
	
	public void setZeroAtBit(int i){
		binaryString[i] = 0;
	}
	
	public int getBit(int i){
		return binaryString[i];
	}
	
	public int getNbOfOnes(){
		int res = 0;
		for (int i=0 ; i < length ; i++){
			if (binaryString[i] == 1){
				res++;
			}
		}
		return res;
	}
	
	public int getValue() {
		int tot = 0;
		for (int i=0 ; i < length ; i++) {
			tot += binaryString[i];
		}
		return tot;
	}
}
