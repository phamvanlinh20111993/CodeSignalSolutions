package LeetCode;

/**
 * url: https://leetcode.com/problems/circle-and-rectangle-overlapping/description/?envType=daily-question&envId=2026-09-19
 * 
 * You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle represented as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the rectangle.

Return true if the circle and rectangle are overlapped otherwise return false. In other words, check if there is any point (xi, yi) that belongs to the circle and the rectangle at the same time.

 

Example 1:


Input: radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
Output: true
Explanation: Circle and rectangle share the point (1,0).
Example 2:

Input: radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
Output: false
Example 3:


Input: radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
Output: true
 

Constraints:

1 <= radius <= 2000
-104 <= xCenter, yCenter <= 104
-104 <= x1 < x2 <= 104
-104 <= y1 < y2 <= 104
 */
public class Circle_and_Rectangle_Overlapping_1401 {
	

    // circle: (x - a)^2 + (y - b)^2 = R^2.
  /*  public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // (x - xCenter)^2 + (y - yCenter)^2 = radius^2
        // (x1, y1) bottom-left
        // (x2, y2) top-right
        // (x1, y2) top-left
        // (x2, y1) bottom-right

        // step 1: find the middle point of rectangle 
        double [] rectangleMiddlePoint = new double[]{(double)(x1+x2)/2, (double)(y1+y2)/2};

        // step 2: find the straight line with cicle
        double [] directionVector = new double[]{ rectangleMiddlePoint[0] - xCenter, 
                                                rectangleMiddlePoint[1] - yCenter }; 
        
         System.out.println(directionVector[0] + " " + directionVector[1]);
        // find the cutting between circle and line in step 2
        // 1, (x - xCenter)^2 + (y - yCenter)^2 = radius^2
        // 2, -b(x - xCenter) + a(y - yCenter) = 0, with b = directionVector[1], 
        //                                               a = directionVector[0]
        /*
            change X = x - xCenter, Y = y - yCenter
            =>  X^2 + y^2 = R^2
                -bX + aY = 0
            => X = aY/b
                b^2*Y^2/a^2 + Y^2 = R^2
                a^2*Y^2 + b^2*Y^2 = b^2*R^2
                
            => Y = b*R/sqrt(b^2 + a^2) <=> y = yCenter +/-  b*R/sqrt(b^2 + a^2)
            
            => X = b*R/sqrt(b^2 + a^2) <=> x = xCenter +/-  a*R/sqrt(b^2 + a^2)
        */
    /*
        double k = Math.sqrt(directionVector[0]*directionVector[0] + directionVector[1]*directionVector[1]);

        if(k == 0) return true;

        double mX1 = xCenter + (directionVector[0]*radius)/k;
        double mX2 = xCenter - (directionVector[0]*radius)/k;

        double mY1 = yCenter + (directionVector[1]*radius)/k;
        double mY2 = yCenter - (directionVector[1]*radius)/k;

        System.out.println(mX1 + " " + mY1);
        System.out.println(mY2 + " " + mY2);

        if(mX1 >= x1 && mX1 <= x2 && mY1 >= y1 && mY1 <= y2){
            return true;
        }

        if(mX2 >= x1 && mX2 <= x2 && mY2 >= y1 && mY2 <= y2){
            return true;
        }

        return false;
    } */


    /**
        ChatGPT solution, very short and concise. :))))
     */
  /*  public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2) {

    int closestX = Math.max(x1, Math.min(xCenter, x2));
    int closestY = Math.max(y1, Math.min(yCenter, y2));

    long dx = xCenter - closestX;
    long dy = yCenter - closestY;

    return dx * dx + dy * dy <= (long) radius * radius;
}  */

    // circle: (x - a)^2 + (y - b)^2 = R^2.
	public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {

		// the circle is inside the rectangle
		if ((xCenter >= x1 && xCenter <= x2) && (yCenter >= y1 && yCenter <= y2))
			return true;

		// the rectangle is inside the cirle
		double[] rectMidPoint = new double[] { (double) (x1 + x2) / 2, (double) (y1 + y2) / 2 };
		if (Math.pow(xCenter - rectMidPoint[0], 2) + Math.pow(yCenter - rectMidPoint[1], 2) <= radius * radius) {
			return true;
		}

		// (x - xCenter)^2 + (y - yCenter)^2 = radius^2
		// (x1, y1) bottom-left
		// (x2, y2) top-right
		// (x1, y2) top-left
		// (x2, y1) bottom-right

		// directorVector top = (x2 - x1, 0) => top equation: (x1-x2)(y - y2) = 0 <=> y
		// = y2
		double topXSquare = radius * radius - (y2 - yCenter) * (y2 - yCenter);
		if (topXSquare >= 0) {
			double d = Math.sqrt(topXSquare);
			double topX = xCenter + d;
			double topX1 = xCenter - d;

			if ((topX >= x1 && topX <= x2) || (topX1 >= x1 && topX1 <= x2))
				return true;
		}

		// directorVector bottom = (x2 - x1, 0) => bottom equation: (x1-x2)(y - y1) = 0
		// => y = y1
		double bottomXSquare = radius * radius - (y1 - yCenter) * (y1 - yCenter);
		if (bottomXSquare >= 0) {
			double d = Math.sqrt(bottomXSquare);
			double bottomX = xCenter + d;
			double bottomX1 = xCenter - d;
			if ((bottomX >= x1 && bottomX <= x2) || (bottomX1 >= x1 && bottomX1 <= x2))
				return true;
		}

		// directorVector left = (0, y1-y2) => left equation: (y2-y1)(x - x1) = 0 => x =
		// x1
		double leftYSquare = radius * radius - (x1 - xCenter) * (x1 - xCenter);
		if (leftYSquare >= 0) {
			double d = Math.sqrt(leftYSquare);
			double leftY = yCenter + d;
			double leftY1 = yCenter - d;
			if ((leftY >= y1 && leftY <= y2) || (leftY1 >= y1 && leftY1 <= y2))
				return true;
		}

		// directorVector right = (0, y1-y2) => right equation: (y2-y1)(x - x2) = x = x2
		double rightYSquare = radius * radius - (x2 - xCenter) * (x2 - xCenter);
		if (rightYSquare >= 0) {
			double d = Math.sqrt(rightYSquare);
			double rightY = yCenter + d;
			double rightY1 = yCenter - d;
			if ((rightY >= y1 && rightY <= y2) || (rightY1 >= y1 && rightY1 <= y2))
				return true;
		}

		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
