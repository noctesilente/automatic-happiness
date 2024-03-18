/* 01. short-circuit-evaluation(논리 연산자) */

/* 표현식을 평가하는 도중 평가 결과가 확정된 경우 나머지 평가 과정을 생략하는 것 */


/* 1. OR의 경우 */
console.log('apple' || 'banana');           // true || true 인 셈이다.
// 'apple' = 문자열이 빈 게 아니니까 truthy
// true 또는 true를 한 것

// 하지만 결과로는 true가 나오지 않고 apple이 나옴
// || 를 쓸 경우, 앞에 true 가 나오면 뒤를 볼 필요 없이 앞만 남음
// 결과가 true인지 false인지를 나오게 하는 요인이 뭔지를 판별해서 그게 출력됨

console.log('' || 'banana');
// 결과: banana

console.log('apple' || false);
// 결과: apple

console.log(false || false);
// 결과: false

// true || true, true || false, false || true, false || false
// 출력: 앞의 값, 앞의 값, 뒤의 값, false


/* 2. AND의 경우 */
console.log('apple' && 'banana');
// 결과: banana
console.log(false && 'banana');
console.log('apple' && false);
console.log(false && false);
// 모두 결과: false


// true && true
// 앞뒤 둘 다 true기 때문에 뒤의 값으로 판별이 됨! 그래서 뒤의 값이 나옴
// true && false, false && true, false && false
// 판단의 근거가 뒤, 앞, 앞
// 뒤가 false, 앞이 false, 앞이 false => 그래서 이것들이 자리에 남게 됨


var num1 = 1;

// if (num1 % 2 == 0) {
//     console.log('짝수입니다.');
// } else {
//     console.log('홀수입니다.');
// }

/* 단축 평가를 활용하여 같은 결과를 얻을 수 있다. */
num1 % 2 == 0 && console.log('짝수입니다.');
// false니까 뒤를 볼 필요 없이 false
// console.log를 한 게 아니니까 실행 안 됨
num1 % 2 == 0 || console.log('홀수입니다.');
// false니까 뒤를 봐야 돼서 뒤를 본 후 출력
// 판단 조건: 뒤
// 홀수입니다.

var num2 = 2;
num2 % 2 == 0 && console.log('짝수입니다.');
num2 % 2 == 0 || console.log('홀수입니다.');
// 짝수입니다.

// 왼쪽이 맞으면 출력되게 하는 게 && 연산자
// 왼쪽이 틀리면 출력되게 하는 게 || 연산자

