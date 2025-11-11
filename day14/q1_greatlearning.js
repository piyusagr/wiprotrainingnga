// Write a JavaScript function named checkOddEven that takes a number as a parameter and logs whether the number is odd or even to the console.

// Requirements:

// Define a function named checkOddEven that takes a parameter number. 

// Log the result to the console.Your goal is to implement the checkOddEven function and test it with different numbers

function checkOddEven(number) {
    if (number % 2 === 0) {
        console.log(number + " is even.");
    } else {
        console.log(number + " is odd.");
    }
}

// Test the function with different numbers
checkOddEven(4);  // Output: 4 is even.
checkOddEven(7);  // Output: 7 is odd.
checkOddEven(0);  // Output: 0 is even.
checkOddEven(-3); // Output: -3 is odd.