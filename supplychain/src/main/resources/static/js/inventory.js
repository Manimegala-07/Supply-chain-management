const inventoryApi = 'http://localhost:8080/api/inventory';
const productApi = 'http://localhost:8080/api/products';

async function fetchStockHistory() {
    const res = await fetch('http://localhost:8080/api/stock-history'); // make sure backend endpoint exists
    const data = await res.json();
    const tbody = document.querySelector('#historyTable tbody');
    tbody.innerHTML = '';
    data.forEach(h => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${h.id}</td>
            <td>${h.product.name}</td>
            <td>${h.quantity}</td>
            <td>${h.actionType}</td>
            <td>${h.remarks}</td>
            <td>${h.timestamp}</td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('restockForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const params = `productId=${document.getElementById('restockProductId').value}&quantity=${document.getElementById('restockQty').value}&remarks=${document.getElementById('restockRemarks').value}`;
    await fetch(`${inventoryApi}/restock?${params}`, { method: 'POST' });
    fetchStockHistory();
});

document.getElementById('removeForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const params = `productId=${document.getElementById('removeProductId').value}&quantity=${document.getElementById('removeQty').value}&remarks=${document.getElementById('removeRemarks').value}`;
    await fetch(`${inventoryApi}/remove?${params}`, { method: 'POST' });
    fetchStockHistory();
});

// Initial load
fetchStockHistory();
