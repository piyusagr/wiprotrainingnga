// Repeated Character

// Problem

// Topic Help
// getProgramInputs() - is an inbuilt custom javascript function which we have created that automatically gets the input from the test cases & stores it as an array (No need to write code to get input from the user)

// Instructions: Write a function repeatCharacter(character, count) that takes a character and a number as input and returns a string with the character repeated the specified number of times.

// Hints:

// Declare a variable result and initialize it as an empty string.
// Use a for loop to iterate count times.
// In each iteration, append the character to the result string.
// Return the result string as the output of the function.

function repeatCharacter(character, count) {
    let result = "";
    for (let i = 0; i < count; i++) {
        result += character;
    }
    return result;
}
console.log(repeatCharacter("a", 5)); // Output: "aaaaa"
console.log(repeatCharacter("b", 3)); // Output: "bbb"
console.log(repeatCharacter("*", 4)); // Output: "****"
