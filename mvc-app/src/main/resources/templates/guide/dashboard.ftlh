<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Guide Dashboard | SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="guide" />

  <div class="container mt-2">
    <h1>Guide Dashboard</h1>
    <p class="text-body-secondary">Welcome to your dashboard! Manage your tours, profile, and reviews.</p>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">
          Profile Summary
          <span class="float-end text-body-secondary" style="font-style:italic;font-size:.9rem;">View and edit your profile information.</span>
        </h5>
        <p class="card-text">Name: ${currentGuide.name}</p>
        <p class="card-text">Email: ${currentGuide.email}</p>
        <p class="card-text">Bio: ${currentGuide.bio!""}</p>
        <a href="/guide/profile" class="btn btn-primary">Go to Profile Settings</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">
          Manage Your Tours
          <span class="float-end text-body-secondary" style="font-style:italic;font-size:.9rem;">Add, edit, or remove your tours.</span>
        </h5>
        <div class="card mb-3" style="width:18rem;">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Total Tours: <span class="badge text-bg-primary rounded-pill">${totalTours}</span></li>
          </ul>
        </div>
        <a href="/guide/tours/new" class="btn btn-primary me-2">Add New Tour</a>
        <a href="/guide/tours" class="btn btn-outline-secondary">Manage All Tours</a>

        <#if recentTours?? && recentTours?size gt 0>
          <h6 class="mt-4 mb-2">Recent Tours</h6>
          <div class="list-group">
            <#list recentTours as tour>
              <div class="list-group-item">
                <div class="d-flex w-100 justify-content-between align-items-start">
                  <div>
                    <h6 class="mb-1">${tour.title}</h6>
                    <p class="mb-1 small text-body-secondary">${tour.location} &bull; $${tour.price}</p>
                    <small>
                      <#if tour.published>
                        <span class="badge bg-success">Published</span>
                      <#else>
                        <span class="badge bg-secondary">Draft</span>
                      </#if>
                    </small>
                  </div>
                  <div class="d-flex gap-1">
                    <a href="/guide/tours/edit/${tour.tourId}" class="btn btn-sm btn-secondary">Edit</a>
                    <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${tour.tourId}">Delete</button>
                  </div>
                </div>
              </div>
              <div class="modal fade" id="deleteModal${tour.tourId}" tabindex="-1">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title">Confirm Delete</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">Are you sure you want to delete "${tour.title}"?</div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                      <a href="/guide/tours/delete/${tour.tourId}" class="btn btn-danger">Delete</a>
                    </div>
                  </div>
                </div>
              </div>
            </#list>
          </div>
        </#if>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
