package ch.fhnw.algd1.conditions;

public class MaxSubProblem {

	public int maxSub(int[] data){

		int sum;
		int max=0;


		for (int i=0; i<data.length; i++){
//			Startwert für Iteration
			sum = data[i];
			for(int j=i; j<data.length; j++){
				if(j!=i){
					sum = sum + data[j];
				}else{
					sum = sum;
				}
				if(max<sum){
					max=sum;
				}
				
			}
		}
		
		return max;
	}
		
}
