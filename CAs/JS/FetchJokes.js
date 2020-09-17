let btn = document.getElementById("fetch_Joke");

btn.addEventListener('submit', function(e){
    e.preventDefault();
    let jokeByID = document.getElementById("jokeID");
    fetchJokeByID(jokeByID.value);
});

let randomBtn = document.getElementById("randomJokeBtn");

randomBtn.addEventListener('click',  function(e){
    e.preventDefault();
    fetchRandomJoke();
});

function fetchJokeByID(id){
    let url = 'http://newbiz.nicklasnielsen.dk/CourseAssignment-1/api/jokes/id/' + id;
    let selectedJoke = document.getElementById("table_body");
    
    fetch(url)
                .then(res => res.json())
                .then(data => {
                    let sj = data.map(j => `<tr>
                    <td>${j.joke}</td>
                    <td>${j.reference}</td>
                    <td>${j.type}</td>
                    </tr>`);
                
                    selectedJoke.innerHTML = sj.join("");
                
                });
    
}

function fetchRandomJoke(){
    let url = 'http://newbiz.nicklasnielsen.dk/CourseAssignment-1/api/jokes/random';
    let selectedJoke = document.getElementById("table_body");
    
    fetch(url)
                .then(res => res.json())
                .then(data => {
                    let sj = data.map(j => `<tr>
                    <td>${j.joke}</td>
                    <td>${j.reference}</td>
                    <td>${j.type}</td>
                    </tr>`);
                
                    selectedJoke.innerHTML = sj.join("");
                
                });
}

let btn = document.getElementById("getAllJokesBtn");

btn.addEventListener('click', function(e) {
    e.preventDefault();
    fetchAllJokes();
});

function fetchAllJokes() {
    let url = 'http://newbiz.nicklasnielsen.dk/CourseAssignment-1/api/jokes/all';
    let allJokes = document.getElementById("table_body");
    
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let jokeArray = data.map(j => `<tr>
                <td>${j.joke}</td>
                <td>${j.reference}</td>
                <td>${j.type}</td>
                </tr>`);

                allJokes.innerHTML = jokeArray.join("");

            });
}