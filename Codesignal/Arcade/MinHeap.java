package Codesignal.Arcade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author PhamLinh
 *
 * @param <E>
 */
public class MinHeap<E> {

    private List<E> root = new ArrayList<>();

    private final Comparator<E> comparator;
    
    private int heapSize = 0;

    public MinHeap() {
	comparator = null;
    }

    public MinHeap(Comparator<E> comparator) {
	this.comparator = comparator;
    }

    private int getParentInd(int ind) {
	return ind % 2 == 0 ? ind / 2 - 1 : ind / 2;
    }

    public int size() {
	return heapSize;
    }

    public void adds(E[] eles) {
	for (int ind = 0; ind < eles.length; ind++) {
	    this.add(eles[ind]);
	}
    }

    public void add(E element) {
	if(heapSize < root.size()) {
	    root.set(heapSize, element);
	}else {
	    root.add(element);
	}
	heapSize++;
	
	if (heapSize < 2)
	    return;

	int lastE = heapSize - 1;
	while (true) {
	    int parentInd = getParentInd(lastE);
	    if (comparator.compare(root.get(parentInd), root.get(lastE)) >= 0) {
		E tmp = root.get(lastE);
		root.set(lastE, root.get(parentInd));
		root.set(parentInd, tmp);
	    }
	    if (parentInd <= 0)
		break;
	    lastE = parentInd;
	}
    }
    
    /**
     * remove remove the root then compare the child node, which smaller will be
     * choosen, apply the child nodes to leaf. we have defect with this approach
     * 
     * @return
     */
   /* public E poll() {
	if (root.size() <= 0) {
	    return null;
	}

	E lastE = root.get(0);

	if (root.size() == 1) {
	    root.remove(0);
	    return lastE;
	}
	int parentInd = 0, size = root.size();
	while (parentInd < size) {
	    int leftChildInd = parentInd * 2 + 1;
	    int rightChildInd = parentInd * 2 + 2;

	    if (leftChildInd < size) {
		if (rightChildInd < size) {
		    if (comparator.compare(root.get(leftChildInd), root.get(rightChildInd)) <= 0) {
			root.set(parentInd, root.get(leftChildInd));
			parentInd = leftChildInd;
		    } else {
			root.set(parentInd, root.get(rightChildInd));
			parentInd = rightChildInd;
		    }
		} else {
		    root.set(parentInd, root.get(leftChildInd));
		    parentInd = leftChildInd;
		}
	    } else {
		break;
	    }
	}

	if (parentInd < size) {
	    root.remove(parentInd);
	} else {
	    root.remove(getParentInd(parentInd));
	}

	return lastE;
    } */
    
   public E poll() {
       if (heapSize <= 0) {
	   return null;
       }

       E lastE = root.get(0);

       root.set(0, root.get(heapSize - 1));
       root.set(heapSize - 1, null);
       heapSize--;

       if (heapSize == 0) {
	   return lastE;
       }

       int parentInd = 0, size = heapSize;
       // System.out.println("dt " + root);

       while (parentInd < size) {
	   int leftChildInd = parentInd * 2 + 1;
	   int rightChildInd = parentInd * 2 + 2;

	   E tmp = root.get(parentInd);

	   if (leftChildInd < size) {
	       if (rightChildInd < size) {
		   if (comparator.compare(root.get(leftChildInd), root.get(rightChildInd)) <= 0) {
		       if (comparator.compare(tmp, root.get(leftChildInd)) >= 0) {
			   root.set(parentInd, root.get(leftChildInd));
			   root.set(leftChildInd, tmp);
			   parentInd = leftChildInd;
		       } else
			   break;

		   } else {
		       if (comparator.compare(tmp, root.get(rightChildInd)) >= 0) {
			   root.set(parentInd, root.get(rightChildInd));
			   root.set(rightChildInd, tmp);
			   parentInd = rightChildInd;
		       } else
			   break;

		   }
	       } else {
		   if (comparator.compare(tmp, root.get(leftChildInd)) >= 0) {
		       root.set(parentInd, root.get(leftChildInd));
		       root.set(leftChildInd, tmp);
		       parentInd = leftChildInd;
		   } else
		       break;
		  
	       }
	   } else {
	       break;
	   }
       }

       return lastE;
   }

    public List<E> getHeapArr() {
	return root;
    }

    public static void test1() {
	MinHeap<Integer> minHeap = new MinHeap<>(new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	    }
	});

	minHeap.add(129);
	minHeap.add(334);
	minHeap.add(1);
	minHeap.add(21);
	minHeap.add(121);
	minHeap.add(19);
	minHeap.add(211);
	minHeap.add(2111);
	minHeap.add(15);
	minHeap.add(17);

	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());

	minHeap.add(15);
	minHeap.add(1200);
	minHeap.add(18);
	minHeap.add(28);
	minHeap.add(8);
	System.out.println(minHeap.getHeapArr());
	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());
	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());
	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());
	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());
	System.out.println(minHeap.poll());
	System.out.println(minHeap.getHeapArr());
    }

    public static void test3() {
	MinHeap<Integer> minHeap = new MinHeap<>(new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	    }
	});

	Integer[] eles = { 6, 10, 111, 56, 105, 120, 115, 117, 101, 138 };
	minHeap.adds(eles);

	System.out.println(minHeap.getHeapArr());

	while (minHeap.size() > 0) {
	    System.out.println("poll= " + minHeap.poll());
	    System.out.println(minHeap.getHeapArr());
	}

    }

    public static void test2() {
	MinHeap<Integer> minHeap = new MinHeap<>(new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	    }
	});

	for (int ind = 0; ind < 10; ind++) {
	    int random = (int) (Math.random() * 150 + 1);
	    minHeap.add(random);
	}

	System.out.println(minHeap.getHeapArr());

	while (minHeap.size() > 0) {
	    System.out.println("poll= " + minHeap.poll());
	}
    }

    public static void main(String[] args) {
	System.out.println("################### test 1##########################");
	test1();

	System.out.println("#####################test 2########################");
	test2();

	System.out.println("#####################test 3########################");
	test3();
    }
}
