package de.draegerit.serialcommander.view;

public class ConnectionSettings {

	private String tabText = "unbenannt";

	private String port;
	private int baudrate = 9600;
	private int dataBits = 8;
	private String parity = "none";
	private float stopBits = 1f;
	
	private String datumFormat = "MM.DD.YYYY HH:mm:SS";

	public float getStopBits() {
		return stopBits;
	}

	public void setStopBits(float stopBits) {
		this.stopBits = stopBits;
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}

	public int getDataBits() {
		return dataBits;
	}

	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public String getDatumFormat() {
		return datumFormat;
	}

	public void setDatumFormat(String datumFormat) {
		this.datumFormat = datumFormat;
	}
}
