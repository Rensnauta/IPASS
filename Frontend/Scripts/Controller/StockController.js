const fetchurl = "http://localhost:8080/api/stock"

fetch(fetchurl) 
.then((Response) => {
    if(!Response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }
    return Response.array.forEach(element => {
        console.log(element)
    });
})