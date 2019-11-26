USE managingtimesheets;
SELECT *
FROM contractors c
         JOIN agency_contractors ac ON c.contractor_id = ac.contractor_id
         JOIN agencies a ON ac.agency_id = a.agency_id
WHERE c.contractor_id = 7;

SELECT * FROM agency_contractors where managingtimesheets.agency_contractors.contractor_id = 7;