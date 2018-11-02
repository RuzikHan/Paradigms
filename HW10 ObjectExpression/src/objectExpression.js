/**
 * Created by Asus on 23.04.2017.
 */
function AbstractOperation(num) {
    this.num = num;
}

AbstractOperation.prototype.evaluate = function () {
    return this.apply(this.num.evaluate.apply(this.num, arguments));
};

AbstractOperation.prototype.toString = function () {
    return this.num.toString() + ' ' + this.operator;
};

function AbstractOperations(firstNum, secondNum) {
    this.firstNum = firstNum;
    this.secondNum = secondNum;
}

AbstractOperations.prototype.evaluate = function () {
    return this.apply(this.firstNum.evaluate.apply(this.firstNum, arguments), this.secondNum.evaluate.apply(this.secondNum, arguments));
};

AbstractOperations.prototype.toString = function () {
    return this.firstNum.toString() + ' ' + this.secondNum.toString() + ' ' + this.operator;
};

function Const(num) {
    this.value = num;
}

Const.prototype.evaluate = function () {
    return this.value;
};

Const.prototype.toString = function () {
    return '' + this.value;
};

function Variable(line) {
    this.name = line;
}

Variable.prototype.evaluate = function () {
    switch (this.name) {
        case 'x':
            return arguments[0];
        case 'y':
            return arguments[1];
        case 'z':
            return arguments[2];
    }
};

Variable.prototype.toString = function () {
    return this.name;
};

function Add(firstNum, secondNum) {
    AbstractOperations.call(this, firstNum, secondNum);
    this.operator = '+';
}

Add.prototype = Object.create(AbstractOperations.prototype);

Add.prototype.apply = function (firstNum, secondNum) {
    return firstNum + secondNum;
};

function Subtract(firstNum, secondNum) {
    AbstractOperations.call(this, firstNum, secondNum);
    this.operator = '-';
}

Subtract.prototype = Object.create(AbstractOperations.prototype);

Subtract.prototype.apply = function (firstNum, secondNum) {
    return firstNum - secondNum;
};

function Multiply(firstNum, secondNum) {
    AbstractOperations.call(this, firstNum, secondNum);
    this.operator = '*';
}

Multiply.prototype = Object.create(AbstractOperations.prototype);

Multiply.prototype.apply = function (firstNum, secondNum) {
    return firstNum * secondNum;
};

function Divide(firstNum, secondNum) {
    AbstractOperations.call(this, firstNum, secondNum);
    this.operator = '/';
}

Divide.prototype = Object.create(AbstractOperations.prototype);

Divide.prototype.apply = function (firstNum, secondNum) {
    return firstNum / secondNum;
};

function Negate(num) {
    AbstractOperation.call(this, num);
    this.operator = 'negate';
}

Negate.prototype = Object.create(AbstractOperation.prototype);

Negate.prototype.apply = function (num) {
    return -num;
};

function Sinh(num) {
    AbstractOperation.call(this, num);
    this.operator = 'sinh';
}

Sinh.prototype = Object.create(AbstractOperation.prototype);

Sinh.prototype.apply = function (num) {
    return (Math.exp(num) - Math.exp(-num)) / 2;
};

function Cosh(num) {
    AbstractOperation.call(this, num);
    this.operator = 'cosh';
}

Cosh.prototype = Object.create(AbstractOperation.prototype);

Cosh.prototype.apply = function (num) {
    return (Math.exp(num) + Math.exp(-num)) / 2;
};