package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.Article.Article;

public class App {
	private static List<Article> articles;

	public App() {
		articles = new ArrayList<>();
	}

	public void start() {
		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {

			String command;
			System.out.print("명령어)");
			command = sc.nextLine().trim();

			if (command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDateStr();
				String title;
				String body;
				System.out.print("제목을 입력해 주세요 :");
				title = sc.nextLine().trim();
				System.out.print("내용을 입력해 주세요 :");
				body = sc.nextLine().trim();

				Article article = new Article(id, title, body, regDate, 0);

				System.out.println(article);
				articles.add(article);

				System.out.printf("%d번글이 생성 되었습니다.\n", id);
			}
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.print("게시글이 존재하지 않습니다.\n");
					continue;
				}
				System.out.println("번호 | 조회 |제목 ");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%4d|%4d |%s \n", article.id, article.Hit, article.title);
				}

			}
			if (command.startsWith("article detail ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);


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

				int foundIndex = getArticleIndexbyId(id);

				articles.remove(foundIndex);
				System.out.printf("%d 번 글은 삭제되었습니다.\n", id);

				if (foundIndex == -1) {
					System.out.println("해당글은 존재하지 않습니다.");
					continue;
				}

			}
			if (command.startsWith("article modify ")) {

				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

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

	private int getArticleIndexbyId(int id) {
		int i = 0;

		for (Article article : articles) {

			if (article.id == id) {
				System.out.printf("article.id의 값:%d\n",article.id);
				System.out.printf("id의 값:%d\n",id);
				System.out.printf("i의 값1:%d\n",i);
				return i;
				
			}
			System.out.printf("i의 값2:%d\n",i);
			i++;
		}

		return -1;
	}

	private Article getArticleById(int id) {

//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//
//			if (article.id == id) {
//				return article;
//			}
//		}

		int index = getArticleIndexbyId(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}
}
