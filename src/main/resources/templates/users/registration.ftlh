<#import "../parts/comp.ftlh" as c>
<@c.page>
    <form id="demo-form" action="/registration" method="post">
        <div class="center">
            <img src="/img/icon/bitva_titanov.png" alt="Битва титанов" style="max-width:268px;width:100%;"><br>
            <img src="/img/icon/main_h.jpg" width="100%" alt=""></div>
        <div class="block_zero center"><h1>Сражайся вместе с нами</h1>
            <span class="medium">Бесплатная онлайн игра, о которой ходят легенды<br>Теперь к легенде можешь прикоснуться и ты!</span>
            <div class="mb10"></div>
            <h1 class="yellow">В игре уже 5 игроков!</h1></div>
        <div class="block_zero center" style="padding-top:15px;">
            <form action="/login" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div>
                    Email:<br>
                    <input  class="text medium-text" type="email" name="email" required>
                    <br>Ник:<br>
                    <input class="text medium-text" type="text" name="username" maxlength="16" required>
                    <br>Пароль:<br>
                    <input class="text medium-text" type="password" name="password" maxlength="16" required>

                    <div class="dgreen"><span class="btn blink-green">
                    <span class="end">
                        <input class="label" type="submit" value="Зарегистрироваться">Рег</span></span>
                    </div>
                </div>
                <br>
            </form>
            <div class="bigBtn"><a class="btn blink-green" href="/login"><span class="end"><span class="label"><span
                                    class="green">Войти</span></span></span></a></div>
        </div>




<#--        <div class="form-group row">-->
<#--            <label class="col-sm-1 col-form-label">Email</label>-->
<#--            <div class="col-sm-3">-->
<#--            <input class="form-control" type="email" placeholder="Enter email" name="email" required>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-1 col-form-label">Username</label>-->
<#--            <div class="col-sm-3">-->
<#--            <input class="form-control" type="text" placeholder="Enter Username" name="username" required>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-1 col-form-label">Password</label>-->
<#--            <div class="col-sm-3">-->
<#--            <input class="form-control" type="password" placeholder="Enter Password" name="password" required>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <div class="col-sm-3">-->
<#--            <button type="submit" class="btn btn-primary mt-2">Регистрация</button>-->
<#--            </div>-->
<#--        </div>-->
    </form>
</@c.page>