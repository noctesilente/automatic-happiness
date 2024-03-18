/* 02. method-shorthand(메소드 단축) */

var dog = {
    name : '뽀삐',
    eat: function(food) {
        console.log(`${food}를 먹는다.`);
    }
}

dog.eat('바나나');
// 바나나를 먹는다.

// 익명 함수의 프로퍼티 키가 결국에는 함수의 이름이 됨 -> eat이라는 함수가 됨 
// 일반적인 프로퍼티보다는 그냥 메소드 형태로 쓰자 -> 메소드 단축

/* ES6 이후부터는 메소드를 정의할 때 콜론(:)과 function 키워드를 생략한 축약 표현을 사용할 수 있다. */
var dog2 = {
    name : '뽀삐',
    eat(food) {
        // : function 생략
        console.log(`${food}를 먹는다.`);
    }
}
// 마치 eat이라는 이름을 가진 함수처럼 작성됨
// 내부는 위로 돌아감
// 이 dog2라는 객체는 두 개의 프로퍼티가 있다고 알고 있으면 됨 -> 함수 이름처럼 보이지만 다름

dog.eat('사과');
// 사과를 먹는다.
