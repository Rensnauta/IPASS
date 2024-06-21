import { ProductService } from "../Service/ProductService";

let productService = new ProductService();

function getProductNumberFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('productNumber');
}

async function populateForm(productNr) {
    let product = await productService.fetchProductByNr(productNr)
    console.log(product);
    document.getElementById('id').value = product.productNr;
    document.getElementById('name').value = product.productName;
}

window.onload = async () => {
    let productNr = await getProductNumberFromURL();
    await populateForm(productNr);
}
