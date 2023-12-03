document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/polls")
        .then(response => response.json())
        .then(polls => {
            const pollsContainer = document.getElementById("polls-container");

            polls.forEach(poll => {
                fetch(`http://localhost:8080/options?id=${poll.id}`)
                    .then(response => response.json())
                    .then(options => {
                        const pollElement = createPollElement(poll, options);
                        pollsContainer.appendChild(pollElement);

                        const voteButton = pollElement.querySelector(".vote-button");
                        voteButton.addEventListener("click", function () {
                            handleVote(poll.id, options);
                        });
                    })
                    .catch(error => console.error(`Error fetching options for poll ${poll.id}:`, error));
            });
        })
        .catch(error => console.error("Error fetching polls:", error));

    function createPollElement(poll, options) {
        const pollElement = document.createElement("div");
        pollElement.classList.add("poll");

        const pollElementId = `poll-${poll.id}`;
        pollElement.id = pollElementId;

        pollElement.innerHTML = `
            <h2>${poll.question}</h2>
            <form>
                <ul>
                    ${options.map(option => `<li><input type="${poll.multipleChoice ? 'checkbox' : 'radio'}" name="option" value="${option.id}">${option.optionText}</li>`).join('')}
                </ul>
            </form>
            <button class="vote-button" data-poll-id="${poll.id}">Vote</button>
        `;

        return pollElement;
    }

    function handleVote(pollId, options) {
        // E temporar pana fac login-ul
        const userId = 1;

        const selectedOptions = Array.from(document.getElementById(`poll-${pollId}`).querySelectorAll('input[name="option"]:checked')).map(input => input.value);

        selectedOptions.forEach(optionId => {
            const voteData = {
                user: { id: userId },
                poll: { id: pollId },
                option: { id: optionId }
            };

            fetch("http://localhost:8080/vote", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(voteData)
            })
            .then(response => response.json())
            .then(data => console.log("Vote successful:", data))
            .catch(error => console.error("Error voting:", error));
        });

        alert("Yay, votat cu succes !");
    }
});
