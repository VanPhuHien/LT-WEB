<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>GraphQL Assignment Demo</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
body {
	font-family: "Segoe UI", Arial, sans-serif;
	margin: 30px;
	background: #f9f9f9;
	color: #333;
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #444;
}

section {
	background: #fff;
	padding: 20px;
	margin-bottom: 25px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.08);
}

h2 {
	margin-top: 0;
	color: #222;
	border-bottom: 1px solid #eee;
	padding-bottom: 5px;
}

ul {
	list-style: none;
	padding-left: 0;
}

li {
	padding: 6px 10px;
	border-bottom: 1px solid #f0f0f0;
}

li:last-child {
	border-bottom: none;
}

.controls {
	margin: 15px 0;
}

.controls input, .controls button {
	padding: 7px 12px;
	margin: 5px 5px 5px 0;
	border-radius: 4px;
	border: 1px solid #ccc;
	font-size: 14px;
}

.controls button {
	background: #1976d2;
	color: #fff;
	border: none;
	cursor: pointer;
	transition: 0.2s;
}

.controls button:hover {
	background: #0d5ba8;
}

.error {
	color: red;
	font-style: italic;
}
</style>
<script>
$(document).ready(function () {
    const graphqlUrl = "/graphql";

    function runQuery(query, onSuccess, onError) {
        $.ajax({
            url: graphqlUrl,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ query }),
            success: onSuccess,
            error: function (err) {
                if (onError) onError(err);
                console.error("GraphQL Error:", err);
            }
        });
    }

    // ========== PRODUCTS ==========
    function fetchProductsSorted() {
        runQuery(
            "{ productsSortedByPrice { id title price } }",
            function (res) {
                let list = res.data.productsSortedByPrice || [];
                let html = "<ul>";
                if (list.length > 0) {
                    list.forEach(p => {
                        html += `<li><strong>${p.title}</strong> — $${p.price.toFixed(2)} <span style="color:#888">(ID: ${p.id})</span></li>`;
                    });
                } else {
                    html += "<li>No products found</li>";
                }
                html += "</ul>";
                $("#productsSorted").html(html);
            },
            function () {
                $("#productsSorted").html('<p class="error">Error loading products</p>');
            }
        );
    }

    function fetchProductsByCategory() {
        let categoryId = $("#categoryIdInput").val();
        if (!categoryId || isNaN(categoryId)) {
            $("#productsByCategory").html('<p class="error">Please enter a valid Category ID</p>');
            return;
        }
        runQuery(
            `{ productsByCategory(categoryId: ${categoryId}) { id title price } }`,
            function (res) {
                let list = res.data.productsByCategory || [];
                let html = "<ul>";
                if (list.length > 0) {
                    list.forEach(p => {
                        html += `<li><strong>${p.title}</strong> — $${p.price.toFixed(2)} <span style="color:#888">(ID: ${p.id})</span></li>`;
                    });
                } else {
                    html += "<li>No products found for this category</li>";
                }
                html += "</ul>";
                $("#productsByCategory").html(html);
            },
            function () {
                $("#productsByCategory").html('<p class="error">Error loading products</p>');
            }
        );
    }

    function createProduct() {
        let title = prompt("Enter product title:");
        let quantity = parseInt(prompt("Enter quantity:"));
        let description = prompt("Enter description:");
        let price = parseFloat(prompt("Enter price:"));
        let userId = parseInt(prompt("Enter user ID:"));

        if (title && !isNaN(quantity) && !isNaN(price) && !isNaN(userId)) {
            let q = `mutation { createProduct(title: "${title}", quantity: ${quantity}, description: "${description}", price: ${price}, userId: ${userId}) { id title } }`;
            runQuery(q, function (res) {
                let p = res.data.createProduct;
                alert(`Created Product — ID: ${p.id}, Title: ${p.title}`);
                fetchProductsSorted();
            });
        } else {
            alert("Invalid input!");
        }
    }

    function deleteProduct() {
        let id = prompt("Enter Product ID to delete:");
        if (id && !isNaN(id)) {
            let q = `mutation { deleteProduct(id: ${id}) }`;
            runQuery(q, function (res) {
                alert("Product deleted: " + (res.data.deleteProduct ? "Success" : "Failed"));
                fetchProductsSorted();
            });
        }
    }

    // ========== USERS ==========
    function createUser() {
        let fullname = prompt("Full name:");
        let email = prompt("Email:");
        let password = prompt("Password:");
        let phone = prompt("Phone:");
        let categoryIds = prompt("Category IDs (comma-separated):")
            .split(",")
            .map(id => parseInt(id.trim()))
            .filter(id => !isNaN(id));

        if (fullname && email && password) {
            let q = `mutation { createUser(fullname: "${fullname}", email: "${email}", password: "${password}", phone: "${phone}", categoryIds: [${categoryIds}]) { id fullname } }`;
            runQuery(q, function (res) {
                let u = res.data.createUser;
                alert(`Created User — ID: ${u.id}, Name: ${u.fullname}`);
            });
        }
    }

    function deleteUser() {
        let id = prompt("Enter User ID to delete:");
        if (id && !isNaN(id)) {
            runQuery(`mutation { deleteUser(id: ${id}) }`, function (res) {
                alert("User deleted: " + (res.data.deleteUser ? "Success" : "Failed"));
            });
        }
    }

    // ========== CATEGORIES ==========
    function createCategory() {
        let name = prompt("Category name:");
        let images = prompt("Images URL:");
        if (name) {
            runQuery(
                `mutation { createCategory(name: "${name}", images: "${images}") { id name } }`,
                function (res) {
                    let c = res.data.createCategory;
                    alert(`Created Category — ID: ${c.id}, Name: ${c.name}`);
                }
            );
        }
    }

    function deleteCategory() {
        let id = prompt("Enter Category ID to delete:");
        if (id && !isNaN(id)) {
            runQuery(`mutation { deleteCategory(id: ${id}) }`, function (res) {
                alert("Category deleted: " + (res.data.deleteCategory ? "Success" : "Failed"));
            });
        }
    }

    // bind events
    $("#btnLoadByCategory").click(fetchProductsByCategory);
    $("#btnCreateProduct").click(createProduct);
    $("#btnDeleteProduct").click(deleteProduct);
    $("#btnCreateUser").click(createUser);
    $("#btnDeleteUser").click(deleteUser);
    $("#btnCreateCategory").click(createCategory);
    $("#btnDeleteCategory").click(deleteCategory);

    // load initial data
    fetchProductsSorted();
});
</script>
</head>
<body>
	<!-- Header -->
	<header
		style="background: #1976d2; color: #fff; padding: 15px 20px; display: flex; justify-content: space-between; align-items: center;">
		<div style="font-size: 20px; font-weight: bold;">GraphQL
			Assignment</div>
		<nav>
			<a href="#products"
				style="color: #fff; margin-right: 15px; text-decoration: none;">Products</a>
			<a href="#users"
				style="color: #fff; margin-right: 15px; text-decoration: none;">Users</a>
			<a href="#categories" style="color: #fff; text-decoration: none;">Categories</a>
		</nav>
	</header>
	<h1>GraphQL Assignment Demo</h1>

	<section>
		<h2>Products Sorted by Price</h2>
		<div id="productsSorted"></div>
	</section>

	<section>
		<h2>Products by Category</h2>
		<div class="controls">
			<input type="number" id="categoryIdInput"
				placeholder="Enter Category ID" />
			<button id="btnLoadByCategory">Load</button>
		</div>
		<div id="productsByCategory"></div>
	</section>

	<section>
		<h2>CRUD Operations</h2>

		<h3>Product</h3>
		<div class="controls">
			<button id="btnCreateProduct">Create Product</button>
			<button id="btnDeleteProduct">Delete Product</button>
		</div>

		<h3>User</h3>
		<div class="controls">
			<button id="btnCreateUser">Create User</button>
			<button id="btnDeleteUser">Delete User</button>
		</div>

		<h3>Category</h3>
		<div class="controls">
			<button id="btnCreateCategory">Create Category</button>
			<button id="btnDeleteCategory">Delete Category</button>
		</div>
	</section>

	<section>
		<h2>REST Example</h2>
		<p>
			<a href="/api/rest/products" target="_blank">/api/rest/products</a>
		</p>
	</section>
	<!-- Footer -->
	<footer
		style="background: #f1f1f1; color: #555; padding: 10px 20px; text-align: center; font-size: 14px;">
		© Văn Phú Hiền - 23110213 </footer>
</body>
</html>
