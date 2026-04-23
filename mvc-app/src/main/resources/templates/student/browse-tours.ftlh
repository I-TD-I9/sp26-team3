<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Browse Tours | SpartanGuide</title>
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
        <li class="breadcrumb-item active" aria-current="page">Browse Tours</li>
      </ol>
    </nav>

    <h2 class="mb-4">Available Tours</h2>

    <#if tours?? && tours?size gt 0>
      <div class="row g-4">
        <#list tours as tour>
          <div class="col-md-6 col-lg-4">
            <div class="card h-100 shadow-sm">
              <img src="/tour-images/${(tour.imagePath)!'tour1.png'}" class="card-img-top"
                   style="height:160px; object-fit:cover;" alt="${tour.title}">
              <div class="card-body">
                <h5 class="card-title">${tour.title}</h5>
                <#if tour.guide??>
                  <p class="card-text text-body-secondary small"><strong>Host:</strong> ${tour.guide.name}</p>
                </#if>
                <p class="card-text">${(tour.description!'')?truncate(120)}</p>
                <ul class="list-group list-group-flush mb-3">
                  <li class="list-group-item px-0 small">Location: ${tour.location}</li>
                  <li class="list-group-item px-0 small">Price: $${tour.price}</li>
                  <li class="list-group-item px-0 small">Capacity: ${tour.capacity}</li>
                </ul>
              </div>
              <div class="card-footer bg-transparent">
                <a href="/student/tours/${tour.tourId}" class="btn btn-primary btn-sm w-100">View Details</a>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Tours Available</h5>
        <p>There are currently no tours available. Please check back soon!</p>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
