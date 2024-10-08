package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.IdeaDAO;
import dto.IdeaDTO;

public class IdeaService {
	IdeaDAO ideadao = IdeaDAO.getInstance();

	public void menu() {
		while (true) {
			System.out.println("1. 제안하기");
			System.out.println("2. 수정하기");
			System.out.println("3. 삭제하기");
			System.out.println("4. 전체보기");
			System.out.println("5. 검색하기");
			System.out.println("6. 종료");
			System.out.print("번호 입력 >> ");

			Scanner in = new Scanner(System.in);
			int selNum = in.nextInt();
			if (selNum == 1) {
				add();
			} else if (selNum == 2) {
				mod();
			} else if (selNum == 3) {
				del();
			} else if (selNum == 4) {
				list();
			} else if (selNum == 5) {
				search();
			} else if (selNum == 6) {
				break;
			}

		}

	}

	private void add() {
		IdeaDTO idea = new IdeaDTO();
		Scanner in = new Scanner(System.in);
		System.out.println("아이디어 제안");
		System.out.println("작성자 이름을 입력하세요");
		String name = in.nextLine();
		idea.setName(name);
		System.out.println("제목을 입력하세요");
		String title = in.nextLine();
		idea.setTitle(title);
		System.out.println("설명을 입력하세요");
		String memo = in.nextLine();
		idea.setMemo(memo);
		ideadao.insert(idea);

	}

	private void mod() {

	}

	private void del() {
		Scanner in = new Scanner(System.in);
		System.out.println("삭제할 아이디어 리스트를 출력합니다.");
		ArrayList<IdeaDTO> idea = ideadao.selectNumTitle();

		for (IdeaDTO numTitle : idea) {
			System.out.println(numTitle.toString());
		}

		System.out.println("삭제할 아이디어 번호를 입력하세요");
		int delNum = in.nextInt();
		ideadao.delete(delNum);
		System.out.println("삭제되었습니다.");

	}

	private void list() {
		System.out.println("아이디어 전체보기");
		ArrayList<IdeaDTO> idea = ideadao.selectAll();

		for (IdeaDTO all : idea) {
			System.out.println(all.toString());
		}

	}

	private void search() {
		

	}

}
