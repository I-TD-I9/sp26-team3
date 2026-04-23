<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Subscriptions | SpartanGuide</title>
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
        <li class="breadcrumb-item active" aria-current="page">Subscriptions</li>
      </ol>
    </nav>

    <div class="row mb-4">
      <div class="col-md-8">
        <h2>Subscription Plans</h2>
        <p class="text-body-secondary">Subscribe to unlock unlimited tour bookings.</p>
      </div>
    </div>

    <div class="row g-4 mb-5">
      <div class="col-md-6 col-lg-4 mx-auto">
        <div class="card h-100 shadow-sm text-center">
          <div class="card-body d-flex flex-column justify-content-between p-4">
            <h5 class="card-title">Spartan Premium</h5>
            <p class="card-text text-body-secondary">Get access to all premium tours and features with unlimited bookings.</p>
            <p class="card-text display-6 fw-bold">$9.99<small class="fs-6 fw-normal text-body-secondary">/month</small></p>
            <form action="/student/subscriptions/new" method="post">
              <button type="submit" class="btn btn-primary mt-auto w-100">Subscribe Now</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <h3 class="h5 mb-3">Your Subscriptions</h3>
    <#if subscriptions?? && subscriptions?size gt 0>
      <div class="row g-3">
        <#list subscriptions as sub>
          <div class="col-12">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="d-flex w-100 justify-content-between align-items-center">
                  <div>
                    <span class="badge bg-${(sub.status.name() == 'ACTIVE')?then('success',(sub.status.name() == 'CANCELLED')?then('danger','secondary'))} me-2">
                      ${sub.status}
                    </span>
                    <small class="text-body-secondary">Started: ${sub.startDate}</small>
                    <#if sub.endDate??>
                      <small class="text-body-secondary ms-2">Ends: ${sub.endDate}</small>
                    </#if>
                  </div>
                  <div>
                    <#if sub.autoRenew>
                      <span class="badge bg-info me-2">Auto-renew ON</span>
                    </#if>
                    <a href="/student/subscriptions/delete/${sub.subscriptionId}"
                       onclick="return confirm('Cancel this subscription?')"
                       class="btn btn-sm btn-outline-danger">Cancel</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        No active subscriptions. Subscribe above to unlock premium tour access!
      </div>
    </#if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
