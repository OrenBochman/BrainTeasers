/**
 * reduce the calls in  fibonarchy 
 * I store previous results in an array
 */
var myArray = [0,1];
var yourself = {
    
    fibonacci : function(n) {
        var a,b;
        if(typeof myArray[n] !== 'undefined'){
            return  myArray[n];
        } else if (typeof myArray[n-1] !== 'undefined') {
            a=myArray[n-1];
        } else {
            a=myArray[n-1]=this.fibonacci(n - 1);
        }
        return a + this.fibonacci(n-2);
    }
};