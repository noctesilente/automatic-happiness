/* 01. spread-syntax(스프레드 문법, 전개 문법) */

let arr = [10, 20, 30];
// 요소 나열 
console.log(...arr);
// 10 20 30

console.log(`가장 큰 값: ${Math.max(10, 20, 30)}`);
// 가장 큰 값: 30
console.log(`가장 큰 값: ${Math.max(...arr)}`);
// 배열 안에 있는 걸 하나씩 꺼내서 이런 식으로 넣어줄 수 있음
// 가장 큰 값: 30


let arr1 = [10, 30, 20];
let arr2 = [100, 300, 200];

/* 배열을 결합(concat)해서 하나의 배열로 만듦 */
console.log([...arr1,...arr2]);
// [ 10, 30, 20, 100, 300, 200 ]

console.log([10, ...arr1, -1, ...arr2, 2]);
// [
//     10,  10,  30,  20,
//     -1, 100, 300, 200,
//      2
//   ]

// 배열로 안 만들고 그냥 붙이기
console.log(...arr1,...arr2);
// 10 30 20 100 300 200

console.log(`가장 큰 값: ${Math.max(...arr1, ...arr2)}`);
// 가장 큰 값: 300

/* 문자열일 때 */
let str = "JavaScript";
console.log(...str);
// J a v a S c r i p t

console.log([...str]);              // 스프레드 연산자로 문자 하나씩 들어간 배열을 쉽게 만들 수 있다.
console.log(Array.from(str));       // 무언가를 배열로 바꿀 때 보편적으로 사용하는 배열의 from 메소드
// 둘 다
// [
//   'J', 'a', 'v', 'a',
//   'S', 'c', 'r', 'i',
//   'p', 't'
// ]