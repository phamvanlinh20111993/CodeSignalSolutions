/*
Enter your query here.
*/
/*SELECT cont.contest_id,
       cont.hacker_id,
       cont.name,
       T.total_submissions,
       T.total_accepted_submissions,
       T1.total_views,
       T1.total_unique_views
FROM Contests cont
JOIN 
  (SELECT con.contest_id,
          sum(t.total_submissions) as total_submissions,
          sum(t.total_accepted_submissions) as total_accepted_submissions
   FROM contests con
   JOIN Colleges coll ON coll.contest_id = con.contest_id
   JOIN Challenges c ON c.college_id = coll.college_id
   JOIN
     (SELECT challenge_id,
             sum(total_submissions) as total_submissions,
             sum(total_accepted_submissions) as total_accepted_submissions 
      FROM Submission_Stats
      GROUP BY challenge_id) AS t ON t.challenge_id = c.challenge_id
   GROUP BY con.contest_id) AS T ON T.contest_id = cont.contest_id
JOIN 
  (SELECT con.contest_id,
          sum(t1.total_views) as total_views,
          sum(t1.total_unique_views) as total_unique_views
   FROM contests con
   JOIN Colleges coll ON coll.contest_id = con.contest_id
   JOIN Challenges c ON c.college_id = coll.college_id
   JOIN
     (SELECT challenge_id,
             sum(total_views) as total_views,
             sum(total_unique_views) as total_unique_views
      FROM View_Stats
      GROUP BY challenge_id) AS t1 ON t1.challenge_id = c.challenge_id
    GROUP BY con.contest_id) AS T1 ON T1.contest_id = cont.contest_id
ORDER BY cont.contest_id; */


/*
Enter your query here.
*/
SELECT cont.contest_id,
       cont.hacker_id,
       cont.name,
       T.total_submissions,
       T.total_accepted_submissions,
       T1.total_views,
       T1.total_unique_views
FROM Contests cont
JOIN 
  (SELECT con.contest_id,
          sum(ss.total_submissions) as total_submissions,
          sum(ss.total_accepted_submissions) as total_accepted_submissions
   FROM contests con
   JOIN Colleges coll ON coll.contest_id = con.contest_id
   JOIN Challenges c ON c.college_id = coll.college_id
   JOIN Submission_Stats ss ON ss.challenge_id = c.challenge_id
   GROUP BY con.contest_id) AS T ON T.contest_id = cont.contest_id
JOIN 
  (SELECT con.contest_id,
          sum(vt.total_views) as total_views,
          sum(vt.total_unique_views) as total_unique_views
   FROM contests con
   JOIN Colleges coll ON coll.contest_id = con.contest_id
   JOIN Challenges c ON c.college_id = coll.college_id
   JOIN View_Stats vt ON vt.challenge_id = c.challenge_id
   GROUP BY con.contest_id) AS T1 ON T1.contest_id = cont.contest_id
ORDER BY cont.contest_id;