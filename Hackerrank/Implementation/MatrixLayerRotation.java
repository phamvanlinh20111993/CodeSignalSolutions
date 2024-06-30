package Hackerrank.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/matrix-rotation-algo/problem?isFullScreen=true
 * 
 * You are given a 2D matrix of dimension and a positive integer . You have to rotate the matrix

times and print the resultant matrix. Rotation should be in anti-clockwise direction.

Rotation of a

matrix is represented by the following figure. Note that in one rotation, you have to shift elements by one step only.

matrix-rotation

It is guaranteed that the minimum of m and n will be even.

As an example rotate the Start matrix by 2:

    Start         First           Second
     1 2 3 4       2  3  4  5      3  4  5  6
    12 1 2 5  ->   1  2  3  6 ->   2  3  4  7
    11 4 3 6      12  1  4  7      1  2  1  8
    10 9 8 7      11 10  9  8     12 11 10  9

Function Description

Complete the matrixRotation function in the editor below.

matrixRotation has the following parameter(s):

    int matrix[m][n]: a 2D array of integers
    int r: the rotation factor

Prints
It should print the resultant 2D integer array and return nothing. Print each row on a separate line as space-separated integers.

Input Format

The first line contains three space separated integers,
, , and , the number of rows and columns in , and the required rotation.
The next lines contain space-separated integers representing the elements of a row of

.

Constraints




Sample Input

Sample Input #01

STDIN        Function
-----        --------
4 4 2        rows m = 4, columns n = 4, rotation factor r = 2
1 2 3 4      matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
5 6 7 8
9 10 11 12
13 14 15 16

Sample Output #01

3 4 8 12
2 11 10 16
1 7 6 15
5 9 13 14

Explanation #01

The matrix is rotated through two rotations.

     1  2  3  4      2  3  4  8      3  4  8 12
     5  6  7  8      1  7 11 12      2 11 10 16
     9 10 11 12  ->  5  6 10 16  ->  1  7  6 15
    13 14 15 16      9 13 14 15      5  9 13 14

Sample Input #02

5 4 7
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28

Sample Output #02

28 27 26 25
22 9 15 19
16 8 21 13
10 14 20 7
4 3 2 1

Explanation 02

The various states through 7 rotations:

    1  2  3  4      2  3  4 10    3  4 10 16    4 10 16 22
    7  8  9 10      1  9 15 16    2 15 21 22    3 21 20 28
    13 14 15 16 ->  7  8 21 22 -> 1  9 20 28 -> 2 15 14 27 ->
    19 20 21 22    13 14 20 28    7  8 14 27    1  9  8 26
    25 26 27 28    19 25 26 27    13 19 25 26   7 13 19 25

    10 16 22 28    16 22 28 27    22 28 27 26    28 27 26 25
     4 20 14 27    10 14  8 26    16  8  9 25    22  9 15 19
     3 21  8 26 ->  4 20  9 25 -> 10 14 15 19 -> 16  8 21 13
     2 15  9 25     3 21 15 19     4 20 21 13    10 14 20  7
     1  7 13 19     2  1  7 13     3  2  1  7     4  3  2  1

Sample Input #03

2 2 3
1 1
1 1

Sample Output #03

1 1
1 1

Explanation #03

All of the elements are the same, so any rotation will repeat the same matrix. 
 */
class Coord{
    public Integer x;
    public Integer y;
    
    public Coord(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "[x="+x+", y="+y+"]";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coord other = (Coord) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }
    
}

class Result {

 public static List<Coord> rotateCoords(Integer left, Integer right, Integer top, Integer bottom){
        List<Coord> res = new ArrayList<>();
        // top conner
        for(Integer ind = left; ind <= right-1; ind++){
            res.add(new Coord(top, ind));
        }
         // right conner
        for(Integer ind = top; ind <= bottom-1; ind++){
            res.add(new Coord(ind, right));
        } 
        // bottom conner
        for(Integer ind = left+1; ind <= right; ind++){
            res.add(new Coord(bottom, ind));
        }
        // left conner
        for(Integer ind = top+1; ind <= bottom; ind++){
            res.add(new Coord(ind, left));
        }
        
        return res;
    }

    public static Map<Coord, Integer> cloneDataByCircle(Integer left, Integer right, Integer top, Integer bottom, List<List<Integer>> matrix){
        Map<Coord, Integer> res = new HashMap<>();
        
        // top conner
        for(Integer ind = left; ind <= right-1; ind++){
            res.put(new Coord(top, ind), matrix.get(top).get(ind+1));
        }
         // right conner
        for(Integer ind = top; ind <= bottom-1; ind++){
            res.put(new Coord(ind, right), matrix.get(ind+1).get(right));
        } 
        // bottom conner
        for(Integer ind = left+1; ind <= right; ind++){
            res.put(new Coord(bottom, ind), matrix.get(bottom).get(ind-1));
        }
        // left conner
        for(Integer ind = top+1; ind <= bottom; ind++){
            res.put(new Coord(ind, left), matrix.get(ind-1).get(left));
        }
        
        return res;
    }
    
    public static List<Integer[]> amountCircle(Integer left, Integer right, Integer top, Integer bottom){
        List<Integer[]> res = new ArrayList<>();
        while(left < right && top < bottom){
            res.add(new Integer[]{left, right, top, bottom, 2 * (right - left + bottom - top)});
            ++left;
            --right;
            ++top;
            --bottom;
        }
        
        return res;
    }
    
    public static List<List<Integer>> makeCirleMove(List<List<Integer>> matrix, List<Integer[]> amountCircleList, int r){
       
         List<List<Integer>> res = matrix.stream()
                                    .map(v -> v.stream().map(t -> t).collect(Collectors.toList()))
                                    .collect(Collectors.toList());
                                    
         for(Integer[] circle : amountCircleList){
            Integer aRT = r % circle[4];
            List<Coord> rotateCoordList = rotateCoords(circle[0], circle[1], circle[2], circle[3]);
            for(Integer ind = 0; ind < aRT; ind++){
                Map<Coord, Integer> cycleValue = cloneDataByCircle(circle[0], circle[1], circle[2], circle[3], res);    
                for(Coord coord : rotateCoordList){
                    res.get(coord.x).set(coord.y, cycleValue.get(coord));
                }
            }
         }
         
         return res;
    }
    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) { 
        List<Integer[]> amountCircleList = amountCircle(0, matrix.get(0).size()-1, 0, matrix.size()-1);
         List<List<Integer>> matrixData = matrix;
         matrixData = makeCirleMove(matrixData, amountCircleList, r);
         
         for(List<Integer> row : matrixData){
            row.forEach(v -> System.out.print(v+ " "));
            System.out.println();
         }
    }

}

public class MatrixLayerRotation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
