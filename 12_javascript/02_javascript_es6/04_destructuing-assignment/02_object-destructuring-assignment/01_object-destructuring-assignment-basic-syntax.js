/* 01. object-destructuring-assignment-basic-syntax(객체 구조 분해 할당 문법) */

let pants = {
    productName: '배기팬츠', 
    color: '검정색', 
    price: 30000,
    getInfo() {
        console.log(this.productName, '좋아!');
    }
};

// let productName = pants.productName;
// let color = pants.color;
// let price = pants.price;

// let {productName, color, price} = pants;
let {color, productName, price, getInfo} = pants;
// 변수 4개를 선언해서 담은 것

console.log(productName, color, price);
// 배기팬츠 검정색 30000

console.log(getInfo);
getInfo();
// 메소드를 호출하는 대상이 없어서 undefined가 뜸
// console.log(getInfo)에서 getInfo 변수를 출력하면 함수 자체가 출력되며, 
// getInfo()를 호출하면 함수를 실행하지만 this가 없기 때문에 undefined가 출력됨


/* 객체 구조분해 할당으로 꺼낸 변수 대신 다른 걸 쓰고 싶다면... */
let{color: co, price: pr, productName: pn} = pants;
console.log(co, pr, pn);
// 검정색 30000 배기팬츠