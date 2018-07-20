package BrainTeasers;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
/**
 * In this version I have added a Bloom filter.
 * 
 * The filter allows to pre-test the get function and avoid 
 * the simulated performance penalty of reading from a hard disk.
 * 
 * TODO: ideally one I'd like to see the range and degree of improvement by the filter. 
 *  
 * @author Oren Bochman
 *
 */
public class BloomingQuickDelete extends QuickDelete {

	public static void main(String[] args) {
		BloomingQuickDelete quickDelete = new BloomingQuickDelete();
		quickDelete.test(quickDelete);
	}

	public BloomFilter<Integer> filter;

	BloomingQuickDelete(){
		filter=BloomFilter.create(Funnels.integerFunnel(),this.EXPECTED_HITS,0.1);
	}

	@Override
	public int get(int index){
		
		if(filter.test(index)) {
			int res=super.get(index);
		  //  System.err.println("value:"+res);
			return res;

		}
		else return 0;
	}
	
	@Override
	public void deleteAll(){
		this.filter = BloomFilter.create(Funnels.integerFunnel(),this.EXPECTED_HITS,0.1);
		super.deleteAll();
	}
}