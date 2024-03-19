/* 01. array-and-object-copy(스프레드 문법을 사용한 배열 및 객체 복사) */

/* 1. 배열 복사 */
let arr = [10, 30, 20];
let arrCopy = [...arr];         // 스프레드 문법을 활용한 깊은 복사

console.log(arr);
console.log(arrCopy);
// 둘 다 [ 10, 30, 20 ]

// 완전히 같은지 확인
console.log(arr === arrCopy);
// false
// 들어있는 값은 같은데 실제로는 다름
// 깊은 복사니까 둘이 다름

/* 2. 객체 복사 */
let obj = {name: '홍길동', age: 20, addr: '서울시', hobby: ['축구', '농구']};
let objCopy = {...obj};     // 사본 만들기

/* 추가적으로 나머지 연산자 개념으로 활용도 가능하다. */
// let age = 30;
// let name: = '강감찬';
// let objCopy = {...obj, age, name};
// { name: '강감찬', age: 30, addr: '서울시', hobby: [ '축구', '농구' ] }
// 설정한 30 빼고 나머지가 다 복사돼서 들어옴

console.log(obj);
console.log(objCopy);
// 둘 다 { name: '홍길동', age: 20 }

console.log(obj === objCopy);
// false
// 깊은 복사니까 둘이 다름
// 중괄호면 배열이고 대괄호면 객체

