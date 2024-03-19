/* 03. function-parameters(함수의 매개변수에서 구조 분해 할당) */

let exampleProduct = {
    items: ["Coffee", "Tea"],
    producer: "John Doe"
};

function displayProduct({producer = "Unknown", items = [], width = 10, height = 20}) {
    console.log(producer);
    console.log(items);
    console.log(width);
    console.log(height);
}

displayProduct(exampleProduct);

// John Doe
// [ 'Coffee', 'Tea' ]
// 10
// 20

/* 함수 호출 시 객체를 던지면 내부적으로 매개변수로 적힌 구조분해 할당이 일어난다. */
// let {producer = "Unknown", items = [], width =10, height = 20} = exampleProduct;
