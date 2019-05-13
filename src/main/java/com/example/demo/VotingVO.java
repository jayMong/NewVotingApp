/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo;

import java.io.Serializable;

/**
 * Voting Management VO
 * 
 * @author jh.choi
 * @since 2019.04.21
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자                수정내용
 *  -------    --------    ---------------------------
 *  2019.04.21 jh.choi              최초생성
 * 
 * </pre>
 */

public class VotingVO implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2759752411380285157L;
    
    private long rowCnt;   
	private long startRowNum;    
    private long endRowNum;    
    private long rNum;    
    private String sidx;    
    private String sord;    
    private String searchField;    
    private String searchOper;    
    private String searchString; 
    private String str;  

    private String exem_id;    
    private String exem_nm;    
    private String exem_type;    
    private Integer question_total_count;    
    private String exem_status;    
    private String start_date;     
    private String end_date;   
	private String exec_desc;
    private String modifier;
    private String question_id;
    private String question_nm;
    private Integer question_order;
    private String question_type;
    private String correct_answer;
    private String question_desc;
    private String item_id;
    private String item_nm;
    private Integer item_order;
    private String item_desc;
    private Integer question_seq;
    private String emp_id;
    private String answer;
    private String right_answer_status;
    private String creation_date;
    
    public long getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(long rowCnt) {
		this.rowCnt = rowCnt;
	}
	public long getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(long startRowNum) {
		this.startRowNum = startRowNum;
	}
	public long getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(long endRowNum) {
		this.endRowNum = endRowNum;
	}
	public long getrNum() {
		return rNum;
	}
	public void setrNum(long rNum) {
		this.rNum = rNum;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getExem_id() {
		return exem_id;
	}
	public void setExem_id(String exem_id) {
		this.exem_id = exem_id;
	}
	public String getExem_nm() {
		return exem_nm;
	}
	public void setExem_nm(String exem_nm) {
		this.exem_nm = exem_nm;
	}
	public String getExem_type() {
		return exem_type;
	}
	public void setExem_type(String exem_type) {
		this.exem_type = exem_type;
	}
	public Integer getQuestion_total_count() {
		return question_total_count;
	}
	public void setQuestion_total_count(Integer question_total_count) {
		this.question_total_count = question_total_count;
	}
	public String getExem_status() {
		return exem_status;
	}
	public void setExem_status(String exem_status) {
		this.exem_status = exem_status;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
    public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getExec_desc() {
		return exec_desc;
	}
	public void setExec_desc(String exec_desc) {
		this.exec_desc = exec_desc;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_nm() {
		return question_nm;
	}
	public void setQuestion_nm(String question_nm) {
		this.question_nm = question_nm;
	}
	public Integer getQuestion_order() {
		return question_order;
	}
	public void setQuestion_order(Integer question_order) {
		this.question_order = question_order;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public String getCorrect_answer() {
		return correct_answer;
	}
	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}
	public String getQuestion_desc() {
		return question_desc;
	}
	public void setQuestion_desc(String question_desc) {
		this.question_desc = question_desc;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public Integer getItem_order() {
		return item_order;
	}
	public void setItem_order(Integer item_order) {
		this.item_order = item_order;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public Integer getQuestion_seq() {
		return question_seq;
	}
	public void setQuestion_seq(Integer question_seq) {
		this.question_seq = question_seq;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRight_answer_status() {
		return right_answer_status;
	}
	public void setRight_answer_status(String right_answer_status) {
		this.right_answer_status = right_answer_status;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getExem_link() {
		return exem_link;
	}
	public void setExem_link(String exem_link) {
		this.exem_link = exem_link;
	}
	private String exem_link; 
}
