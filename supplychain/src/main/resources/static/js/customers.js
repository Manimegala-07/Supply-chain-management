const customerApi = 'http://localhost:8080/api/customers';

async function fetchCustomers() {
    const res = await fetch(customerApi);
    const data = await res.json();
    const tbody = document.querySelector('#customerTable tbody');
    tbody.innerHTML = '';
    data.forEach(c => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.phone}</td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('addCustomerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const customer = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value
    };
    await fetch(customerApi, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customer)
    });
    document.getElementById('addCustomerForm').reset();
    fetchCustomers();
});

// Initial load
fetchCustomers();
