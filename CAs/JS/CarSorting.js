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
    let url = './api/cars/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {


                let sortedByYear = data.sort(function (a, b) {
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
            });

}

function sortByModel() {
    let url = './api/cars/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {

                let sortedByModel = data.sort(function (a, b) {
                    return a.model.toLowerCase().localeCompare(b.model.toLowerCase());
                });

                let tableArray = sortedByModel.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
                )

                table.innerHTML = tableArray.join("");
            });

}

function sortByMake() {
    let url = './api/cars/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {

                let sortedByMake = data.sort(function (a, b) {
                    return a.make.toLowerCase().localeCompare(b.make.toLowerCase());
                });

                let tableArray = sortedByMake.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
                )

                table.innerHTML = tableArray.join("");
            });


}

function sortByPrice() {
    let url = './api/cars/all';
    let table = document.getElementById("table_body");

    fetch(url)
            .then(res => res.json())
            .then(data => {

                let sortedByPrice = data.sort(function (a, b) {
                    if (a.price < b.price) {
                        return -1;
                    }
                    if (a.price > b.price) {
                        return 1;
                    }
                    return 0;
                });

                let tableArray = sortedByPrice.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            </tr>`
                )

                table.innerHTML = tableArray.join("");
            });

}