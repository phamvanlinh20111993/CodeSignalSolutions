WITH temp(submission_date, hacker_id, amount) AS
  (SELECT submission_date,
          hacker_id,
          count(hacker_id) AS amount
   FROM Submissions
   GROUP BY submission_date,
            hacker_id)
SELECT t.submission_date,
       t1.c,
       t.hacker_id,
       t.name
FROM
  (SELECT t.*,
          h.name
   FROM
     (SELECT t.submission_date,
             max(t.amount) AS amount,
             min(t.hacker_id) AS hacker_id
      FROM
        (SELECT t.*,
                t1.hacker_id
         FROM
           (SELECT te.submission_date,
                   max(te.amount) AS amount
            FROM TEMP te
            GROUP BY te.submission_date) t
         JOIN TEMP t1 ON t1.submission_date = t.submission_date
         AND t.amount = t1.amount) t
      GROUP BY t.submission_date) t
   JOIN hackers h ON t.hacker_id = h.hacker_id) t,

  (SELECT te.submission_date,
          count(te.hacker_id) AS c
   FROM
     (SELECT DISTINCT Sb.submission_date,
                      hacker_id
      FROM submissions Sb
      -- TODO Try to refactor as join query
      WHERE Sb.hacker_id in
          (SELECT t.hacker_id
           FROM
             (SELECT DISTINCT hacker_id,
                              submission_date
              FROM submissions
              WHERE submission_date <= Sb.submission_date ) t
           GROUP BY t.hacker_id
           HAVING count(t.submission_date) >= DATEDIFF(DAY, '2016-03-01', Sb.submission_date)+1) ) te
   GROUP BY te.submission_date) t1
WHERE t1.submission_date = t.submission_date
ORDER BY t1.submission_date