export class Product {
        constructor(productnr, productname, expirationdate, stock, category, price) {
            this.productnr = productnr;
            this.productname = productname;
            this.expirationdate = expirationdate;
            this.stock = stock;
            this.category = category;
            this.price = price;
            console.log(this)
        }
}
