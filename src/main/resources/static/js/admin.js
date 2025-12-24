// document.getElementById("header").innerHTML = `
//   <h1>Pharmacy Admin Page</h1>
// `;


document.getElementById("footer").innerHTML = `
  <p>&copy; 2025 My Pharmacy</p>
`;

document.getElementById("logoutBtn").addEventListener("click", () => {
    // If using Spring Security, redirect to /logout
    window.location.href = "/logout";

    // If no Spring Security, simple redirect to index.html
    // window.location.href = "/index.html";
});

document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.querySelector("#refill-table tbody");
    const searchInput = document.getElementById("search-input");

    // Header & Footer injection
    //document.getElementById("header").innerHTML = "<h1>Pharmacy Admin Page</h1>";
    document.getElementById("footer").innerHTML = "<p>&copy; 2025 My Pharmacy</p>";

    let refillRequests = [];

    function renderTable(data) {
        tableBody.innerHTML = "";
        data.forEach(request => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${request.name}</td>
                <td>${request.phone}</td>
                <td>${request.prescription}</td>
                <td>${new Date(request.submittedAt).toLocaleString()}</td>
                <td>
                    <button class="delete-btn" data-id="${request.id}">Delete</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    }


    // Fetch refill requests from backend
    fetch("/api/refills/all")
        .then(res => res.json())
        .then(data => {
            refillRequests = data;
            renderTable(refillRequests);
        })
        .catch(err => console.error(err));

    // Search functionality
    searchInput.addEventListener("input", function () {
        const query = this.value.toLowerCase();
        const filtered = refillRequests.filter(r =>
            r.name.toLowerCase().includes(query) ||
            r.phone.includes(query)
        );
        renderTable(filtered);
    });

    // Delete button logic
    document.addEventListener("click", function (e) {
        if (e.target.classList.contains("delete-btn")) {
            const id = e.target.getAttribute("data-id");
            if (!id) return;
            if (confirm("Are you sure you want to delete this request?")) {
                fetch(`/api/refills/${id}`, { method: "DELETE" })
                    .then(res => {
                        if (res.ok) {
                            alert("Request deleted successfully.");
                            refillRequests = refillRequests.filter(r => r.id != id);
                            renderTable(refillRequests);
                        } else {
                            alert("Failed to delete request.");
                        }
                    })
                    .catch(err => {
                        console.error(err);
                        alert("Error deleting request.");
                    });
            }
        }
    });
});

