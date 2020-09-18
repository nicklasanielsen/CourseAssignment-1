/**
 * 
 * @author Nicklas Nielsen
 */

let fetchBtn = document.getElementById("");

fetchBtn.addEventListener('click', function (e) {
    e.preventDefault();
    updateCarsTable();
});

function fetchAllCars() {
    let url = './api/cars/all';

    fetch(url)
            .then(res => res.json())
            .then(data => {
                return data;
            });
}

function updateCarsTable() {
    let cars = fetchAllCars();
    let table = document.getElementById("table_body");

    cars = cars.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.pice}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            </tr>`);

    table.innerHTML = cars.join("");
}