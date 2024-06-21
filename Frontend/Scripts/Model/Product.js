export class Product {
    productData = []
        constructor(productnr, productname, expirationdate, stock, category, price) {
            this.productnr = productnr;
            this.productname = productname;
            this.expirationdate = expirationdate;
            this.stock = stock;
            this.category = category;
            this.price = price;
            productData.push(this)
        }

        fetchProducts() {
            console.log("fetching products")
            fetch(fetchurl)
                .then((response) => {
                    if(!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`)
                    }
                    return response.json();
                })
                .catch(error => {
                        console.log("Failed to fetch products:", error)
                })
}
}
