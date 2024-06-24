import { ProductService } from "../Service/ProductService";

let productService = new ProductService(),
    productNr = getProductNumberFromURL();

function getProductNumberFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('productNumber');
}

async function populateForm(productNr) {
    let product = await productService.fetchProductByNr(productNr)
    console.log(product);
    document.getElementById('id').value = product.productNr;
    document.getElementById('name').value = product.productName;
    document.getElementById('price').value = product.price;
    document.getElementById('stock').value = product.stock;
    document.getElementById('date').value = product.expirationDate;
}

function collectFormData() {
    const productNr = document.getElementById('id').value;
    const productName = document.getElementById('name').value;
    const price = document.getElementById('price').value;
    const stock = document.getElementById('stock').value;
    const expirationDate = document.getElementById('date').value;

    // Step 2: Prepare Data for Sending
    const productData = {
        productNr,
        productName,
        price,
        stock,
        expirationDate
    };

    return productData;
}

document.getElementById('submitButton').addEventListener('click', async (event) => {
    event.preventDefault(); // Prevent form from submitting the traditional way
    const formData = collectFormData();
    await productService.sendFormData(formData);
});

window.onload = async () => {
    let productNr = await getProductNumberFromURL();
    await populateForm(productNr);
}
