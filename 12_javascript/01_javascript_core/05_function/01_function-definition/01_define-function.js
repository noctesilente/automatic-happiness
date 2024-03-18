/* 01. define-function */
/* function-declaration(함수 선언문) */

function hello(name) {
    return `${name}님 안녕하세요!`;
}
// 함수만 독립적으로 선언한 것이고 어디에 대입된 것이 아니니까 세미콜론 사용 X

console.log(hello('홍길동'));
// 홍길동님 안녕하세요!


/* function-expression(함수 표현식) */
/*
  자바 스크립트의 함수는 객체 타입의 값으로 값의 설정을 갖는 객체를 일급 객체라고 한다.
  함수는 일급 객체이므로 함수 리터럴로 생성한 함수 객체를 변수에 할당할 수 있다.
*/

var hello2 = function(name) {
    return `${name}님 안녕하세요!`;
};
// 변수에 대입되는 값이니까 보통 마지막에 세미콜론을 붙임

console.log(hello2('홍길동'));
// 홍길동님 안녕하세요!