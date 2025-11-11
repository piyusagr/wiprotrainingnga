// Write a JavaScript function named findMax that takes an array of numbers as a parameter and returns the maximum value in the array.


function findMax(numbers) {
    let max = numbers[0];
    for (let i = 1; i < numbers.length; i++) {
        if (numbers[i] > max) {
            max = numbers[i];
        }
    }
    return max;
}
// Test the function with different arrays
console.log(findMax([3, 5, 7, 2, 8]));
