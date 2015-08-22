package br.com.moip.webhooks;

public class WebHooksCode implements Comparable<WebHooksCode> {
	
	private String code;
	private int quantdade;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantdade() {
		return quantdade;
	}

	public void setQuantdade(int quantdade) {
		this.quantdade = quantdade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		WebHooksCode other = (WebHooksCode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public int compareTo(WebHooksCode arg) {
		if (arg.getQuantdade() > this.getQuantdade())
			return 1;
		if (arg.getQuantdade() < this.getQuantdade())
			return -1;
		else {
			return 0;
		}
	}

}
