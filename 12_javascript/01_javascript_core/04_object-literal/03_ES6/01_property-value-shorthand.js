/* 01. property-value-shorthand(프로퍼티 값 단축 구문) */

var id = 'p-0001';
var price = 30000;

var product = {
    id: id,
    price: price
}

console.log(product);
// { id: 'p-0001', price: 30000 }
// 변수 명과 프로퍼티 키가 두 번 일치를 하면 이렇게 두 번 적을 바에는 그냥 한 번 적을 수 있음


/*
  ES6에서는 프로퍼티 값으로 변수를 사용하는 경우
  변수 이름과 프로퍼티 키가 동일한 이름일 때 프로퍼티 키를 생략할 수 있다.
  프로퍼티 키는 변수 이름과 일치되게 자동으로 생성된다. 
*/
var product2 = {id, price};
console.log(product2);
// { id: 'p-0001', price: 30000 }
// id라는 변수를 id라는 프로퍼티 키에 담으라는 것을 축약해서 말하고 있음
