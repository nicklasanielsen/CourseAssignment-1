let getAllPersonsBtn = document.getElementById("Reload_Members_Submit");

getAllPersonsBtn.addEventListener('click', (e) => {
    e.preventDefault();
    fetchAllPersons();
});

function fetchAllPersons() {
    let url = 'https://www.newbiz.nicklasnielsen.dk/api/groupmembers/all';
    let allPersons = document.getElementById("table_body");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.name}</td><td>${x.studentID}</td><td>${x.github}</td></tr>`);
//                  let newArray = data.map(x => `<tr><th>${x.name}</th></tr><tr>
//                  <th>${x.studentID}</th></tr><tr><th>${x.github}</th></tr>`);
                allPersons.innerHTML =
//                    `<th>Name</th><th>studentID</th><th>github</th>
//                    ${newArray.join("")}`;
                        `<table>
                    <thead><th>Name</th><th>Phone</th></thead>
                    ${newArray.join("")}
                </table>`;
            });
}