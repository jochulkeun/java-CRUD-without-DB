package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.Article.Article;

public class makeArticle {

	public static void main(String[] args) {

		List<Article> articles = new ArrayList<>();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {
			String command;
			System.out.print("명령어)");
			command = sc.nextLine();

			if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String title;
				String body;
				System.out.print("제목을 입력해 주세요 :");
				title = sc.nextLine().trim();
				System.out.print("내용을 입력해 주세요 :");
				body = sc.nextLine().trim();

				Article article = new Article(id, title, body);

				System.out.println(article);
				articles.add(article);

				System.out.printf("%d번글이 생성 되었습니다.\n", id);
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
			if (command.startsWith("article detail ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size() ; i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("해당글은 존재하지 않습니다.");
				} else {
					System.out.printf("번호 : %s \n", foundArticle.id);
					System.out.printf("제목 : %s \n", foundArticle.title);
					System.out.printf("내용 : %s \n", foundArticle.body);
				}
			}
			if (command.startsWith("article delete ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundIndex = null;

				for (int i = 0; i < articles.size()  ; i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundIndex = article;
						articles.remove(foundIndex);
						System.out.printf("%d 번 글은 삭제되었습니다.\n",id);
						break;
					}
				}
				if (foundIndex == null) {
					System.out.println("해당글은 존재하지 않습니다.");
					continue;
				} 
			}
		}
	}
}
