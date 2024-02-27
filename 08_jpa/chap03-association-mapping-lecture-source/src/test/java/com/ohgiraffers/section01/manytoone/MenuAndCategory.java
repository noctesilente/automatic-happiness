package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    // 메뉴를 볼 때 중간 객체를 통해 카테고리에 대한 정보를 가진 것들(이름 등등)을 가져옴
    // 메뉴를 볼 때 카테고리 이름도 같이 보게 하고 싶어서 설계
    // MenuAndCategory = Menu 엔티티, category = 카테고리 정보를 가져와서 담는 엔티티
    // 나중에 CategoryVO로 설정해줌

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @JoinColumn(name = "category_code")
    // fk로 쓰인 컬럼의 이름을 쓰기
    @ManyToOne
    // 두 가지 관점을 다 봐야 함
    // 메뉴 전체랑 카테고리 전체는 many to one 관계
    // 하지만 메뉴 한 개에는 카테고리 한 개이므로 List를 써주지 않음
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    public MenuAndCategory() {
    }

    public MenuAndCategory(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuAndCategory{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
