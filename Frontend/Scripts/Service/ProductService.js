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
}