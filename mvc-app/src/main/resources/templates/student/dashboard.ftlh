<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Student Dashboard | SpartanGuide</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
  <#include "/fragments/navbar.ftlh">
  <@renderNavbar userType="student" />

  <div class="container mt-2">
    <h1>Welcome, ${currentStudent.name}!</h1>
    <p class="text-body-secondary">Manage your tours, subscriptions, and profile.</p>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">
          Profile Summary
          <span class="float-end text-body-secondary" style="font-style:italic;font-size:.9rem;">View and edit your information.</span>
        </h5>
        <p class="card-text">Name: ${currentStudent.name}</p>
        <p class="card-text">Email: ${currentStudent.email}</p>
        <p class="card-text">Major: ${currentStudent.major!""}</p>
        <a href="/student/profile" class="btn btn-primary">Edit Profile</a>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">
          Subscription Overview
          <span class="float-end text-body-secondary" style="font-style:italic;font-size:.9rem;">Manage your active subscriptions.</span>
        </h5>
        <#if subscriptions?? && subscriptions?size gt 0>
          <div class="list-group">
            <#list subscriptions as sub>
              <div class="list-group-item">
                <div class="d-flex w-100 justify-content-between align-items-start">
                  <div>
                    <span class="badge bg-${(sub.status.name() == 'ACTIVE')?then('success','secondary')} me-2">${sub.status}</span>
                    Started: ${sub.startDate}
                  </div>
                  <#if sub.endDate??>
                    <small class="text-body-secondary">Ends: ${sub.endDate}</small>
                  </#if>
                </div>
              </div>
            </#list>
          </div>
        <#else>
          <p class="text-body-secondary">No subscriptions yet. <a href="/student/subscriptions">Browse subscription plans</a>.</p>
        </#if>
        <a href="/student/subscriptions" class="btn btn-primary mt-3">Manage Subscriptions</a>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Quick Actions</h5>
        <a href="/student/tours" class="btn btn-success me-2">Browse Tours</a>
        <a href="/student/subscriptions" class="btn btn-info">My Subscriptions</a>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
