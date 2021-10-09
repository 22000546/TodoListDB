package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		//TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			
			case "help":
				Menu.displaymenu();
				break;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listAllCategories(l);
				break;

			case "ls_name_asc":
				System.out.println("이름 순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("이름 역순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("시간 순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("시간 역순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "comp":
				int id = sc.nextInt();
				TodoUtil.completeItem(l, id);
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("해당하는 명령어가 존재하지 않습니다. 다시 입력해주세요.");
				System.out.println("명령어 설명을 보기 원하신다면 help를 입력해주세요.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
