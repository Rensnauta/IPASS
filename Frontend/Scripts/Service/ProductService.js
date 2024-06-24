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
            throw error; // Rethrow to allow caller to handle
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
            console.log('Success:', responseData);
            // Handle success response
        } catch (error) {
            console.error('Error:', error);
            // Handle errors here
        }
    }
}