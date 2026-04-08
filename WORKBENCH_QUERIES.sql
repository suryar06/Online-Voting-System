-- ================================================================
--   VotePro — MySQL Workbench Queries
--   Copy & paste any query into MySQL Workbench and press ⚡
-- ================================================================


-- ── SETUP (Run this FIRST before starting Spring Boot) ──────────

CREATE DATABASE IF NOT EXISTS voting_db;
USE voting_db;


-- ================================================================
--   VIEW DATA
-- ================================================================

-- 1. See ALL registered voters (every column)
SELECT * FROM voting_db.users;

-- 2. See ALL voters — clean readable view
SELECT
    user_id,
    name,
    voter_id,
    aadhar_id,
    dob,
    gender,
    phone,
    CASE WHEN has_voted = 1 THEN '✅ Voted' ELSE '⏳ Not Voted' END AS vote_status
FROM voting_db.users
ORDER BY user_id DESC;

-- 3. See only voters who HAVE voted
SELECT user_id, name, voter_id, aadhar_id, phone
FROM voting_db.users
WHERE has_voted = 1;

-- 4. See only voters who have NOT voted yet
SELECT user_id, name, voter_id, aadhar_id, phone
FROM voting_db.users
WHERE has_voted = 0;


-- ================================================================
--   COUNT / STATS
-- ================================================================

-- 5. Total registered voters
SELECT COUNT(*) AS total_registered FROM voting_db.users;

-- 6. Total who have voted
SELECT COUNT(*) AS total_voted FROM voting_db.users WHERE has_voted = 1;

-- 7. Total who have NOT voted
SELECT COUNT(*) AS pending_votes FROM voting_db.users WHERE has_voted = 0;

-- 8. Full summary in one query
SELECT
    COUNT(*)                                          AS total_registered,
    SUM(has_voted)                                    AS total_voted,
    COUNT(*) - SUM(has_voted)                         AS not_yet_voted,
    ROUND(SUM(has_voted) * 100.0 / COUNT(*), 1)       AS vote_percentage
FROM voting_db.users;


-- ================================================================
--   SEARCH
-- ================================================================

-- 9. Search by Voter ID (replace the value)
SELECT * FROM voting_db.users WHERE voter_id = 'ABC1234567';

-- 10. Search by Aadhaar ID
SELECT * FROM voting_db.users WHERE aadhar_id = '512167164722';

-- 11. Search by name (partial match)
SELECT * FROM voting_db.users WHERE name LIKE '%Varshini%';

-- 12. Check if a specific voter has voted
SELECT name, voter_id, has_voted FROM voting_db.users WHERE voter_id = 'ABC1234567';


-- ================================================================
--   TESTING / RESET (Use carefully — only for testing!)
-- ================================================================

-- 13. Reset ALL votes to 0 (allow everyone to vote again — testing only)
-- UPDATE voting_db.users SET has_voted = 0;

-- 14. Reset vote for one specific voter
-- UPDATE voting_db.users SET has_voted = 0 WHERE voter_id = 'ABC1234567';

-- 15. Delete a specific voter
-- DELETE FROM voting_db.users WHERE voter_id = 'ABC1234567';

-- 16. Delete ALL voters (DANGER — wipes entire table)
-- DELETE FROM voting_db.users;

-- 17. Drop and recreate table (full reset)
-- DROP TABLE IF EXISTS voting_db.users;
