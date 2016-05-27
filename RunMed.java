/*****************************************************
 * class RunMed
 * Implements an online algorithm to track the median of a growing dataset
 * Maintains 2 heaps: minheap for upper half of dataset, maxheap for lower half
 * Median will always be one of these:
 *  - max of left heap  (lower range)
 *  - min of right heap (upper range)
 *  - mean of heap roots
 *****************************************************/

public class RunMed {

    //instance vars
    private ALMaxHeap leftHeap;  //for lower range of dataset
    private ALMinHeap rightHeap; //for upper range of dataset
    private int sizeleft;
    private int sizeright;


    /*****************************************************
     * default constructor  ---  inits empty heap
     *****************************************************/
    public RunMed() 
    { 
        leftHeap = new ALMaxHeap();
        rightHeap = new ALMinHeap();
        sizeleft = 0;
        sizeright = 0;
    }//O(1)



    /*****************************************************
     * double getMedian()  ---  returns median of dataset
     *****************************************************/
    public double getMedian() 
    {
        if (rightHeap.isEmpty()) {
            return 0;
        }
        else if (leftHeap.isEmpty()) {
            return rightHeap.peekMin();
        }
	else if (sizeleft > sizeright){
	    //System.out.println(leftHeap);
	    return rightHeap.peekMin();
	}
	else if (sizeright > sizeleft){ 
	    //System.out.println("v~~~~~~~~~~~~v");
	    //System.out.println(rightHeap);
	    //System.out.println("^~~~~~~~~~~~~^");
	    return leftHeap.peekMax();
	}
	else{
            int hi = leftHeap.peekMax();
	    // System.out.println(hi);
            int lo = rightHeap.peekMin();
	    // System.out.println((hi + lo)/2.0);
            return (double)(hi+lo)/2.0;
        }
    }//O(1)



    /*****************************************************
     * insert(int)  ---  adds a new element to the dataset
     * postcondition: dataset is maintained such that 
     *                getMedian() can run in constant time
     *****************************************************/
    public void insert( int addVal )
    {   
	if(rightHeap.isEmpty()){
	    rightHeap.add(addVal);
	    sizeright += 1;
	    return;
	}
	if(sizeleft == sizeright){
	    if (addVal > leftHeap.peekMax()){
		leftHeap.add(addVal);
		sizeleft += 1;
	    }
	    else{
		rightHeap.add(addVal);
		sizeright += 1;
	    }
	}
        if (sizeleft > sizeright) {
            rightHeap.add(addVal);
            sizeright += 1;
        }
        else {
            leftHeap.add(addVal);
            sizeleft += 1;
        }
     }//O(?)



    /*****************************************************
     * boolean isEmpty()  ---  tells whether a median may be calculated
     * postcondition: dataset structure unchanged
     *****************************************************/
    public boolean isEmpty() 
    {
        return leftHeap.isEmpty() && rightHeap.isEmpty();
    }//O(?)



    //main method for testing
    public static void main( String[] args ) {


        RunMed med = new RunMed();
        med.insert(1);
	System.out.println( med.getMedian() ); //1
        med.insert(3);
	System.out.println( med.getMedian() ); //2
        med.insert(5);
	System.out.println( med.getMedian() ); //3
        med.insert(7);
	System.out.println( med.getMedian() ); //4
        med.insert(9);
	System.out.println( med.getMedian() ); //5


    }//end main()

}//end class RunMed



    /*****************************************************
     * 
     *****************************************************/
    // (  )
    // {
    // 	/*** YOUR IMPLEMENTATION HERE ***/
    // }//O(?)
