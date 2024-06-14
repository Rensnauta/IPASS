export class StockView {
    drawTable(products){
        let tableHTML = '<table id = "stockTable"><tr><th>Productnumber</th><th>Product Name</th><th>Expiration Date</th><th>Stock</th><th>Category</th><th>Price</th></tr>';
    products.forEach(item => {
        tableHTML += `<tr>
            <td data-cell="productnr"> <a href="homepage.html">${item.productNr}</a></td>
            <td data-cell="productname">${item.productName}</td>
            <td data-cell="expirationdate">${item.expirationDate}</td>
            <td data-cell="stock">${item.stock}</td>
            <td data-cell="category">${item.category}</td>
            <td data-cell="price">${item.price}</td>
        </tr>`;
        })
        tableHTML += '</table>';
        document.getElementById("tablediv").innerHTML = tableHTML
    }
}