function login() {
	const username = document.getElementById('username').value;
	const password = document.getElementById('password').value;
	fetch('/api/auth/login', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ username, password })
	})
		.then(response => response.json())
		.then(data => {
			localStorage.setItem('token', data.token);
			window.location.href = '/profile';
		})
		.catch(error => console.error('Error:', error));
}

function getProfile() {
	const token = localStorage.getItem('token');
	fetch('/api/users/me', {
		headers: { 'Authorization': `Bearer ${token}` }
	})
		.then(response => response.json())
		.then(data => {
			document.getElementById('profileInfo').innerText = JSON.stringify(data);
		})
		.catch(error => console.error('Error:', error));
}