package dto;

public class IdeaDTO {
	private int num = 0;
	private String title = null;
	private String memo = null;
	private String name = null;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IdeaDTO [num=" + num + ", title=" + title + ", memo=" + memo + ", name=" + name + "]";
	}

}
