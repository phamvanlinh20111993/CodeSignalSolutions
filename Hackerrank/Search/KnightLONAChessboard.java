package Hackerrank.Search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author PhamLinh
 * @link https://www.hackerrank.com/challenges/knightl-on-chessboard/problem?isFullScreen=true
 * 
 *  is a chess piece that moves in an L shape. We define the possible moves of  as any movement from some position  to some  satisfying either of the following:

 and , or
 and 
Note that  and  allow for the same exact set of movements. For example, the diagram below depicts the possible locations that  or  can move to from its current location at the center of a  chessboard:

image

Observe that for each possible movement, the Knight moves  units in one direction (i.e., horizontal or vertical) and  unit in the perpendicular direction.

Given the value of  for an  chessboard, answer the following question for each  pair where :

What is the minimum number of moves it takes for  to get from position  to position ? If it's not possible for the Knight to reach that destination, the answer is -1 instead.
Then print the answer for each  according to the Output Format specified below.

Input Format

A single integer denoting .

Constraints

Output Format

Print exactly  lines of output in which each line  (where ) contains  space-separated integers describing the minimum number of moves  must make for each respective  (where ). If some  cannot reach position , print -1 instead.

For example, if , we organize the answers for all the  pairs in our output like this:

(1,1) (1,2)
(2,1) (2,2)
Sample Input 0

5
Sample Output 0

4 4 2 8
4 2 4 4
2 4 -1 -1
8 4 -1 1
Explanation 0

The diagram below depicts possible minimal paths for , , and :

image

One minimal path for  is:

We then print 4 4 2 8 as our first line of output because  took  moves,  took  moves,  took  moves, and  took  moves.

In some of the later rows of output, it's impossible for  to reach position . For example,  can only move back and forth between  and  so it will never reach .
 */
class Point1{
    
    private int x; 
    private int y;
    
    private int level;
    
    public Point1(){
         
    }
    
    public Point1(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void setLevel(int l){
        this.level = l;
    }
    
    public String toKey(){
        return this.x + "-" + this.y;
    }
    
    public boolean isInSquare(int n){
        return x > -1 && y > -1 && x < n && y < n;
    }
    
    public Point1 newPoint1(Point1 n){
        return new Point1(this.x + n.getX(), this.y + n.getY());
    }
}

public class KnightLONAChessboard {

public static int bfs(int n, Point1 startPoint1, Point1 d){
        
        Queue<Point1> queue = new LinkedList<>();
        Set<String> isVisited = new HashSet<>();
        
        Point1 []  DIRECTIONS = {
            new Point1(d.getX(), d.getY()),
            new Point1(-d.getX(), d.getY()),
            new Point1(-d.getX(), -d.getY()),
            new Point1(d.getX(), -d.getY()),
             
            new Point1(d.getY(), d.getX()),
            new Point1(-d.getY(), d.getX()),
            new Point1(-d.getY(), -d.getX()),
            new Point1(d.getY(), -d.getX()),
        };
        
        isVisited.add(startPoint1.toKey());
        queue.add(startPoint1);
       
        while(queue.size() > 0){
            Point1 p = queue.poll();
            
            if(p.getX() == n-1 && p.getY() == n-1){
               return p.getLevel();
            }
            
            for(Point1 di: DIRECTIONS){
                Point1 newP = di.newPoint1(p);
                if(newP.isInSquare(n) && !isVisited.contains(newP.toKey())){
                    isVisited.add(newP.toKey());
                    newP.setLevel(p.getLevel()+1);
                    queue.add(newP);
                }
            }
        }
        
        return -1;
    }

    public static List<List<Integer>> knightlOnAChessboard(int n) {
        
        Point1 startP = new Point1(0, 0);
        startP.setLevel(0);
        
        List<List<Integer>> resp = new ArrayList<>();
        for(int y = 1; y < n; y++){
            List<Integer> r = new ArrayList<>();
            for(int x = 1; x < n; x++){
                Point1 checkP = new Point1(x, y);
                int res = bfs(n, startP, checkP);
                r.add(res);
            }
            resp.add(r);
        }
        
        return resp;
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
