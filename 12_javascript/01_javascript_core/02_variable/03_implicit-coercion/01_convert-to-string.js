/* 03. implicit-coercion(암묵적 타입 변환) */

/* 01. convert-to-string(문자열 타입으로 변환) */
console.log('===== 문자열 타입으로 변환 =====');

/* 문자열 타입이 아닌 피연산자와 문자열을 연결하면 암묵적으로 변환된다. */
console.log(10 + '20');
// 1020

/* 템플릿 리터럴 방식(백틱키 활용 구문)에서 표현식 삽입은 문자열 타입으로 암묵적으로 변환된다. */
console.log(`10 + 20: ${10 + 20}`);
// 10 + 20: 30

// 다 암묵적으로 문자열로 변환됨
console.log(1 + '');
console.log(NaN + '');
console.log(Infinity + '');
console.log(true + '');
console.log(null + '');
console.log(undefined + '');
// cannot convert a Symbol value to a string
// console.log(Symbol() + '');


console.log({} + '');
// [object Object]
console.log([1, 2] + '');
// 1,2
console.log(function () { } + '');
// function () { }

