/* 01. [[Prototype]](프로토입 객체) */

/* 모든 객체는 프로토타입 객체에 접근할 수 있다. */

const user = {
    activate: true,
    login: function() {
        console.log('로그인 되었습니다.');
    }
};

console.log(user.__proto__.constructor === Object);
// true

console.log(user.__proto__ === Object.prototype);
// true


const user2 = {
    activate: false,
    login: function() {
        console.log('로그인 실패되었습니다.');
    }
}

console.log(user2.__proto__);
// [Object: null prototype] {}

const student = {
    passion: true
};

console.log(student.__proto__);
// [Object: null prototype] {}

// 자바 스크립트의 상속
// 부모를 자신의 프로토타입에 담아서 프로토타입은 자연스럽게 자신한테 주기 때문에 상속받음
// 프로토타입에 user2 넣어서 상속받기
student.__proto__ = user2;              // 부모 객체를 자신의 [[Prototype]]에 담으면 상속의 개념이 된다.
// 이제 student는 login을 쓸 수 있게 됨
student.login();
// 로그인 실패되었습니다.


/* 프로토타입 체인 */
const greedyStudent = {
    class: 1502,
    __proto__: student
}

console.log(greedyStudent.activate);       // user2에서 상속
// false
console.log(greedyStudent.passion);        // student에서 상속
// true