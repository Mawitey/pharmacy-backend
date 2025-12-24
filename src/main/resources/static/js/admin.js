document.addEventListener("DOMContentLoaded", () => {

    const tableBody = document.querySelector("tbody");
    const searchInput = document.getElementById("searchInput");

    let allData = [];

    function renderTable(data) {
        tableBody.innerHTML = "";

        data.forEach(item => {
            const row = document.createElement("tr");

            row.innerHTML = `
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.phone}</td>
        <td>${item.prescription}</td>
        <td>${item.timestamp}</td>
      `;

            tableBody.appendChild(row);
        });
    }

    // Load data
    fetch("/api/refills/all")
        .then(res => res.json())
        .then(data => {
            allData = data;
            renderTable(allData);
        });

    // Search
    searchInput.addEventListener("input", () => {
        const value = searchInput.value.toLowerCase();

        const filtered = allData.filter(item =>
            item.name.toLowerCase().includes(value) ||
            item.phone.toLowerCase().includes(value) ||
            item.prescription.toLowerCase().includes(value) ||
            String(item.id).includes(value)
        );

        renderTable(filtered);
    });

});
