export class StockView {
    drawTable(products){
        let tableHTML = '<table><tr><th>Productnumber</th><th>Product Name</th><th>Expiration Date</th><th>Stock</th><th>Category</th><th>Price</th></tr>';
    products.forEach(item => {
        tableHTML += `<tr>
            <td>${item.productNr}</td>
            <td>${item.productName}</td>
            <td>${item.expirationDate}</td>
            <td>${item.stock}</td>
            <td>${item.category}</td>
            <td>${item.price}</td>
        </tr>`;
        })
        tableHTML += '</table>';
        document.body.innerHTML = tableHTML;
    }
}