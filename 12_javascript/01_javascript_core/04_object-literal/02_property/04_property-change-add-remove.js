/* 04. property-change-add-remove(프로퍼티 값 변경, 추가 삭제) */

var dog = {
name: '뽀삐'
};

/* 1. 프로퍼티 수정 */
dog.name = '두부';
console.log(dog);
// { name: '두부' }

/* 2. 프로퍼티 동적 추가 */
dog.age = 3;
console.log(dog);
// { name: '두부', age: 3 }

/* 3. 프로퍼티 동적 삭제 */
delete dog.age;
console.log(dog);
// { name: '두부' }

// 자바스크립트는 존재하지 않는 프로퍼티 제거도 에러 발생조차 하지 않는다.
// 이것을 보완한 것이 타입스크립트 
// delete dog.something;
console.log(dog);
// { name: '두부' }