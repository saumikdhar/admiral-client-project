SHOW databases ;
USE managingtimesheets;
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Aristotle','Livingston',21,8);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Clio','Vaughan',22,11);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Jayme','Sparks',23,15);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Shoshana','Daniels',24,5);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Haley','Hodges',25,8);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Alexandra','Jennings',26,9);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Ori','Levy',27,12);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Graiden','Thornton',28,9);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Aidan','Maxwell',29,11);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Amity','Newman',30,1);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Kareem','Mcbride',31,5);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Evan','Dunlap',32,19);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Constance','Lowery',33,8);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Wade','Rice',34,14);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Jescie','Head',35,8);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Ryan','Stevens',36,1);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Venus','Riddle',37,2);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Aurelia','Callahan',38,17);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Elliott','Malone',39,11);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Tiger','Molina',40,13);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Cooper','Marshall',41,16);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Quintessa','Hogan',42,13);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Travis','Savage',43,2);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Keelie','Mccarty',44,4);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Rae','Gilmore',45,12);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Ralph','Barnett',46,7);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Shafira','Thornton',47,15);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Riley','Conway',48,9);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Mariko','Blackburn',49,10);
INSERT INTO contractors (contractor_firstname,contractor_lastname,login_id,manager_id) VALUES ('Juliet','Conley',50,15);

select * from timesheets join agency_contractors ac on timesheets.agency_contractor_id = ac.agency_contractor_id join contractors c2 on ac.contractor_id = c2.contractor_id join
    managers m on c2.manager_id = m.manager_id where
        m.manager_first_name = 'Jordan' and m.manager_last_name = 'Coffey' and status = 'pending'