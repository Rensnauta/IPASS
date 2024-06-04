const fetchurl = "http://localhost:8080/api/stock"

fetch(fetchurl)
    .then((response) => {
        if(!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`)
        }
        return response.json();
    })
    .then((data) => {
        data.forEach(element => {
            console.log(element)
        });
    })
    .catch((error) => {
        console.error('Error:', error);
    });