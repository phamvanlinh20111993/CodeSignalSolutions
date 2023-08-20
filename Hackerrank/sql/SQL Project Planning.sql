/*
Enter your query here.
*/
DELIMITER //

CREATE PROCEDURE GetProjectDate()
BEGIN
    DECLARE n INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE j INT DEFAULT 1;
    
    DECLARE sd DATE;
    DECLARE sd2 DATE;
    
    DECLARE ed DATE;
    DECLARE ed1 DATE;
    DECLARE ed2 DATE;
    DECLARE ed3 DATE;
    
    CREATE TEMPORARY TABLE IF NOT EXISTS result (  
        startDate Date,
        endDate Date
    ); 
    TRUNCATE TABLE result;
    
    SELECT COUNT(*) FROM Projects INTO n;
    SET i=0;
    
     CREATE TEMPORARY TABLE IF NOT EXISTS ProjectSort (  
        start_date Date,
        end_date Date
    ); 
    TRUNCATE TABLE ProjectSort;
    
    insert into ProjectSort(start_date, end_date) Select start_date, end_date from Projects 
    order by start_date, end_date; 
    
    WHILE i<n DO 
      
      Select start_date, end_date from ProjectSort limit i,1 into sd, ed; 
      SET ed1 = ed;
      SET ed3 = ed;
      
      SET j = i+1;
      myloop: WHILE j < n DO
        Select start_date, end_date from ProjectSort limit j,1 into sd2, ed2;
        IF sd2 <> ed1 THEN
            SET ed3 = ed1;
            LEAVE myloop;
        ELSE 
            SET ed1 = ed2;
        END IF;
        SET j = j + 1;
      END WHILE myloop;
      
      INSERT INTO result(startDate, endDate) VALUES(sd, ed3);
      
      SET i = j;  
    END WHILE;
    
  SELECT *  FROM result order by DATEDIFF(endDate, startDate), startDate; 
END //

DELIMITER ;

CALL GetProjectDate();