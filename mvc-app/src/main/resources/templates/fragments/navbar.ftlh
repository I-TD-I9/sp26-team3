<#macro renderNavbar userType="student">
  <nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom mb-4">
    <div class="container">
      <a class="navbar-brand fw-semibold" href="/">SpartanGuide</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
          </li>
          <#if userType == "guide">
            <li class="nav-item">
              <a class="nav-link" href="/guide/dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/guide/tours">My Tours</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/guide/tours/new">Create Tour</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/guide/reviews">Reviews</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/guide/profile">Profile</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/guide/logout">Sign Out</a>
            </li>
          <#elseif userType == "student">
            <li class="nav-item">
              <a class="nav-link" href="/student/dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/student/tours">Browse Tours</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/student/subscriptions">Subscriptions</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/student/reviews">My Reviews</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/student/profile">Profile</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/student/logout">Sign Out</a>
            </li>
          </#if>
        </ul>
      </div>
    </div>
  </nav>
</#macro>
