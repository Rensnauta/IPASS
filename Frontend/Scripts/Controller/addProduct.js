import { ProductService } from "../Service/ProductService";

const categories = {};
const service = new ProductService();

function collectFormData() {
    const productNr = document.getElementById('id').value;
    const productName = document.getElementById('name').value;
    const price = document.getElementById('price').value;
    const stock = document.getElementById('stock').value;
    const category = document.getElementById('category').value;
    const expirationDate = document.getElementById('date').value;

    if (productNr.length === 0 || productName.length === 0 || price.length === 0) {
        document.getElementById('messageLabel').textContent = 'Please fill in all required fields.';
        return;
    }

    const productData = {
        productNr,
        productName,
        price,
        category,
        stock,
        expirationDate
    };
    console.log(productData);
    return productData;
}

document.getElementById('submitButton').addEventListener('click', async (event) => {
    event.preventDefault();
    const formData = collectFormData();
    await service.newProduct(formData);
    
});

 window.onload = async () => {
    const categoriesArray = await service.fetchCategories();
    const categorySelect = document.getElementById('category'); // Assuming the select element has id="category"

    categoriesArray.forEach(category => {
        const option = document.createElement('option');
        option.value = category.id; // Assuming category objects have an 'id' property
        option.textContent = category.name; // Display name in the dropdown
        categorySelect.appendChild(option);
    });
};