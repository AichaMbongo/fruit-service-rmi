// Tab switching logic
function openTab(evt, tabName) {
    // Hide all tabcontent
    document.querySelectorAll('.tabcontent').forEach(tab => tab.style.display = "none");
    // Remove 'active' class from all tablinks
    document.querySelectorAll('.tablink').forEach(tab => tab.classList.remove("active"));
    // Show selected tab and set active
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.classList.add("active");
}

// Helper to show alerts
function showAlert(id, status, message) {
    const alertDiv = document.getElementById(id);
    if (!message) {
        alertDiv.style.display = "none";
        return;
    }
    alertDiv.textContent = message;
    alertDiv.className = "alert " + (status === "success" ? "success" : "error");
    alertDiv.style.display = "block";
}

// Helper to show results
function showResult(id, data) {
    document.getElementById(id).textContent = data;
}

// Add Fruit
document.getElementById("addFruitForm").onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams();
    params.append("action", "addFruit");
    params.append("fruitName", form.fruitName.value);
    params.append("price", form.price.value);

    const response = await fetch("/fruitService", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: params
    });
    let text = await response.text();
    try {
        let json = JSON.parse(text);
        showAlert("alertAdd", json.status, json.message);
        showResult("resultAdd", JSON.stringify(json, null, 2));
    } catch {
        showAlert("alertAdd", "success", "Operation completed.");
        showResult("resultAdd", text);
    }
};

// Update Fruit Price
document.getElementById("updateFruitForm").onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams();
    params.append("action", "updateFruit");
    params.append("fruitName", form.fruitName.value);
    params.append("newPrice", form.newPrice.value);

    const response = await fetch("/fruitService", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: params
    });
    let text = await response.text();
    try {
        let json = JSON.parse(text);
        showAlert("alertUpdate", json.status, json.message);
        showResult("resultUpdate", JSON.stringify(json, null, 2));
    } catch {
        showAlert("alertUpdate", "success", "Operation completed.");
        showResult("resultUpdate", text);
    }
};

// Delete Fruit
document.getElementById("deleteFruitForm").onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams();
    params.append("action", "deleteFruit");
    params.append("fruitName", form.fruitName.value);

    const response = await fetch("/fruitService", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: params
    });
    let text = await response.text();
    try {
        let json = JSON.parse(text);
        showAlert("alertDelete", json.status, json.message);
        showResult("resultDelete", JSON.stringify(json, null, 2));
    } catch {
        showAlert("alertDelete", "success", "Operation completed.");
        showResult("resultDelete", text);
    }
};

// Calculate Cost
document.getElementById("calculateCostForm").onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams();
    params.append("action", "calculateCost");
    params.append("fruitName", form.fruitName.value);
    params.append("quantity", form.quantity.value);

    const response = await fetch("/fruitService?" + params, {
        method: "GET"
    });
    let text = await response.text();
    try {
        let json = JSON.parse(text);
        if(json.status && json.status === "error") {
            showAlert("alertCalculate", "error", json.message);
        } else {
            showAlert("alertCalculate", "success", "Cost calculated successfully.");
        }
        showResult("resultCalculate", JSON.stringify(json, null, 2));
    } catch {
        showAlert("alertCalculate", "success", "Operation completed.");
        showResult("resultCalculate", text);
    }
};

// Print Receipt
document.getElementById("printReceiptForm").onsubmit = async function(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams();
    params.append("action", "printReceipt");
    params.append("fruitName", form.fruitName.value);
    params.append("quantity", form.quantity.value);
    params.append("amountGiven", form.amountGiven.value);
    params.append("cashierId", form.cashierId.value);

    const response = await fetch("/fruitService?" + params, {
        method: "GET"
    });
    let text = await response.text();
    // Try to parse JSON, otherwise assume plain text receipt
    try {
        let json = JSON.parse(text);
        if(json.status && json.status === "error") {
            showAlert("alertPrint", "error", json.message);
            showResult("resultPrint", JSON.stringify(json, null, 2));
        } else {
            showAlert("alertPrint", "success", "Receipt generated successfully.");
            showResult("resultPrint", JSON.stringify(json, null, 2));
        }
    } catch {
        showAlert("alertPrint", "success", "Receipt generated successfully.");
        showResult("resultPrint", text);
    }
};

async function fetchFruits() {
    const response = await fetch("/fruitService?action=listFruits");
    const alertDiv = document.getElementById("alertList");
    const tableBody = document.querySelector("#fruitTable tbody");
    tableBody.innerHTML = "";
    alertDiv.style.display = "none";
    try {
        const data = await response.json();
        if (data.fruits && data.fruits.length > 0) {
            data.fruits.forEach(fruit => {
                const row = document.createElement("tr");
                row.innerHTML = `<td>${fruit.name}</td><td>${fruit.price}</td>`;
                tableBody.appendChild(row);
            });
        } else {
            alertDiv.className = "alert info";
            alertDiv.textContent = "No fruits available.";
            alertDiv.style.display = "block";
        }
    } catch (err) {
        alertDiv.className = "alert error";
        alertDiv.textContent = "Failed to load fruit list.";
        alertDiv.style.display = "block";
    }
}
// Initial fetch of fruits on page load 
document.addEventListener("DOMContentLoaded", function() {
    fetchFruits();
    // Set default tab
    document.querySelector(".tablink").click();
});
// Add event listener for tab links