// d)Add a validation to it to check all the parameter must be in numbers except name after submitting and then calculate total net salary

function validateInputs(name, workingDays, basicPay, bonuses) {
    if (name.trim() === "") {
        alert("Employee name must be filled out");
        return false;
    }
    if (isNaN(workingDays) || workingDays <= 0) {
        alert("Working days must be a positive number");
        return false;
    }
    if (isNaN(basicPay) || basicPay <= 0) {
        alert("Basic pay must be a positive number");
        return false;
    }
    if (isNaN(bonuses)) {
        alert("Bonuses must be a valid number");
        return false;
    }
    return true;
}


function calculateGrossSalary(basic, bonus, workingDays) {
    const perDay = basic / 30;
    let grossSalary = perDay * workingDays + bonus;
    return grossSalary;
}

function calculationTax(gross) {

    let taxRate = 0;
    if (gross > 60000)
        taxRate = 0.15;
    else if (gross > 40000)
        taxRate = 0.10;
    else
        taxRate = 0.05;

    return gross * taxRate;
}

function calculateSalary() {
    var employeeName = document.getElementById("employeeName").value;
    var workingDays = parseInt(document.getElementById("workingDays").value);
    var basicPay = parseFloat(document.getElementById("basicPay").value);
    var bonuses = parseFloat(document.getElementById("bonuses").value);
    var grossSalary = calculateGrossSalary(basicPay, bonuses, workingDays);
    var taxDeductions = calculationTax(grossSalary);
    var netSalary = grossSalary - taxDeductions;
    if (!validateInputs(employeeName, workingDays, basicPay, bonuses)) {
        return false;
    }
    document.getElementById("employeeNameResult").innerText = employeeName;
    document.getElementById("grossSalary").innerText = grossSalary.toFixed(2);
    document.getElementById("taxDeductions").innerText = taxDeductions.toFixed(2);
    document.getElementById("netSalary").innerText = netSalary.toFixed(2);
    return false; // Prevent form submission
}