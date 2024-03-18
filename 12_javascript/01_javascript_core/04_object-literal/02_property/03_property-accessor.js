/* 03. property-accessor(프로퍼티 접근) */

var dog = {
    name : '뽀삐',
    eat: function(food) {
        console.log(`${this.name}(은)는 ${food}를 맛있게 먹어요.`);
    }
};

/* 1. 마침표 표기법(dot notation) */
console.log(dog.name);
// 뽀삐

dog.eat('고구마');
// 뽀삐(은)는 고구마를 맛있게 먹어요.


/* 2. 대괄호 표기법(square bracket notation) */
console.log(dog['name']);
// 뽀삐

dog['eat']('고구마');
// 뽀삐(은)는 고구마를 맛있게 먹어요.
// 배열이 아니라 프로퍼티에 접근하는 것


// 둘이 같은 건데 굳이 둘 다 배우는 이유는 대괄호 표기법만 가능한 경우가 있기 때문

/* 대괄호 표기법만 가능한 경우 */
var obj = {
    'dash-key': 'dash-value',
    0: 1
}

/* 프로퍼티 키가 식별자 네이밍 규칙을 준수하지 않은 이름일 경우 반드시 대괄호 표기법을 사용한다.(홀따옴표 필수!) */

// 마침표 표기법으로 접근을 하려면
// console.log(obj.dash-key);
// 에러 발생
// _ 랑 $ 외의 특수 문자는 마침표 표기법으로 접근이 안 됨

// console.log(obj.'dash-key');
// unexpected string 이라는 에러 발생

// console.log(obj[dash-key]1);
// 마찬가지로 에러 발생

// 이런 특수한 경우의 프로퍼티일 경우 반드시 대괄호 표기법으로 해줘야 함

// 이렇게 접근해야 함
console.log(obj['dash-key']);

/* 프로퍼티 키가 숫자로 이루어진 경우 홀따옴표를 생략한 대괄호 표기법도 가능하지만 가능하면 홀따옴표를 붙이자! */
// 숫자로 된 프로퍼티일 경우에도 대괄호만 됨
// console.log(obj.0);
// console.log(obj.'0');
console.log(obj[0]);
console.log(obj['0']);
// 둘 다 돌아감
// 하지만 두 번째 경우로 기억하기 -> 대괄호랑 홀따옴표