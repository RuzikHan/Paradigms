function AbstractOperation(num) {
    this.num = num;
}

AbstractOperation.prototype.evaluate = function (x, y, z) {
    return this.apply(this.num.evaluate(x, y, z));
};

AbstractOperation.prototype.toString = function () {
    return this.num.toString() + ' ' + this.operator;
};

AbstractOperation.prototype.prefix = function () {
    return '(' + this.operator + ' ' + this.num.prefix() + ')';
};

function AbstractOperations(firstNum, secondNum) {
    this.firstNum = firstNum;
    this.secondNum = secondNum;
}

AbstractOperations.prototype.evaluate = function (x, y, z) {
    return this.apply(this.firstNum.evaluate(x, y, z), this.secondNum.evaluate(x, y, z));
};

AbstractOperations.prototype.toString = function () {
    return this.firstNum.toString() + ' ' + this.secondNum.toString() + ' ' + this.operator;
};

AbstractOperations.prototype.prefix = function () {
    return '(' + this.operator + ' ' + this.firstNum.prefix() + ' ' + this.secondNum.prefix() + ')';
};

function Const(num) {
    this.value = num;
}

Const.prototype.evaluate = function (x, y, z) {
    return this.value;
};

Const.prototype.toString = function () {
    return '' + this.value;
};

Const.prototype.prefix = function () {
    return this.toString();
};

function Variable(line) {
    this.name = line;
}

Variable.prototype.evaluate = function (x, y, z) {
    switch (this.name) {
        case 'x':
            return x;
        case 'y':
            return y;
        case 'z':
            return z;
    }
};

Variable.prototype.toString = function () {
    return this.name;
};

Variable.prototype.prefix = function () {
    return this.toString();
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

function Sin(num) {
    AbstractOperation.call(this, num);
    this.operator = 'sin';
}

Sin.prototype = Object.create(AbstractOperation.prototype);

Sin.prototype.apply = function (num) {
    return Math.sin(num);
};

function Cos(num) {
    AbstractOperation.call(this, num);
    this.operator = 'cos';
}

Cos.prototype = Object.create(AbstractOperation.prototype);

Cos.prototype.apply = function (num) {
    return Math.cos(num);
};

function parsePrefix(line) {
    if (line.length == 3 && line[0] == "(" && (line[1] == "0" || line[1] == "x") && line[2] == ")") {
        throw new Error('Missing action');
    }
    var balance = 0;
    for (var i = 0; i < line.length(); i++) {
        if (line[i] == '(') {
            balance++;
        } else if (line[i] == ')') {
            balance--;
        }
        if (balance < 0) {
            throw new Error('No opening parenthesis')
        }
    }
    if (balance > 0) {
        throw new Error('No closing parenthesis');
    } else if (balance < 0) {
        throw new Error('No opening parenthesis');
    }
    line = line.replace(/\(|\)/g, ' ');
    line = line.trim();
    var operations = {  '+': Add,
                        '-': Subtract,
                        '*': Multiply,
                        '/': Divide
    };
    var operation = {   'negate': Negate,
                        'sin': Sin,
                        'cos': Cos
    };
    var symbol = line.split(/\s+/);
    function lineParse() {
        if (symbol.length == 0) {
            throw new Error('No actions');
        }
        var val = symbol.shift();
        if (val in operations) {
            return new operations[val](lineParse(), lineParse());
        } else if (val in operation) {
            return new operation[val](lineParse());
        } else if (/^-?[0-9]+$/.test(val)) {
            return new Const(parseInt(val));
        } else if (/^x$|^y$|^z$/.test(val)) {
            return new Variable(val);
        } else {
            throw new Error('Unexpected symbol ' + val);
        }
    }
    var ans = lineParse();
    if (symbol.length > 0){
        throw new Error('Missing action');
    }
    return ans;
}
