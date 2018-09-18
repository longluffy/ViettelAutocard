package com.vt.dto;

public class NapTheDTO {
	private NapTheMethodEnum serviceType;
	private String soThueBao;
	private String maTheCao;
	
	public NapTheDTO(NapTheMethodEnum serviceType, String soThueBao, String maTheCao) {
		super();
		this.serviceType = serviceType;
		this.soThueBao = soThueBao;
		this.maTheCao = maTheCao;
	}
	public NapTheMethodEnum getServiceType() {
		return serviceType;
	}
	public void setServiceType(NapTheMethodEnum serviceType) {
		this.serviceType = serviceType;
	}
	public String getSoThueBao() {
		return soThueBao;
	}
	public void setSoThueBao(String soThueBao) {
		this.soThueBao = soThueBao;
	}
	public String getMaTheCao() {
		return maTheCao;
	}
	public void setMaTheCao(String maTheCao) {
		this.maTheCao = maTheCao;
	}
	
	
}
