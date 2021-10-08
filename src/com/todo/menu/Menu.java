package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< 메뉴 및 명령어 안내 >");
        System.out.println("1. 새로운 항목 추가 : add");
        System.out.println("2. 기존 항목 삭제 : del");
        System.out.println("3. 기존 항목 수정 : edit");
        System.out.println("4. 전체 항목 보기 : ls");
        System.out.println("5. 카테고리 목록 보기 : ls_cate");
        System.out.println("6. 이름 순으로 정렬 : ls_name_asc");
        System.out.println("7. 이름 역순으로 정렬 : ls_name_desc");
        System.out.println("8. 시간 순으로 정렬 : ls_date");
        System.out.println("9. 시간 역순으로 정렬 : ls_date_desc");
        System.out.println("10. 키워드로 검색 : find <keyword>");
        System.out.println("11. 카테고리 키워드로 검색 : find_cate <keyword>");
        System.out.println("12. 종료 : exit (Or press escape key)");
        
    }
    
    public static void prompt() {
    	System.out.print("\n명령어를 입력해 주세요. > ");
    }
    
    
}
