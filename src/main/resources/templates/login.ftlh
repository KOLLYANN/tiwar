<#import "parts/comp.ftlh" as c>
<@c.page>
<form action="/login" method="post">
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-3">
                <input type="email" class="form-control" name="username" id="inputEmail3" placeholder="Email" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword3" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password" required>
            </div>
        </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Вход</button>
</form>
<a href="/registration">Registration</a>
</@c.page>