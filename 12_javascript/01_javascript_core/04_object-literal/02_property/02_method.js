/* 02. method */
/* 자바 스크립트의 함수는 객체이다. 함수는 값으로 취급할 수 있고 프로퍼티 값으로 사용할 수도 있다. */

var name = '달마시안';

var dog = {
    name : '뽀삐',
    eat: function(food) {
        // anonymous function - 이름이 없는 함수
        // console.log(`${name}(은)는 ${food}를 맛있게 먹어요.`);     // this.이 없으면 전역변수를 뜻한다.
        // this. 안 붙이면 전역변수 -> 위에서 언급한 달마시안이 들어감
        // 달마시안(은)는 고구마를 맛있게 먹어요.
        // 잘 먹었네

        console.log(`${this.name}(은)는 ${food}를 맛있게 먹어요.`);     // 메소드에서의 'this'는 메소드를 호출한 객체

        return '잘 먹었네';
    }
};

console.log(dog.eat);
// [Function: eat]

console.log(dog.eat());
// ReferenceError: name is not defined
// 실행은 되는데 이름이 정의가 되지 않았다고 뜸

console.log(dog.eat('Food1', 'Food2'));
// 뽀삐(은)는 Food1를 맛있게 먹어요.
// 잘 먹었네
// 두 개 넣으면 앞 하나만 뜸

console.log(dog.eat('고구마'));
// 뽀삐(은)는 고구마를 맛있게 먹어요.
// 잘 먹었네
// 사실상 console.log('잘 먹었네')가 되는 것