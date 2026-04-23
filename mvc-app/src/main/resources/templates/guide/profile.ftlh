<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Guide Profile | SpartanGuide</title>
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
        <li class="breadcrumb-item active" aria-current="page">Profile Settings</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Profile updated successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row g-4">
      <div class="col-md-4 text-center">
        <img src="/profile-pictures/${(guide.profilePicturePath)!'avatar.png'}"
             alt="${guide.name}" class="img-thumbnail rounded-circle mb-3" width="150" height="150">
        <h4>${guide.name}</h4>
        <p class="text-body-secondary">${guide.email}</p>
        <span class="badge bg-${(guide.status.name() == 'ACTIVE')?then('success','secondary')}">${guide.status}</span>
        <p class="mt-3 text-body-secondary">${guide.bio!""}</p>

        <#if tours?? && tours?size gt 0>
          <hr>
          <h6 class="text-body-secondary">${tours?size} tour<#if tours?size != 1>s</#if> created</h6>
        </#if>
      </div>

      <div class="col-md-8">
        <div class="card shadow-sm">
          <div class="card-header">
            <h4 class="mb-0">Profile Settings</h4>
          </div>
          <div class="card-body">
            <form action="/guide/profile" method="post" enctype="multipart/form-data">
              <div class="mb-3">
                <label class="form-label" for="name">Full Name</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${guide.name}" required maxlength="100">
              </div>
              <div class="mb-3">
                <label class="form-label" for="email">Email Address</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${guide.email}" required>
              </div>
              <div class="mb-3">
                <label class="form-label" for="bio">Bio</label>
                <textarea class="form-control" id="bio" name="bio" rows="4"
                          placeholder="Tell students about yourself...">${guide.bio!""}</textarea>
              </div>
              <div class="mb-3">
                <label class="form-label" for="picture">Profile Picture</label>
                <input type="file" class="form-control" id="picture" name="picture" accept="image/*">
              </div>
              <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save Changes</button>
                <a href="/guide/dashboard" class="btn btn-secondary">Cancel</a>
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
