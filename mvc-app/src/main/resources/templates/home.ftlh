<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom mb-4">
    <div class="container">
      <a class="navbar-brand fw-semibold" href="/">SpartanGuide</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item"><a class="nav-link active" href="/">Home</a></li>
          <li class="nav-item"><a class="nav-link" href="/signin">Sign In</a></li>
          <li class="nav-item"><a class="nav-link" href="/signup">Sign Up</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <div class="px-4 py-5 my-3">
      <div class="row align-items-center g-4">
        <div class="col-lg-6 text-center text-lg-start">
          <h1 class="display-5 fw-semibold mb-4">Find campus tours built by Spartans.</h1>
          <p class="lead mb-4">Browse tours created by fellow Spartans to adventure beyond campus.</p>
          <div class="d-flex gap-2 justify-content-lg-start justify-content-center">
            <a href="/signup" class="btn btn-primary btn-lg px-4">Get Started</a>
            <a href="/student/tours" class="btn btn-outline-secondary btn-lg px-4">Browse Tours</a>
          </div>
        </div>
        <div class="col-lg-6">
          <div id="heroCarousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner rounded">
              <div class="carousel-item active" data-bs-interval="4000">
                <img src="https://www.escapetravelservices.com/wp-content/uploads/2016/12/treks-in-winter.jpg"
                     class="d-block w-100" style="max-height:350px;object-fit:cover;" alt="Winter trek">
              </div>
              <div class="carousel-item" data-bs-interval="4000">
                <img src="https://res.cloudinary.com/sagacity/image/upload/c_crop,h_733,w_1100,x_0,y_0/c_limit,dpr_auto,f_auto,fl_lossy,q_80,w_1200/0116-rogue-river_grrogx.jpg"
                     class="d-block w-100" style="max-height:350px;object-fit:cover;" alt="River tour">
              </div>
              <div class="carousel-item">
                <img src="https://www.visitterrace.com/wp-content/uploads/2024/01/Biking-2.jpg"
                     class="d-block w-100" style="max-height:350px;object-fit:cover;" alt="Biking tour">
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#heroCarousel" data-bs-slide="prev">
              <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#heroCarousel" data-bs-slide="next">
              <span class="carousel-control-next-icon"></span>
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="row text-center g-3 py-4 mb-5 bg-body-tertiary rounded">
      <div class="col-md-4">
        <div class="p-3">
          <h3 class="h5">Quick Picks</h3>
          <p class="mb-0 text-body-secondary">Short tours focused on one building or feature.</p>
        </div>
      </div>
      <div class="col-md-4">
        <div class="p-3">
          <h3 class="h5">Student Made</h3>
          <p class="mb-0 text-body-secondary">Guides written by current Spartans.</p>
        </div>
      </div>
      <div class="col-md-4">
        <div class="p-3">
          <h3 class="h5">Easy Upload</h3>
          <p class="mb-0 text-body-secondary">Create a guide with a simple form.</p>
        </div>
      </div>
    </div>

    <div class="py-4 mb-5">
      <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="h4 mb-0">Available Tours</h2>
        <a class="btn btn-sm btn-outline-secondary" href="/student/tours">View all</a>
      </div>
      <#if tours?has_content>
      <div class="row g-3">
        <#list tours as tour>
        <div class="col-md-4">
          <div class="card h-100 border-0 shadow-sm">
            <div class="card-body">
              <h5 class="card-title">${tour.title}</h5>
              <p class="card-text text-body-secondary">${(tour.description!'')?truncate(120)}</p>
              <ul class="list-group list-group-flush mb-3">
                <li class="list-group-item px-0 small">Location: ${tour.location}</li>
                <li class="list-group-item px-0 small">Price: $${tour.price} &bull; Capacity: ${tour.capacity}</li>
              </ul>
            </div>
            <div class="card-footer bg-transparent">
              <a href="/student/tours/${tour.tourId}" class="btn btn-sm btn-primary w-100">View Details</a>
            </div>
          </div>
        </div>
        </#list>
      </div>
      <#else>
      <div class="alert alert-info">
        No tours available yet. <a href="/signup">Sign up as a guide</a> to create the first one!
      </div>
      </#if>
    </div>

    <#if !tours?has_content || tours?size == 0>
    <div class="text-center py-5 mb-5 bg-body-tertiary rounded">
      <h2 class="display-6 mb-3">Ready to Explore the Campus?</h2>
      <p class="lead mb-4">Join SpartanGuide to browse tours or create your own.</p>
      <div class="d-flex gap-2 justify-content-center">
        <a href="/signup" class="btn btn-primary btn-lg px-5">Sign Up Now</a>
        <a href="/signin" class="btn btn-outline-secondary btn-lg px-5">Sign In</a>
      </div>
    </div>
    </#if>
  </div>

  <footer class="py-4 border-top mt-4">
    <div class="container">
      <span class="text-body-secondary">SpartanGuide</span>
    </div>
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
