package article.Article;

public class Article {

	public int id;
	public String title;
	public String body;
	public String regDate;
	public int Hit;

	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.Hit = Hit;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + ", regDate=" + regDate + "]";
	}

	public void increaseHit() {
		Hit++;
	}




}
