/**
 * 
 * @author Nicklas Nielsen
 */

let fetchBtn = document.getElementById("fetchBtn");

fetchBtn.addEventListener('click', function (e) {
    fetchAllCars();
});

function fetchAllCars() {
    let url = './api/cars/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let cars = data.map(car => `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    </tr>`);

                table.innerHTML = cars.join("");
            });
}