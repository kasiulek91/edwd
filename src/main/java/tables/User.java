package tables;

import java.util.Date;

public class User {
	private String userId;
	private Date userCreatedAt;
	private String userPhone;
	private String userSkype;
	private String userEmailDomain;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Date userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserSkype() {
		return userSkype;
	}

	public void setUserSkype(String userSkype) {
		this.userSkype = userSkype;
	}

	public String getUserEmailDomain() {
		return userEmailDomain;
	}

	public void setUserEmailDomain(String userEmailDomain) {
		this.userEmailDomain = userEmailDomain;
	}

}
