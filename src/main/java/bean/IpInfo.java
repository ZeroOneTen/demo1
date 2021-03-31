package bean;

public class IpInfo {
	
	public IpInfo(String ipAddress, int port, String location,String anonymousType, String type, String confirmTime) {
		this(ipAddress, port, location, anonymousType, type, confirmTime, null);
	}

	public IpInfo(String ipAddress, int port, String location,String anonymousType, String type, String confirmTime, String responseSpeed) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.location = location;
		this.anonymousType = anonymousType;
		this.type = type;
		this.confirmTime = confirmTime;
		this.responseSpeed = responseSpeed;
	}
	// IP地址
	private String ipAddress;
	// port端口
	private int port;
	// 位置
	private String location;
	// 匿名度
	private String anonymousType;
	// 类型
	private String type;
	// 最后验证时间
	private String confirmTime;
	// 响应速度
	private String responseSpeed;
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAnonymousType() {
		return anonymousType;
	}

	public void setAnonymousType(String anonymousType) {
		this.anonymousType = anonymousType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getResponseSpeed() {
		return responseSpeed;
	}

	public void setResponseSpeed(String responseSpeed) {
		this.responseSpeed = responseSpeed;
	}

	@Override
	public boolean equals(Object anthor) {
		if (this == anthor) {
			return true;
		}
		if (anthor == null || getClass() != anthor.getClass()) {
			return false;
		}

		IpInfo ipInfo = (IpInfo) anthor;
		return (this.ipAddress.equals(ipInfo.ipAddress)
				&& this.port == ipInfo.port
				&& this.location.equals(ipInfo.location)
				&& this.anonymousType.equals(ipInfo.anonymousType)
				&& this.type.equals(ipInfo.type) && this.confirmTime.equals(ipInfo.confirmTime))
				&& this.responseSpeed.equals(ipInfo.responseSpeed);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash
				+ (this.ipAddress != null ? this.ipAddress.hashCode() : 0);
		hash = 89 * hash + this.port;
		hash = 89 * hash
				+ (this.location != null ? this.location.hashCode() : 0);
		hash = 89
				* hash
				+ (this.anonymousType != null ? this.anonymousType.hashCode()
						: 0);
		hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
		hash = 89 * hash
				+ (this.confirmTime != null ? this.confirmTime.hashCode() : 0);
		hash = 89
				* hash
				+ (this.responseSpeed != null ? this.responseSpeed.hashCode()
						: 0);
		return hash;
	}

	@Override
	public String toString() {
		return "IpInfo [ipAddress=" + ipAddress + ", port=" + port + ", location=" + location + ", anonymousType="
				+ anonymousType + ", type=" + type + ", confirmTime=" + confirmTime + ", responseSpeed=" + responseSpeed
				+ "]";
	}
	
	
}
