-- URL: https://app.codesignal.com/arcade/db/a-table-of-desserts/p9HAu8mLsR46uqWBs
/**
 * The latest Tic-Tac-Toe World Championship has just concluded. It was a big success and attracted a lot of participants! Now everyone is waiting for the results. As a member of the judging committee, you've been tasked with creating a championship leaderboard.

The results of all the tic-tac-toe matches are stored in the results table, which has the following structure:

timestamp: the date and time of the game;
name_naughts: the name of the player that played with naughts (O);
name_crosses: the name of the player that played with crosses (X);
board: the state of the tic-tac-toe board at the end of the game, in the format described below.
The board is a string of 9 characters that represent the state of board's 9 cells at the end of the match. The first 3 characters represent the first (upper) row, the second 3 characters represent the second row, etc. The character is O if the respective board cell contained a naught, X if it contained a cross, or . if the cell was empty at the end of the game.

For example, this board

 | |O
 |O| 
X|X|X
is represented by the string ..O.O.XXX.

It is guaranteed that the opposing players have different names. It's also guaranteed that each board represents a valid terminal state for a tic-tac-toe game, meaning that either of the players wins or it's a draw.

Players are awarded points based on their performance: They get 2 points for each game they win, 1 point for a draw, and 0 points if they lose.

Create a leaderboard with the following format:

Given the results table, compose a results table that has the following six columns: name, points, played, won, draw, and lost , containing the player names, their points calculated as described above, the number of games they have played, the number of games they have won, and the number of games they have lost, respectively. The table should be sorted in descending order by the points, then in ascending order by the total number of played games, then in descending order by the number of victories, and then in ascending order by player names.
 */
DROP PROCEDURE IF EXISTS checkSymbolWin;
CREATE PROCEDURE checkSymbolWin(IN board VARCHAR(10), OUT symbol VARCHAR(1))
BEGIN

    SET symbol = 'D';
    
    SET @p0 = SUBSTRING(board, 1, 1);
    SET @p1 = SUBSTRING(board, 2, 1);
    SET @p2 = SUBSTRING(board, 3, 1);
    SET @p3 = SUBSTRING(board, 4, 1);
    SET @p4 = SUBSTRING(board, 5, 1);
    SET @p5 = SUBSTRING(board, 6, 1);
    SET @p6 = SUBSTRING(board, 7, 1);
    SET @p7 = SUBSTRING(board, 8, 1);
    SET @p8 = SUBSTRING(board, 9, 1);
    
    IF ((@p0 = @p1 AND @p1 = @p2) 
      OR ( @p0 = @p3 AND @p3 = @p6 )) AND @p0 <> '.' THEN 
        SET symbol = @p0;
    END IF;
    
    IF ((@p6 = @p7 AND @p7 = @p8) 
      OR ( @p2 = @p5 AND @p5 = @p8)) AND @p8 <> '.' THEN 
        SET symbol = @p8;
    END IF;
        
    IF ((@p1 = @p4 AND @p4 = @p7) 
       OR (@p3 = @p4 AND @p4 = @p5) 
       OR (@p0 = @p4 AND @p4 = @p8) 
       OR (@p2 = @p4 AND @p4 = @p6)) AND @p4 <> '.' THEN 
        SET symbol = @p4;
    END IF;
    
    IF symbol = 'O' THEN
        SET symbol = 'W';
    END IF;
    
    IF symbol = 'X' THEN
        SET symbol = 'L';
    END IF;
    
END$$

CREATE PROCEDURE tictactoeTournament()
BEGIN
    DECLARE curNameNaught VARCHAR(100);
    DECLARE curNameCross VARCHAR(100);
    DECLARE curBoard VARCHAR(10);
    DECLARE done INT DEFAULT FALSE;
    DECLARE cur CURSOR FOR SELECT name_naughts, name_crosses, board 
    FROM results;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    DROP TABLE IF EXISTS resultTable;
    CREATE TEMPORARY TABLE resultTable
    (
        name VARCHAR(20) not null,
        points INT(3) not null default 0,
        played INT(3) not null default 0,
        won INT(3) not null default 0,
        draw INT(3) not null default 0,
        lost INT(3) not null default 0
    );
    -- key: player's name, value = [points, played, won, draw, lost]
    SET @JsonChar = JSON_OBJECT();
    
    OPEN cur;
    loop_through_rows: LOOP
        FETCH cur INTO curNameNaught, curNameCross, curBoard;
        IF done THEN
            LEAVE loop_through_rows;
        END IF;
        
        CALL checkSymbolWin(curBoard, @total);
    --    SELECT curNameNaught, curNameCross, @total, curBoard;
        -- check is exit curNameNaught in json char
        IF JSON_EXTRACT(@JsonChar, CONCAT('$."', curNameNaught, '"')) IS NOT NULL THEN
            SET @arrayKey = JSON_EXTRACT(@JsonChar, CONCAT('$."', curNameNaught, '"'));
            SET @points = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
            SET @played = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED) + 1;
            SET @won = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
            SET @draw = CAST(JSON_EXTRACT(@arrayKey, '$[3]') AS SIGNED);
            SET @lost = CAST(JSON_EXTRACT(@arrayKey, '$[4]') AS SIGNED);
            
            IF @total = 'W' THEN
                SET @won := @won + 1;
                SET @points := @points + 2;
            END IF;
            
            IF @total = 'D' THEN
                SET @draw := @draw + 1;
                SET @points := @points + 1;
            END IF;
            
            IF @total = 'L' THEN
                SET @lost := @lost + 1;
            END IF;
            
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', curNameNaught, '"'), 
            JSON_ARRAY(@points, @played, @won, @draw, @lost));
        ELSE
            SET @points = 0;
            SET @played = 1;
            SET @won = 0;
            SET @draw = 0;
            SET @lost = 0;
            
            IF @total = 'W' THEN
                SET @won := @won + 1;
                SET @points := @points + 2;
            END IF;
            
            IF @total = 'D' THEN
                SET @draw := @draw + 1;
                SET @points := @points + 1;
            END IF;
            
            IF @total = 'L' THEN
                SET @lost := @lost + 1;
            END IF;
            
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', curNameNaught, '"'),
             JSON_ARRAY(@points, @played, @won, @draw, @lost));
        END IF;
        
         -- check is exit curNameCross in json char
        IF JSON_EXTRACT(@JsonChar, CONCAT('$."', curNameCross, '"')) IS NOT NULL THEN
            SET @arrayKey = JSON_EXTRACT(@JsonChar, CONCAT('$."', curNameCross, '"'));
            SET @points = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
            SET @played = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED) + 1;
            SET @won = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
            SET @draw = CAST(JSON_EXTRACT(@arrayKey, '$[3]') AS SIGNED);
            SET @lost = CAST(JSON_EXTRACT(@arrayKey, '$[4]') AS SIGNED);
            
            IF @total = 'W' THEN
                SET @lost := @lost + 1;
            END IF;
            
            IF @total = 'D' THEN
                SET @draw := @draw + 1;
                SET @points := @points + 1;
            END IF;
            
            IF @total = 'L' THEN
                SET @won := @won + 1;
                SET @points := @points + 2;
            END IF;
            
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', curNameCross, '"'), 
            JSON_ARRAY(@points ,@played, @won, @draw, @lost));
        ELSE
            SET @points = 0;
            SET @played = 1;
            SET @won = 0;
            SET @draw = 0;
            SET @lost = 0;
            
            IF @total = 'W' THEN
                SET @lost := @lost + 1;
            END IF;
            
            IF @total = 'D' THEN
                SET @draw := @draw + 1;
                SET @points := @points + 1;
            END IF;
            
            IF @total = 'L' THEN
                SET @won := @won + 1;
                SET @points := @points + 2;
            END IF;
            
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', curNameCross, '"'), 
            JSON_ARRAY(@points, @played, @won, @draw, @lost));
        END IF;
         
    END LOOP;
    CLOSE cur;
    
   -- SELECT @JsonChar;
    
     # get data from jsonChar
    SET @i = 0;
    SET @arrKeys = JSON_KEYS(@JsonChar);
     
    WHILE @i < JSON_LENGTH(@JsonChar) DO
        SET @key = JSON_EXTRACT(@arrKeys, CONCAT('$[', @i, ']'));
        SET @arrayValue = JSON_EXTRACT(@JsonChar,  CONCAT('$.', @key));
        
        SET @points = CAST(JSON_EXTRACT(@arrayValue, '$[0]') AS SIGNED);
        SET @played = CAST(JSON_EXTRACT(@arrayValue, '$[1]') AS SIGNED);
        SET @won = CAST(JSON_EXTRACT(@arrayValue, '$[2]') AS SIGNED);
        SET @draw = CAST(JSON_EXTRACT(@arrayValue, '$[3]') AS SIGNED);
        SET @lost = CAST(JSON_EXTRACT(@arrayValue, '$[4]') AS SIGNED);
        
        SET @key = TRIM(@key);
        SET @key = SUBSTRING(@key, 2, LENGTH(@key) - 2);
        INSERT INTO resultTable values(@key, @points, @played, @won, @draw, @lost);
        
        SET @i = @i + 1;
    END WHILE;

    SELECT * FROM resultTable ORDER BY points DESC, played ASC, won DESC, name ASC;
    
END