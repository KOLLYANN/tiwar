<#include "security.ftlh">
<#import "../parts/comp.ftlh" as c>
<@c.page>
    <div class="head " style="position:relative;"><span class="ttl fl">Главная</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"/> <span
                    class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"/> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="block_zero center">
        <img src="/img/title.jpg" width="100%" alt=""/></div>
    <div class="menuList">
        <li>
            <a href="/arena/">
                <img src="/img/icon/arena.png" alt=""/>Арена
            </a>
        </li>
        <#if us.getClan()??>
            <li><a href="/cave">
                    <img src="/img/icon/cave.png" alt=""/>Олимп
                    <span class="green"> </span>
                    <span class="grey small"></span>
                </a>
            </li>
        <#else>
            <li><a style="pointer-events: none" href="/cave">
                    <img src="/img/icon/cave.png" alt=""/>Олимп
                    <span class="green"></span>
                    <span class="grey small">Нужен клан</span>
                </a></li>
        </#if>
        <li><a href="/coliseum/"><img src="/img/icon/coliseum.png" alt=""/>Колизей</a></li>
        <li><a href="/campaign"><img src="/img/icon/farm.png" alt=""/>Поход<span class="green"> </span></a></li>
    </div>
    <div class="mini-line"></div>
    <div class="menuList">
        <li>
            <a href="/shop/">
                <img src="/img/icon/equip.png" alt=""/>Магазин снаряжения
                <span class="green"> </span>
            </a>
        </li>
        <li>
            <a href="/rating/">
                <img src="/img/icon/train.png" alt=""/>Рейтинг
            </a>
        </li>
    </div>
    <div class="line"></div>
    <div class="player menuList">
        <li>
            <a href="/profile/${user.getId()}">
                <img src="/img/icon/user.png" alt=""/>Мой герой
            </a>
        </li>
        <li>
            <a href="/clan/">
                <img src="/img/icon/user.png" alt=""/>Мой клан
                <span class="green"> (+)</span></a>
        </li>
        <li class="noBorder">
            <a href="/">
                <img src="/img/icon/arrow.png" alt=""/>На главную
            </a>
        </li>
    </div>
    <div class="line"></div>
    <div class="foot">
        <div id="admin">
            <img src="/img/icon/0.png" alt=""/> ${us.getUsername()}
        </div>
        <div class="center">
            <img src="/img/icon/level.png" alt="lvl"/> ${us.getUserLevel()}
            | <img src="/img/icon/gold.png" alt="g"/> ${us.getGold()} |
            <img src="/img/icon/silver.png" alt="e"/> ${us.getSilver()}
        </div>
        <div class="mini-line" style="margin-top: 10px"></div>
        <div class="center "><br/>
            <span class="grey">
        <a href="">Форум</a> |
        <a href="/chat">Чат</a> |
        <a href="/rating/">Рейтинг</a>
    </span>
            <div class="mb10"></div>
            <span class="small grey">
        <!-- 100 --> ${aTime?iso_utc}
            <br/>
        vk.com/kolya0598 © 2022, 18+
    </span>
        </div>
    </div>
</@c.page>
