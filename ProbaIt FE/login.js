document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    const loginButton = document.getElementById("login-button");

    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const usernameOrEmailInput = document.getElementById("usernameOrEmail");
        const passwordInput = document.getElementById("password");

        const usernameOrEmail = usernameOrEmailInput.value.trim();
        const password = passwordInput.value;

        if (usernameOrEmail && password) {

            if (isValidEmail(usernameOrEmail)) {
                const loginData = {
                    email: usernameOrEmail,
                    password: password
                };

                loginUser(loginData);
            } else {
                const loginData = {
                    username: usernameOrEmail,
                    password: password
                };

                loginUser(loginData);
            }
            
        } else {
            alert("Please enter a username or email and a password.");
        }
    });

    function isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function loginUser(loginData) {
        fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        })
        .then(response => response.json())
        .then(data => {
            console.log("Login successful:", data);
            localStorage.setItem('loggedUserId', data.id);
            localStorage.setItem('loggedUserUsername', data.username);
            window.location.href = "landing page.html";
        })
        .catch(error => {
            console.error("Error logging in:", error);
        });
    }
});
