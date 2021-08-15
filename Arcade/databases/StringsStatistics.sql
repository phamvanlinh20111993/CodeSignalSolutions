
-- URL: https://app.codesignal.com/arcade/db/between-join-and-select/JrJvTsLSDik4CgoCi
/**
 * You are collecting some statistics about strings in the table strs, which only has one column:

str - a unique string that consists only of lowercase English letters.
Your goal is to return a new table ans, which has the following columns:
letter - a unique lowercase English letter;
total - the total number of occurrences of this letter in all the strings from table strs;
occurrence - the number of strings from table strs in which this letter occurs at least once;
max_occurrence - the maximal number of occurrences of this letter in a single string;
max_occurence_reached - the number of strings in which this maxumal number of occurrences is reached.
The rows should be ordered lexicographically by letter. For letters that are not contained in the strs table, don't add a row to the output table (i.e., all integers in the total column should be positive).
 */
DROP PROCEDURE IF EXISTS getTotalCharInString;
CREATE PROCEDURE getTotalCharInString(IN ch VARCHAR(1), OUT total INTEGER)
BEGIN
    
    SET @groupRowStrToOne = ( SELECT GROUP_CONCAT(str SEPARATOR '') AS oneChar 
                              FROM strs );
    SET @strReplace = REGEXP_REPLACE(@groupRowStrToOne, CONCAT("[^", ch, "]"), '');
    /**
        LENGTH() returns the length of the string measured in bytes. 
        CHAR_LENGTH() returns the length of the string measured in characters.
    **/
    SET total = CHAR_LENGTH(@strReplace);
END$$


/*Please add ; after each select statement*/
CREATE PROCEDURE stringsStatistics()

BEGIN
    DECLARE counter INT DEFAULT 1;
    DECLARE i INT DEFAULT 0;
  /**  CALL getTotalCharInString('b', @total);
    SELECT @total; */
    
    SET @JsonChar = JSON_OBJECT();
    SET @JsonCharOccr = JSON_OBJECT();
    SET @JsonCharMaxOccr = JSON_OBJECT();
    SET @groupRowStrToOne = CONCAT((SELECT GROUP_CONCAT(str SEPARATOR ';') AS oneChar 
                              FROM strs), ';');
                    
    myLoop: WHILE counter <= CHAR_LENGTH(@groupRowStrToOne) DO
        SET @ch = SUBSTR(@groupRowStrToOne, counter, 1);
        
        IF (@ch = ";") THEN
            SET counter = counter + 1;
            SET @JsonCharOccr = JSON_OBJECT();
            
            # check max occurrence
            SET i = 0;
            SET @arrKeysJsonMaxOcc = JSON_KEYS(@JsonCharMaxOccr);
            
            WHILE i < JSON_LENGTH(@JsonCharMaxOccr) DO
                SET @key = JSON_EXTRACT(@arrKeysJsonMaxOcc, CONCAT('$[', i, ']'));
                SET @valueKey = CAST(JSON_EXTRACT(@JsonCharMaxOccr, CONCAT('$.', @key)) AS SIGNED);
                SET @arrayKey = JSON_EXTRACT(@JsonChar, CONCAT('$.', @key));
                SET @the = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
                
                IF @valueKey > @the THEN
                    SET @fie = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
                    SET @see = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED);
                    SET @the = @valueKey;
                    SET @foe = 1;
                    SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$.', @key), 
                                             JSON_ARRAY(@fie, @see, @the, @foe));
                # max_occurrence_reached
                ELSEIF @valueKey = @the THEN
                    SET @fie = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
                    SET @see = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED);
                    SET @the = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
                    SET @foe = CAST(JSON_EXTRACT(@arrayKey, '$[3]') AS SIGNED) + 1;
                    SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$.', @key), 
                                             JSON_ARRAY(@fie, @see, @the, @foe));
                END IF;
                
                SET i = i + 1;
            END WHILE;
            SET @JsonCharMaxOccr = JSON_OBJECT();
            
            # continue
            ITERATE myLoop;
        END IF;
        
        # total, occurrence,
        IF JSON_EXTRACT(@JsonChar, CONCAT('$."', @ch, '"')) IS NULL THEN
            # json array with 4 present elements: total, occurrence, 
            # max_occurrence, max_occurrence_reached
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', @ch, '"'), JSON_ARRAY(1, 1, 0, 0));
            SET @JsonCharOccr = JSON_SET(@JsonCharOccr, CONCAT('$."', @ch, '"'), 1);
        ELSE
            SET @arrayKey = JSON_EXTRACT(@JsonChar, CONCAT('$."', @ch, '"'));
                        
            SET @fie = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
            SET @see = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED);
            SET @the = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
            SET @foe = CAST(JSON_EXTRACT(@arrayKey, '$[3]') AS SIGNED);
            # total          
            SET @fie = @fie + 1;
            
            # occurrence
            IF JSON_EXTRACT(@JsonCharOccr, CONCAT('$."', @ch, '"')) IS NULL THEN
                SET @see = @see + 1;
                SET @JsonCharOccr = JSON_SET(@JsonCharOccr, CONCAT('$."', @ch, '"'), 1);
            END IF;
            
            # result
            SET @JsonChar = JSON_SET(@JsonChar, CONCAT('$."', @ch, '"'), 
                                     JSON_ARRAY(@fie, @see, @the, @foe));
        END IF;
        
        #max_occurrence
        IF JSON_EXTRACT(@JsonCharMaxOccr, CONCAT('$."', @ch, '"')) IS NULL THEN
            SET @JsonCharMaxOccr = JSON_SET(@JsonCharMaxOccr, CONCAT('$."', @ch, '"'), 1);
        ELSE
            SET @getMax = CAST(JSON_EXTRACT(@JsonCharMaxOccr, CONCAT('$."', @ch, '"')) AS SIGNED) + 1;
            SET @JsonCharMaxOccr = JSON_SET(@JsonCharMaxOccr, CONCAT('$."', @ch, '"'), @getMax);
        END IF;
        
        SET counter = counter + 1;
    END WHILE myLoop;
    
    DROP TABLE IF EXISTS tempTable;
    CREATE TEMPORARY TABLE tempTable
    (
        letter varchar(20) not null,
        total int(9) not null,
        occurrence int(9) not null,
        max_occurrence int(9) not null,
        max_occurrence_reached int(9) not null
    );
    # get data from jsonChar
    SET i = 0;
    SET @arrKeysJsonMaxOcc = JSON_KEYS(@JsonChar);
    WHILE i < JSON_LENGTH(@JsonChar) DO
        SET @key = JSON_EXTRACT(@arrKeysJsonMaxOcc, CONCAT('$[', i, ']'));
        SET @arrayKey = JSON_EXTRACT(@JsonChar, CONCAT('$.', @key));
        
        SET @fie = CAST(JSON_EXTRACT(@arrayKey, '$[0]') AS SIGNED);
        SET @see = CAST(JSON_EXTRACT(@arrayKey, '$[1]') AS SIGNED);
        SET @the = CAST(JSON_EXTRACT(@arrayKey, '$[2]') AS SIGNED);
        SET @foe = CAST(JSON_EXTRACT(@arrayKey, '$[3]') AS SIGNED);
        
        
        SET @key = SUBSTR(@key, 2, 1);
        INSERT INTO tempTable values(@key, @fie, @see, @the, @foe);
        
        SET i = i + 1;
    END WHILE;

    SELECT * FROM tempTable;


/**    DECLARE doneLoop BOOLEAN DEFAULT FALSE; 
    DECLARE strD VARCHAR(100);
    
    DECLARE strR CURSOR FOR SELECT str FROM strs; 
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET doneLoop = TRUE; 
    
    OPEN strR; 
        readLoop: LOOP     
            IF doneLoop THEN 
             LEAVE readLoop;     
            END IF; 
            
            # do some thing here
            FETCH strR INTO strD;   
            SELECT strD AS valu;

    END LOOP readLoop; 

    CLOSE strR; #Closing the cursor  **/

END