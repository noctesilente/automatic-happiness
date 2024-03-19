/* 02. differences-from-regular-array(일반적인 배열과의 차이점) */

// regular array = 자바에서의 배열을 생각하면 됨

const arr = [
    '홍길동',
    20,
    true,
    null,
    undefined,
    NaN,
    Infinity,
    [],
    {},
    function() {}
];
// 자바스크립트에서는 array list랑 array 구분할 필요 없이 그냥 냅다 다 담고 수정... 등등을 다 할 수 있음

console.log(arr);

/*
  writable - true면 값을 수정할 수 있다. 아니면 읽기만 가능하다.
  enumerable - true면 반복문을 사용해 나열할 수 있다. 그렇지 않다면 반복문 활용 시 포함 안 된다.
  configurable - true면 프로퍼티 삭제가 가능하다. 그렇지 않으면 삭제가 불가능하다.
*/
console.log(Object.getOwnPropertyDescriptors([1, 2, 3]));
// 이런 식으로 나오면 배열
// {
//   '0': { value: 1, writable: true, enumerable: true, configurable: true },
//   '1': { value: 2, writable: true, enumerable: true, configurable: true },
//   '2': { value: 3, writable: true, enumerable: true, configurable: true },
//   length: { value: 3, writable: true, enumerable: false, configurable: false }
// }

