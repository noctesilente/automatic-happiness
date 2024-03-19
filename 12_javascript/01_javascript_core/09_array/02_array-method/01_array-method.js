/* 01. array-method(배열 메소드) */

const foodList = ['물회', '삼계탕', '냉면', '수박', '물회'];

/* indexOf */
console.log(`foodList.indexOf('물회'): ${foodList.indexOf('물회')}`);
console.log(`foodList.indexOf('삼겹살'): ${foodList.indexOf('삼겹살')}`);
// foodList.indexOf('물회'): 0
// foodList.indexOf('삼겹살'): -1 -> 없으면

/* includes */
console.log(`foodList.includes('물회'): ${foodList.includes('물회')}`);
console.log(`foodList.includes('삼겹살'): ${foodList.includes('삼겹살')}`);
// foodList.includes('물회'): true
// foodList.includes('삼겹살'): false


const chineseFood = ['짜장면', '짬뽕', '탕수육'];
// 자바였으면 애초에 크기를 지정하고 시작하니까 여기에 더 뭘 추가할 수가 없음
// 자바스크립트는 가능

/* push */
chineseFood.push('팔보채');
chineseFood.push('양장피');
console.log(`push 후: ${chineseFood}`);
// push 후: 짜장면,짬뽕,탕수육,팔보채,양장피
// push 한 순서대로 뒤에 추가


/* pop */
// 맨 마지막이 없어짐
// chineseFood.pop();

// 이렇게 하면 제거한 것을 여기에 반환도 시켜줌
// 제거하는 김에 반환값을 담아놓고 제거 
console.log(`chineseFood.pop(): ${chineseFood.pop()}`);
// chineseFood.pop(): 양장피
console.log(`pop 후: ${chineseFood}`);
// pop 후: 짜장면,짬뽕,탕수육,팔보채


const chickenList = ['양념치킨', '후라이드', '파닭'];

/* unshift */
// push랑 반대로 앞에 쌓음
console.log(`chickenList.unshift(): ${chickenList.unshift('간장치킨')}`);
console.log(`chickenList.unshift(): ${chickenList.unshift('마늘치킨')}`);
// chickenList.unshift(): 4
// chickenList.unshift(): 5

console.log(`unshift 후: ${chickenList}`);
// unshift 후: 마늘치킨,간장치킨,양념치킨,후라이드,파닭


/* shift */
// 앞을 제거
console.log(`chickenList.shift(): ${chickenList.shift()}`);
// chickenList.shift(): 마늘치킨

console.log(`shift 후: ${chickenList}`);
// shift 후: 간장치킨,양념치킨,후라이드,파닭


/* concat: 두 개 이상의 배열을 결합하여 새로운 배열을 반환(결합 순서의 유의미) */
const idol1 = ['트와이스', '레드벨벳', '블랙핑크'];
const idol2 = ['뉴진스', '르세라핌'];
const idol3 = ['에스파', '아이브'];

console.log(`idol1 기준으로 idol2 배열을 concat: ${idol1.concat(idol2)}`);
// idol1 기준으로 idol2 배열을 concat: 트와이스,레드벨벳,블랙핑크,뉴진스,르세라핌

// ES6 방식
// ES6 할 때 스프레드 연산자 배울 때 다시 설명
console.log(`idol1 기준으로 idol2 배열을 concat: ${[...idol1,...idol2]}`);
// idol1 기준으로 idol2 배열을 concat: 트와이스,레드벨벳,블랙핑크,뉴진스,르세라핌

console.log(`idol3 기준으로 idol1, idol2 배열을 concat: ${idol3.concat(idol1, idol2)}`);
// idol3 기준으로 idol1, idol2 배열을 concat: 에스파,아이브,트와이스,레드벨벳,블랙핑크,뉴진스,르세라핌


/* slice: 배열의 요소를 선택한 후 복사하기 - 원본에 영향 X */
/* splice: 배열의 index 위치의 요소 제거 및 추가 */
const front = ['HTML', 'CSS', 'JavaScript', 'Vue'];

console.log(`front.slice(1, 3): ${front.slice(1, 3)}`);
// 1번부터 3-1인 2까지를 잘라낸다는 뜻
// front.slice(1, 3): CSS,JavaScript

console.log(`front: ${front}`);
// front: HTML,CSS,JavaScript,Vue
// 원본은 그대로 유지

/* splice(인텍스, 제거할 길이, 추가할 값1, 추가할 값2, ...) */
console.log(`front.splice(3, 1, "JDBC"): ${front.splice(3, 1, "JDBC")}`);
// front.splice(3, 1, "JDBC"): Vue
console.log(`front: ${front}`);
// front: HTML,CSS,JavaScript,JDBC


/* join: 배열을 우리가 원하는 구분자와 함께 결합하여 문자열로 반환 */
const snackList = ['사탕', '초콜릿', '젤리', '껌'];
console.log(`snackList.join(): ${snackList.join()}`);
// snackList.join(): 사탕,초콜릿,젤리,껌
console.log(`snackList.join('/'): ${snackList.join('/')}`);
// snackList.join('/'): 사탕/초콜릿/젤리/껌
console.log(`snackList: ${snackList}`);
// snackList: 사탕,초콜릿,젤리,껌

// front에서 가공 처리 후 back에 넘겨줄 때 씀