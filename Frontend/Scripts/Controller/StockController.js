const fetchurl = "http://localhost:8080/api/stock"

import { Product } from "../Model/Product"
import { StockView } from "../View/StockView"
let stockview = new StockView

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
                productData = jsonData
                stockview.drawTable(productData)
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
      // Declare variables
  let input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchbox");
  filter = input.value.toUpperCase();
  table = document.getElementById("stockTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
}



window.onload = () => {
    const controller = new StockController
    controller.fetchProducts()
    controller.addEventListeners()
};  

