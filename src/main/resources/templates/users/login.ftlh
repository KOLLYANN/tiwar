<#import "../parts/comp.ftlh" as c>
<@c.page>
    <div class="center">
<#--        <img src="/img/icon/bitva_titanov.png" alt="Битва титанов" style="max-width:268px;width:100%;"><br>-->
<#--        <img src="/img/icon/main_h.jpg" width="100%" alt=""></div>-->
    <div class="block_zero center"><h1>Сражайся вместе с нами</h1>
        <span class="medium">Бесплатная онлайн игра, о которой ходят легенды<br>Теперь к легенде можешь прикоснуться и ты!</span>
        <div class="mb10"></div>
        <h1 class="yellow">В игре уже 5 игроков!</h1></div>
    <div class="block_zero center" style="padding-top:15px;">
        <form action="/login" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div>
                Email:<br>
                <input class="text medium-text" type="email"  name="username" required>
                <br>Пароль:<br>
                <input class="text medium-text" type="password" name="password" maxlength="16">
                <div class="dgreen"><span class="btn blink-green">
                    <span class="end">
                        <input class="label" type="submit" value="Войти">Войти</span></span>
                </div>
            </div>
            <br>
        </form>
        <div class="bigBtn"><a class="btn blink-green" href="/registration"><span class="end"><span class="label"><span
                                class="green">Начать свой путь</span></span></span></a></div>
    </div>

</@c.page>