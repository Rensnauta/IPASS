export class ProductService {
    async fetchProductByNr(productNr) {
        try {
            const response = await fetch(`http://localhost:8080/api/products/${productNr}`);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            console.log("fetching product");
            return await response.json();
        } catch (error) {
            console.log("Failed to fetch products:", error);
            throw error; 
        }
    }

    async sendFormData(productData) {
        try {
            const response = await fetch('http://localhost:8080/api/products/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(productData),
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const responseData = await response.json();
        } catch (error) {
            console.error('Error:', error);
        }
    }

    async newProduct(productData) {
        try {
            const response = await fetch('http://localhost:8080/api/products/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(productData),
            });
    
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
    
            const responseData = await response.json();
        } catch (error) {
            console.error('Error:', error);
        }
    }

    async fetchCategories() {
        try {
            const response = await fetch('http://localhost:8080/api/categories');
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.log("Failed to fetch categories:", error);
            throw error;
        }
    }
}