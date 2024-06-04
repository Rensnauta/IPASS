const fetchurl = "http://localhost:8080/api/stock"

import { Product } from "../Model/Product"
let allProducts = []

new Product (12, 3123, "test", 1-1-2032, 12, "vegetables", 12)

function fetchProducts() {
    console.log("fetching products")
    fetch(fetchurl)
        .then((response) => {
            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`)
            }
            return response.json();
        })
        .then(jsonData => {
            let tableHTML = '<table><tr><th>Productnumber</th><th>Product Name</th><th>Expiration Date</th><th>Stock</th><th>Category</th><th>Price</th></tr>';
            jsonData.forEach(item => {
                tableHTML += `<tr>
                    <td>${item.productNr}</td>
                    <td>${item.productName}</td>
                    <td>${item.expirationDate}</td>
                    <td>${item.stock}</td>
                    <td>${item.category}</td>
                    <td>${item.price}</td>
                </tr>`;
            });
            tableHTML += '</table>';
            document.body.innerHTML = tableHTML;
        })
        .catch(error => {
                console.log("Failed to fetch products:", error)
        })
}



window.onload = () => {
    fetchProducts();
};  
