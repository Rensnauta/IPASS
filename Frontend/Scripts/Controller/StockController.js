/* eslint-disable no-plusplus */
/* eslint-disable max-statements */


import { Product } from "../Model/Product";
import { StockView } from "../View/StockView"
const fetchurl = "http://localhost:8080/api/stock",
      stockview = new StockView();

let productData = []

class StockController {
    fetchProducts() {
        console.log("fetching products")
        fetch(fetchurl)
            .then((response) => {
                if(!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`)
                }
                return response.json();
            })
            .then(jsonData => {
                let productData = jsonData; // Declare productData here
                stockview.drawTable(productData)
                productData.forEach(item => {
                    new Product(item.productNr, item.productName, item.expirationDate, item.stock, item.category, item.price)
                })
            })
            .catch(error => {
                    console.log("Failed to fetch products:", error)
            })
    }

    addEventListeners() {
      const searchbox = document.getElementById('searchbox');
      searchbox.addEventListener('input', () => this.searchTable());
    }

    searchTable() {
      let input, filter, table, tr, td, i, txtValue;
      input = document.getElementById("searchbox");
      filter = input.value.toUpperCase();
      table = document.getElementById("stockTable");
      tr = table.getElementsByTagName("tr");
      
      // Loop through all table rows, and hide those who don't match the search query
      for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        // Flag to indicate if the text was found in any of the cells
        let found = false;
        for (let j = 0; j < td.length; j++) { 
          // Iterate through each cell
          let currentTd = td[j];
          if (currentTd) {
            txtValue = currentTd.textContent || currentTd.innerText;
            // Check if the cell contains an <a> element and include its text content in the search
            let links = currentTd.getElementsByTagName("a");
            for (let k = 0; k < links.length; k++) {
              txtValue += " " + (links[k].textContent || links[k].innerText);
            }
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
              found = true;
              // Exit the loop since we found a match
              break;
            }
          }
        }
        // Show or hide the row based on the search result
        tr[i].style.display = found ? "" : "none";
      }
    }
}



window.onload = () => {
    const controller = new StockController
    controller.fetchProducts()
    controller.addEventListeners()
};  

