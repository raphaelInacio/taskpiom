package br.com.moip.webhooks;

public class WebHooksURL implements Comparable<WebHooksURL> {

	private String url;
	private String code;
	private int quantdade;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		WebHooksURL other = (WebHooksURL) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getUrl();
	}

	public int compareTo(WebHooksURL arg) {
		if (arg.getQuantdade() > this.getQuantdade())
			return 1;
		if (arg.getQuantdade() < this.getQuantdade())
			return -1;
		else {
			return 0;
		}
	}

}
