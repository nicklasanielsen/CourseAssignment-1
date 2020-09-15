let btn = document.getElementById("reloadMembers");

btn.addEventListener('click', function(e) {
    e.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    let url = 'https://www.newbiz.nicklasnielsen.dk/api/groupmembers/all';
    let allMembers = document.getElementById("table_body");

    let count = 1;

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let membersArray = data.map(member => `<tr>
                <td>${count}</td>
                <td>${member.name}</td>
                <td>${member.studentID}</td>
                <td>${member.github}</td>
                </tr>`,
                count++);

                allMembers.innerHTML = membersArray.join("");

            });
}