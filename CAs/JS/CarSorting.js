/**
 * 
 * @author Mathias Nielsen
 */
let sort1 = document.getElementById("sortByYear");
let sort2 = document.getElementById("sortByModel");
let sort3 = document.getElementById("sortByMake");
let sort4 = document.getElementById("sortByPrice");

sort1.addEventListener('click', function (e) {
    e.preventDefault();
    sortByYear();
});

sort2.addEventListener('click', function (e) {
    e.preventDefault();
    sortByModel();
});

sort3.addEventListener('click', function (e) {
    e.preventDefault();
    sortByMake();
});

sort4.addEventListener('click', function (e) {
    e.preventDefault();
    sortByPrice();
});

function sortByYear() {
    let cars = fetchAllCars();
    let table = document.getElementById("table_body");

    let sortedByYear = cars.sort(function (a, b) {
        if (a.year < b.year) {
            return -1;
        }
        if (a.year > b.year) {
            return 1;
        }
        return 0;
    });

    let tableArray = sortedByYear.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
    )

    table.innerHTML = tableArray.join("");
}

function sortByModel() {
    let cars = fetchAllCars();
    let table = document.getElementById("table_body");

    let sortedByModel = cars.sort(function (a, b) {
        return a.model.toLowerCase().localeCompare(b.model.toLowerCase());
    });
    
    let tableArray = sortedByYear.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
    )

    table.innerHTML = tableArray.join("");
}

function sortByMake() {
    let cars = fetchAllCars();
    let table = document.getElementById("table_body");

    let sortedByMake = cars.sort(function (a, b) {
        return a.make.toLowerCase().localeCompare(b.make.toLowerCase());
    });
    
    let tableArray = sortedByYear.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
    )

    table.innerHTML = tableArray.join("");
}

function sortByPrice() {
    let cars = fetchAllCars();
    let table = document.getElementById("table_body");

    let sortedByPrice = cars.sort(function (a, b) {
        if (a.price < b.price) {
            return -1;
        }
        if (a.price > b.price) {
            return 1;
        }
        return 0;
    });
    
    let tableArray = sortedByYear.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
    )

    table.innerHTML = tableArray.join("");
}