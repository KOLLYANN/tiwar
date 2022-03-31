<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Клановый чат</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span
                    class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>

    <div class="block_zero">
        <form action="/chat/clan/send" method="post">
            <div>
                <table style="width:100%;padding:0px">
                    <tbody>
                    <tr>
                        <td style="width:100%">
                            <input id="sml" type="text" class="text large" name="text" maxlength="250">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="submit" name="message" style="background-color: black; color: white" value="Отправить">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div style="margin-top:2px;"></div>
            <a style="padding:0px 0px 0px 5px" class="grey " href="/chat">Обновить</a>
            <div style="clear:both;"></div>
        </form>
    </div>
    <div class="dot-line"></div>
    <div class="block_zero">
        <#if messages??>
            <#list messages as message>
                <div>
                    <img src="/img/icon/0.png" alt="">
                    <a href="/profile/${message.getUser().getId()}">${message.getUser().getUsername()}</a>
                    <span class="white">${message.getText()}</span>
                </div>

            <#else>
                Нет сообщений
            </#list>
        </#if>
    </div>
    <div class="dot-line"></div>
    <div class="block_zero medium"><img src="/img/icon/chat.png" alt=""> <a
                href="/chat">Чат</a>
    </div>
    <div class="dot-line"></div>
    <div class="block_zero">
        <form method="get" action="/chat/clan" style="float: left">
            <input type="hidden" name="page" value="previous"/>
            <input type="submit" style="background-color: black; color: white" value="&lt;&lt;&lt;">
        </form>
        <form method="get" action="/chat/clan">
            <input type="hidden" name="page" value="next"/>
            <input type="submit" style="background-color: black; color: white" value="&gt;&gt;&gt;">
        </form>

    </div>

    <div class="line"></div>
    <div class="player menuList">
        <li><a href="/profile/${user.getId()}"><img src="/img/icon/user.png" alt="">Мой герой</a></li>
        <li><a href="/clan/"><img src="/img/icon/user.png" alt="">Мой клан<span class="green"> (+)</span></a>
        </li>
        <li class="noBorder"><a href="/"><img src="/img/icon/arrow.png" alt="">На главную</a></li>
    </div>
    <div class="line"></div>
    <div class="foot">
    <div><img src="/img/icon/0.png" alt=""> ${us.getUsername()}<span class="float-right medium"><a
                    href="#">Настройки</a></span></div>
    <div class="center"><img src="/img/icon/level.png" alt="lvl"> ${us.getUserLevel()}
        | <img src="/img/icon/gold.png" alt="g"> ${us.getGold()} |
        <img src="/img/icon/silver.png" alt="e"> ${us.getSilver()}
    </div>
    <div class="mini-line" style="margin-top: 10px"></div>
    <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="/chat">Чат</a> | <a
                    href="/rating/">Рейтинг</a></span>
        <div class="mb10"></div>
        <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
    </div>

</@c.page>