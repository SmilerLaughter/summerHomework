package domain;

public class Permission {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + permissionId;
		result = prime * result
				+ ((permissionName == null) ? 0 : permissionName.hashCode());
		result = prime * result
				+ ((permissionNote == null) ? 0 : permissionNote.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (level != other.level)
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (permissionId != other.permissionId)
			return false;
		if (permissionName == null) {
			if (other.permissionName != null)
				return false;
		} else if (!permissionName.equals(other.permissionName))
			return false;
		if (permissionNote == null) {
			if (other.permissionNote != null)
				return false;
		} else if (!permissionNote.equals(other.permissionNote))
			return false;
		return true;
	}


	private int permissionId;
	private String permissionName;
	private String permissionNote;
	private String method;
	private int level;
	
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Permission() {
		// TODO Auto-generated constructor stub
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionNote() {
		return permissionNote;
	}

	public void setPermissionNote(String permissionNote) {
		this.permissionNote = permissionNote;
	}
	

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", pernissionName="
				+ permissionName + ", permissionNote=" + permissionNote + "]";
	}
	

	public Permission(String permissionName, String permissionNote,
			String method) {
		super();
		this.permissionName = permissionName;
		this.permissionNote = permissionNote;
		this.method = method;
	}

	
}
