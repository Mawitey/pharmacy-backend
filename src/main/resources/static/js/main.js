document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("refillForm");
    const submitBtn = document.getElementById("submitBtn");

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const message = document.getElementById("message");
        const name = document.getElementById("name").value;
        const phone = document.getElementById("phone").value;
        const prescription = document.getElementById("prescription").value;


        submitBtn.disabled = true;
        submitBtn.textContent = "Submitting...";
        message.innerText = ""; // clear previous message

        fetch("/api/refills", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, phone, prescription})
        })
            .then(response => {
                if (!response.ok) throw new Error("Server error");
                return response.text(); // backend returns ID
            })
            .then(id => {
                message.innerText = "Refill submitted successfully. Confirmation #: " + id;
                message.style.color = "green";

                submitBtn.disabled = false;
                submitBtn.textContent = "Submit Request";
                form.reset();
            })
            .catch(() => {
                message.innerText = "Error submitting refill request.";
                message.style.color = "red";

                submitBtn.disabled = false;
                submitBtn.textContent = "Submit Request";
            });
    });
});
