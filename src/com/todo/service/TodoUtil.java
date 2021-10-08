package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "======== 새로운 항목 추가 ========\n"
				+ "제목을 입력하세요. : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.print("카테고리를 입력하세요. : ");
		category = sc.next();
		
		System.out.print("설명을 입력하세요. : ");
		sc.nextLine();
		desc = sc.nextLine().trim();
				
		System.out.print("마감일자를 입력하세요. : ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		
		System.out.println("항목이 정상적으로 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "======== 기존 항목 삭제 ========\n"
				+ "선택할 항목의 번호를 입력해주세요. : ");
		
		int index = sc.nextInt() - 1;
		
		if(index < 0 || index > l.getSize()) {
			System.out.println("입력하신 번호에 해당하는 항목이 없습니다. ");
			return;
		} else {
			l.deleteItem(l.getItem(index));
			System.out.println("해당 항목이 성공적으로 삭제되었습니다.");
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "======== 기존 항목 수정 ========\n"
				+ "수정하고 싶은 항목의 번호를 입력해주세요. : ");
		int index = sc.nextInt() - 1;
		
		if(index < 0 || index > l.getSize()) {
			System.out.println("입력하신 번호에 해당하는 항목이 없습니다. ");
			return;
		}

		System.out.print("새로운 제목을 입력해주세요. : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.print("새로운 카테고리를 입력하세요. : ");
		String category = sc.next();
		
		System.out.print("새로운 설명을 입력해주세요. : ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.print("새로운 마감일자를 입력하세요. : ");
		String due_date = sc.next();
		
		l.deleteItem(l.getItem(index));
		l.addItem(new TodoItem(new_title, new_description, category, due_date));
		System.out.println("항목이 성공적으로 수정되었습니다.");

	}
	
	public static void findItem(TodoList l, String keyword) {
		
		int num = 0;
		
		for(TodoItem item : l.getList()) {
			if(item.findKeyword(keyword)) {
				System.out.print(l.indexOf(item)+1 + ". ");
				System.out.println("[" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() 
				+ " (" + item.getCurrent_date() + " ~ " + item.getDue_date() + ")");
				num ++;
			}	
		}
		System.out.println("총 " + num + "개의 항목을 찾았습니다.\n");
		
	}
	
	public static void findCategory(TodoList l, String keyword) {
		
		int num = 0;
		
		for(TodoItem item : l.getList()) {
			if(item.findCategory(keyword)) {
				System.out.print(l.indexOf(item)+1 + ". ");
				System.out.println("[" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() 
				+ " (" + item.getCurrent_date() + " ~ " + item.getDue_date() + ")");
				num ++;
			}	
		}
		System.out.println("총 " + num + "개의 항목을 찾았습니다.\n");
		
	}

	public static void listAll(TodoList l) {
		System.out.println("======== 전체 항목 보기 ========");
		for (TodoItem item : l.getList()) {
			System.out.print(l.indexOf(item)+1 + ". ");
			System.out.println("[" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() 
			+ " (" + item.getCurrent_date() + " ~ " + item.getDue_date() + ")");
		}
	}

	public static void listAllCategories(TodoList l) {
		System.out.println("======== 전체 카테고리 보기 ========");
		HashSet<String> categories = new HashSet<>();
		for(TodoItem item : l.getList()) {
			categories.add(item.getCategory());
		}
		int i = 0;
		for(String cate : categories) {
			System.out.print(cate);
			// Other way : hasNext()
			if(i < categories.size() - 1) {
				System.out.print(" / ");
			}
			i ++;
		}
		System.out.println();
		System.out.println("총 " + categories.size() + "개의 카테고리가 등록되어 있습니다.");
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("\n데이터가 todolist.txt에 저장되었습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				String due_date = st.nextToken();
				
				TodoItem i = new TodoItem(title, desc, date, category, due_date);
				l.addItem(i);
				
			}
			br.close();
			System.out.println("todolist.txt 파일의 데이터가 정상적으로 로딩되었습니다.\n");
			
		} catch (FileNotFoundException e) {
			System.out.println("todolist.txt 파일을 정상적으로 찾지 못하였습니다.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
