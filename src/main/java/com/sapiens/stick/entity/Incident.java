package com.sapiens.stick.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.opencsv.bean.CsvBindByName;


@Entity
@Table(name = "incidents")
public class Incident {
	
	public Incident() {
		
	}
	
	@Override
	public String toString() {
		return "Incident [id=" + id + ", component=" + component + ", status=" + status + ", description=" + description
				+ ", customer=" + customer + ", priority=" + priority + ", processor=" + processor + ", irt=" + irt
				+ "]";
	}

	@Id
	@GeneratedValue(generator = "incrementor")
	@GenericGenerator(name = "incrementor", strategy = "increment")
	@CsvBindByName(column = "id")
	private Long id;
	
	@Column(name = "component")
	@CsvBindByName(column = "Component")
	private String component;
	
	@Column(name = "status")
	@CsvBindByName(column = "Status")
	private String status;
	
	@Column(name = "description")
	@CsvBindByName(column = "Description")
	private String description;
	
	@Column(name = "customer")
	@CsvBindByName(column = "Customer")
	private String customer;
	
	@Column(name = "priority")
	@CsvBindByName(column = "Priority")
	private String priority;
	
	@Column(name = "processor")
	@CsvBindByName(column = "Processor")
	private String processor;
	
	/*@Column(name = "Date")
	private Date mptPlannedEndDate; */
	
	@Column(name = "irt")
	private int irt;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	/*public Date getMptPlannedEndDate() {
		return mptPlannedEndDate;
	}

	public void setMptPlannedEndDate(Date mptPlannedEndDate) {
		this.mptPlannedEndDate = mptPlannedEndDate;
	} */

	public int getIrt() {
		return irt;
	}

	public void setIrt(int irt) {
		this.irt = irt;
	}
	public Incident(Long id, String component, String status, String desc,
					String customer,String processor, String priority, int irt) {
		this.id = id;
		this.component = component;
		this.status = status;
		this.description = desc;
		this.customer = customer;
		this.processor = processor;
		this.priority = priority;
		this.irt = irt;
	}
	
	
}
