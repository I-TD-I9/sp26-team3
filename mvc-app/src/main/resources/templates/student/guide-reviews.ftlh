<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Reviews | SpartanGuide</title>
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
        <li class="breadcrumb-item active" aria-current="page">My Reviews</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Review submitted successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <div class="row mb-4">
      <div class="col-md-8">
        <h2>My Reviews</h2>
        <p class="text-body-secondary">Reviews you've left for tour guides.</p>
      </div>
      <div class="col-md-4 text-end">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newReviewModal">
          + Write a Review
        </button>
      </div>
    </div>

    <#if reviews?? && reviews?size gt 0>
      <div class="row">
        <#list reviews as review>
          <div class="col-12 mb-4">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="row mb-2">
                  <div class="col-md-8">
                    <span class="badge bg-warning text-dark fs-6 me-2">${review.rating}/5 ★</span>
                    <#if review.guide??>
                      <small class="text-body-secondary">for guide <strong>${review.guide.name}</strong></small>
                    </#if>
                  </div>
                </div>
                <p class="card-text">${review.comment!""}</p>
                <div class="d-flex gap-2">
                  <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal"
                          data-bs-target="#editReviewModal${review.reviewId}">Edit</button>
                  <a href="/student/reviews/delete/${review.reviewId}"
                     onclick="return confirm('Delete this review?')"
                     class="btn btn-sm btn-outline-danger">Delete</a>
                </div>
              </div>
            </div>
          </div>

          <!-- Edit Review Modal -->
          <div class="modal fade" id="editReviewModal${review.reviewId}" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Edit Review</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="/student/reviews/${review.reviewId}/edit" method="post">
                  <div class="modal-body">
                    <div class="mb-3">
                      <label class="form-label">Rating (1–5)</label>
                      <input type="number" class="form-control" name="rating" min="1" max="5"
                             value="${review.rating}" required>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Comment</label>
                      <textarea class="form-control" name="comment" rows="3" required>${review.comment!""}</textarea>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info">
        <h5>No Reviews Yet</h5>
        <p>You haven't left any reviews yet. After attending a tour, leave feedback for your guide!</p>
      </div>
    </#if>
  </div>

  <!-- New Review Modal -->
  <div class="modal fade" id="newReviewModal" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Write a Review</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <form action="/student/reviews/new" method="post">
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label" for="guideId">Guide</label>
              <select class="form-select" id="guideId" name="guideId" required>
                <option value="">-- Select a guide --</option>
                <#if guides?has_content>
                  <#list guides as guide>
                    <option value="${guide.guideId}">${guide.name}</option>
                  </#list>
                </#if>
              </select>
            </div>
            <div class="mb-3">
              <label class="form-label" for="rating">Rating (1–5)</label>
              <input type="number" class="form-control" id="rating" name="rating" min="1" max="5" required>
            </div>
            <div class="mb-3">
              <label class="form-label" for="comment">Comment</label>
              <textarea class="form-control" id="comment" name="comment" rows="4"
                        placeholder="Share your experience with this guide..."></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-primary">Submit Review</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
          crossorigin="anonymous"></script>
</body>
</html>
