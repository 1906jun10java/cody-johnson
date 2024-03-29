-- CREATE SEQUENCES
CREATE SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE CREDENTIAL_SEQ;
CREATE SEQUENCE EMPLOYEE_ROLE_SEQ;
CREATE SEQUENCE REIMBURSEMENT_SEQ;
CREATE SEQUENCE REIMBURSEMENT_TYPE_SEQ;

-- CREATE EMPLOYEE TABLE
CREATE TABLE EMPLOYEE(
	E_ID NUMBER PRIMARY KEY,
	E_LEVEL NUMBER,
	E_EMAIL VARCHAR2(32) UNIQUE,
	E_FIRST_NAME VARCHAR2(32),
	E_LAST_NAME VARCHAR2(32),
	E_REPORTS_TO NUMBER
);

-- CREATE CREDENTIAL TABLE
CREATE TABLE CREDENTIAL(
	E_ID NUMBER PRIMARY KEY,
	E_PASSWORD VARCHAR2(64),
	FOREIGN KEY (E_ID) REFERENCES EMPLOYEE(E_ID)
);

-- CREATE REIMBURSEMENT_TYPE TABLE
CREATE TABLE REIMBURSEMENT_TYPE(
	R_TYPE_ID NUMBER PRIMARY KEY,
	R_TYPE_NAME VARCHAR2(32) UNIQUE
);

-- CREATE REIMBURSEMENT_STATUS TABLE
CREATE TABLE REIMBURSEMENT_STATUS(
	R_STATUS_ID NUMBER PRIMARY KEY,
	R_STATUS_NAME VARCHAR2(32) UNIQUE
);

-- CREATE REIMBURSEMENT TABLE
CREATE TABLE REIMBURSEMENT(
	R_ID NUMBER PRIMARY KEY,
	E_ID NUMBER,
	R_TYPE_ID NUMBER,
	R_STATUS_ID NUMBER,
	R_AMOUNT NUMBER(9,2),
	R_UNIX_TS NUMBER,
	R_DESCRIPTION VARCHAR2(128),
	R_RECEIPT_IMG BLOB,
	FOREIGN KEY (R_TYPE_ID) REFERENCES REIMBURSEMENT_TYPE(R_TYPE_ID),
	FOREIGN KEY (R_STATUS_ID) REFERENCES REIMBURSEMENT_STATUS(R_STATUS_ID),
	FOREIGN KEY (E_ID) REFERENCES EMPLOYEE(E_ID)
);

-- PROCEDURE TO INSERT REIMBURSEMENT
CREATE OR REPLACE PROCEDURE INSERT_REIMBURSEMENT(
    P_E_ID IN NUMBER,
    P_TYPE_ID IN NUMBER,
    P_STATUS_ID IN NUMBER,
    P_AMOUNT IN NUMBER,
    P_UNIX_TS IN NUMBER,
    P_DESCRIPTION IN VARCHAR2,
    P_RECEIPT_IMG IN BLOB
)
AS
BEGIN
    INSERT INTO REIMBURSEMENT VALUES(
        REIMBURSEMENT_SEQ.NEXTVAL,
        P_E_ID,
        P_TYPE_ID,
        P_STATUS_ID,
        P_AMOUNT,
        P_UNIX_TS,
        P_DESCRIPTION,
        P_RECEIPT_IMG
    );
    COMMIT;
END;

-- INSERT EMPLOYEES
-- CEO
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 4, 'tmorton@ers.com', 'Trevor', 'Morton', null);
-- Department Heads
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 3, 'trodriquez@ers.com', 'Toyah', 'Rodriquez', 1);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 3, 'rfitzpatrick@ers.com', 'Richie', 'Fitzpatrick', 1);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 3, 'kramsey@ers.com', 'Kingston', 'Ramsey', 1);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 3, 'ahiggs@ers.com', 'Ann', 'Higgs', 1);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 3, 'sforrest@ers.com', 'Simrah', 'Forrest', 1);
-- Supervisors
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'aconway@ers.com', 'Andrew', 'Conway', 2);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'mhagan@ers.com', 'Maddy', 'Hagan', 2);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'syork@ers.com', 'Shana', 'York', 3);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'jwitt@ers.com', 'James', 'Witt', 3);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'lgriffith@ers.com', 'Lukas', 'Griffith', 4);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'spate@ers.com', 'Sara', 'Pate', 4);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'bbentley@ers.com', 'Barry', 'Bentley', 5);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'ascott@ers.com', 'Armani', 'Scott', 5);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'jheaton@ers.com', 'Jessica', 'Heaton', 6);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 2, 'nsherman@ers.com', 'Nana', 'Sherman', 6);
-- Engineers
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'jdorsey@ers.com', 'Jane', 'Dorsey', 7);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'bsolomon@ers.com', 'Bridie', 'Solomon', 7);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'kshea@ers.com', 'Kara', 'Shea', 7);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'mwoodward@ers.com', 'Milan', 'Woodward', 8);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'sellis@ers.com', 'Sameeha', 'Ellis', 8);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'jmontes@ers.com', 'Jimmy', 'Montes', 8);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'mcampos@ers.com', 'Meerab', 'Campos', 9);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'amaloy@ers.com', 'Aubrey', 'Maloy', 9);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'rbray@ers.com', 'Ronald', 'Bray', 9);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'zcrosby@ers.com', 'Zoey', 'Crosby', 10);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'lbarclay@ers.com', 'Lorenzo', 'Barclay', 10);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'jhererra@ers.com', 'Justin', 'Hererra', 10);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'esmith@ers.com', 'Erika', 'Smith', 11);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'csutherland@ers.com', 'Callan', 'Sutherland', 11);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'icash@ers.com', 'Ibrar', 'Cash', 11);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'nclark@ers.com', 'Niel', 'Clark', 12);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'bmorton@ers.com', 'Billy', 'Morton', 12);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'hsimmonds@ers.com', 'Hendrix', 'Simmonds', 12);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'aswanson@ers.com', 'Alessio', 'Swanson', 13);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'tcorona@ers.com', 'Talha', 'Corona', 13);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'cdonovan@ers.com', 'Cecil', 'Donovan', 13);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'rgardner@ers.com', 'Reuben', 'Gardner', 14);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'mmarsh@ers.com', 'Muneeb', 'Marsh', 14);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'fvillanueva@ers.com', 'Fiza', 'Villanueva', 14);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'okavanaugh@ers.com', 'Otis', 'Kavanaugh', 15);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'hmcniel@ers.com', 'Hal', 'McNiel', 15);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'hhyde@ers.com', 'Hayley', 'Hyde', 15);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'sstein@ers.com', 'Saskia', 'Stein', 16);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'cjohnson@ers.com', 'Cody', 'Johnson', 16);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, 1, 'sgwaltney@ers.com', 'Sloan', 'Gwaltney', 16);

-- INSERT CREDENTIALS
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');
INSERT INTO CREDENTIAL VALUES(CREDENTIAL_SEQ.NEXTVAL, 'password');

-- INSERT REIMBURSEMENT TYPES
INSERT INTO REIMBURSEMENT_TYPE VALUES(1, 'Seminar');
INSERT INTO REIMBURSEMENT_TYPE VALUES(2, 'Preparation Classes');
INSERT INTO REIMBURSEMENT_TYPE VALUES(3, 'Certification');
INSERT INTO REIMBURSEMENT_TYPE VALUES(4, 'Technical Training');
INSERT INTO REIMBURSEMENT_TYPE VALUES(5, 'Other');

-- INSERT REIMBURSEMENT STATUSES
INSERT INTO REIMBURSEMENT_STATUS VALUES(-1, 'Rejected');
INSERT INTO REIMBURSEMENT_STATUS VALUES(0, 'More Information Required');
INSERT INTO REIMBURSEMENT_STATUS VALUES(1, 'Initialized');
INSERT INTO REIMBURSEMENT_STATUS VALUES(2, 'Supervisor Approved');
INSERT INTO REIMBURSEMENT_STATUS VALUES(3, 'Department Head Approved');
INSERT INTO REIMBURSEMENT_STATUS VALUES(4, 'Accepted');

-- GET EMPLOYEE PASSWORD BY EMAIL
SELECT E.E_ID, C.E_PASSWORD
FROM EMPLOYEE E
INNER JOIN CREDENTIAL C
ON E.E_ID = C.E_ID
WHERE E_EMAIL = 'cjohnson@ers.com';

-- GET EMPLOYEE'S SUPERVISOR
SELECT S.E_ID, S.E_EMAIL, S.E_FIRST_NAME, S.E_LAST_NAME FROM EMPLOYEE E
JOIN EMPLOYEE S ON E.E_REPORTS_TO = S.E_ID
WHERE E.E_ID = 45;

-- GET SUPERVISOR'S SUBORDINATES
SELECT E_ID, E_EMAIL, E_FIRST_NAME, E_LAST_NAME FROM EMPLOYEE
WHERE E_REPORTS_TO = 16
ORDER BY E_ID;

-- GET SUPERVISOR'S SUBORDINATES' REIMBURSEMENTS
SELECT * FROM REIMBURSEMENT R
JOIN REIMBURSEMENT_STATUS S ON R.R_STATUS_ID = S.R_STATUS_ID
JOIN REIMBURSEMENT_TYPE T ON R.R_TYPE_ID = T.R_TYPE_ID
WHERE R.E_ID IN (
	SELECT E_ID FROM EMPLOYEE E1
	WHERE E1.E_ID IN (
		SELECT E_ID FROM EMPLOYEE E2
		WHERE E2.E_REPORTS_TO IN (
			SELECT E_ID FROM EMPLOYEE E3
			WHERE E3.E_REPORTS_TO IN (
				SELECT E_ID FROM EMPLOYEE E4
				WHERE E4.E_REPORTS_TO = 16
			)
		)
	)
	UNION
	SELECT E_ID FROM EMPLOYEE E1
	WHERE E1.E_ID IN (
		SELECT E_ID FROM EMPLOYEE E2
		WHERE E2.E_REPORTS_TO IN (
			SELECT E_ID FROM EMPLOYEE E3
			WHERE E3.E_REPORTS_TO = 16
		)
	)
	UNION
	SELECT E_ID FROM EMPLOYEE E1
	WHERE E1.E_ID IN (
		SELECT E_ID FROM EMPLOYEE E2
		WHERE E2.E_REPORTS_TO = 16
	)
)
ORDER BY R.R_UNIX_TS;

-- GET REIMBURSEMENT AND STATUSES
SELECT * FROM REIMBURSEMENT R
JOIN REIMBURSEMENT_STATUS S ON R.R_STATUS_ID = S.R_STATUS_ID
JOIN REIMBURSEMENT_TYPE T ON R.R_TYPE_ID = T.R_TYPE_ID
WHERE E_ID = 45 ORDER BY R_UNIX_TS DESC;

-- DROP ALL
DROP SEQUENCE EMPLOYEE_SEQ;
DROP SEQUENCE CREDENTIAL_SEQ;
DROP SEQUENCE EMPLOYEE_ROLE_SEQ;
DROP SEQUENCE REIMBURSEMENT_SEQ;
DROP SEQUENCE REIMBURSEMENT_TYPE_SEQ;
DROP TABLE REIMBURSEMENT;
DROP TABLE REIMBURSEMENT_TYPE;
DROP TABLE CREDENTIAL;
DROP TABLE EMPLOYEE;
DROP TABLE REIMBURSEMENT_STATUS;
