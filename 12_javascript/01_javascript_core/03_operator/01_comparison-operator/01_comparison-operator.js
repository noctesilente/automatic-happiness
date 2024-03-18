/* 기본적인 연산자는 java와 다르지 않다.(추가적이거나 다른 부분만 다룰 예정) */

/* 01. comparison operator */

/* 동등/동일(일치) 비교 연산자 */

/* 1. 숫자 1, 문자 '1', true 비교 */
console.log(`1 == 1: ${1 == 1}`);
console.log(`1 == true: ${1 == true}`);
console.log(`1 == '1': ${1 == '1'}`);

// 문자열과 숫자열은 원래 같다고 나오지 않는데 여기서는 같다고 나옴
// 동등하다고 해석해야 함
// 1 == 1: true
// 1 == true: true
// 1 == '1': true
// 자료형이 다른 건 동일한 게 아니라 동등한 것

// 자료형까지 일치(동일)한지는 '==='으로 비교한다.
console.log(`1 === '1': ${1 === '1'}`);
// 1 === '1': false

console.log(`0 == false: ${0 == false}`);
console.log(`0 === false: ${0 === false}`);
// 0 == false: true
// 0 === false: false


/* 2. NaN은 자신과 일치하지 않는 유일한 값이다. */
console.log(`NaN == NaN: ${NaN == NaN}`);
console.log(`NaN === NaN: ${NaN === NaN}`);
// NaN == NaN: false
// NaN === NaN: false

/* NaN인지 판별하기 위해서는 Number에서 제공하는 isNaN 함수를 활용해야 한다. */
console.log(`Number.isNaN(NaN): ${Number.isNaN(NaN)}`);

/* 3. 일치하지 않는 값 비교 */
console.log(`1 != '1': ${1 != '1'}`);
console.log(`1 !== '1': ${1 !== '1'}`);
// 1 != '1': false
// 1 !== '1': true

// 동등과 동일, == / ===, != / !== 주의