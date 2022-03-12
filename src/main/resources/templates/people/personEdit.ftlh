<#import "../parts/comp.ftlh" as p>
<@p.page>
    <p>${name!"Unknown"}</p>
    <div class="row mb-3">
        <form method="post" action="/people/${id}/edit">
            <label class="col-sm-2 col-form-label" for="firstname">Firstname</label>
            <div class="col-sm-10">
                <input class="form-control"  type="text" name="firstname" value="${name}" placeholder="Firstname" required>
            </div>
            <label class="col-sm-2 col-form-label" for="lastname">Lastname  </label>
            <div class="col-sm-10">
                <input class="form-control" type="text" name="lastname" value="${lastname}" placeholder="Lastname" required >
            </div>

            <label class="col-sm-2 col-form-label" for="age">Age   </label>
            <div class="col-sm-10">
                <input class="form-control" type="text" name="age" value="${age}" placeholder="Age" required>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button class="btn btn-primary mt-3" type="submit">Update</button>
        </form>
        <form method="post" action="/people/${id}/upload" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Загрузить</button>
        </form>
    </div>

    <a href="/people">People</a>
</@p.page>
