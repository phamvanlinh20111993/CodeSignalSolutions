-- URL: https://app.codesignal.com/arcade/db/a-table-of-desserts/KeGzSCXCSQQNNoqKw
/**
 * In the 2-player game Battleship, each player takes turns guessing the position of the other player's battleships on a 10 x 10 playing board. When a player correctly guesses a grid that contains a segment of an opponent's battleship, the ship is damaged. If all the segments of a ship have been damaged, the ship is declared to be sunk. You're evaluating an ongoing Battleship game, and have two tables.

The table locations_of_ships contains the locations of one of the player's ships. This table contains the following columns:

id - the unique ID of the ship;
upper_left_x - the x-coordinate of the upper left corner;
upper_left_y - the y-coordinate of the upper left corner;
bottom_right_x - the x-coordinate of the bottom right corner;
bottom_right_y - the y-coordinate of the bottom right corner.
In this task there can be these types of ships - 1 × 1, 1 × 2, 1 × 3, 1 × 4, 2 × 1, 3 × 1, 4 × 1 , number of ships of particular type is not fixed, but it is guaranteed that they don't overlap.

The target squares of the opponent's shots are given in another table, opponents_shots, which has the following columns:

id - the unique ID of the shot;
target_x - the x-coordinate of the target square;
target_y - the y-coordinate of the target square.
All the coordinates in these tables are 1-based.

The goal is to return a table that describes the current state of the game. For each class of ship (i.e. for each different size), there should be a row containing four integers: a ship's size in the column size, the number of undamaged ships of that type in the column undamaged, the number of partly damaged ships of that size in the column partly_damaged, and the number of ships of that type that have already been sunk in the column sunk. The result should be ordered by the size of the ships.
 */

CREATE PROCEDURE battleshipGameResults()
BEGIN
	/* Write your SQL here. Terminate each statement with a semicolon. */
    -- SELECT l.*, 
    -- (ABS(l.upper_left_x - l.bottom_right_x) + 
    --     ABS(l.upper_left_y - l.bottom_right_y) + 1)  as total_segment 
    -- FROM locations_of_ships l;
    
    
    /* 
        check opponents is belong a segment S with two point A(x, y), B(x1, y1) width A < B
        target C(x2, y2). Prerequisites: each segment is a square of length 1
        => belong segment S if x2 == x1 and y2 >= y and y2 <= y1
                           or  y2 == y1 and x2 >= x && x2 <= x1
    */
    
    SELECT temp2.total_segment AS size,
       SUM(IF(temp2.total_damage is NULL, 1, 0)) AS undamaged,
       SUM(IF(temp2.total_segment > temp2.total_damage, 1, 0)) AS partly_damaged,
       SUM(IF(temp2.total_segment = temp2.total_damage, 1, 0)) AS sunk
    FROM 
    (
        SELECT 
            temp.total_segment,
            temp1.total_damage
        FROM
        (
            SELECT l.*, 
                (ABS(l.upper_left_x - l.bottom_right_x) + 
                ABS(l.upper_left_y - l.bottom_right_y) + 1)  AS total_segment 
            FROM locations_of_ships l
        ) AS temp
        LEFT JOIN (   
            SELECT l.upper_left_x, 
                    l.upper_left_y, 
                    l.bottom_right_x, 
                    l.bottom_right_y, COUNT(*) AS total_damage 
            FROM locations_of_ships l
            JOIN opponents_shots o 
            ON (o.target_x = l.upper_left_x AND o.target_y >= l.upper_left_y 
                AND o.target_y <= l.bottom_right_y) 
            OR (o.target_y = l.upper_left_y AND o.target_x >= l.upper_left_x
                AND o.target_x <= l.bottom_right_x)
            GROUP BY l.upper_left_x, 
                    l.upper_left_y, 
                    l.bottom_right_x, 
                    l.bottom_right_y
        ) AS temp1
        ON temp.upper_left_x = temp1.upper_left_x 
            AND temp.upper_left_y = temp1.upper_left_y 
            AND temp.bottom_right_x = temp1.bottom_right_x 
            AND temp.bottom_right_y = temp1.bottom_right_y
    ) AS temp2
    GROUP BY temp2.total_segment
    ORDER BY size;
END