<#import "../parts/comp.ftlh" as p>
<@p.page>
    <h2>New user</h2>
    <div class="row mb-3">
    <form method="post" action="/people">
        <label class="col-sm-2 col-form-label" for="firstname">Firstname</label>
            <div class="col-sm-10">
            <input class="form-control"  type="text" name="firstname" placeholder="Firstname" required>
            </div>
        <label class="col-sm-2 col-form-label" for="lastname">Lastname  </label>
            <div class="col-sm-10">
            <input class="form-control" type="text" name="lastname" placeholder="Lastname" required>
            </div>

        <label class="col-sm-2 col-form-label" for="age">Age   </label>
            <div class="col-sm-10">
            <input class="form-control" type="text" name="age" placeholder="Age" required>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary mt-3" type="submit">Add user</button>
    </form>
    </div>
</@p.page>