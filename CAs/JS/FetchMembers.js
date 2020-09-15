/**
 * 
 * @author Nikolaj Larsen
 */
let btn = document.getElementById("reloadMembers");

btn.addEventListener('click', function (e) {
    e.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    let url = 'https://newbiz.nicklasnielsen.dk/CourseAssignment-1/api/groupmembers/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let membersArray = data.map(member => `<tr>
                <td>${member.fullname}</td>
                <td>${member.studentID}</td>
                <td>${member.github}</td>
                </tr>`);

                table.innerHTML = membersArray.join("");
            });
}