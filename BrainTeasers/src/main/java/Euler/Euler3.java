package Euler;

public class Euler3 {
	
	
	public static void main(String[] args) {
		
		long inp=600851475143l;
		long ptr=3;
		while(ptr< Math.sqrt(inp)) {
			if(inp%ptr==0) {
				System.out.println(ptr);
				inp/=ptr;
				System.out.println("continuing with "+inp);
				ptr=1;
			}
			ptr++;
		}
		System.out.println("done");
		
		
	}
}
