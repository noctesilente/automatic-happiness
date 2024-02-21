package com.ohgiraffers.section01.xml;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("======== 마이바티스 동적 SQL ========");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose(when, otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim(where, set) 확인하기");
            System.out.println("9. 종료하기");
            System.out.print("메뉴를 선택하세요: ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    /* 1-1 */
                    ifSubMenu();
                    break;
                case 2:
                    chooseSubMenu();
                    break;
                case 3:
                    forEachSubMenu();
                    break;
                case 4:
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다");
                    return;
            }
        } while (true);
    }

    /* 1-2 */
    private static void ifSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("======== if 서브 메뉴 ========");
            System.out.println("1. 원하는 금액대에 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력해 주세요: ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.findMenuByPrice(inputPrice());
                    break;
                case 2:
                    menuService.searchMenu(inputSearchCriteria());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    /* 1-3 */
    private static int inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 가격대의 최대 금액을 입력해 주세요: ");
        return sc.nextInt();
    }

    private static SearchCriteria inputSearchCriteria() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색 기준을 입력해주세요(name or category): ");
        String condition = scanner.nextLine();
        System.out.print("검색어를 입력해주세요: ");
        String value = scanner.nextLine();

        return new SearchCriteria(condition, value);
    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("======== choose 서브 메뉴 ========");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력하세요: ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuBySupCategory(inputSupCategory());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static SearchCriteria inputSupCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("상위 분류를 입력해 주세요(식사, 음료, 디저트): ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }

    public static void forEachSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("======== foreach 서브 메뉴 ========");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력하세요: ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuByRandomMenuCode(generateRandomMenuCodeList());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    /* 설명. 1부터 21까지의 5개의 중복되지 않는 번호 생성해 List에 쌓아 반환하는 메소드 */
    // 다른 데에서 접근이 안 되도록 private
    private static List<Integer> generateRandomMenuCodeList() {
        // 1부터 21까지의 난수를 생성해서 5개가 쌓이도록 난수 뽑기
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) {
            int temp = (int) (Math.random() * 21) + 1;
            set.add(temp);
        }

        /* 설명. set을 List 형태로 바꿔서 오름파순 정렬까지 진행 */
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        // 로그 찍기
        System.out.println("랜덤 수 생성한 메소드가 반환하는 list: " + list);

        return list;
    }
}
