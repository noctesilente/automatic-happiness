/* 05. symbol-and-object */

/* symbol 타입 */
/*
  symbol은 ES6에서 추가된 7번째 타입으로 변경 불가능한 원시 타입의 값이다.
  다른 값과 중복되지 않는 유일무이한 값으로 이름 충돌 위험이 없는 객체의 
  유일한 프로퍼티 키를 만들기 위해 사용한다.
  symbol 이외의 원시 값은 리터럴을 통해 생성하지만 symbol은 Symbol 함수를 통해
  호출해 생성한다.
*/

var key1 = 'key';
var key2 = 'key';
console.log(key1);
console.log(key2);

/* 리터럴 객체 생성 */
// 아무런 속성도 없고 기능도 없는 객체
var obj = {};

// 대괄호는 객체의 속성에 접근하는 것 -> 처음 만들 때는 속성이 없음 -> 없는 속성에 접근하면 그 속성을 만들어줌
// 담겨 있는 값으로 바꿔서 실행해주기 때문에 key1이나 key2나 'key'로 바뀌어서 되는 것
obj[key1] = 'value1';   // => {key: 'value1'}
obj[key2] = 'value2';   // => {key: 'value2'}

console.log(obj);

// symbol 사용

var key1 = Symbol('key');
var key2 = Symbol('key');
console.log(key1);
console.log(key2);

var obj = {};

obj[key1] = 'value1';   // => {key: 'value1'}
obj[key2] = 'value2';   // => {key: 'value2'}

console.log(obj);

// 객체에 같은 값을 지녔지만 다른 프로퍼티 
// 똑같은 값인데 구분하고 싶을 때 symbol 사용
// 모양이 아무리 똑같이 생겨도 따로 생성이 됨!
