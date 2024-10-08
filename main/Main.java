package main;

import service.IdeaService;

public class Main {

	public static void main(String[] args) {
		System.out.println("아이디어 공모전");
		IdeaService ideaservice = new IdeaService();
		ideaservice.menu();

	}

}
