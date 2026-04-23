<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reviews | SpartanGuide</title>
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
        <li class="breadcrumb-item active" aria-current="page">Reviews</li>
      </ol>
    </nav>

    <#if RequestParameters.success??>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        Review submitted successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </#if>

    <!-- Reviews received from students -->
    <div class="row mb-4">
      <div class="col-md-8">
        <h2>Reviews Received</h2>
        <p class="text-body-secondary">Feedback left for you by students.</p>
      </div>
      <div class="col-md-4 text-end">
        <#if receivedReviews?? && receivedReviews?size gt 0>
          <span class="badge bg-primary p-2 fs-6">${receivedReviews?size} review<#if receivedReviews?size != 1>s</#if></span>
        </#if>
      </div>
    </div>

    <#if receivedReviews?? && receivedReviews?size gt 0>
      <div class="row mb-5">
        <#list receivedReviews as review>
          <div class="col-12 mb-3">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="d-flex justify-content-between align-items-start mb-2">
                  <div>
                    <span class="badge bg-warning text-dark fs-6 me-2">${review.rating}/5 ★</span>
                    <#if review.reviewer??>
                      <small class="text-body-secondary">by <strong>${review.reviewer.name}</strong></small>
                    </#if>
                  </div>
                </div>
                <p class="card-text">${review.comment!""}</p>
              </div>
            </div>
          </div>
        </#list>
      </div>
    <#else>
      <div class="alert alert-info mb-5">
        <h5>No Reviews Yet</h5>
        <p>Students haven't left any reviews for you yet. Great tours earn great feedback!</p>
      </div>
    </#if>

    <!-- Reviews written about students -->
    <div class="row mb-4">
      <div class="col-md-8">
        <h2>Reviews Written</h2>
        <p class="text-body-secondary">Reviews you've written about students.</p>
      </div>
      <div class="col-md-4 text-end">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newReviewModal">
          + Review a Student
        </button>
      </div>
    </div>

    <#if writtenReviews?? && writtenReviews?size gt 0>
      <div class="row">
        <#list writtenReviews as review>
          <div class="col-12 mb-3">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="d-flex justify-content-between align-items-start mb-2">
                  <div>
                    <span class="badge bg-info text-dark fs-6 me-2">${review.rating}/5 ★</span>
                    <#if review.student??>
                      <small class="text-body-secondary">for student <strong>${review.student.name}</strong></small>
                    </#if>
                  </div>
                  <div class="d-flex gap-2">
                    <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal"
                            data-bs-target="#editReviewModal${review.reviewId}">Edit</button>
                    <a href="/guide/reviews/delete/${review.reviewId}"
                       onclick="return confirm('Delete this review?')"
                       class="btn btn-sm btn-outline-danger">Delete</a>
                  </div>
                </div>
                <p class="card-text">${review.comment!""}</p>
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
                <form action="/guide/reviews/${review.reviewId}/edit" method="post">
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
        <h5>No Reviews Written</h5>
        <p>You haven't reviewed any students yet.</p>
      </div>
    </#if>
  </div>

  <!-- New Student Review Modal -->
  <div class="modal fade" id="newReviewModal" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Review a Student</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <form action="/guide/reviews/new" method="post">
          <div class="modal-body">
            <div class="mb-3">
              <label class="form-label" for="studentId">Student</label>
              <select class="form-select" id="studentId" name="studentId" required>
                <option value="">-- Select a student --</option>
                <#if students?has_content>
                  <#list students as student>
                    <option value="${student.studentId}">${student.name} (${student.email})</option>
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
                        placeholder="Share your experience with this student..."></textarea>
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
