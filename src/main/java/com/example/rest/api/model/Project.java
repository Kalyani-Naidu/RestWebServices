package com.example.rest.api.model;

public class Project {
	
	
	private int empId;
	private int projectCode;
	private String projectName;

	public Project(int empId, int projectCode, String projectName) {
		super();
		this.empId = empId;
		this.projectCode = projectCode;
		this.projectName = projectName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "ProjectEntity [empId=" + empId + ", projectCode=" + projectCode + ", projectName=" + projectName + "]";
	}

	public Project() {
		super();
	}

}
