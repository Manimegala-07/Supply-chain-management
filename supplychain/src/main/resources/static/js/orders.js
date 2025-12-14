const orderApi = 'http://localhost:8080/api/orders';
const customerApi = 'http://localhost:8080/api/customers';

async function fetchCustomersForOrder() {
    const res = await fetch(customerApi);
    const customers = await res.json();
    const select = document.getElementById('customerSelect');
    select.innerHTML = '';
    customers.forEach(c => {
        const option = document.createElement('option');
        option.value = c.id;
        option.textContent = c.name;
        select.appendChild(option);
    });
}

async function fetchOrders() {
    const res = await fetch(orderApi);
    const data = await res.json();
    const tbody = document.querySelector('#orderTable tbody');
    tbody.innerHTML = '';
    data.forEach(o => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${o.id}</td>
            <td>${o.customer.name}</td>
            <td>${o.totalAmount}</td>
            <td>${o.orderDate}</td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('createOrderForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const order = {
        customer: { id: parseInt(document.getElementById('customerSelect').value) },
        items: [{ product: { id: parseInt(document.getElementById('productId').value) }, quantity: parseInt(document.getElementById('quantity').value) }]
    };
    await fetch(orderApi, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(order)
    });
    document.getElementById('createOrderForm').reset();
    fetchOrders();
});

// Initial load
fetchCustomersForOrder();
fetchOrders();
