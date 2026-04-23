<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${title} | SpartanGuide</title>
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
        <li class="breadcrumb-item"><a href="/guide/tours">My Tours</a></li>
        <li class="breadcrumb-item active" aria-current="page">${title}</li>
      </ol>
    </nav>

    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card shadow-sm">
          <div class="card-header">
            <h4 class="mb-0">${title}</h4>
          </div>
          <div class="card-body p-4">
            <#if tour.tourId??>
            <form action="/guide/tours/edit/${tour.tourId}" method="post" enctype="multipart/form-data">
            <#else>
            <form action="/guide/tours/new" method="post" enctype="multipart/form-data">
            </#if>
              <div class="mb-3">
                <label class="form-label" for="title">Tour Title</label>
                <input type="text" class="form-control" id="title" name="title"
                       value="${tour.title!""}" required maxlength="200"
                       placeholder="e.g., Campus Sunrise Walk">
              </div>
              <div class="mb-3">
                <label class="form-label" for="description">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4"
                          placeholder="Describe what students will experience...">${tour.description!""}</textarea>
              </div>
              <div class="row g-3">
                <div class="col-md-6">
                  <label class="form-label" for="location">Meeting Location</label>
                  <input type="text" class="form-control" id="location" name="location"
                         value="${tour.location!""}" required
                         placeholder="e.g., Student Union">
                </div>
                <div class="col-md-3">
                  <label class="form-label" for="price">Price ($)</label>
                  <input type="number" step="0.01" min="0" class="form-control" id="price" name="price"
                         value="${tour.price!0}" required>
                </div>
                <div class="col-md-3">
                  <label class="form-label" for="capacity">Max Group Size</label>
                  <input type="number" min="1" class="form-control" id="capacity" name="capacity"
                         value="${tour.capacity!10}" required>
                </div>
              </div>
              <div class="mb-3 mt-3">
                <label class="form-label" for="image">Tour Image</label>
                <#if tour.imagePath??>
                  <div class="mb-2">
                    <img src="/tour-images/${tour.imagePath}" alt="Current tour image"
                         class="img-thumbnail" style="height:120px; object-fit:cover;">
                  </div>
                </#if>
                <input type="file" class="form-control" id="image" name="image" accept="image/*">
              </div>
              <div class="d-grid gap-2 mt-4">
                <button type="submit" class="btn btn-success">
                  <#if tour.tourId??>Save Changes<#else>Create Tour</#if>
                </button>
                <a href="/guide/tours" class="btn btn-outline-secondary">Cancel</a>
              </div>
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
