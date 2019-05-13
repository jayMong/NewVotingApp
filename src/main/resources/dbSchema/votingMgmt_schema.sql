CREATE SEQUENCE seq_exem_id START 1;
CREATE SEQUENCE seq_question_id START 1;
CREATE SEQUENCE seq_item_id START 1;

CREATE TABLE vote_exem_master
(
  exem_id character varying(20) NOT NULL,
  exem_nm character varying(200) NOT NULL,
  exem_type character varying(2) NOT NULL,
  question_total_count integer,
  exem_status character varying(1) NOT NULL,
  start_date timestamp without time zone,
  end_date timestamp without time zone,
  exec_desc character varying(2000),
  modifier character varying(9),
  CONSTRAINT pk_vote_exem_master PRIMARY KEY (exem_id )
);

CREATE TABLE vote_question_master
(
  question_id character varying(20) NOT NULL,
  question_nm character varying(200) NOT NULL,
  exem_id character varying(20) NOT NULL,
  question_order integer,
  question_type character varying(10) NOT NULL,
  correct_answer character varying(200),
  question_desc character varying(2000),
  modifier character varying(9),
  CONSTRAINT pk_vote_question_master PRIMARY KEY (question_id )
);

CREATE TABLE vote_item_master
(
  item_id character varying(20) NOT NULL,
  item_nm character varying(200) NOT NULL,
  question_id character varying(20) NOT NULL,
  item_order integer,
  item_desc character varying(2000),
  modifier character varying(9),
  CONSTRAINT pk_vote_item_master PRIMARY KEY (question_id,item_id )
);

CREATE TABLE vote_question_history
(
  question_id character varying(20) NOT NULL,
  question_seq integer NOT NULL,
  emp_id character varying(9) NOT NULL,
  exem_id character varying(20) NOT NULL,
  question_type character varying(2) ,
  answer character varying(200),
  right_answer_status character varying(2),
  creation_date timestamp without time zone DEFAULT (now() at time zone 'Asia/Seoul'),
  CONSTRAINT pk_vvote_question_history PRIMARY KEY (question_id, question_seq, emp_id  )
);