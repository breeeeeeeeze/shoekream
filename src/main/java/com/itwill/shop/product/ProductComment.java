package com.itwill.shop.product;

import java.sql.Date;

public class ProductComment {

	private int pc_no;
	private int p_no;
	private int pc_mark;
	private String writer;
	private String content;
	private Date regDate;
	
	public int getPc_no() {
		return pc_no;
	}
	public void setPc_no(int pc_no) {
		this.pc_no = pc_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getPc_mark() {
		return pc_mark;
	}
	public void setPc_mark(int pc_mark) {
		this.pc_mark = pc_mark;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
