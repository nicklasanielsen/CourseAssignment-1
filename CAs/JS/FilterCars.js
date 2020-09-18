/**
 * @author Nikolaj Larsen
 */ 

document.getElementById("filter").addEventListener('click', function(e){
    e.preventDefault();
    filterCars();
});

function filterCars(){
    let cars = fetchAllCars();
    let searchParamater = document.getElementById("filterOption");
    let FilterValue = document.getElementById("filterValue");
    let result;
    
    if(searchParamater === "year"){
        result = filterCarByYear(cars, FilterValue);       
    }else if(searchParamater === "make"){
        result = filterCarByMake(cars, FilterValue);
    }else if(searchParamater === "price"){
        result = filterCarByPrice(cars, FilterValue);
    }else if(searchParamater === "model"){
        result = filterCarByModel(cars, FilterValue);
    }else {
        return;
    }
    
    document.getElementById("table_body").innerHTML = result.map(car => `<tr>
            <td>${car.id}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            </tr>`).join("");
    
}

function filterCarsByYear(cars, filterValue){
    let filterCar = cars.filter(car => car.year === filterValue);
    return filterCar;
}

function filterCarsByMake(cars, filterValue){
    let filterCar = cars.filter(car => car.make === filterValue);
    return filterCar;
}

function filterCarsByPrice(cars, filterValue){
    let filterCar = cars.filter(car => car.price === filterValue);
    return filterCar;
}

function filterCarsByModel(cars, filterValue){
    let filterCar = cars.filter(car => car.model === filterValue);
    return filterCar;
}