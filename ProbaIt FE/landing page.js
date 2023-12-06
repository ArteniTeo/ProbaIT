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

        const loggedUserId = localStorage.getItem('loggedUserId');

        const loginButton = document.getElementById("login-button");
        const logoutButton = document.getElementById("logout-button");
        const createPollButton = document.getElementById("create-poll-button");
    
        if (loggedUserId) {
            loginButton.hidden = true;
            logoutButton.hidden = false;
            createPollButton.hidden = false;
        } else {
            loginButton.hidden = false;
            logoutButton.hidden = true;
            createPollButton.hidden = true;
        }
    
        logoutButton.addEventListener("click", function () {
            localStorage.removeItem('loggedUserId');
            localStorage.removeItem('loggedUserUsername');
            
            window.location.href = "landing page.html";
        });

        function createPollElement(poll, options) {
            const pollElement = document.createElement("div");
            pollElement.classList.add("poll");
    
            const pollElementId = `poll-${poll.id}`;
            pollElement.id = pollElementId;
    
            const isCreator = localStorage.getItem('loggedUserId') == poll.user.id;
    
            pollElement.innerHTML = `
                <div class="poll-header">
                    <h2>${poll.question}</h2>
                    ${isCreator ? '<button class="remove-button" data-poll-id="${poll.id}">Remove</button>' : ''}
                </div>
                <form>
                    <ul>
                        ${options.map(option => `<li><input type="${poll.multipleChoice ? 'checkbox' : 'radio'}" name="option" value="${option.id}">${option.optionText}</li>`).join('')}
                    </ul>
                </form>
                <button class="vote-button" data-poll-id="${poll.id}">Vote</button>
            `;
    
            if (isCreator) {
                const removeButton = pollElement.querySelector(".remove-button");
                removeButton.addEventListener("click", function () {
                    handleRemove(poll.id, poll.user.id);
                });
            }
    
            return pollElement;
        }
    
        function handleRemove(pollId, userId) {
            fetch(`http://localhost:8080/poll?pollId=${pollId}&userId=${userId}`, {
                method: "DELETE",
            })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                        console.log(`Poll ${pollId} removed successfully.`);
                    } else {
                        console.error(`Error removing poll ${pollId}.`);
                    }
                })
                .catch(error => console.error("Error removing poll:", error));
        }

    function handleVote(pollId, options) {
        if(localStorage.getItem('loggedUserId') != null) {
            const userId = localStorage.getItem('loggedUserId');

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
        } else {
            alert("Trebuie sa te loghezi inaite de a vota !");
        }
    }
});