/* 03. nested-function(중첩함수) */

/*
  함수 내부에 정의된 함수를 중첩 함수 또는 내부 함수라고 한다.
  중첩 함수를 포함하는 함수를 외부 함수라고 한다.
  일반적으로 중첩 함수는 자신을 포함하는 외부 함수를 돕는 헬퍼(helper) 함수의 역할을 한다. 
*/

// 외부에서는 inner 함수를 호출 못 하기 때문에 자기만 쓸 거면 이런 식으로 사용 

function outer()  {
    var outerVal = '외부함수';

    function inner() {
        var innerVal = '내부함수';
        console.log(outerVal, innerVal);
    }
    inner();
}

outer();    // outer를 호출해야 실행됨.
// 외부함수 내부함수