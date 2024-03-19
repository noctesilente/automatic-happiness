/* 01. array-destructuring-assignment-basic-syntax(배열 구조 분해 할당 문법) */

let nameArr = ["Gildong", "hong"];
// let firstName = nameArr[0];
// let lastName = nameArr[1];
// console.log(firstName, ' ', lastName);

// -> 이런 식으로 해줄 수 있음
let [firstName, lastName] = nameArr;
console.log(firstName, lastName);
// Gildong hong


/* 반환 값이 배열인 메소드를 활용한 구조분해 할당 */
// let [firstName2, lastName2] = 'Saimdang Shin'.split(' ');
let [firstName2, lastName2] = 'Saimdang Shin'.match(/[a-z]+/gi);
console.log(firstName2, lastName2);
// Saimdang Shin


/* 구조분해 할당 시 쉼표를 활용하여 불필요한 배열 요소를 버릴 수도 있다. */
let [firstName3, , lastName3] = ['firstName', 'middleName', 'lastName'];
console.log(firstName3, lastName3);
// firstName lastName