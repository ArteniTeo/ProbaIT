document.addEventListener("DOMContentLoaded", function() {
    const signupForm = document.getElementById("signup-form");

    signupForm.addEventListener("submit", function(event) {
        event.preventDefault();

        // Get form values
        const username = document.getElementById("username").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // Prepare JSON body
        const requestBody = {
            "username": username,
            "email": email,
            "password": password
        };

        // Send POST request
        fetch("http://localhost:8080/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Signup failed");
            }
            return response.json();
        })
        .then(data => {
            localStorage.setItem('loggedUserId', data.id);
            localStorage.setItem('loggedUserUsername', data.username);
            window.location.href = "landing page.html";
        })
        .catch(error => {
            console.error("Signup error:", error);
        });
    });
});
