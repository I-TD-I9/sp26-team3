<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tour Management | SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="guide" />

  <div class="container mt-2">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/guide/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active" aria-current="page">Tour Management</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Tour saved successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>
    <#if RequestParameters.deleted??>
      <div class="alert alert-info alert-dismissible fade show" role="alert">
        Tour deleted successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row mb-4">
      <div class="col-md-8">
        <h2>My Tours</h2>
      </div>
      <div class="col-md-4 text-end">
        <a href="/guide/tours/new" class="btn btn-success btn-lg">+ Add New Tour</a>
      </div>
    </div>

    <#if tours?? && tours?size gt 0>
      <div class="row g-4">
        <#list tours as tour>
          <div class="col-md-6">
            <div class="card h-100 shadow-sm">
              <img src="/tour-images/${(tour.imagePath)!'tour1.png'}" class="card-img-top"
                   style="height:160px; object-fit:cover;" alt="${tour.title}">
              <div class="card-body">
                <h5 class="card-title">${tour.title}</h5>
                <p class="card-text text-body-secondary">${(tour.description!'')?truncate(120)}</p>
                <div class="row mb-2">
                  <div class="col-6">
                    <small class="text-body-secondary">Price:</small>
                    <p class="h5 mb-0">$${tour.price}</p>
                  </div>
                  <div class="col-6">
                    <small class="text-body-secondary">Capacity:</small>
                    <p class="h5 mb-0">${tour.capacity}</p>
                  </div>
                </div>
                <p class="small text-body-secondary mb-0">Location: ${tour.location}</p>
              </div>
              <div class="card-footer bg-transparent">
                <div class="d-flex gap-2 align-items-center">
                  <#if tour.published>
                    <span class="badge bg-success me-auto">Published</span>
                  <#else>
                    <span class="badge bg-secondary me-auto">Draft</span>
                  </#if>
                  <a href="/guide/tours/edit/${tour.tourId}" class="btn btn-sm btn-primary">Edit</a>
                  <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${tour.tourId}">Delete</button>
                </div>
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
                <div class="modal-body">Are you sure you want to delete "${tour.title}"? This cannot be undone.</div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                  <a href="/guide/tours/delete/${tour.tourId}" class="btn btn-danger">Yes, Delete</a>
                </div>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Tours Yet</h5>
        <p>You haven't created any tours yet. Start by adding your first tour!</p>
        <a href="/guide/tours/new" class="btn btn-success">Create First Tour</a>
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
