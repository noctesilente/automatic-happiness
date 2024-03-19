/* 02. various-usage(다양한 사용법) */

let shirts = {
    productName: '베이직 셔츠'  
};

/* 기본 값 설정을 객체 구조 분해 할당으로 한 번에 처리 */
let {productName: productName2 = "어떤 상품", color: color2 = "어떤 색상", price: price2 = 0} = shirts;
console.log(`productName: ${productName2}, color: ${color2}, price: ${price2}`);
// productName: 베이직 셔츠, color: 어떤 색상, price: 0

/* 프로퍼티가 많은 객체에서 원하는 정보만 추출하는 경우 객체 구조 분해 할당을 자주 쓴다. */
let pants = {
    productName: '배기팬츠',
    color: '검정색',
    price: 25000
};

/* rest parameter(...)로 나머지 요소를 한 번에 가져올 수도 있다.(새로운 객체가 나머지 프로퍼티를 지님) */
let {productName, ...r} = pants;
// rest 말고 아무거나 써도 되는 듯 

console.log(`productName: ${productName}`);
// productName: 배기팬츠

console.log(`rest: ${r}`);
// rest: [object Object]
// 문자열이 결합되면서 이런 문제 발생
console.log(r);
// { color: '검정색', price: 25000 }

console.log(`color: ${r.color}`);
// color: 검정색
console.log(`price: ${r.price}`);
// price: 25000
