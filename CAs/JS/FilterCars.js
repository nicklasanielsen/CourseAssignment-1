/**
 * @author Nikolaj Larsen
 */ 

document.getElementById("filter").addEventListener('submit', function(e){
    e.preventDefault();
    filterCars();
});

function filterCars(){
    let searchParamater = document.getElementById("filterOption").value;
    let FilterValue = document.getElementById("filterValue").value;
    
    if(searchParamater === "year"){
        filterCarsByYear(FilterValue);       
    }else if(searchParamater === "make"){
        filterCarsByMake(FilterValue);
    }else if(searchParamater === "price"){
        filterCarsByPrice(FilterValue);
    }else if(searchParamater === "model"){
        filterCarsByModel(FilterValue);
    }
}

function filterCarsByYear(filterValue){
    let tableBody = document.getElementById("table_body");
    let url = './api/cars/all';

    fetch(url)
            .then(res => res.json())
            .then(data => {
            let cars = data.filter(car => car.year === parseInt(filterValue)).map(car =>
                    `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    </tr>`);

                tableBody.innerHTML = cars.join("");
        });  
}

function filterCarsByMake(filterValue){
    let tableBody = document.getElementById("table_body");
    let url = './api/cars/all';

    fetch(url)
            .then(res => res.json())
            .then(data => {
            let cars = data.filter(car => car.make === filterValue).map(car =>
                    `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    </tr>`);

                tableBody.innerHTML = cars.join("");
        }); 
}

function filterCarsByPrice(filterValue){
    let tableBody = document.getElementById("table_body");
    let url = './api/cars/all';

    fetch(url)
            .then(res => res.json())
            .then(data => {
            let cars = data.filter(car => car.price === parseFloat(filterValue)).map(car =>
                    `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    </tr>`);

                tableBody.innerHTML = cars.join("");
        }); 
}

function filterCarsByModel(filterValue){
    let tableBody = document.getElementById("table_body");
    let url = './api/cars/all';

    fetch(url)
            .then(res => res.json())
            .then(data => {
            let cars = data.filter(car => car.model === filterValue).map(car =>
                    `<tr>
                    <td>${car.id}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    </tr>`);

                tableBody.innerHTML = cars.join("");
        }); 
}