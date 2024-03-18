/* 02. differences-from-regular-function(일반 함수와의 차이점들) */

function Student(name, age) {
    this.name = name;
    this.age = age;
    this.getInfo = function () {
        return `${this.name}(은)는 ${this.age}세 입니다.`;
    }
}

/* 
  일반 함수와 생성자 함수의 특별한 형식적 차이는 없다.
  (첫 문자를 대문자로 기술하려 구별하는 노력 정도)
  new 연산자 없이 호출하면 일반 함수로 동작한다.
*/

// new 안 씀!
const student = Student('강감찬', 35);
// console.log(student);
// undefined


/*
  생성자 함수가 new 연산자 없이 호출되는 것을 방지하기 위해서 ES6에서는 new.target을 지원한다.
  (new 연산자 없이 호출해도 new를 붙여주게 해서 빌트인 함수로 만들기)
*/
console.log(typeof Number('1'))
// number
// Number = 일반 함수가 아니라 빌트인 함수
// new를 안 붙였는데도 객체를 생성하는 함수 = 빌트인 함수 

function Dog(name, age) {

    console.log('new target: ', new.target);
    if (!new.target) {                  // new 없이 함수 호출 시 true가 되게 하는 구문
        return new Dog(name, age);      // new 연산자와 함께 생성자 함수를 재귀 호출하여 생성된 인스턴스를 반환한다.
    }

    this.name = name;
    this.age = age;
}

const dog1 = Dog('뽀삐', 3);
console.log(dog1);
// new target:  undefined
// undefined
// undefined = falsy -> 그래서 위에서 !new.target
// 붙이고 하면
// new target:  [Function: Dog]
// Dog { name: '뽀삐', age: 3 }


const dog2 = new Dog('뽀삐', 3);
console.log(dog2);
// new target:  [Function: Dog]
// Dog { name: '뽀삐', age: 3 }

/* 제공되는 빌트인 함수들: Object, String, Number, Boolean, Date, Regex, ... */
