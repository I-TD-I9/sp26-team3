<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${tour.title} | SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="student" />

  <div class="container mt-2">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/student/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a href="/student/tours">Browse Tours</a></li>
        <li class="breadcrumb-item active" aria-current="page">${tour.title}</li>
      </ol>
    </nav>

    <div class="row g-4">
      <div class="col-md-8">
        <div class="card mb-4 shadow-sm">
          <img src="/tour-images/${(tour.imagePath)!'tour1.png'}" class="card-img-top"
               style="height:220px; object-fit:cover;" alt="${tour.title}">
          <div class="card-header bg-body-tertiary">
            <#if tour.guide??>
              <div class="d-flex align-items-center gap-3">
                <img src="/profile-pictures/${(tour.guide.profilePicturePath)!'avatar.png'}"
                     alt="${tour.guide.name}" class="rounded-circle" width="48" height="48" style="object-fit:cover;">
                <div>
                  <p class="mb-0 small text-body-secondary">Guide host</p>
                  <h2 class="h6 mb-0">${tour.guide.name}</h2>
                  <p class="mb-0 small text-body-secondary">${tour.guide.bio!""}</p>
                </div>
              </div>
            </#if>
          </div>
          <div class="card-body">
            <h2 class="h4 mb-2">${tour.title}</h2>
            <p class="text-body-secondary">${tour.description!""}</p>
            <ul class="list-group list-group-flush mb-3">
              <li class="list-group-item d-flex justify-content-between">
                <span>Location</span><span>${tour.location}</span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <span>Price</span><span>$${tour.price}</span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <span>Capacity</span><span>${tour.capacity}</span>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card shadow-sm sticky-top" style="top:20px;">
          <div class="card-body">
            <h5 class="card-title">Book This Tour</h5>
            <form action="/student/tours/${tour.tourId}/book" method="post">
              <button type="submit" class="btn btn-primary w-100">Register</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
