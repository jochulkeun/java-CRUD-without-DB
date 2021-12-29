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
			command = sc.nextLine().trim();

			if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getNowDateStr();
				String title;
				String body;
				System.out.print("제목을 입력해 주세요 :");
				title = sc.nextLine().trim();
				System.out.print("내용을 입력해 주세요 :");
				body = sc.nextLine().trim();

				
				Article article = new Article(id, title, body, regDate );

				System.out.println(article);
				articles.add(article);

				System.out.printf("%d번글이 생성 되었습니다.\n", id);
			}
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.print("게시글이 존재하지 않습니다.\n");
					continue;
				}
				System.out.println("번호  |   제목   | 조회수");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d    |  %s    |%d \n", article.id, article.title,article.Hit);
				}

			}
			if (command.startsWith("article detail ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
					}
				}
				
				if (foundArticle == null) {
					System.out.println("해당글은 존재하지 않습니다.");
				} 
				
					foundArticle.increaseHit();
				
					System.out.printf("번호 : %s \n", foundArticle.id);
					System.out.printf("제목 : %s \n", foundArticle.title);
					System.out.printf("내용 : %s \n", foundArticle.body);
					System.out.printf("날짜 : %s \n", foundArticle.regDate);
					System.out.printf("조회수 : %d \n", foundArticle.Hit);
				
			}
			if (command.startsWith("article delete ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundIndex = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundIndex = article;
						articles.remove(foundIndex);
						System.out.printf("%d 번 글은 삭제되었습니다.\n", id);
						break;
					}
				}
				if (foundIndex == null) {
					System.out.println("해당글은 존재하지 않습니다.");
					continue;
				}
			}
			if (command.startsWith("article modify ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("해당글은 존재하지 않습니다.");
				} else {

					System.out.print("제목을 입력해 주세요 :");
					String title = sc.nextLine().trim();
					System.out.print("내용을 입력해 주세요 :");
					String body = sc.nextLine().trim();

					foundArticle.title = title;
					foundArticle.body = body;

				}
				System.out.printf("%d 번글이 수정되었습니다.\n", id);
			}
		}
	}
}
