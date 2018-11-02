/**
 * Created by Asus on 16.04.2017.
 */

var variables = {x: 0, y: 1, z: 2, u: 3, v: 4, w: 5};

var cnst = function (num) {
    return function () {
        return num;
    }
};

var variable = function (line) {
    var tmp = variables[line];
    return function () {
        return arguments[tmp];
    }
};

var abstractOperations = function (operator) {
    return function (firstNum, secondNum) {
        return function() {
            return operator(firstNum.apply(this, arguments), secondNum.apply(this, arguments));
        }
    }
};

var abstractOperation = function (operator) {
    return function (num) {
        return function() {
            return operator(num.apply(this, arguments));
        }
    }
};

var add = abstractOperations(function (firstNum, secondNum) {
    return firstNum + secondNum;
});

var subtract = abstractOperations(function (firstNum, secondNum) {
    return firstNum - secondNum;
});

var multiply = abstractOperations(function (firstNum, secondNum) {
    return firstNum * secondNum;
});

var divide = abstractOperations(function (firstNum, secondNum) {
    return firstNum / secondNum;
});

var negate = abstractOperation(function (num) {
    return -num;
});

var expr = subtract(multiply(cnst(2), variable("x")), cnst(3));

println(expr(5));