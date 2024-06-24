import { ExpirationService } from "../Service/ExpirationService";

const expirationService = new ExpirationService();

function collectProductInfo() {
    const productDivs = document.querySelectorAll('.product'); // Select all product divs
    const productsInfo = []; // Initialize an array to hold the product info objects

    productDivs.forEach(div => {
        const productNameAndId = div.querySelector('label').textContent.split(', '); // Assuming the format is "Name: productName, ID: productNr"
        const productNr = productNameAndId[1].replace('ID: ', '');
        const expirationDate = div.querySelector('input[type="date"]').value;
        const expiredAmount = div.querySelector('input[type="number"]').value;

        const productInfo = {
            productNr,
            expirationDate,
            expiredAmount: parseInt(expiredAmount, 10) // Convert to integer
        };

        productsInfo.push(productInfo);
    });

    return JSON.stringify(productsInfo); // Convert the array to a JSON string if needed
}

window.onload = async function () {
    const products = await expirationService.getProductsAboutToExpire();
    const container = document.getElementById('productDiv'); // Ensure you have a div with this ID in your HTML

    products.forEach(product => {
        // Create a div to hold the product info and inputs
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');

        // Create and append the label
        const label = document.createElement('label');
        label.textContent = `Name: ${product.productName}, ID: ${product.productNr}`;
        productDiv.appendChild(label);

        // Create and append the expiration date input
        const expirationDateInput = document.createElement('input');
        expirationDateInput.type = 'date';
        expirationDateInput.value = product.expirationDate; // Assuming the format is compatible
        expirationDateInput.name = 'expirationDate';
        productDiv.appendChild(expirationDateInput);

        // Create and append the amount of expired products input
        const amountInput = document.createElement('input');
        amountInput.type = 'number';
        amountInput.name = 'expiredAmount';
        productDiv.appendChild(amountInput);

        // Append the product div to the container
        container.appendChild(productDiv);
    });

    document.getElementById('submitButton').addEventListener('click', async () => {
        const productsInfo = collectProductInfo();
        await expirationService.sendExpiredProducts(productsInfo);
    });
}