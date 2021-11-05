package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.Article.Article;

public class makeArticle {

	public static void main(String[] args) {

		List<Article> articles = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		int id = 0;

		while (true) {
			String command;
			System.out.print("명령어)");
			command = sc.nextLine();

			if (command.equals("article write")) {

				String title;
				String body;
				System.out.print("제목을 입력해 주세요 :");
				title = sc.nextLine().trim();
				System.out.print("내용을 입력해 주세요 :");
				body = sc.nextLine().trim();
				id++;
				System.out.printf("%d번글이 생성 되었습니다.\n", id);

				Article article = new Article(id, title, body);

				System.out.println(article);
				articles.add(article);

			}
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.print("게시글이 존재하지 않습니다.");
				}
				System.out.println("번호  |   제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d    |  %s\n", article.id, article.title);
				}
			}
		}
	}
}
