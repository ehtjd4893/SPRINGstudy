DROP TABLE BOARD;

CREATE TABLE BOARD
(
	NO NUMBER PRIMARY KEY,
	WRITER VARCHAR2(64) NOT NULL,
	TITLE VARCHAR2(64) NOT NULL,
	CONTENT VARCHAR2(500) NOT NULL,
	POSTDATE DATE,
	LASTMODIFIED DATE,
	IP VARCHAR2(64) NOT NULL,
	HIT NUMBER NOT NULL,
	IMAGE VARCHAR2(64)
);

DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;