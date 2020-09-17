let joke = document.getElementById("fetch_Joke");
let randomJoke = document.getElementById("randomJokeBtn");
let allJokes = document.getElementById("getAllJokesBtn");

joke.addEventListener('submit', function (e) {
    e.preventDefault();
    let jokeByID = document.getElementById("jokeID").value;
    fetchJokeByID(jokeByID);
});

randomJoke.addEventListener('click', function (e) {
    e.preventDefault();
    fetchRandomJoke();
});

allJokes.addEventListener('click', function (e) {
    e.preventDefault();
    fetchAllJokes();
});

function fetchJokeByID(id) {
    let url = './api/jokes/id/' + id;
    let selectedJoke = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                if (data == null) {
                    selectedJoke.innerHTML = "<tr><td>" + id + "</td><td>Der findes ikke en joke på dette id</td><td></td><td></td></tr>";
                } else {
                    let sj = `<tr>
                    <td>${data.id}</td>
                    <td>${data.joke}</td>
                    <td>${data.reference}</td>
                    <td>${data.type}</td>
                    </tr>`;

                    selectedJoke.innerHTML = sj;
                }
            });

}

function fetchRandomJoke() {
    let url = './api/jokes/random';
    let selectedJoke = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let sj = `<tr>
                    <td>${data.id}</td>
                    <td>${data.joke}</td>
                    <td>${data.reference}</td>
                    <td>${data.type}</td>
                    </tr>`;

                selectedJoke.innerHTML = sj;
            });
}

function fetchAllJokes() {
    let url = './api/jokes/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let jokesArray = data.map(joke => `<tr>
                <td></td>
                <td>${joke.joke}</td>
                <td>${joke.reference}</td>
                <td>${joke.type}</td>
                </tr>`);

                table.innerHTML = jokesArray.join("");
            });
}