const apiUrl = 'http://localhost:8080/api/products';

async function fetchProducts() {
    const res = await fetch(apiUrl);
    const data = await res.json();
    const tbody = document.querySelector('#productTable tbody');
    tbody.innerHTML = '';
    data.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.category}</td>
            <td>${p.supplier}</td>
            <td>${p.price}</td>
            <td>${p.stock}</td>
            <td><button onclick="deleteProduct(${p.id})">Delete</button></td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('addProductForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const product = {
        name: document.getElementById('name').value,
        category: document.getElementById('category').value,
        supplier: document.getElementById('supplier').value,
        price: parseFloat(document.getElementById('price').value),
        stock: parseInt(document.getElementById('stock').value)
    };
    await fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product)
    });
    document.getElementById('addProductForm').reset();
    fetchProducts();
});

async function deleteProduct(id){
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    fetchProducts();
}

// Initial load
fetchProducts();
