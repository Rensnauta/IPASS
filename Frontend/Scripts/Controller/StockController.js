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
}





window.onload = () => {
    const controller = new StockController
    controller.fetchProducts();
};  
