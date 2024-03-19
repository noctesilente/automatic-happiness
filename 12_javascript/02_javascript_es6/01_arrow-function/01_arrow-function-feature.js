/* 01. arrow-function-feature(화살표 함수의 특징) */

/* 1. 화살표 함수는 자체적으로 this를 가지지 않는다. (화살표 함수를 호출한 객체의 의미가 아니다.) */
/* 객체의 메소드 안에서 동일한 객체의 프로퍼티를 대상으로 콜백 함수를 적용할 때 사용할 수 있다. */
let theater = {
    store: "강남점",
    titles: ["파묘", "Dune2", "웡카", "가여운 것들", "메이디셈버"],

    showMovieList() {
        console.log(this.store);

        // 화살표 함수
        /* forEach: Array에서 제공하는 메소드로 배열의 요소별로 돌아가면 콜백 함수를 실행하는 함수 */
        this.titles.forEach(
            // this.titles = 호출한 store 가 갖고 있는 titles
            // ["파묘", "Dune2", "웡카", "가여운 것들", "메이디셈버"]
            title => console.log(this.store + ": " + title)
            // 이 this는 더 상위 레벨의 this를 가리키게 하고 싶으면 화살표 함수를 쓰기
            // 더 상위 레벨의 this = this.titles.forEach의 this
        );

        // 강남점: 파묘
        // 강남점: Dune2
        // 강남점: 웡카
        // 강남점: 가여운 것들
        // 강남점: 메이디셈버


        // this.titles.forEach(
        //     // 화살표 함수 말고 익명 함수로 만들어서 하기
        //     function(title) {
        //         console.log(this.store + ": " + title);
        //     }
        // )

        // undefined: 파묘
        // undefined: Dune2
        // undefined: 웡카
        // undefined: 가여운 것들
        // undefined: 메이디셈버
    }
}

theater.showMovieList();
// 강남점


/* 2. 화살표 함수는 new와 함께 호출할 수 없다. */
/* 생성자 함수로 쓰지 않는 일반 함수는 화살표 함수로 만든다.(객체 생성 X) */
// 객체를 생성하기 위함이 아닌 용도가 매우 명확하고 강함
// 그래서 new를 쓸 수 없음
const arrowFunc = () => {};

const normalFunc = function() {};

// new arrowFunc();
// arrow function은 생성자가 아니라는 말과 함께 에러 발생

new normalFunc();
// 실행됨


/* 3. 화살표 함수는 arguments를 지원하지 않는다. */
let test = function () {
    console.log(arguments);

    const arrowFunc = () => console.log(arguments);
    arrowFunc(10, 20);
    // 이거 하면 arrowFunc의 arguments가 아닌 이 함수의 arguments만 나옴
    // 상위 개념만 반환 - 상위 function이 가진 arguments를 반환
}
test(1, 2, 3, 4, 5);
// [Arguments] { '0': 1, '1': 2, '2': 3, '3': 4, '4': 5 }


/*
  화살표 함수는 다른 함수의 인수로 전달되어 콜백 함수로 사용되는 경우가 많다.
  위와 같은 특징들은 콜백 함수 내부의 this가 외부 함수의 this와 다르기 때문에 발생하는 문제를 해결하기 위해
  의도적으로 설계된 것이라 할 수 있다.(arguments도 마찬가지)
  따라서 화살표 함수를 사용할 때는 어느 정도 개념을 정확히 인지하고 써야 한다.
*/
