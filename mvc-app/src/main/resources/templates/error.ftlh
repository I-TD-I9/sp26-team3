<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error | SpartanGuide</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body class="bg-body-tertiary">
<nav class="navbar bg-body-tertiary border-bottom">
    <div class="container">
        <a class="navbar-brand fw-semibold" href="/">SpartanGuide</a>
    </div>
</nav>

<main class="container py-5 text-center">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-body p-5">
                    <h1 class="display-1 text-danger">!</h1>
                    <h2 class="h4 mb-3">Something went wrong</h2>
                    <p class="text-body-secondary mb-4">
                        ${errorMessage!"An unexpected error occurred."}
                    </p>
                    <a href="/" class="btn btn-primary">Go Home</a>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="py-4 border-top">
    <div class="container">
        <span class="text-body-secondary">SpartanGuide</span>
    </div>
</footer>
</body>
</html>
