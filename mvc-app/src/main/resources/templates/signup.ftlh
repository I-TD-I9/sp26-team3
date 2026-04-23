<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign Up | SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body class="bg-body-tertiary">
<nav class="navbar bg-body-tertiary border-bottom">
  <div class="container">
    <a class="navbar-brand fw-semibold" href="/">SpartanGuide</a>
    <ul class="navbar-nav ms-auto flex-row gap-3">
      <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="/signin">Sign In</a></li>
    </ul>
  </div>
</nav>

<main class="container py-5">
  <div class="row justify-content-center">
    <div class="col-sm-10 col-md-7 col-lg-6">
      <div class="card shadow-sm">
        <div class="card-header">
          <ul class="nav nav-tabs card-header-tabs">
            <li class="nav-item">
              <a class="nav-link active" data-bs-toggle="tab" href="#student">Student</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="tab" href="#guide">Guide</a>
            </li>
          </ul>
        </div>
        <div class="card-body p-4">
          <div class="tab-content">
            <div class="tab-pane fade show active" id="student">
              <h5 class="mb-3">Student Sign Up</h5>
              <form method="post" action="/student/signup">
                <div class="mb-3">
                  <label class="form-label" for="studentName">Full name</label>
                  <input type="text" class="form-control" id="studentName" name="name" required>
                </div>
                <div class="mb-3">
                  <label class="form-label" for="studentEmail">Email</label>
                  <input type="email" class="form-control" id="studentEmail" name="email" required>
                </div>
                <div class="mb-3">
                  <label class="form-label" for="studentPassword">Password</label>
                  <input type="password" class="form-control" id="studentPassword" name="passwordHash" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Create account</button>
              </form>
              <p class="text-body-secondary small mt-3 mb-0">
                Already have an account? <a href="/signin">Sign in</a>.
              </p>
            </div>
            <div class="tab-pane fade" id="guide">
              <h5 class="mb-3">Guide Sign Up</h5>
              <form method="post" action="/guide/signup">
                <div class="mb-3">
                  <label class="form-label" for="guideName">Full name</label>
                  <input type="text" class="form-control" id="guideName" name="name" required>
                </div>
                <div class="mb-3">
                  <label class="form-label" for="guideEmail">Email</label>
                  <input type="email" class="form-control" id="guideEmail" name="email" required>
                </div>
                <div class="mb-3">
                  <label class="form-label" for="guidePassword">Password</label>
                  <input type="password" class="form-control" id="guidePassword" name="passwordHash" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Create account</button>
              </form>
              <p class="text-body-secondary small mt-3 mb-0">
                Already have an account? <a href="/signin">Sign in</a>.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
</body>
</html>
