export class ExpirationService {
    async getProductsAboutToExpire() {
        try {
            const response = await fetch('http://localhost:8080/api/products/expiring', {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ` + window.sessionStorage.getItem('myJWT')
                }
            });
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error:', error);
            // Handle errors here
            throw error; // Rethrow the error if you want to allow the caller to handle it as well
        }
    }

    sendExpiredProducts(expiredProducts) {
        console.log(JSON.stringify(expiredProducts));
        fetch('http://localhost:8080/api/products/expired', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ` + window.sessionStorage.getItem('myJWT')
            },
            body: JSON.stringify(expiredProducts),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .catch(error => {
                console.error('Error:', error);
                // Handle errors here
            });
    }
}