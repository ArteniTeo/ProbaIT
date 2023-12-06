document.addEventListener("DOMContentLoaded", function () {
    const createPollForm = document.getElementById("create-poll-form");
    const optionsContainer = document.getElementById("options-container");
    let optionCount = 2;

    createPollForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const question = document.getElementById("question").value;
        const multipleChoice = document.getElementById("multiple-choice").value === "true";
        const userId = localStorage.getItem('loggedUserId');

        if (question && userId) {
            const pollRequestBody = {
                "user": { "id": userId },
                "question": question,
                "multipleChoice": multipleChoice
            };

            fetch("http://localhost:8080/poll", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(pollRequestBody)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Poll creation failed");
                }
                return response.json();
            })
            .then(pollData => {
                console.log("Poll creation successful:", pollData);

                for (let i = 1; i <= optionCount; i++) {
                    const optionText = document.getElementById(`option${i}`).value;
                    if (optionText.trim() !== "") {
                        const optionRequestBody = {
                            "poll": { "id": pollData.id },
                            "optionText": optionText
                        };

                        fetch("http://localhost:8080/option", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(optionRequestBody)
                        })
                        .then(response => {
                            if (!response.ok) {
                                console.error(`Option ${i} creation failed`);
                            }
                            return response.json();
                        })
                        .then(optionData => {
                            console.log(`Option ${i} creation successful:`, optionData);
                        })
                        .catch(error => {
                            console.error(`Option ${i} creation error:`, error);
                        });
                    }
                }

                window.location.href = "landing page.html";
            })
            .catch(error => {
                console.error("Poll creation error:", error);
            });
        } else {
            alert("Please enter a question and make sure you are logged in.");
        }
    });

    document.getElementById("add-option-button").addEventListener("click", function () {
        optionCount++;
        const newOption = createOptionField(optionCount);
        optionsContainer.appendChild(newOption);
    });

    function createOptionField(index) {
        const newOption = document.createElement("div");
        newOption.classList.add("option");
        newOption.innerHTML = `
            <label for="option${index}">Option ${index}:</label>
            <input type="text" id="option${index}" name="option${index}" required>
            <button class="remove-option-button" type="button">Remove</button>
        `;

        const removeOptionButton = newOption.querySelector(".remove-option-button");
        removeOptionButton.addEventListener("click", function () {
            optionsContainer.removeChild(newOption);
            optionCount--;

            updateOptionNumbering();
        });

        return newOption;
    }

    function updateOptionNumbering() {
        const optionElements = optionsContainer.querySelectorAll('.option');
        optionElements.forEach((optionElement, index) => {
            const label = optionElement.querySelector('label');
            const input = optionElement.querySelector('input');
            
            if (label) {
                label.setAttribute("for", `option${index + 1}`);
            }

            if (input) {
                input.id = `option${index + 1}`;
                input.name = `option${index + 1}`;
            }
        });
    }
});
