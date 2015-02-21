package ch.fhnw.algd1.search.prime;

public class Primality {
	public static boolean isPrime(int x) {

	int t = 2;
	while(!(x % t==0)){
		t++;
	}
	return (x==t);
	}
}
