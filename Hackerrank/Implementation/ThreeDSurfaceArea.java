package Hackerrank.Implementation;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreeDSurfaceArea {
    
    private static int[][] drs = {{0, 0, 1}, 
            {0, 0, -1}, 
            {0, 1, 0}, 
            {0, -1, 0}, 
            {1, 0, 0}, 
            {-1, 0, 0}};
    
    private static Set<String> isVisited = new HashSet<>();
    
    public static String genKey(int x, int y, int z) {
	return x + "-" + y + "-" + z;
    }
    
    public static boolean hasEmptySide(int x, int y, int z, int mX, int mY, int mZ, Set<String> coords){
        for(int[] dr : drs){
            int dx = x + dr[0], dy = y + dr[1], dz = z + dr[2];
            String k = genKey(dx, dy, dz);
            if(!coords.contains(k)){
               return true;
            }
        }
        return false;
    }
    
    public static boolean isInRange(int x, int y, int z, int mX, int mY, int mZ){
        return !(x < 0 || y < 0 || z < 0 || x > mX || y > mY || z > mZ) ;
    }
   
    public static int recursiveCount(int x, int y, int z, int mX, int mY, int mZ, Set<String> coords) {

	String currentKey = genKey(x, y, z);

	if (x < 0 || y < 0 || z < 0 || x > mX || y > mY || z > mZ || !coords.contains(currentKey)
		|| isVisited.contains(currentKey)) {
	    return 0;
	}

	isVisited.add(currentKey);

	boolean isExistAbove = coords.contains(genKey(x, y, z + 1)),
		isExistBelow = coords.contains(genKey(x, y, z - 1)), 
		isExistFont = coords.contains(genKey(x, y + 1, z)),
		isExistBack = coords.contains(genKey(x, y - 1, z)),
		isExistLeftSize = coords.contains(genKey(x - 1, y, z)),
		isExistRightSize = coords.contains(genKey(x + 1, y, z));

	int count = 0;

	if (!isExistAbove)
	    count++;

	if (!isExistBelow)
	    count++;

	if (!isExistFont)
	    count++;

	if (!isExistBack)
	    count++;

	if (!isExistLeftSize)
	    count++;

	if (!isExistRightSize)
	    count++;

	return count 
		+ recursiveCount(x, y, z + 1, mX, mY, mZ, coords) 
		+ recursiveCount(x, y, z - 1, mX, mY, mZ, coords)
		+ recursiveCount(x, y + 1, z, mX, mY, mZ, coords) 
		+ recursiveCount(x, y - 1, z, mX, mY, mZ, coords)
		+ recursiveCount(x - 1, y, z, mX, mY, mZ, coords) 
		+ recursiveCount(x + 1, y, z, mX, mY, mZ, coords);
    }
    
    public static int dfs(int sx, int sy, int sz, int mX, int mY, int mZ, Set<String> coords) {   
        Stack<String> stack = new Stack<>(); 
        stack.add(genKey(sx, sy, sz));
        isVisited.add(genKey(sx, sy, sz));
        
        int count = 0;
        while (stack.size() > 0) {
            String currentKey = stack.pop();
            String[] spl = currentKey.split(";");
            int x = Integer.parseInt(spl[0]), 
                y = Integer.parseInt(spl[1]), 
                z = Integer.parseInt(spl[2]);
            
            for(int[] dr : drs){
                int dx = x + dr[0], dy = y + dr[1], dz = z + dr[2];
                String k = genKey(dx, dy, dz);
                if(!coords.contains(k)){
                    count++;
                }
                if(isInRange(dx, dy, dz, mX, mY, mZ) 
                    && coords.contains(k) 
                    && hasEmptySide(dx, dy, dz, mX, mY, mZ, coords)
                    && !isVisited.contains(k)){  
                    stack.add(k);
                    isVisited.add(k);
                }  
            }
        }

        return count;
    }

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER. The function accepts
     * 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) {
	// create (x,y,z) coords
	int maxX = 1, maxY = 1, maxZ = 1;

	isVisited = new HashSet<>();

	Set<String> coords = new HashSet<>();

	for (int y = 0; y < A.size(); y++) {
	    for (int x = 0; x < A.get(0).size(); x++) {
		for (int z = 0; z < A.get(y).get(x); z++) {
		    coords.add(genKey(x, y, z));
		    if (maxZ < z)
			maxZ = z;
		}
		if (maxX < x)
		    maxX = x;
	    }
	    if (maxY < y)
		maxY = y;
	}

	return recursiveCount(0, 0, 0, maxX, maxY, maxZ, coords);
    }

    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

	String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

	int H = Integer.parseInt(firstMultipleInput[0]);

	int W = Integer.parseInt(firstMultipleInput[1]);

	List<List<Integer>> A = new ArrayList<>();

	IntStream.range(0, H).forEach(i -> {
	    try {
		A.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
			.collect(toList()));
	    } catch (IOException ex) {
		throw new RuntimeException(ex);
	    }
	});

	int result = ThreeDSurfaceArea.surfaceArea(A);

	bufferedWriter.write(String.valueOf(result));
	bufferedWriter.newLine();

	bufferedReader.close();
	bufferedWriter.close();
    }

}
